package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

public interface SocialService {

	Map<String, String> naverLogin(String code, String state) throws Exception;

	Map<String, String> naverUserInfo(String access_token) throws Exception;

	List<Map<String, String>> naverNews() throws Exception;

	List<Map<String,String>> naverLocalNews(String regionName) throws Exception;
	
	List<Map<String,String>> naverBlog() throws Exception;

}
