package com.framework.loippi.taskExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BidTaskExecutor implements Runnable {

	private Logger log = LoggerFactory.getLogger(BidTaskExecutor.class);
	
	private Integer i ;
	
	public BidTaskExecutor(Integer i) {
		this.i = i;
	}

	@Override
	public void run() {
		 log.info("测试executor!" + i);
	}
}
