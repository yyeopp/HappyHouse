package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.BusStationDto;
import com.ssafy.happyhouse.model.dto.HospitalInfoDto;
import com.ssafy.happyhouse.model.dto.HospitalListDto;
import com.ssafy.happyhouse.model.dto.NearObj;
import com.ssafy.happyhouse.model.dto.SchoolDto;
import com.ssafy.happyhouse.model.dto.SubwayDto;

@Mapper
public interface ExpandedMapper {

//	List<HospitalListDto> hospitalList(String dongCode) throws SQLException; // 동으로 병원 검색 시, 동에 해당하는 병원 전체 조회
	HospitalInfoDto viewHospital(String hospitalNo) throws SQLException; // 마커 클릭 시, 병원 상세 정보 조회
	List<HospitalListDto> hospitalListRange(String neLat, String neLng, String swLat, String swLng);
	List<SchoolDto> schoolListRange(String neLat, String neLng, String swLat, String swLng);
	List<BusStationDto> BusStationListRange(String neLat, String neLng, String swLat, String swLng);
	List<SubwayDto> SubwayListRange(String neLat, String neLng, String swLat, String swLng);
	List<NearObj> nearSchool(String lng,String lat);
	List<NearObj> nearHospital(String lng, String lat);
	List<NearObj> nearSubway(String lng, String lat);
}
