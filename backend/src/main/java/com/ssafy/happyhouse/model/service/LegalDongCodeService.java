package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.BaseAddress;
import com.ssafy.happyhouse.model.dto.DongCodeDto;
import com.ssafy.happyhouse.model.dto.GugunCodeDto;
import com.ssafy.happyhouse.model.dto.SidoCodeDto;

public interface LegalDongCodeService {
	
	public List<SidoCodeDto> searchSidoList() throws Exception;
	public List<GugunCodeDto> searchGugunList(String sidoCode) throws Exception;
	public List<DongCodeDto> searchDongList(String gugunCode) throws Exception;
	public BaseAddress searchCenter(String gugunCode) throws Exception;
}
