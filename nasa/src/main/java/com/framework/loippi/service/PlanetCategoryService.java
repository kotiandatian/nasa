package com.framework.loippi.service;

import com.framework.loippi.entity.PlanetCategory;
import java.util.List;
/**
 * SERVICE - PlanetCategory(星球类别表)
 * 
 * @author wmj
 * @version 2.0
 */
public interface PlanetCategoryService  extends GenericService<PlanetCategory, Long> {
	public	List<PlanetCategory> findListByPage(Object parameter);
        public	 Long deletes(Object parameter);
}
