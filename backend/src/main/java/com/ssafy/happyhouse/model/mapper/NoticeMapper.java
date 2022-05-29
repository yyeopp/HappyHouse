package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.BoardDto;

public interface NoticeMapper {

	List<BoardDto> getNoticeList() throws SQLException; // 공지사항 전체 출력

	BoardDto getNotice(int boardId) throws SQLException; // 공지사항 1개 출력
	
	void hitNotice(int boardId) throws SQLException;

	void writeNotice(BoardDto boardDto) throws SQLException; // 공지사항 작성


	void deleteNotice(int boardId) throws SQLException; // 공지사항 삭제

}
