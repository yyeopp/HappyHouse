package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.dto.CommentDto;

@Mapper
public interface QnaMapper {
	
	List<BoardDto> getQnAList() throws Exception;

	List<BoardDto> getQnaListById(String memberId) throws Exception;

	BoardDto getBoard(int boardId) throws Exception;

	void writeBoard(BoardDto boardDto) throws Exception;

	void updateBoard(BoardDto boardDto) throws Exception;

	void deleteBoard(BoardDto boardDto) throws Exception;

	void writeComment(CommentDto commentDto) throws Exception;

	void deleteQna(int boardId) throws Exception;

	void deleteComment(int commentId) throws Exception;

	CommentDto getComment(int boardId) throws Exception;
}
