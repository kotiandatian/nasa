package com.framework.loippi.controller.admin;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framework.loippi.controller.api.result.nasa.Collection;
import com.framework.loippi.controller.api.result.nasa.Metadata;
import com.framework.loippi.controller.api.result.nasa.NasaResult;
import com.framework.loippi.entity.PlanetCategory;
import com.framework.loippi.job.UploadResourceFromNASAJob;
import com.framework.loippi.service.PlanetCategoryService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.JsonUtils;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.RandomUtils;
import com.framework.loippi.utils.StringUtil;
import com.framework.loippi.utils.nasa.NasaUtil;

/**
 * Controller - 星球类别表
 * 
 * @author wmj
 * @version 2.0
 */
@Controller("adminPlanetCategoryController")
@RequestMapping({ "/admin/planet_category" })
public class PlanetCategoryController extends GenericController {

	@Resource
	private PlanetCategoryService planetCategoryService;

	/**
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(ModelMap model) {
		return "/admin/planet_category/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(PlanetCategory planetCategory, RedirectAttributes redirectAttributes) {
		Date currentDate = new Date();
		
		planetCategory.setCreateTime(currentDate);
		planetCategory.setPublished(2);//是否已经爬取（1，是  2，否）
		planetCategory.setStatus(1);//状态（1.正常  2.禁用）
		planetCategory.setUpdateTime(currentDate);
		planetCategory.setUuid(RandomUtils.getRandomNumber());
		
		planetCategoryService.save(planetCategory);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		PlanetCategory planetCategory = planetCategoryService.find(id);
		
		model.addAttribute("planetCategory", planetCategory);
		return "/admin/planet_category/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		PlanetCategory planetCategory = planetCategoryService.find(id);
		model.addAttribute("planetCategory", planetCategory);
		return "/admin/planet_category/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(PlanetCategory planetCategory, RedirectAttributes redirectAttributes) {
		
		planetCategory.setUpdateTime(new Date());
		planetCategoryService.update(planetCategory);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 列表查询
	 * 
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/list" }, method = { RequestMethod.GET })
	public String list(Pageable pageable,HttpServletRequest request, ModelMap model) {
		
		Map<String, Object> paramter = ParameterUtils.getParametersMapStartingWith(request, "filter_");
		Map map=new HashMap();
		  for (String key : paramter.keySet()) {  
	            if(!StringUtil.isEmpty( paramter.get(key).toString())){
	            	map.put(key,  paramter.get(key));
	            }
	        }  
		pageable.setParameter(map);
		model.addAttribute("paramter", paramter);
		model.addAttribute("page", this.planetCategoryService.findByPage(pageable));
		return "/admin/planet_category/list";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.planetCategoryService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 校验操作
	 * 
	 * @param ids
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = { "/check" }, method = { RequestMethod.GET })
	public @ResponseBody String check(String titleEn) throws Exception {
		
		String baseUrl = UploadResourceFromNASAJob.baseUrl;
		 Map hashMap = new HashMap();
	     hashMap.put("q", titleEn);
	     //hashMap.put("page", "1");
		 String masaResourceResult = NasaUtil.requestForGetHttp(baseUrl, hashMap);
		 
		 
		 JSONObject jsonObject = new JSONObject(masaResourceResult);
		 JSONObject collection = (JSONObject) jsonObject.get("collection");
		 
		 JSONObject metadata = (JSONObject) collection.get("metadata");
			
		 Integer total_hits = (Integer)metadata.get("total_hits");
		 
		 hashMap.put("titleEn", titleEn);
		 hashMap.put("totalHits", 0);
		 if(null != total_hits && total_hits != 0){
			 System.err.println(total_hits);
			 hashMap.put("totalHits", total_hits);
		 }
		 String valueToString = JSONObject.valueToString(hashMap);
		 
		 System.err.println(valueToString);
		return valueToString;
	}
	
	
}
