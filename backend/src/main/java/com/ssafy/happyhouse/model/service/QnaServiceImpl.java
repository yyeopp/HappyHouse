package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.dto.CommentDto;
import com.ssafy.happyhouse.model.mapper.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService {

	@Autowired
	QnaMapper qnaMapper;

	@Override
	public List<BoardDto> getQnAList() throws Exception {
		return qnaMapper.getQnAList();
	}

	@Override
	public BoardDto getBoard(int boardId) throws Exception {
		return qnaMapper.getBoard(boardId);
	}

	@Override
	@Transactional
	public void writeBoard(BoardDto boardDto) throws Exception {
		qnaMapper.writeBoard(boardDto);
	}

	@Override
	@Transactional
	public void updateBoard(BoardDto boardDto) throws Exception {
		qnaMapper.updateBoard(boardDto);
	}

	@Override
	@Transactional
	public void deleteBoard(BoardDto boardDto) throws Exception {
		qnaMapper.deleteBoard(boardDto);
	}

	@Override
	@Transactional
	public void writeComment(CommentDto commentDto) throws Exception {
		qnaMapper.writeComment(commentDto);
	}

	@Override
	@Transactional
	public void deleteQna(int boardId) throws Exception {
		qnaMapper.deleteQna(boardId);
	}

	@Override
	@Transactional
	public void deleteComment(int commentId) throws Exception {
		qnaMapper.deleteComment(commentId);
	}

	@Override
	public CommentDto getComment(int boardId) throws Exception {
		return qnaMapper.getComment(boardId);
	}

	@Override
	public List<BoardDto> getQnaListById(String memberId) throws Exception {
		return qnaMapper.getQnaListById(memberId);
	}

}
