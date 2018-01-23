package com.framework.loippi.job;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.framework.loippi.entity.PlanetItem;
import com.framework.loippi.service.PlanetItemService;
import com.framework.loippi.utils.Paramap;
import com.framework.loippi.utils.redis.JedisCache;
import com.framework.loippi.utils.youdao.youdaoUtil;

//更新音乐人直播在线标识
@Component("UploadTranslationFromYoudaoJob")
@Lazy(false)
public class UploadTranslationFromYoudaoJob {
	// <cron-expression>0 0/30 * * * ?</cron-expression>:每隔30分钟
	// <cron-expression>0 0/15 * * * ?</cron-expression>每隔15分钟
	// <cron-expression>0 0 0/1 * * ?</cron-expression>每隔1个小时
	// 每隔5秒执行一次：*/5 * * * * ?
	// 每隔1分钟执行一次：0 */1 * * * ?
	// 每天23点执行一次：0 0 23 * * ?
	// 每天凌晨1点执行一次：0 0 1 * * ?
	// 每月1号凌晨1点执行一次：0 0 1 1 * ?
	// 每月最后一天23点执行一次：0 0 23 L * ?
	// 每周星期天凌晨1点实行一次：0 0 1 ? * L
	// 在26分、29分、33分执行一次：0 26,29,33 * * * ?
	// 每天的0点、13点、18点、21点都执行一次：0 0 0,13,18,21 * * ?

	@Resource
	private JedisCache jedisCache;
	@Resource
	private PlanetItemService planetItemService;

	private Logger log = LoggerFactory.getLogger(UploadTranslationFromYoudaoJob.class);

	@Scheduled(cron = "0 0 0/1 * * ?")
	public void build() {
		log.info("每一小时执行一次(次数为一千次)，翻译title and description!");

		int i = 0;

		Paramap paramap = Paramap.create().put("status", 1).put("published", 2).put("pageNumber", 0).put("pageSize",
				1010);
		Date currentDate = new Date();
		List<PlanetItem> planetItemList = planetItemService.findList(paramap);
		if (!CollectionUtils.isEmpty(planetItemList)) {
			PlanetItem planetItem2 = null;
			for (PlanetItem planetItem : planetItemList) {
				if (i < 1000) {
					try {
						planetItem2 = new PlanetItem();
						planetItem2.setId(planetItem.getId());
						// 翻译title
						String titleZh = youdaoUtil.requestForHttpFromZh2En(planetItem.getTitleEn());
						// 翻译description
						String descriptionZh = youdaoUtil.requestForHttpFromZh2En(planetItem.getTitleEn());

						planetItem2.setTitleZh(titleZh);
						planetItem2.setDescriptionZh(descriptionZh);
						// 更新为已翻译
						planetItem2.setPublished(1);
						planetItem2.setUpdateTime(currentDate);

						planetItemService.update(planetItem2);

						i = i + 2;
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}

		}
	}
}
