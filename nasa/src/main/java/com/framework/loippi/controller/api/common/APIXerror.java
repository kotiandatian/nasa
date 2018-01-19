package com.framework.loippi.controller.api.common;

public enum APIXerror {
	
	SYSTEM_ERROR_5000000("5000000","system error"),
	USER_NOT_LOGIN_5000001("5000001","用户未登录"),
	USER_SESS_EXPIRED_5000002("5000002","会话信息失败"),
	REQUIRE_PARAMETERS_LA("5000003","参数la为空"),
	NO_AUTH("5000004","无操作权限"),
	NO_PERMITTED_5000005("5000005","账号锁定中"),
	PARAM_INVALID_5000020("5000020","参数有误"),
	MOBILE_INUSER_5000101("5000101","该手机已被注册"),
	LOGIN_PASSWORD_ERROR_5000102("5000102","用户登录密码错误"),
	OBJECT_IS_NOT_EXIST_5000103("5000103","用户不存在"),
	OBJECT_IS_NOCONFORMITY_5000104("5000104","手机与用户信息不符"),
	CODE_IS_NOT_EXIST_5000105("5000105","验证码不存在"),
	VALID_CODE_ERROR_5000106("5000106","验证码有误"),
	USER_UNBIND_5000107("5000107","用户未绑定"),
	NOT_MOBILE_5000108("5000108","该号码非手机号"),
	OBJECT_IS_EXIST_5000109("5000109","用户已经存在"),
	NICKNAME_IS_EXIST("5000110","用户昵称已经存在"),
	NOT_MOBILE_5000111("5000111","无法添加好友"),
	WALLET_NOT_EXIST_50001009("50001009","用户钱包不存在"),
	RECHARGE_ERROR_50001010("50001010","充值失败"),
	RECHARGE_ACCOUNT_NOT_EXIST_50001011("50001011","充值账号不存在"),
	TO_PAY_ERROR_50001012("50001012","支付失败"),
	TO_DAILY_ATTENDANCE_ERROR_50001013("50001013","您今日已签过"),
	MIN_WITHDRAW_ERROR_50001016("50001016","提现金额小于提现最低金额"),
	COUPON_NOT_ENOUGH_50001020("50001020","抵用券不足"),
	CREDIT_NOT_ENOUGH_50001021("50001021","积分不足"),
	WALLET_REDUCE_DEPOSIT_50001022("50001022","余额不足"),
	OBJECT_UNFOUCE_5001023("5001023","未收藏对象"),
	NEED_BOSSPASS_5001024("5001024","需要bossPass"),
	BOSS_PASSWORD_ERROR_5001025("5001025","老板约密码错误"),
	PASSWORD_NEED_PERFECT_5001026("5001026","账号密码需完善"),
	NEED_PAY_DAMAGES_5001027("5001027","需要交纳违约金"),
	ORDER_ARLEARY_COMPLETE_5001028("5001028","订单已完成"),
	NOT_MEET_OWNER_ORDER_5001029("5001029","不能接自己发的单"),
	EVALUATE_IS_NOT_EXIST_5001030("5001030","该评论不存在"),
	JION_NUM_ENOUGH_5001031("5001031","该订单参与人数已够"),
	UNDONE_ORDER_IS_EXIST_5001032("5001032","已存在该订单的未完成订单"),
	VERIFY_REALNAME_ERROR_5001033("5001033","信息有误,实名验证失败,请重新验证"),
	FINGERMARKTOKEN_NEED_PERFECT_5001034("5001034","指纹token需完善"),
	FINGERMARK_LOGIN_TOKEN_ERROR_5001035("5001035","指纹token错误"),
	INVITE_FRIEND_ERROR_5001036("5001036","邀请好友错误"),
	FACESWIPING_LOGIN_TOKEN_ERROR_5001037("5001037","刷脸token错误"),
	ORDER_TYPE_NON_SUPPORT_ERROR_5001038("5001038","该订单类型不支持游戏"),
	ORDER_STUTAS_NON_SUPPORT_ERROR_5001038("5001038","该订单状态不支持游戏"),
	USER_STUTAS_FORBIDDEN_5001039("5001039","该用户处于禁用状态，请联系管理员！"),
	FRIEND_APPLYING_5001040("5001040","好友申请中，请耐心等待回复！"),
	ALREARY_FRIEND_5001041("5001041","已经是是好友关系！"),
	ORDER_GAME_REDUCTION_PEOPLE_BEYOND_5001042("5001042","订单减免人数超出了订单人数！"),
	ORDER_GAME_NOT_STARTED_5001043("5001043","订单游戏还未开始，请耐心等待！"),
	ORDER_ALREADY_COMPLETE_5001044("5001044","订单已经完成，不能再次确认到达！"),
	;
/*
	SYSTEM_ERROR_EN("5000000","系统出错en"),
	
	USER_SESS_EXPIRED("5000103","会话信息失效"),
	USER_NOT_LOGIN("5000104","用户未登录"),
	LOGIN_PASSWORD_ERROR("5000105","用户登录密码错误"),
	OBJECT_IS_NOT_EXIST("5000106","用户不存在"),
	LOGIN_ACCOUNT_DISABLED("5000107","用户状态为禁用"),
	PARAM_INVALID("5000108","参数有误"),
	USER_UNBIND("5000109","用户未绑定"),
	MOBILE_INUSER("5000111","该手机已被注册"),
	VALID_CODE_ERROR("5000112","验证码有误"),
	NOT_MOBILE("5000113","该号码非手机号"),
	CODE_IS_NOT_EXIST("5000114","验证码不存在"),
	OBJECT_IS_NOT_MUSICIAN("5000115","用户未实名认证"),
	OBJECT_IS_NOT_VERIFICATION("5000116","用户未经过认证"),
	OBJECT_IS_EXIST("5000117","用户已经存在"),
	OBJECT_IS_FINISH("5000118","优惠券已领取完"),
	OBJECT_IS_AMPLE("5000119","限领个数已够"),
	VALID_RAMCODE_ERROR("5000120","图形验证码有误"),
	OBJECT_IS_NOCONFORMITY("5000121","手机与用户信息不符"),
	
	Wallet_IS_NOT_EXIST("5001000","用户钱包不存在"),

	CAN_NOT_COMMENT("5002000","不能回复自己的评论"),
	
	ALREADY_PRAISED("5002001","已点赞"),
	ALREADY_FOUCUS("5003001","已关注"),
	

	//订单状态
	Item_out("5010001","商品已下架"),
	OBJECT_ISNOT_EXIST("5010002","用户不存在"),
	OBJECT_ISNOT_EXIST2("5010003","用户不存在"),
	Order_status_notall2("5010004","仍有订单音乐人未确认接单，请稍后付款"),
	Order_no_money("5010005","支付失败"),
	Order_no_pay_password("5010006","您尚未设置支付密码"),
	Order_error_password("5010007","支付密码错误"),
	ORDER_STATUS_ERROR("5010008","接单失败"),
	ORDER_BIDDING_STATUS_ERROR("5030009","抢单失败"),
	
	//直播状态
	Live_NO_Gene("5020001","商品已下架"),
	Live_NO_money("5020002","星币不足，支付失败"),
	Live_NO_purchase("5020003","商品已下架"),
	
	//用户与音乐人端登录
	USER_PENDING("5000113","暂未完成音乐人实名认证"),
	USER_ONAGREE("5000114","您的实名认证信息正在审核中"),
	USER_DISAGREE("5000115","您的实名认证信息审核失败"),
	USER_SUCCESS("5000116","您的实名认证信息已通过"),
	
	
	
	//新版本错误码
	HAD_GOODS("6000101","商品类型已经存在"),
	NO_Live("6000102","喔喔，主播忙线中，要不要再试一次?"),

	//保证金
	DEPOSIT_NO_GIVE("5030001","首次发佈商品，未缴纳保证金"),
	NOT_WALLET_REDUCE_DEPOSIT("5030002","保证金和钱包馀额不足，请走其他通道充值");

*/

	public String xCode;
	public String massage;
	
	APIXerror(String xCode, String massage) {
		this.xCode = xCode;
		this.massage = massage;
	}
	
	
	 /**
     * 不能为空
     */
    public static final int NOT_NULL = 5000002;
    /**
     * 长度太小
     */
    public static final int SIZE_TOO_MIN = 5000507;

}
