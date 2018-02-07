package com.framework.loippi.controller.api;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.loippi.api.core.GenericAPIController;
import com.framework.loippi.api.utils.ApiUtils;
import com.framework.loippi.api.utils.Xerror;
import com.framework.loippi.entity.App;
import com.framework.loippi.entity.App.Device;
import com.framework.loippi.entity.Feedback;
import com.framework.loippi.service.AdService;
import com.framework.loippi.service.AppService;
import com.framework.loippi.service.AreaService;
import com.framework.loippi.service.CacheService;
import com.framework.loippi.service.FeedbackService;

/**
 * Controller - 通用
 * 
 * @author caisz
 * @version 1.0
 */
@Controller
@RequestMapping("/api/common")
public class CommonController extends GenericAPIController {
	
	@Resource
	private CacheService cacheService;
	
	@Resource
	private AreaService areaService;
	
	@Resource
	private AppService appService;
	
	@Resource
	private AdService adService;
	
	@Resource
	private FeedbackService feedbackService;
	
	/**
	 * 发送短信验证码： 向第三方发送短信验证码
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/msg", method = { RequestMethod.POST })
	public @ResponseBody String msg(String mobile) {
		if (StringUtils.isEmpty(mobile)) {
			return ApiUtils.error(Xerror.PARAM_INVALID);
		}
		String code = RandomStringUtils.random(6, "0123456789");
		cacheService.set(mobile, code, 60 * 10);
		return ApiUtils.success(code);
	}
	
	
}
