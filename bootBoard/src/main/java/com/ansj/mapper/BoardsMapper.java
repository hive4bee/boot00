package com.ansj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ansj.domain.BoardsDTO;
import com.ansj.domain.StartDTO;

@Repository
@Mapper
public interface BoardsMapper {
	public void writeBoard(BoardsDTO dto) ;

	public List<BoardsDTO> getBoardList(StartDTO st);

	public int getTotalBoardCount();

	public BoardsDTO readBoard(int bno);
	
	public void updateBoard(BoardsDTO dto);
	
	public void deleteBoard(int bno);

	public List<BoardsDTO> getBoardListWithKeyword(StartDTO st);

	public int getTotalBoardCountWithKeyword(String keyword);

	public int getMax();

	public void replyBoard(BoardsDTO boardsDTO);

	public void deleteParentBoard(int bgroup);
}
