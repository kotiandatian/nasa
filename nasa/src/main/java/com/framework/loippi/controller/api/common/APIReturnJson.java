package com.framework.loippi.controller.api.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class APIReturnJson {

    
	public static String error(Object obj,Integer la)
	{
		Map returnMap = new HashMap();
		la = 1;//TODO 之后再开放国际化
		if(null == la || la == 1){
			APIXerror aPIXerrorEnum = APIXerror.valueOf(obj.toString());
			returnMap.put("message", aPIXerrorEnum.massage);
			returnMap.put("status", aPIXerrorEnum.xCode);
		}else{
			APIXerror aPIXerrorEnum = APIXerror.valueOf(obj.toString() + "_EN");
			returnMap.put("message", aPIXerrorEnum.massage);
			returnMap.put("status", aPIXerrorEnum.xCode);
		}
		
		
		JSONObject json = JSONObject.fromObject(returnMap);
		return json.toString();
	}
	
	/*public static String error(String obj,int la)
	{
		
		Map returnMap = new HashMap();
		returnMap.put("message", obj);
		returnMap.put("status", 5000000);
		
		JSONObject json = JSONObject.fromObject(returnMap);
		return json.toString();
	}*/
	
	public static String success()
	{
		Map returnMap = new HashMap();
		returnMap.put("message", "ok");
		returnMap.put("status", 2000000);
		JSONObject json = JSONObject.fromObject(returnMap);
		return json.toString();
	}
	public static String success(Object obj)
	{
		Map returnMap = new HashMap();
		returnMap.put("message", "ok");
		returnMap.put("data", obj);
		returnMap.put("status", 2000000);
		JSONObject json = JSONObject.fromObject(returnMap);
		return json.toString();
	}
	
	
	public static void main(String[] args) {
		ArrayList<String> arrayList = new ArrayList<String>();
		for (Integer i =2 ; i<1000 ; i++) {
			arrayList.add(i.toString());
		}
		HashMap hashMap = new HashMap();
		hashMap.put("json", arrayList);
		JSONObject json = JSONObject.fromObject(hashMap);
		System.out.println(json.toString());
	}
}
