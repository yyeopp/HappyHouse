package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.HouseDealInfo;
import com.ssafy.happyhouse.model.dto.HouseList;

public interface HouseService {

	public List<HouseList> searchHouseList(Map<String, Object> param) throws Exception;

	public List<HouseList> searchHouseListRange(Map<String, Object> param) throws Exception;

	public List<HouseDealInfo> searchHouseDealInfo(long aptCode) throws Exception;

	public Map<String, String> searchRegionInfo(long aptCode) throws Exception;

	public Map<String, Integer> searchHitRank(long aptCode, int regionCode, String regionName) throws Exception;

	public Map<String, Integer> searchBookmarkInfo(long aptCode, int regionCode) throws Exception;

	public int searchAverageAmount(int regionCode) throws Exception;

	public List<Map<String, Object>> searchGugunAvg(Map<String, Object> param) throws Exception;

	public void addHouseBookmark(long aptCode, String memberId) throws Exception;

	public void removeHouseBookmark(long aptCode, String memberId) throws Exception;

	public List<Map<String, String>> regionSearch(String keyword) throws Exception;

	public List<Map<String, String>> houseSearch(String keyword) throws Exception;

	public List<Map<String, String>> subwaySearch(String keyword) throws Exception;

	public Map<String, String> browseHouseInfo(long aptCode) throws Exception;

	public List<Map<String, String>> houseDealChart(long aptCode);

	public List<Map<String, String>> houseTrend(int sidoCode) throws Exception;
	
	public List<Map<String, String>> houseTrendAll() throws Exception;
}
