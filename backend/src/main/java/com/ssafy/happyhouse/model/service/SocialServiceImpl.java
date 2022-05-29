package com.ssafy.happyhouse.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialServiceImpl implements SocialService {

	@Autowired
	MemberService memberService;

	@Override
	public Map<String, String> naverLogin(String code, String state) throws Exception {

		String clientId = "xPjKwK6tSXOB8XcW3zIz";

		String apiURL;
		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		apiURL += "client_id=" + clientId;
		apiURL += "&client_secret=" + "HmXEPSxUid";
		apiURL += "&code=" + code;
		apiURL += "&state=" + state;
		String access_token = "";
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;

			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}

			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();

			Map<String, String> tokens = new HashMap<String, String>();
			if (responseCode == 200) {
				JSONObject jObject = new JSONObject(res.toString());
				access_token = jObject.getString("access_token");
				tokens.put("access_token", access_token);
				return tokens;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public Map<String, String> naverUserInfo(String access_token) {
		String header = "Bearer " + access_token;
		String apiURL = "https://openapi.naver.com/v1/nid/me";

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("Authorization", header);
		String res = get(apiURL, requestHeaders);

		JSONObject jObject = new JSONObject(res.toString());
		JSONObject response = jObject.getJSONObject("response");
		String id = response.getString("id");
		String nickname = response.getString("nickname");
		String email = response.getString("email");

//		MemberDto memberDto = new MemberDto();
//		memberDto.setId(id);
//		memberDto.setName(nickname);
//		memberDto.setEmail(email);
//
//		try {
//			memberService.registerMember(memberDto);
//		} catch (Exception e) {
//
//		}

		Map<String, String> userInfo = new HashMap<String, String>();
		userInfo.put("id", id);
		userInfo.put("nickname", nickname);
		userInfo.put("email", email);

		return userInfo;
	}

	@Override
	public List<Map<String, String>> naverNews() throws Exception {

		String clientId = "xPjKwK6tSXOB8XcW3zIz";
		String clientSecret = "HmXEPSxUid";

		String text = null;
		String display = null;
		try {
			text = URLEncoder.encode("\"부동산\" 아파트", "UTF-8");
			display = "10";
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}

		String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + text + "&display=" + display;

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);

		String responseBody = get(apiURL, requestHeaders);

		return newsResult(responseBody);
	}

	@Override
	public List<Map<String, String>> naverLocalNews(String regionName) throws Exception {

		String clientId = "xPjKwK6tSXOB8XcW3zIz";
		String clientSecret = "HmXEPSxUid";

		String sidoName = generateRegionName(regionName)[0];
		String sigunguName = generateRegionName(regionName)[1];

		String inputText = "\"부동산\" " + sidoName + " \"" + sigunguName + "\"";

		String text;
		String display;

		try {
			text = URLEncoder.encode(inputText, "UTF-8");
			display = "10";
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}

		String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + text + "&display=" + display;

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);

		String responseBody = get(apiURL, requestHeaders);

		return newsResult(responseBody);
	}

	@Override
	public List<Map<String, String>> naverBlog() throws Exception {
		String clientId = "xPjKwK6tSXOB8XcW3zIz";
		String clientSecret = "HmXEPSxUid";

		String text = null;
		String display = null;
		try {
			text = URLEncoder.encode("\"부동산\" 아파트", "UTF-8");
			display = "10";
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}

		String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text + "&display=" + display;

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);

		String responseBody = get(apiURL, requestHeaders);

		return blogResult(responseBody);
	}

	private List<Map<String, String>> newsResult(String responseBody) {
		JSONObject jObject = new JSONObject(responseBody);
		JSONArray list = jObject.getJSONArray("items");
		List<Map<String, String>> newsResult = new ArrayList<Map<String, String>>();

		for (int i = 0; i < list.length(); i++) {
			JSONObject obj = list.getJSONObject(i);
			Map<String, String> item = new HashMap<String, String>();
			item.put("link", obj.getString("link"));
			item.put("title", obj.getString("title"));
			item.put("description", obj.getString("description"));
			item.put("pubDate", obj.getString("pubDate"));

			newsResult.add(item);
		}
		return newsResult;
	}

	private List<Map<String, String>> blogResult(String responseBody) {
		JSONObject jObject = new JSONObject(responseBody);
		JSONArray list = jObject.getJSONArray("items");
		List<Map<String, String>> newsResult = new ArrayList<Map<String, String>>();

		for (int i = 0; i < list.length(); i++) {
			JSONObject obj = list.getJSONObject(i);
			Map<String, String> item = new HashMap<String, String>();
			item.put("link", obj.getString("link"));
			item.put("title", obj.getString("title"));
			item.put("description", obj.getString("description"));
			item.put("pubDate", obj.getString("postdate"));

			newsResult.add(item);
		}
		return newsResult;
	}

	private String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				return readBody(con.getInputStream());
			} else {
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private String[] generateRegionName(String region) {

		String[] result = new String[2];

		String[] temp = region.split(" ");
		if (temp.length > 2) {
			result[1] = temp[1] + " " + temp[2];
		} else {
			result[1] = temp[1];
		}

		if (temp[0].equals("서울특별시")) {
			result[0] = "서울";
		} else if (temp[0].equals("부산광역시")) {
			result[0] = "부산";
		} else if (temp[0].equals("대구광역시")) {
			result[0] = "대구";
		} else if (temp[0].equals("대전광역시")) {
			result[0] = "대전";
		} else if (temp[0].equals("인천광역시")) {
			result[0] = "인천";
		} else if (temp[0].equals("광주광역시")) {
			result[0] = "광주";
		} else if (temp[0].equals("울산광역시")) {
			result[0] = "울산";
		} else if (temp[0].equals("세종특별자치시")) {
			result[0] = "세종";
		} else if (temp[0].equals("경기도")) {
			result[0] = "경기";
		} else if (temp[0].equals("강원도")) {
			result[0] = "강원";
		} else if (temp[0].equals("충청북도")) {
			result[0] = "충북";
		} else if (temp[0].equals("충청남도")) {
			result[0] = "충남";
		} else if (temp[0].equals("전라북도")) {
			result[0] = "전북";
		} else if (temp[0].equals("전라남도")) {
			result[0] = "전남";
		} else if (temp[0].equals("경상북도")) {
			result[0] = "경북";
		} else if (temp[0].equals("경상남도")) {
			result[0] = "경남";
		} else if (temp[0].equals("제주특별자치도")) {
			result[0] = "제주";
		}

		return result;

	}

	private HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}

}
