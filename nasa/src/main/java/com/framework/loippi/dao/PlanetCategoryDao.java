package com.framework.loippi.dao;

import com.framework.loippi.entity.PlanetCategory;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - PlanetCategory(星球类别表)
 * 
 * @author wmj
 * @version 2.0
 */
public interface PlanetCategoryDao  extends GenericDao<PlanetCategory, Long> {
	List<PlanetCategory> findListByPage(Object parameter);
        Long deletes(Object parameter);
}
