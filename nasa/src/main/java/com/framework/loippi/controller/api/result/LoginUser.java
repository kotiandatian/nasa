package com.framework.loippi.controller.api.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {
	private Long id;
	private String avatar;
	private String nickname;
	//二维码
	private String qrCode;
	//private Integer sex;
	private String token;
	private String phone;
	private String sessionId;
	private String hxId;


}
