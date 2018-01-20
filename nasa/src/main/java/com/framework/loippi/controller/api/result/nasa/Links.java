package com.framework.loippi.controller.api.result.nasa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Links {
	
	private String href;
	
	private String prompt;
	
	private String rel;
	
	private String render;
	
}
