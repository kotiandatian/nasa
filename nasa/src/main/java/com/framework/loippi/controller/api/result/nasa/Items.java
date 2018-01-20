package com.framework.loippi.controller.api.result.nasa;

import java.util.List;

public class Items {
	private String href;
	
	private List<Links> links;
	
	private List<Data> data;

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public List<Links> getLinks() {
		return links;
	}

	public void setLinks(List<Links> links) {
		this.links = links;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	
}
