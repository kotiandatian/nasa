package com.framework.loippi.controller.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.loippi.controller.api.common.APIReturnJson;
import com.framework.loippi.controller.api.result.api.PlanetItemResult;
import com.framework.loippi.controller.api.result.api.category.PlanetCategoryResult;
import com.framework.loippi.entity.PlanetCategory;
import com.framework.loippi.entity.PlanetItem;
import com.framework.loippi.service.PlanetCategoryService;
import com.framework.loippi.service.PlanetItemService;
import com.framework.loippi.utils.Paramap;

@Controller
@RequestMapping("api/planet/*")
public class PlanetController extends ApiBaseController {

	@Resource
	private PlanetItemService planetItemService;
	@Resource
	private PlanetCategoryService planetCategoryService;
	
	//首頁-planet
	@RequestMapping(value = "/getPlanetItemList.json", method = RequestMethod.POST)
	public @ResponseBody String getPlanetItemList(@RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "20") Integer pageSize,HttpServletRequest request) {
		
		Paramap paramap = Paramap.create()
					.put("pageNumber", (pageNumber - 1) * pageSize)
					.put("pageSize", pageSize)
					.put("status", 1);
		
		List<PlanetItemResult> planetItemResultLit = new ArrayList<PlanetItemResult>();
		List<PlanetItem> planetItemList = planetItemService.findList(paramap);
		
		PlanetItemResult planetItemResult = null;
		if(!CollectionUtils.isEmpty(planetItemList)){
			for (PlanetItem planetItem : planetItemList) {
				planetItemResult = PlanetItemResult.build(planetItem);
				
				planetItemResultLit.add(planetItemResult);
			}
		}
		
		return APIReturnJson.success(planetItemResultLit);
	}
	
	//首頁-类别
	@RequestMapping(value = "/getPlanetCategoryList.json", method = RequestMethod.POST)
	public @ResponseBody String getPlanetCategoryList(HttpServletRequest request) {
		
		Paramap paramap = Paramap.create()
					.put("published", 1)
					.put("status", 1)
					.put("order ", " sort");
		
		List<PlanetCategoryResult> planetCategoryResultList = new ArrayList<PlanetCategoryResult>();
		PlanetCategoryResult planetCategoryResult = null;
		
		List<PlanetCategory> planetCategoryList = planetCategoryService.findList(paramap);
		if(!CollectionUtils.isEmpty(planetCategoryList)){
			for (PlanetCategory planetCategory : planetCategoryList) {
				planetCategoryResult = new PlanetCategoryResult();
				planetCategoryResult.setId(planetCategory.getId());
				planetCategoryResult.setNameEn(planetCategory.getNameEn());
				planetCategoryResult.setNameZh(planetCategory.getNameZh());
				
				planetCategoryResultList.add(planetCategoryResult);
			}
		}
		
		return APIReturnJson.success(planetCategoryResultList);
	}

}
