package com.framework.loippi.controller.api.result.nasa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasaResourceResult {

	//前页
	private String prevUrl;
	
	//后一页
	private String nextUrl;
	
	private String totalHref;
	
	private String render;
	
	private String title;
	
	private String description;
	
	private String mediaType;
	
	private String nasaId;
	
	private String center;
	
	//date_created
	private java.util.Date dateCreated;
	
	private String location;
}
