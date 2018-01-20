package com.framework.loippi.dao;

import com.framework.loippi.entity.PlanetItem;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - PlanetItem(星球资源表)
 * 
 * @author wmj
 * @version 2.0
 */
public interface PlanetItemDao  extends GenericDao<PlanetItem, Long> {
	List<PlanetItem> findListByPage(Object parameter);
        Long deletes(Object parameter);
}
