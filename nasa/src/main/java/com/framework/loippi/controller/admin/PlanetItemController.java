package com.framework.loippi.controller.admin;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framework.loippi.entity.PlanetCategory;
import com.framework.loippi.entity.PlanetItem;
import com.framework.loippi.job.UploadResourceFromNASAJob;
import com.framework.loippi.service.PlanetCategoryService;
import com.framework.loippi.service.PlanetItemService;
import com.framework.loippi.support.Message;
import com.framework.loippi.support.Pageable;
import com.framework.loippi.utils.ParameterUtils;
import com.framework.loippi.utils.StringUtil;

/**
 * Controller - 星球资源表
 * 
 * @author wmj
 * @version 2.0
 */
@Controller("adminPlanetItemController")
@RequestMapping({ "/admin/planet_item" })
public class PlanetItemController extends GenericController {

	@Resource
	private PlanetItemService planetItemService;
	@Resource
	private PlanetCategoryService planetCategoryService;
	@Resource
	private UploadResourceFromNASAJob updateCatchIngJob;
	/** 
	 * 跳转添加页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/add" }, method = { RequestMethod.GET })
	public String add(ModelMap model) {
		return "/admin/planet_item/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(PlanetItem planetItem, RedirectAttributes redirectAttributes) {
		planetItemService.save(planetItem);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {
		PlanetItem planetItem = planetItemService.find(id);
		model.addAttribute("planetItem", planetItem);
		return "/admin/planet_item/edit";
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable Long id, ModelMap model) {
		PlanetItem planetItem = planetItemService.find(id);
		model.addAttribute("planetItem", planetItem);
		return "/admin/planet_item/view";
	}
	

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(PlanetItem planetItem, RedirectAttributes redirectAttributes) {
		planetItemService.update(planetItem);
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
		model.addAttribute("page", this.planetItemService.findByPage(pageable));
		
		//封装全部类别
		List<PlanetCategory> planetCategoryList = planetCategoryService.findAll();
		model.addAttribute("planetCategoryList", planetCategoryList);
		
		return "/admin/planet_item/list";
	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = { "/delete" }, method = { RequestMethod.POST })
	public @ResponseBody Message delete(Long[] ids) {
		this.planetItemService.deleteAll(ids);
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 改变状态
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "/changeStatus" }, method = { RequestMethod.GET })
	public String changeStatus(Long id) {
		PlanetItem planetItem = planetItemService.find(id);
		if(null != planetItem){
			PlanetItem planetItem2 = new PlanetItem();
			planetItem2.setId(planetItem.getId());
			planetItem2.setUpdateTime(new Date());
			planetItem2.setStatus(planetItem.getStatus() == 1 ? 2 :1);
			
			planetItemService.update(planetItem2);
		}
		

		return "redirect:list.jhtml";
	}

	
	/**
	 * 同步更新
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "/syncUpdate" }, method = { RequestMethod.GET })
	public String syncUpdate() {
		
		updateCatchIngJob.UploadResourceFromNASA();
		
		return "redirect:list.jhtml";
	}
}
