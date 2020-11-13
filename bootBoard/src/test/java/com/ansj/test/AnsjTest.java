package com.ansj.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ansj.domain.BoardsDTO;
import com.ansj.domain.StartDTO;
import com.ansj.mapper.BoardsMapper;

import lombok.extern.java.Log;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class AnsjTest {
	@Autowired
	BoardsMapper mapper;
	//--------------------------------------------------------------------------//
	//1. 삽입 테스트 (insert)
	@Test
	public void insertTest() {
		BoardsDTO dto = null;
		for(int i = 1; i<=300; i++) {
			dto = new BoardsDTO();
			dto.setBsubject("just test for insert and pagination"+i);
			dto.setBcontent("Content Sample..."+i);
			dto.setMid("user0"+(i%10));
			
			mapper.writeBoard(dto);
		}
	}
	//--------------------------------------------------------------------------//
	
	//--------------------------------------------------------------------------//
	//2. 전체 글 수 파악 테스트 SELECT COUNT(*)
//	@Test
//	public void totalTest() {
//		int total = mapper.getTotalBoardCount();
//		log.info("전체 글 수 : " + total);
//	}
	//--------------------------------------------------------------------------//
	
	//--------------------------------------------------------------------------//
	//3. 리스트 호출 테스트(SELECT) 
//	@Test
//	public void listTest() {
//		List<BoardsDTO> list = null;
//		StartDTO st = new StartDTO();
//		list = mapper.getBoardList(st);
//		list.forEach(board -> log.info(board.toString()));
//	}
	//--------------------------------------------------------------------------//
	
	//--------------------------------------------------------------------------//
	//4. 글 조회하기(SELECT)
//	@Test
//	public void readBoardTest() {
//		BoardsDTO dto = null;
//		dto=mapper.readBoard(100);
//		log.info("조회하고자 하는 글: " + dto);
//	}
	//--------------------------------------------------------------------------//
	
}
