package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.BoardDto;

public interface NoticeService {

	List<BoardDto> getNoticeList() throws Exception; // 공지사항 전체 출력

	BoardDto getNotice(int boardId) throws Exception; // 공지사항 1개 출력

	void writeNotice(BoardDto boardDto) throws Exception; // 공지사항 작성


	void deleteNotice(int no) throws Exception; // 공지사항 삭제

}
