package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeMapper noticeMapper;

	@Override
	public List<BoardDto> getNoticeList() throws Exception {
		return noticeMapper.getNoticeList();
	}

	@Override
	public BoardDto getNotice(int boardId) throws Exception {
		noticeMapper.hitNotice(boardId);
		return noticeMapper.getNotice(boardId);
	}

	@Override
	@Transactional
	public void writeNotice(BoardDto boardDto) throws Exception {
		noticeMapper.writeNotice(boardDto);
	}

	@Override
	public void deleteNotice(int boardId) throws Exception {
		noticeMapper.deleteNotice(boardId);
	}

}
