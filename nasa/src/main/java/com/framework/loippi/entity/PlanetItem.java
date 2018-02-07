package com.framework.loippi.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.framework.loippi.controller.api.result.nasa.Items;
import com.framework.loippi.controller.api.result.nasa.Links;
import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 星球资源表
 * 
 * @author wmj
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "U_PLANET_ITEM")
public class PlanetItem implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** nasa的id */
	@Column(name = "nasa_id" )
	private String nasaId;
	
	/** 获取该照片所有类型（原图，缩略图）链接 .json结尾 */
	@Column(name = "total_href" )
	private String totalHref;
	
	/** 是否获取全部类型的图片（1是  2.否） */
	@Column(name = "total_status" )
	private Integer totalStatus;
	
	/** 资源类型 */
	@Column(name = "media_type" )
	private String mediaType;
	
	/** 原图 */
	@Column(name = "href_orig" )
	private String hrefOrig;
	
	/**  */
	@Column(name = "href_large" )
	private String hrefLarge;
	
	/**  */
	@Column(name = "href_medium" )
	private String hrefMedium;
	
	/**  */
	@Column(name = "href_small" )
	private String hrefSmall;
	
	/** 缩略图 */
	@Column(name = "href_thumb" )
	private String hrefThumb;
	
	/** 类别id */
	@Column(name = "category_id" )
	private Long categoryId;
	
	/** 类别中文名称 */
	@Column(name = "category_zh" )
	private String categoryZh;
	
	/** 类别英文名称 */
	@Column(name = "category_en" )
	private String categoryEn;
	
	/** 英文标题 */
	@Column(name = "title_en" )
	private String titleEn;
	
	/** 中文标题 */
	@Column(name = "title_zh" )
	private String titleZh;
	
	/** 中文描述 */
	@Column(name = "description_zh" )
	private String descriptionZh;
	
	/** 英文描述 */
	@Column(name = "description_en" )
	private String descriptionEn;
	
	/** 中文位置 */
	@Column(name = "location_zh" )
	private String locationZh;
	
	/** 英文位置 */
	@Column(name = "location_en" )
	private String locationEn;
	
	/** 资源来源中心 */
	@Column(name = "center" )
	private String center;
	
	/** 数据发布时间 */
	@Column(name = "date_created" )
	private java.util.Date dateCreated;
	
	/** 是否已经爬取（1，是  2，否） */
	@Column(name = "published" )
	private Integer published;
	
	/** 是否启用（1，是  2，否） */
	@Column(name = "status" )
	private Integer status;
	
	/** 更新时间 */
	@Column(name = "update_time" )
	private java.util.Date updateTime;
	
	/** 创建时间 */
	@Column(name = "create_time" )
	private java.util.Date createTime;

	public static PlanetItem build(Items items2, PlanetCategory planetCategory, Date currentDate) {
		
		PlanetItem planetItem = null;
		if(null != items2){
			
			planetItem = new PlanetItem();
			 List<Links> links = items2.getLinks();
			//判断links
			 if(!CollectionUtils.isEmpty(links)){
				 Links links2 = links.get(0);
				 
				 planetItem.setHrefThumb(links2.getHref());
			 }
			//判断data
			List<com.framework.loippi.controller.api.result.nasa.Data> data = items2.getData();
			if(!CollectionUtils.isEmpty(data)){
				com.framework.loippi.controller.api.result.nasa.Data data2 = data.get(0);
				
				planetItem.setDescriptionEn(data2.getDescription());
				planetItem.setCenter(data2.getCenter());
				planetItem.setMediaType(data2.getMedia_type());
				planetItem.setDateCreated(data2.getDate_created());
				//planetItem.setLocationZh(data2.getl);
				planetItem.setTitleEn(data2.getTitle());
				planetItem.setNasaId(data2.getNasa_id());
			}
			
			planetItem.setCategoryEn(planetCategory.getNameEn());
			planetItem.setCategoryZh(planetCategory.getNameZh());
			planetItem.setCategoryId(planetCategory.getId());
			planetItem.setCreateTime(currentDate);
			planetItem.setTotalHref(items2.getHref());
			planetItem.setPublished(2);//是否已经爬取（1，是  2，否）爬取其他大小的图片  小图中图
			planetItem.setStatus(1);//是否启用（1，是  2，否）
			planetItem.setUpdateTime(currentDate);
			
//			planetItem.setHrefLarge(hrefLarge);
//			planetItem.setHrefMedium(hrefMedium);
//			planetItem.setHrefOrig(hrefOrig);
//			planetItem.setHrefSmall(hrefSmall);
		}
		
		return planetItem;
	}


	
}
