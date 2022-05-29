package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.service.NoticeService;

@RestController
@RequestMapping("/notice")
@CrossOrigin("*")
public class NoticeController {

	@Autowired
	NoticeService noticeService;

	@GetMapping("/list")
	public ResponseEntity<?> getNoticeList() {
		try {
			List<BoardDto> list = noticeService.getNoticeList();
			return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{boardId}")
	public ResponseEntity<?> getNotice(@PathVariable int boardId) {
		try {

			BoardDto boardDto = noticeService.getNotice(boardId);
			return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			exceptionHandling(e);
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/write")
	public ResponseEntity<?> writeNotice(@RequestBody BoardDto boardDto) {
		try {
			System.out.println(boardDto);
			noticeService.writeNotice(boardDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			exceptionHandling(e);
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/delete/{boardId}")
	public ResponseEntity<?> deleteNotice(@PathVariable int boardId) {
		try {
			noticeService.deleteNotice(boardId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			exceptionHandling(e);
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
