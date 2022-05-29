package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.BaseAddress;
import com.ssafy.happyhouse.model.dto.DongCodeDto;
import com.ssafy.happyhouse.model.dto.GugunCodeDto;
import com.ssafy.happyhouse.model.dto.SidoCodeDto;
import com.ssafy.happyhouse.model.mapper.LegalDongCodeMapper;

@Service
public class LegalDongCodeServiceImpl implements LegalDongCodeService {
	
	@Autowired
	private LegalDongCodeMapper legalDongCodeMapper;

	@Override
	public List<SidoCodeDto> searchSidoList() throws Exception {
		return legalDongCodeMapper.searchSidoList();
	}

	@Override
	public List<GugunCodeDto> searchGugunList(String sidoCode) throws Exception {
		return legalDongCodeMapper.searchGugunList(sidoCode);
	}

	@Override
	public List<DongCodeDto> searchDongList(String gugunCode) throws Exception {
		return legalDongCodeMapper.searchDongList(gugunCode);
	}

	@Override
	public BaseAddress searchCenter(String gugunCode) throws Exception {
		return legalDongCodeMapper.searchCenter(gugunCode);
	}

}
