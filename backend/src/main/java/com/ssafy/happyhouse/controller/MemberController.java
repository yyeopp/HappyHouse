package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.dto.CommentDto;
import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.service.BookmarkService;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.MemberService;
import com.ssafy.happyhouse.model.service.QnaService;
import com.ssafy.happyhouse.model.service.SocialService;

@Controller
@CrossOrigin("*")
@RequestMapping("/member")
public class MemberController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	MemberService memberService;

	@Autowired
	BookmarkService bookmarkService;

	@Autowired
	QnaService qnaService;

	@Autowired
	SocialService socialService;

	@Autowired
	private JwtServiceImpl jwtService;

	@GetMapping("/login/naver")
	public String naverLogin(@RequestParam(value = "code") String code, @RequestParam(value = "state") String state) {
		try {
			Map<String, String> tokens = socialService.naverLogin(code, state);
			String access_token = tokens.get("access_token");
			return "redirect:http://localhost:8080/account/waiting?access_token=" + access_token;

		} catch (Exception e) {
			e.printStackTrace();
			return "/";
		}
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto userInfo = memberService.getMember(memberDto);
			if (userInfo != null) {
				String accesToken = jwtService.createAccessToken("userid", userInfo.getId(), "access-token");
				String refreshToken = jwtService.createRefreshToken("userid", userInfo.getId(), "refresh-token");
				resultMap.put("access-token", accesToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/info/naver")
	public ResponseEntity<?> getNaverInfo(HttpServletRequest request) {

		String accessToken = request.getHeader("access-token");
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			Map<String, String> userInfo = socialService.naverUserInfo(accessToken);
			result.put("userInfo", userInfo);
			result.put("message", "success");

			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("userid") String userid,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String accesToken = request.getHeader("access-token");

		// refresh 토큰을 받았고 그 토큰이 유효하다면 access token을 재발급한다
		if (request.getHeader("refresh-token") != null && jwtService.isUsable(request.getHeader("refresh-token"))) {

			accesToken = jwtService.createAccessToken("userid", userid, "accessToken");
			String refreshToken = jwtService.createRefreshToken("userid", userid, "refreshToken");
			resultMap.put("access-token", accesToken);
			resultMap.put("refresh-token", refreshToken);
		}

		if (jwtService.isUsable(accesToken)) {
			try {
				MemberDto memberDto = memberService.getInfo(userid);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/regist")
	public ResponseEntity<?> regist(@RequestBody MemberDto memberDto) {
		try {
			memberService.registerMember(memberDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/update")
	public ResponseEntity<?> updateMember(@RequestBody MemberDto memberDto) {
		try {
			memberService.updateMember(memberDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/delete/{memberId}")
	public ResponseEntity<?> deleteMember(@PathVariable String memberId) {
		try {
			bookmarkService.deleteBookmarkById(memberId);

			List<BoardDto> qnaList = qnaService.getQnaListById(memberId);
			for (BoardDto qna : qnaList) {
				CommentDto comment = qnaService.getComment(qna.getBoardId());
				if (comment != null) {
					qnaService.deleteComment(comment.getCommentId());
				}
				qnaService.deleteQna(qna.getBoardId());

			}
			memberService.deleteMember(memberId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/idcheck/{id}")
	public ResponseEntity<?> idcheck(@PathVariable String id) {
		int cnt = 1;
		try {
			cnt = memberService.idCheck(id);
			return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(cnt, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/findpwd")
	public ResponseEntity<?> findPassword(@RequestBody MemberDto memberDto) {
		try {
			String pw = memberService.findPassword(memberDto);
			if (pw != null) {
				memberService.sendEmail(memberDto.getEmail(), pw);
				return new ResponseEntity<Integer>(1, HttpStatus.OK);
			}
			return new ResponseEntity<Integer>(2, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(3, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

}
