package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.dto.CommentDto;

public interface QnaService {

	List<BoardDto> getQnAList() throws Exception;
	
	List<BoardDto> getQnaListById(String memberId) throws Exception;

	BoardDto getBoard(int boardId) throws Exception;
	
	CommentDto getComment(int boardId) throws Exception;

	void writeBoard(BoardDto boardDto) throws Exception;

	void writeComment(CommentDto commentDto) throws Exception;

	void updateBoard(BoardDto boardDto) throws Exception;

	void deleteBoard(BoardDto boardDto) throws Exception;
	
	void deleteQna(int boardId) throws Exception;
	
	void deleteComment(int commentId) throws Exception;
	

}
