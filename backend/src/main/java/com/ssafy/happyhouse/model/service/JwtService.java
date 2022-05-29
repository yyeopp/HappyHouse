package com.ssafy.happyhouse.model.service;

import java.util.Map;

public interface JwtService {

	<T> String createAccessToken(String key, T data, String subject);
	 String createRefreshToken(String key,String data, String subject);
	Map<String, Object> get(String key);
	String getUserId();
	boolean isUsable(String jwt);
	
}
