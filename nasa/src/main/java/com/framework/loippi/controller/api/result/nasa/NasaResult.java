package com.framework.loippi.controller.api.result.nasa;

public class NasaResult {

	private Collection collection;

	public Collection getCollection() {
		return collection;
	}

	public void setCollection(Collection collection) {
		this.collection = collection;
	}

	@Override
	public String toString() {
		return "NasaResult [collection=" + collection + ", getCollection()=" + getCollection() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
