package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.HouseDealInfo;
import com.ssafy.happyhouse.model.dto.HouseList;

@Mapper
public interface HouseMapper {

	public List<HouseList> searchHouseList(Map<String, Object> param) throws Exception;

	public List<HouseList> searchHouseListRange(Map<String, Object> param);

	public List<HouseDealInfo> searchHouseDealInfo(long aptCode) throws Exception;

	public void hitHouseInfo(long aptCode) throws Exception;

	public Map<String, String> searchRegionInfo(long aptCode) throws Exception;

	public Map<String, Integer> searchHitRank(long aptCode, int regionCode) throws Exception;

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
