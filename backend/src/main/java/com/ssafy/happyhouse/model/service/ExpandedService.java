package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.BusStationDto;
import com.ssafy.happyhouse.model.dto.HospitalListDto;
import com.ssafy.happyhouse.model.dto.NearObj;
import com.ssafy.happyhouse.model.dto.SchoolDto;
import com.ssafy.happyhouse.model.dto.SubwayDto;

public interface ExpandedService {

//	List<HospitalListDto> hospitalList(String dongCode) throws SQLException; // 동으로 병원 검색 시, 동에 해당하는 병원 전체 조회
	List<HospitalListDto> hospitalListRange(String neLat, String neLng, String swLat, String swLng);
	List<SchoolDto> schoolListRange(String neLat, String neLng, String swLat, String swLng);
	List<BusStationDto> BusStationListRange(String neLat, String neLng, String swLat, String swLng);
	List<SubwayDto> SubwayListRange(String neLat, String neLng, String swLat, String swLng);
	List<NearObj> nearSchool(String lng,String lat);
	List<NearObj> nearHospital(String lng,String lat);
	List<NearObj> nearSubway(String lng,String lat);
	
}
