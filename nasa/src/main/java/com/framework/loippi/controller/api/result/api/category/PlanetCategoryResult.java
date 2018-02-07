package com.framework.loippi.controller.api.result.api.category;

import com.framework.loippi.controller.api.result.api.PlanetItemDetailResult;
import com.framework.loippi.mybatis.ext.annotation.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanetCategoryResult {

	/**  */
	private Long id;
	
	/** 中文名称 */
	private String nameZh;
	
	/** 英文名称 */
	private String nameEn;
	
}
