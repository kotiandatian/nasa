package com.framework.loippi.controller.api.result.nasa;

import java.util.Date;

public class Data {

	private String title;
	
	private String description;
	
	private Date date_created;
	
	private String media_type;
	
	private String center;
	
	private String nasa_id;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public Date getDate_created() {
		return date_created;
	}

	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}

	
	public String getMedia_type() {
		return media_type;
	}

	public void setMedia_type(String media_type) {
		this.media_type = media_type;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public String getNasa_id() {
		return nasa_id;
	}

	public void setNasa_id(String nasa_id) {
		this.nasa_id = nasa_id;
	}
	
}
