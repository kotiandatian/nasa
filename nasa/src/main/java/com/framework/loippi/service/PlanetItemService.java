package com.framework.loippi.service;

import com.framework.loippi.entity.PlanetItem;
import java.util.List;
/**
 * SERVICE - PlanetItem(星球资源表)
 * 
 * @author wmj
 * @version 2.0
 */
public interface PlanetItemService  extends GenericService<PlanetItem, Long> {
	public	List<PlanetItem> findListByPage(Object parameter);
        public	 Long deletes(Object parameter);
}
