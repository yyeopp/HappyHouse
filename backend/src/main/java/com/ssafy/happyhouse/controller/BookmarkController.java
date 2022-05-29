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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.HouseList;
import com.ssafy.happyhouse.model.service.BookmarkService;
import com.ssafy.happyhouse.model.service.HouseService;

@RestController
@RequestMapping("/bookmark")
@CrossOrigin("*")
public class BookmarkController {

	@Autowired
	BookmarkService bookmarkService;

	@Autowired
	HouseService houseService;

	@GetMapping("/list/{memberId}")
	public ResponseEntity<?> bookmarkList(@PathVariable String memberId) {
		try {
			List<HouseList> list = bookmarkService.bookmarkList(memberId);
			if (list != null && !list.isEmpty()) {
				return new ResponseEntity<List<HouseList>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}

	}

	@PostMapping("/insert/{memberId}/{aptCode}")
	public ResponseEntity<?> insertBookmark(@PathVariable("memberId") String memberId,
			@PathVariable("aptCode") long aptCode) {
		try {
			bookmarkService.insertBookmark(memberId, aptCode);
			houseService.addHouseBookmark(aptCode, memberId);
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/delete/{memberId}/{aptCode}")
	public ResponseEntity<?> deleteBookmark(@PathVariable("memberId") String memberId,
			@PathVariable("aptCode") long aptCode) {
		try {
			bookmarkService.deleteBookmark(memberId, aptCode);
			houseService.removeHouseBookmark(aptCode, memberId);
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
