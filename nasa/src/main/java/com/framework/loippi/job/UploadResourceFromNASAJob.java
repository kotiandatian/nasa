package com.framework.loippi.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.framework.loippi.utils.redis.JedisCache;

//更新音乐人直播在线标识
@Component("UploadResourceFromNASAJob")
@Lazy(false)
public class UploadResourceFromNASAJob{
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
	
	private Logger log = LoggerFactory.getLogger(UploadResourceFromNASAJob.class);

	@Scheduled(cron = "0 0 0/1 * * ?")  
	public void build() {
		  log.info("每3秒执行一次，查看音乐人直播在线标识!");
		  
		  
	}
} 

