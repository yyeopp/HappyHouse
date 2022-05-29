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

import com.ssafy.happyhouse.model.dto.HouseDealInfo;
import com.ssafy.happyhouse.model.dto.HouseList;
import com.ssafy.happyhouse.model.service.HouseService;
import com.ssafy.happyhouse.model.service.SocialService;

@RestController
@RequestMapping("/house")
@CrossOrigin("*")
public class HouseController {

	@Autowired
	HouseService houseService;

	@Autowired
	SocialService socialService;

	@GetMapping("/houselist/gugun")
	public ResponseEntity<?> getHouseList(@RequestParam("gugunCode") String gugunCode,
			@RequestParam("buildYear") String buildYear, @RequestParam("minPrice") int minPrice,
			@RequestParam("maxPrice") int maxPrice) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("gugunCode", gugunCode);
			param.put("buildYear", buildYear);
			param.put("minPrice", minPrice);
			param.put("maxPrice", maxPrice);

			List<HouseList> list = houseService.searchHouseList(param);
			return new ResponseEntity<List<HouseList>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/housedeal/expanded/{aptCode}")
	public ResponseEntity<?> getHouseInfoExp(@PathVariable long aptCode) {
		try {
			// 완성될 맵
			Map<String, Object> houseinfoMap = new HashMap<String, Object>();
			// 지역정보 로딩
			Map<String, String> regionInfo = houseService.searchRegionInfo(aptCode);

			String sidoName = regionInfo.get("sidoName");

			String regionName = regionInfo.get("sigunguName");
			int regionCode = Integer.parseInt(regionInfo.get("sigunguCode"));

			// 지역 내 조회수 순위
			Map<String, String> hitRank = new HashMap<String, String>();
			hitRank.put("regionName", regionName);

			Map<String, Integer> map = houseService.searchHitRank(aptCode, regionCode, regionName);

			String regionTotal = String.valueOf(map.get("regionTotal"));
			String aptRank = String.valueOf(map.get("hitRank"));
			hitRank.put("regionTotal", regionTotal);
			hitRank.put("aptRank", aptRank);

			houseinfoMap.put("hitRank", hitRank);

			// 북마크 정보 조회
			Map<String, Integer> bookmarkInfo = houseService.searchBookmarkInfo(aptCode, regionCode);

			houseinfoMap.put("bookmarkInfo", bookmarkInfo);

			// 지역 내 평균 거래가 조회
			int averageAmount = houseService.searchAverageAmount(regionCode);

			houseinfoMap.put("averageAmount", averageAmount);

			// 지역 뉴스 조회
			String region = sidoName + " " + regionName;
			List<Map<String, String>> localNews = socialService.naverLocalNews(region);

			houseinfoMap.put("news", localNews);

			return new ResponseEntity<Map<String, Object>>(houseinfoMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/housedeal/{aptCode}")
	public ResponseEntity<?> getHouseDealInfo(@PathVariable("aptCode") long aptcode) {
		try {
			Map<String, Object> houseinfoMap = new HashMap<String, Object>();

			List<HouseDealInfo> dealList = houseService.searchHouseDealInfo(aptcode);

			houseinfoMap.put("dealList", dealList);

			return new ResponseEntity<Map<String, Object>>(houseinfoMap, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/houselist")
	public ResponseEntity<?> getHouseListRange(@RequestParam("neLat") String neLat, @RequestParam("neLng") String neLng,
			@RequestParam("swLat") String swLat, @RequestParam("swLng") String swLng,
			@RequestParam("buildYear") String buildYear, @RequestParam("minPrice") int minPrice,
			@RequestParam("maxPrice") int maxPrice) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("neLat", neLat);
			param.put("neLng", neLng);
			param.put("swLat", swLat);
			param.put("swLng", swLng);
			param.put("buildYear", buildYear);
			param.put("minPrice", minPrice);
			param.put("maxPrice", maxPrice);
			List<HouseList> list = houseService.searchHouseListRange(param);
			return new ResponseEntity<List<HouseList>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/gugunavg")
	public ResponseEntity<?> searchGugunAvg(@RequestParam("neLat") String neLat, @RequestParam("neLng") String neLng,
			@RequestParam("swLat") String swLat, @RequestParam("swLng") String swLng,
			@RequestParam("buildYear") String buildYear, @RequestParam("minPrice") int minPrice,
			@RequestParam("maxPrice") int maxPrice) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("neLat", neLat);
			param.put("neLng", neLng);
			param.put("swLat", swLat);
			param.put("swLng", swLng);
			param.put("buildYear", buildYear);
			param.put("minPrice", minPrice);
			param.put("maxPrice", maxPrice);
			List<Map<String, Object>> list = houseService.searchGugunAvg(param);
			return new ResponseEntity<List<Map<String, Object>>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/search")
	public ResponseEntity<?> searchInfo(@RequestParam String keyword) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			// 지역 검색
			List<Map<String, String>> regionSearch = houseService.regionSearch(keyword);
			result.put("regions", regionSearch);

			// 아파트 검색
			List<Map<String, String>> houseSearch = houseService.houseSearch(keyword);
			result.put("houses", houseSearch);

			// 지하철 검색
			List<Map<String, String>> subwaySearch = houseService.subwaySearch(keyword);
			result.put("subways", subwaySearch);

			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/housedeal/chart/{aptCode}")
	public ResponseEntity<?> houseDealChart(@PathVariable("aptCode") long aptcode) {
		try {
			List<Map<String, String>> houseDealChart = houseService.houseDealChart(aptcode);
			return new ResponseEntity<List<Map<String, String>>>(houseDealChart, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/search/{aptCode}")
	public ResponseEntity<?> browseHouseInfo(@PathVariable long aptCode) {
		try {
			Map<String, String> houseInfo = houseService.browseHouseInfo(aptCode);
			return new ResponseEntity<Map<String, String>>(houseInfo, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/trend/{sidoCode}")
	public ResponseEntity<?> houseTrend(@PathVariable int sidoCode) {
		try {
			List<Map<String, String>> houseTrend = houseService.houseTrend(sidoCode);
			return new ResponseEntity<List<Map<String, String>>>(houseTrend, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/trend/all")
	public ResponseEntity<?> houseTrend() {
		try {
			List<Map<String, String>> houseTrendAll = houseService.houseTrendAll();
			return new ResponseEntity<List<Map<String, String>>>(houseTrendAll, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
