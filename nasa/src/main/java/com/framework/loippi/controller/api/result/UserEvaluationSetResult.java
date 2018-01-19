package com.framework.loippi.controller.api.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEvaluationSetResult {
	/** 序号 */
	private Long id;
	
	/**  */
	private String context;
	
	private String image;
}
