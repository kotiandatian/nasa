package com.framework.loippi.controller.api;

import javax.annotation.Resource;

import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.framework.loippi.controller.api.common.APIReturnJson;
import com.framework.loippi.job.UploadResourceFromNASAJob;
import com.framework.loippi.job.UploadTotalHref2OtherHerfJob;
import com.framework.loippi.job.UploadTranslationFromYoudaoJob;

@Controller
@RequestMapping(value = "/nasa")
public class User_admin extends ApiBaseController {

	@Resource(name = "taskExecutor")
	private TaskExecutor taskExecutor;
	@Resource
	private UploadResourceFromNASAJob updateCatchIngJob;
	@Resource
	private UploadTranslationFromYoudaoJob uploadTranslationFromYoudaoJob;
	@Resource
	private UploadTotalHref2OtherHerfJob uploadTotalHref2OtherHerfJob;
	

	@RequestMapping(value = "/testExceutor", method = RequestMethod.GET)
	public String testExceutor() {

//		for (int i = 1; i < 10; i++) {
//			taskExecutor.execute(new BidTaskExecutor(i));
//		}
//		updateCatchIngJob.UploadResourceFromNASA();
//		uploadTranslationFromYoudaoJob.build();
//		uploadTotalHref2OtherHerfJob.build();
		
		return APIReturnJson.success("oos");
	}
}
