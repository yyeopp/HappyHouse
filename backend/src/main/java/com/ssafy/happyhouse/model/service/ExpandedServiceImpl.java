package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.BusStationDto;
import com.ssafy.happyhouse.model.dto.HospitalListDto;
import com.ssafy.happyhouse.model.dto.NearObj;
import com.ssafy.happyhouse.model.dto.SchoolDto;
import com.ssafy.happyhouse.model.dto.SubwayDto;
import com.ssafy.happyhouse.model.mapper.ExpandedMapper;

@Service
public class ExpandedServiceImpl implements ExpandedService {
	
	@Autowired
	private ExpandedMapper expandedMapper;

//	@Override
//	public List<HospitalListDto> hospitalList(String dongCode) throws SQLException {
//		return expandedMapper.hospitalList(dongCode);
//	}


	@Override
	public List<HospitalListDto> hospitalListRange(String neLat, String neLng, String swLat, String swLng) {
		return expandedMapper.hospitalListRange(neLat,neLng,swLat,swLng);
	}

	@Override
	public List<SchoolDto> schoolListRange(String neLat, String neLng, String swLat, String swLng) {
		return expandedMapper.schoolListRange(neLat, neLng, swLat, swLng);
	}

	@Override
	public List<BusStationDto> BusStationListRange(String neLat, String neLng, String swLat, String swLng) {
		return expandedMapper.BusStationListRange(neLat, neLng, swLat, swLng);
	}

	@Override
	public List<SubwayDto> SubwayListRange(String neLat, String neLng, String swLat, String swLng) {
		return expandedMapper.SubwayListRange(neLat, neLng, swLat, swLng);
	}


	@Override
	public List<NearObj> nearSchool(String lng, String lat) {
		return expandedMapper.nearSchool(lng,lat);
	}


	@Override
	public List<NearObj> nearHospital(String lng, String lat) {
		return expandedMapper.nearHospital(lng,lat);
	}


	@Override
	public List<NearObj> nearSubway(String lng, String lat) {
		return expandedMapper.nearSubway(lng,lat);
	}

}
