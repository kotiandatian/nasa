package com.framework.loippi.entity;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 星球类别表
 * 
 * @author wmj
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "U_PLANET_CATEGORY")
public class PlanetCategory implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 中文名称 */
	@Column(name = "name_zh" )
	private String nameZh;
	
	/** 英文名称 */
	@Column(name = "name_en" )
	private String nameEn;
	
	/** 中文描述 */
	@Column(name = "describe_zh" )
	private String describeZh;
	
	/** 英文描述 */
	@Column(name = "describe_en" )
	private String describeEn;
	
	/** 状态（1.正常  2.禁用） */
	@Column(name = "status" )
	private Integer status;
	
	/** 排序（越大排在越前） */
	@Column(name = "sort" )
	private Integer sort;
	
	/** uuid */
	@Column(name = "uuid" )
	private String uuid;
	
	/** 是否已经爬取（1，是  2，否） */
	@Column(name = "published" )
	private Integer published;
	
	/** 更新时间 */
	@Column(name = "update_time" )
	private java.util.Date updateTime;
	
	/** 创建时间 */
	@Column(name = "create_time" )
	private java.util.Date createTime;
	
}
