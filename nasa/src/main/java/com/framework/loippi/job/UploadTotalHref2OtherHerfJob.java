package com.framework.loippi.job;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.framework.loippi.utils.thumb.ThumbUtil;

//更新音乐人直播在线标识
@Component("UploadTotalHref2OtherHerfJob")
@Lazy(false)
public class UploadTotalHref2OtherHerfJob {
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

	private Logger log = LoggerFactory.getLogger(UploadTotalHref2OtherHerfJob.class);

	@Scheduled(cron = "0 0/10 * * * ?")
	public void build() {
		log.info("每十分钟执行一次(次数为一千次)，获取各类型规格图片!");

		Paramap paramap = Paramap.create()
				.put("status", 1)
				.put("totalStatus", 2)
				.put("mediaType", "image")
				.put("pageNumber", 0)
				.put("pageSize",1000);
		Date currentDate = new Date();
		
		List<PlanetItem> planetItemList = planetItemService.findList(paramap);
		if (!CollectionUtils.isEmpty(planetItemList)) {
			PlanetItem planetItem2 = null;
			for (PlanetItem planetItem : planetItemList) {
				try {
					planetItem2 = new PlanetItem();
					planetItem2.setId(planetItem.getId());
					
					Map hashMap = new HashMap();
					    
			        String requestForGetHttp = ThumbUtil.requestForGetHttp(planetItem.getTotalHref(), hashMap);
			        org.json.JSONArray jsonArray = new org.json.JSONArray(requestForGetHttp);
			        
			        int length = jsonArray.length();
			        for(int i =0 ;i<length ;i++){
			        	String url = (String)jsonArray.get(i);
			        	  
			        	//判断链接类型 orig  medium  small  thumb  metadata
			        	System.err.println(url);
			        	planetItem2 = verificationType(url,planetItem2);
			    		
			        }
			        // 更新为已翻译
					planetItem2.setTotalStatus(1);
					planetItem2.setUpdateTime(currentDate);
			        planetItemService.update(planetItem2);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private PlanetItem verificationType(String url, PlanetItem planetItem2) {
		//判断链接类型 orig  medium  small  thumb  metadata
		if(url.contains("orig")){
			planetItem2.setHrefOrig(url);
		}else if(url.contains("medium")){
			planetItem2.setHrefMedium(url);
		}else if(url.contains("small")){
			planetItem2.setHrefSmall(url);
		}else if(url.contains("thumb")){
			planetItem2.setHrefThumb(url);
		}else if(url.contains("large")){
			planetItem2.setHrefLarge(url);
		}
		
		return planetItem2;
	}
}
