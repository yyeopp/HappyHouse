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
import com.ssafy.happyhouse.model.dto.CommentDto;
import com.ssafy.happyhouse.model.service.QnaService;

@RestController
@RequestMapping("/qna")
@CrossOrigin("*")
public class QnaController {

	@Autowired
	QnaService qnaService;

	@PostMapping("/write")
	public ResponseEntity<?> writeBoard(@RequestBody BoardDto boardDto) {
		try {
			System.out.println(boardDto);
			qnaService.writeBoard(boardDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/write/comment")
	public ResponseEntity<?> writeComment(@RequestBody CommentDto commentDto) {
		try {
			System.out.println(commentDto);
			qnaService.writeComment(commentDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping("/list")
	public ResponseEntity<?> getQnAList() {
		try {
			List<BoardDto> list = qnaService.getQnAList();
			return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/list/{memberId}")
	public ResponseEntity<?> getQnaListById(@PathVariable String memberId) {
		try {
			List<BoardDto> list = qnaService.getQnaListById(memberId);
			return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/delete/{boardId}")
	public ResponseEntity<?> deleteQna(@PathVariable int boardId) {
		try {
			CommentDto comment = qnaService.getComment(boardId);
			if (comment != null) {
				qnaService.deleteComment(comment.getCommentId());
			}
			qnaService.deleteQna(boardId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/delete/comment/{commentId}")
	public ResponseEntity<?> deleteComment(@PathVariable int commentId) {
		try {
			qnaService.deleteComment(commentId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
