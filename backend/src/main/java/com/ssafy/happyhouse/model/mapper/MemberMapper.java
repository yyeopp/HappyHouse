package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;

import com.ssafy.happyhouse.model.dto.MemberDto;

public interface MemberMapper {

	void registMember(MemberDto memberDto) throws SQLException; // 회원가입

	MemberDto getMember(MemberDto memberDto) throws SQLException; // 로그인

	MemberDto getInfo(String id) throws SQLException;

	String findPassword(MemberDto memberDto) throws SQLException; // 비밀번호 찾기

	void updateMember(MemberDto memberDto) throws SQLException; // 회원정보 수정

	void deleteMember(String id) throws SQLException; // 회원 탈퇴

	int idCheck(String id) throws SQLException;
}
