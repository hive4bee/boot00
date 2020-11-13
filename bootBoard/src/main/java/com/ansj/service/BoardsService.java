package com.ansj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ansj.domain.BoardsDTO;
import com.ansj.domain.StartDTO;
import com.ansj.mapper.BoardsMapper;

@Service
public class BoardsService {
	@Autowired
	BoardsMapper mapper;
	
	public void writeBoard(BoardsDTO dto) {
		mapper.writeBoard(dto);
	}

	public List<BoardsDTO> getBoardList(StartDTO st) {
		return mapper.getBoardList(st);
	}

	public int getTotalBoardCount() {
		return mapper.getTotalBoardCount();
	}

	public BoardsDTO readBoard(int bno) {
		return mapper.readBoard(bno);
	}
	
	public void updateBoard(BoardsDTO dto) {
		mapper.updateBoard(dto);
	}
	
	public void deleteBoard(int bno) {
		mapper.deleteBoard(bno);
	}

	public List<BoardsDTO> getBoardListWithKeyword(StartDTO st) {
		return mapper.getBoardListWithKeyword(st);
	}

	public int getTotalBoardCountWithKeyword(String keyword) {
		return mapper.getTotalBoardCountWithKeyword(keyword);
	}

	public int getMax() {
		return mapper.getMax();
	}

	public void replyBoard(BoardsDTO boardsDTO) {
		mapper.replyBoard(boardsDTO);
	}

	public void deleteParentBoard(int bgroup) {
		mapper.deleteParentBoard(bgroup);
	}
}
