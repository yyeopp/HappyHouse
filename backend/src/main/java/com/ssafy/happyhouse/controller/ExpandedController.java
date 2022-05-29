package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.BusStationDto;
import com.ssafy.happyhouse.model.dto.HospitalListDto;
import com.ssafy.happyhouse.model.dto.NearObj;
import com.ssafy.happyhouse.model.dto.SchoolDto;
import com.ssafy.happyhouse.model.dto.SubwayDto;
import com.ssafy.happyhouse.model.service.ExpandedService;

@RestController
@RequestMapping("/expanded")
@CrossOrigin("*")
public class ExpandedController {
	
	@Autowired
	ExpandedService expandedService;
	
	@GetMapping("/hospitallist")
	public ResponseEntity<?> getHospitalListRange(@RequestParam("neLat") String neLat, @RequestParam("neLng") String neLng,
			@RequestParam("swLat") String swLat,@RequestParam("swLng") String swLng) {
		try {
			List<HospitalListDto> list = expandedService.hospitalListRange(neLat,neLng,swLat,swLng);
			return new ResponseEntity<List<HospitalListDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/schoollist")
	public ResponseEntity<?> getSchoolListRange(@RequestParam("neLat") String neLat, @RequestParam("neLng") String neLng,
			@RequestParam("swLat") String swLat,@RequestParam("swLng") String swLng) {
		try {
			List<SchoolDto> list = expandedService.schoolListRange(neLat,neLng,swLat,swLng);
			return new ResponseEntity<List<SchoolDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/busstationlist")
	public ResponseEntity<?> getBusStationListRange(@RequestParam("neLat") String neLat, @RequestParam("neLng") String neLng,
			@RequestParam("swLat") String swLat,@RequestParam("swLng") String swLng) {
		try {
			List<BusStationDto> list = expandedService.BusStationListRange(neLat, neLng, swLat, swLng);
			return new ResponseEntity<List<BusStationDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/subwaylist")
	public ResponseEntity<?> getSubWayListRange(@RequestParam("neLat") String neLat, @RequestParam("neLng") String neLng,
			@RequestParam("swLat") String swLat,@RequestParam("swLng") String swLng) {
		try {
			List<SubwayDto> list = expandedService.SubwayListRange(neLat, neLng, swLat, swLng);
			return new ResponseEntity<List<SubwayDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/near/{lng}/{lat}")
	public ResponseEntity<?> getNearObject(@PathVariable("lng") String lng, @PathVariable("lat") String lat) {
		try {
			Map<String, List<NearObj>> resultMap = new HashMap<>();
			
			List<NearObj> SchoolList = expandedService.nearSchool(lng, lat);
			List<NearObj> hospitalList = expandedService.nearHospital(lng, lat);
			List<NearObj> subwaylList = expandedService.nearSubway(lng, lat);
			
			resultMap.put("school", SchoolList);
			resultMap.put("hospital", hospitalList);
			resultMap.put("subway",subwaylList);
			return new ResponseEntity<Map<String, List<NearObj>>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
