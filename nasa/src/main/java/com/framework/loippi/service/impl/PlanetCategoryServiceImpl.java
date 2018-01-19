package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.PlanetCategoryDao;
import com.framework.loippi.entity.PlanetCategory;
import com.framework.loippi.service.PlanetCategoryService;
import java.util.List;
/**
 * SERVICE - PlanetCategory(星球类别表)
 * 
 * @author wmj
 * @version 2.0
 */
@Service
public class PlanetCategoryServiceImpl extends GenericServiceImpl<PlanetCategory, Long> implements PlanetCategoryService {
	
	@Autowired
	private PlanetCategoryDao planetCategoryDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(planetCategoryDao);
	}

	public List<PlanetCategory> findListByPage(Object parameter){
		return planetCategoryDao.findListByPage(parameter);
	}

	public Long deletes(Object parameter){
		return planetCategoryDao.deletes( parameter);
	}
	
	
}
