package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dto.MemberDto;

public interface MemberService {

	void registerMember(MemberDto memberDto) throws Exception;

	MemberDto getMember(MemberDto memberDto) throws Exception;

	MemberDto getInfo(String id) throws Exception;

	String findPassword(MemberDto memberDto) throws Exception; // 비밀번호 찾기
	
	
	
	void sendEmail(String email, String password) throws Exception;

	void updateMember(MemberDto memberDto) throws Exception; // 회원정보 수정

	void deleteMember(String id) throws Exception; // 회원 탈퇴

	int idCheck(String id) throws Exception; // 아이디 중복체크
}
