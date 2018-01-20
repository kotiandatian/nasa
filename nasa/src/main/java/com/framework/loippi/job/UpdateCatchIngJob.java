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

import com.framework.loippi.controller.api.result.nasa.Collection;
import com.framework.loippi.controller.api.result.nasa.Items;
import com.framework.loippi.controller.api.result.nasa.Links;
import com.framework.loippi.controller.api.result.nasa.NasaResult;
import com.framework.loippi.entity.PlanetCategory;
import com.framework.loippi.entity.PlanetItem;
import com.framework.loippi.service.PlanetCategoryService;
import com.framework.loippi.service.PlanetItemService;
import com.framework.loippi.utils.JsonUtils;
import com.framework.loippi.utils.Paramap;
import com.framework.loippi.utils.StringUtils;
import com.framework.loippi.utils.nasa.NasaUtil;
import com.framework.loippi.utils.redis.JedisCache;
import com.google.gson.Gson;

//更新音乐人直播在线标识
@Component("UpdateCatchIngJob")
@Lazy(false)
public class UpdateCatchIngJob{
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
	private PlanetCategoryService planetCategoryService;
	@Resource
	private PlanetItemService planetItemService;
	
	private static String baseUrl = "https://images-api.nasa.gov/search";
	
	private Logger log = LoggerFactory.getLogger(UpdateCatchIngJob.class);

	@Scheduled(cron = "0 0/30 * * * ?") //每隔30分钟 
	public void build() {
		  log.info("每30分钟执行一次，从NASA获取数据!");
		  
		  UploadResourceFromNASA();
	}
	
	public void UploadResourceFromNASA(){
		  Date currentDate = new Date();
		  //查询没有获取类别的集合
		  Paramap paramap = Paramap.create()
				  .put("published", 2)//是否已经爬取（1，是  2，否）
				  .put("status", 1);//状态（1.正常  2.禁用）
				  
		  List<PlanetCategory> planetCategoryList = planetCategoryService.findList(paramap);
		  if(!CollectionUtils.isEmpty(planetCategoryList)){
			 for (PlanetCategory planetCategory : planetCategoryList) {
				 try {
					 Map hashMap = new HashMap();
				     hashMap.put("q", planetCategory.getNameEn());
				     //hashMap.put("page", "1");
					 
				     loopRequestForGetHttp(baseUrl, hashMap,planetCategory,currentDate);
					 //更新标识位已爬取
					 PlanetCategory planetCategory2 = new PlanetCategory();
					 planetCategory2.setId(planetCategory.getId());
					 planetCategory2.setPublished(1);
					 planetCategoryService.update(planetCategory2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		  }
	}
	
	
	//循环调用
	private void loopRequestForGetHttp(String url,Map hashMap,
			PlanetCategory planetCategory,Date currentDate) throws Exception{
		
		String masaResourceResult = NasaUtil.requestForGetHttp(url, hashMap);
		 
//		 NasaResult nasaResult = (NasaResult) JsonUtils
//					.getObject4JsonString(masaResourceResult, NasaResult.class);
		 
		 log.info("NASA下载的资源  " + masaResourceResult);
		 
		 NasaResult nasaResult = (NasaResult) new Gson().fromJson(masaResourceResult, NasaResult.class);
		 //获取最外层
		 Collection collection = nasaResult.getCollection();
		 
		 if(null != collection){
			 log.info("正在下载的链接  " + collection.getHref());
			 
			 //判断上下页
			 List<Links> links = collection.getLinks();
			 if(!CollectionUtils.isEmpty(links)){
				 for (Links links2 : links) {
					//获取下一页链接  递归回调
					 if(!StringUtils.isNullOrEmpty(links2.getRel()) 
							 && links2.getRel().equals("next")){
						//TODO
						loopRequestForGetHttp(links2.getHref(), new HashMap(),planetCategory,currentDate);
					 }
				}
			 }
			 
			 //保存item
			 List<Items> items = collection.getItems();
			 PlanetItem planetItem = null;
			 for (Items items2 : items) {
				planetItem = PlanetItem.build(items2,planetCategory,currentDate);
				if(null != planetItem)
					planetItemService.save(planetItem);
			}
		 }
		 
	}
	
	public static void main(String[] args) throws Exception {
		 Map hashMap = new HashMap();
	     hashMap.put("q", "comet");
	     //hashMap.put("page", "1");
		 String masaResourceResult = NasaUtil.requestForGetHttp(baseUrl, hashMap);
		 
		 NasaResult nasaResult = (NasaResult) JsonUtils
					.getObject4JsonString(masaResourceResult, NasaResult.class);
		 
		 System.out.println(nasaResult);
	}
} 

