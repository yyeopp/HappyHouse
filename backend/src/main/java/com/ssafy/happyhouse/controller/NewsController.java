package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.happyhouse.model.service.SocialService;

@Controller
@RequestMapping("/news")
@CrossOrigin("*")
public class NewsController {

	@Autowired
	SocialService socialService;

	@GetMapping("/list")
	public ResponseEntity<?> getNaverNews() {
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			List<Map<String, String>> list = socialService.naverNews();
			result.put("news", list);
			return new ResponseEntity<List<Map<String, String>>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/blog")
	public ResponseEntity<?> getNaverBlog() {
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			List<Map<String, String>> list = socialService.naverBlog();
			result.put("news", list);
			return new ResponseEntity<List<Map<String, String>>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
