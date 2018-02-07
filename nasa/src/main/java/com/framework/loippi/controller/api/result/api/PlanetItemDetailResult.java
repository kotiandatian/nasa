package com.framework.loippi.controller.api.result.api;

import com.framework.loippi.mybatis.ext.annotation.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanetItemDetailResult {

	/**  */
	private Long id;
	
	/** nasa的id */
	private String nasaId;

	/** 资源类型 */
	private String mediaType;
	
	/** 原图 */
	private String hrefOrig;
	
	/**  */
	private String hrefLarge;
	
	/**  */
	private String hrefMedium;
	
	/**  */
	private String hrefSmall;
	
	/** 缩略图 */
	private String hrefThumb;
	
	/** 类别id */
	private Long categoryId;
	
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
	
	/** 中文位置 */
	private String locationZh;
	
	/** 英文位置 */
	private String locationEn;
	
	/** 资源来源中心 */
	private String center;
	
	/** 数据发布时间 */
	private String dateCreated;
	

}
