package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.HouseDealInfo;
import com.ssafy.happyhouse.model.dto.HouseList;
import com.ssafy.happyhouse.model.mapper.HouseMapper;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	HouseMapper houseMapper;

	@Override
	public List<HouseList> searchHouseList(Map<String, Object> param) throws Exception {
		return houseMapper.searchHouseList(param);
	}

	@Override
	public List<HouseDealInfo> searchHouseDealInfo(long aptCode) throws Exception {
		houseMapper.hitHouseInfo(aptCode);
		return houseMapper.searchHouseDealInfo(aptCode);
	}

	@Override
	public List<HouseList> searchHouseListRange(Map<String, Object> param) throws Exception {
		return houseMapper.searchHouseListRange(param);
	}

	@Override
	public Map<String, String> searchRegionInfo(long aptCode) throws Exception {
		return houseMapper.searchRegionInfo(aptCode);
	}

	@Override
	public Map<String, Integer> searchHitRank(long aptCode, int regionCode, String regionName) throws Exception {

		return houseMapper.searchHitRank(aptCode, regionCode);
	}

	@Override
	public int searchAverageAmount(int regionCode) throws Exception {
		return houseMapper.searchAverageAmount(regionCode);
	}

	@Override
	public Map<String, Integer> searchBookmarkInfo(long aptCode, int regionCode) throws Exception {
		return houseMapper.searchBookmarkInfo(aptCode, regionCode);
	}

	@Override
	public List<Map<String, Object>> searchGugunAvg(Map<String, Object> param) throws Exception {
		return houseMapper.searchGugunAvg(param);
	}

	public void addHouseBookmark(long aptCode, String memberId) throws Exception {
		houseMapper.addHouseBookmark(aptCode, memberId);
	}

	@Override
	public void removeHouseBookmark(long aptCode, String memberId) throws Exception {
		houseMapper.removeHouseBookmark(aptCode, memberId);

	}

	@Override
	public List<Map<String, String>> regionSearch(String keyword) throws Exception {
		return houseMapper.regionSearch(keyword);
	}

	@Override
	public List<Map<String, String>> houseSearch(String keyword) throws Exception {
		return houseMapper.houseSearch(keyword);
	}

	@Override
	public List<Map<String, String>> subwaySearch(String keyword) throws Exception {
		return houseMapper.subwaySearch(keyword);
	}

	@Override
	public Map<String, String> browseHouseInfo(long aptCode) throws Exception {
		return houseMapper.browseHouseInfo(aptCode);
	}

	public List<Map<String, String>> houseDealChart(long aptCode) {
		return houseMapper.houseDealChart(aptCode);
	}

	@Override
	public List<Map<String, String>> houseTrend(int sidoCode) throws Exception {
		return houseMapper.houseTrend(sidoCode);
	}

	@Override
	public List<Map<String, String>> houseTrendAll() throws Exception {
		return houseMapper.houseTrendAll();
	}
}
