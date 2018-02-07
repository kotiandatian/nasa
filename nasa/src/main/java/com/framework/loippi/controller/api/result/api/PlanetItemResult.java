package com.framework.loippi.controller.api.result.api;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.framework.loippi.entity.PlanetItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanetItemResult {
	/**  */
	private Long id;
	
	/** nasa的id */
	private String nasaId;

	/** 资源类型 */
//	private String mediaType;
//	
//	/** 原图 */
//	private String hrefOrig;
//	
//	/**  */
//	private String hrefLarge;
//	
//	/**  */
//	private String hrefMedium;
//	
//	/**  */
//	private String hrefSmall;
	
	/** 缩略图 */
	private String hrefThumb;
	
	/** 类别id */
//	private Long categoryId;
	
	/** 类别中文名称 */
	private String categoryZh;
	
	/** 类别英文名称 */
	private String categoryEn;
	
	/** 英文标题 */
	private String titleEn;
	
	/** 中文标题 */
	private String titleZh;
	
	/** 中文描述 */
	private String descriptionZh;
	
	/** 英文描述 */
	private String descriptionEn;
	
//	/** 中文位置 */
//	private String locationZh;
//	
//	/** 英文位置 */
//	private String locationEn;
	
	/** 资源来源中心 */
	private String center;
	
	/** 数据发布时间 */
	private String dateCreated;

	public static PlanetItemResult build(PlanetItem planetItem) {
		PlanetItemResult planetItemResult = new PlanetItemResult();
		if(null != planetItem)
			try {
				BeanUtils.copyProperties(planetItemResult, planetItem);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		
		return planetItemResult;
	}
	

	
	
}
