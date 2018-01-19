package com.framework.loippi.controller.api;

import javax.annotation.Resource;

import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.framework.loippi.api.utils.ApiUtils;
import com.framework.loippi.taskExecutor.BidTaskExecutor;

@Controller
@RequestMapping(value = "/nasa")
public class User_admin extends ApiBaseController {

	@Resource(name = "taskExecutor")
	private TaskExecutor taskExecutor;

	@RequestMapping(value = "/testExceutor", method = RequestMethod.GET)
	public String testExceutor() {

		for (int i = 1; i < 10; i++) {
			taskExecutor.execute(new BidTaskExecutor(i));
		}
		return ApiUtils.success();
	}
}