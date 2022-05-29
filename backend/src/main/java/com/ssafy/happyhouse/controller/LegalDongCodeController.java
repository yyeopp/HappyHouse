package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.happyhouse.model.dto.BaseAddress;
import com.ssafy.happyhouse.model.dto.DongCodeDto;
import com.ssafy.happyhouse.model.dto.GugunCodeDto;
import com.ssafy.happyhouse.model.dto.SidoCodeDto;
import com.ssafy.happyhouse.model.service.LegalDongCodeService;

@Controller
@RequestMapping("/legaldong")
@CrossOrigin("*")
public class LegalDongCodeController {

	@Autowired
	private LegalDongCodeService legalDongCodeService;
	
	@GetMapping("/sido")
	public ResponseEntity<?> getSidoCode() {
		try {
			List<SidoCodeDto> list = legalDongCodeService.searchSidoList();
			return new ResponseEntity<List<SidoCodeDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<?> getGugunCode(@RequestParam("code") String sidoCode) {
		try {
			System.out.println(sidoCode);
			List<GugunCodeDto> list = legalDongCodeService.searchGugunList(sidoCode);
			System.out.println(list);
			return new ResponseEntity<List<GugunCodeDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/dong")
	public ResponseEntity<?> getDongCode(@RequestParam("code") String gugunCode) {
		try {
			List<DongCodeDto> list = legalDongCodeService.searchDongList(gugunCode);
			return new ResponseEntity<List<DongCodeDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/center/{gugunCode}")
	public ResponseEntity<?> getCenter(@PathVariable("gugunCode") String gugunCode) {
		try {
			BaseAddress addr = legalDongCodeService.searchCenter(gugunCode);
			return new ResponseEntity<BaseAddress>(addr, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
