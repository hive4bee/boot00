package com.ansj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ansj.domain.BoardsDTO;
import com.ansj.domain.PageDTO;
import com.ansj.domain.StartDTO;
import com.ansj.service.BoardsService;

import lombok.extern.java.Log;

//--------------------------------------------------------------------------//
//게시판 컨트롤러 작성....
//--------------------------------------------------------------------------//
@Controller
@RequestMapping("/boards/")
@Log
public class BoardController {
	//--------------------------------------------------------------------------//
	//서비스 의존성 주입
	@Autowired
	BoardsService service;
	//--------------------------------------------------------------------------//
	
	//0. 최대 번호 얻기
	private int getMax() {
		int result = service.getMax();
		return result;
	}
	
	//--------------------------------------------------------------------------//
	//1. 리스트를 메서드를 호출한다. method: get	URL: /boards/list
	@GetMapping("/list")
	public void list(StartDTO st, Model model) {
		log.info("list() called....");
		log.info("startDTO: " + st);
		List<BoardsDTO> list = null;
		int total = 0;
		if(st.getKeyword() == null) {
			//해당 페이지에 대한 리스트를 불러온다.
			list = service.getBoardList(st);
			//페이지 처리를 위해 전체 게시글 수를 total에 할당
			total = service.getTotalBoardCount();
		}else {
			list = service.getBoardListWithKeyword(st);
			total = service.getTotalBoardCountWithKeyword(st.getKeyword());
		}
		//그리고 나서 페이지 처리
		PageDTO pageMaker = new PageDTO(st, total);
		log.info("PageDTO: " + pageMaker);
		model.addAttribute("list", list);
		model.addAttribute("pageMaker",pageMaker);
		model.addAttribute("keyword", st.getKeyword());
	}//end list() method....
	//--------------------------------------------------------------------------//
	
	//--------------------------------------------------------------------------//
	//
	//--------------------------------------------------------------------------//
	
	//--------------------------------------------------------------------------//
	//2. 글제목을 클릭했을 때 상세히 보기, method:get URL: /boards/readBoard?bno=
	@GetMapping("/read")
	public void readBoard(@RequestParam("bno") int bno, 
						  @RequestParam("page") int page, 
						  @RequestParam(value="keyword") String keyword, Model model) {
		log.info("===========read============");
		log.info("readBoard() called...");
		log.info("bno: " + bno);
		log.info("keyword: " + keyword);
		log.info("===========read============");
		BoardsDTO dto = service.readBoard(bno);
		model.addAttribute("dto", dto);
		model.addAttribute("page", page);
		if(keyword != null) {
			model.addAttribute("keyword", keyword);
		}
	}
	//--------------------------------------------------------------------------//
	
	//--------------------------------------------------------------------------//
	//3. 글 수정폼을 가져온다., method:get URL: /boards/modifyForm
	@GetMapping("/modifyForm")
	public void modifyForm(@RequestParam("bno") int bno, 
						   @RequestParam("page") int page, 
						   @RequestParam("keyword") String keyword, Model model) {
		log.info("bno: " + bno);
		BoardsDTO dto = service.readBoard(bno);
		model.addAttribute("dto", dto);
		model.addAttribute("page", page);
		model.addAttribute("keyword", keyword);
	}
	//--------------------------------------------------------------------------//
	
	//--------------------------------------------------------------------------//
	//4. 글 수정을 완료한다. method:post URL: /boards/modifyPro
	@PostMapping("/modifyPro")
	public String modifyPro(BoardsDTO boardsDTO, 
							@RequestParam("keyword") String keyword, @RequestParam("page") int page) {
		log.info("boardsDTO : " + boardsDTO);
		service.updateBoard(boardsDTO);
		
		return "redirect:/boards/list?page="+page+"&keyword="+keyword;
	}
	
	//--------------------------------------------------------------------------//
	
	//--------------------------------------------------------------------------//
	//5. 글 작성 폼. method:get URL:/boards/writeForm
	@GetMapping("/writeForm")
	public void writeForm() {
		
	}
	//--------------------------------------------------------------------------//
	
	//5-2. 답글 작성 폼 method:get URL:/boards/replyForm
	@GetMapping("/replyForm")
	public void replyForm(BoardsDTO boardsDTO, Model model, String page) {
		log.info("=======================");
		log.info("boardsDTO: " + boardsDTO);
		log.info("=======================");
		model.addAttribute("dto", boardsDTO);
		model.addAttribute("page", page);
	}
	
	//--------------------------------------------------------------------------//
	//6. 글 작성 처리 method:post URL:/boards/writePro
	@PostMapping("/writePro")
	public String writePro(BoardsDTO boardsDTO) {
		//int max = getMax();
		//boardsDTO.setBno(max);boardsDTO.setBgroup(max);
		service.writeBoard(boardsDTO);
		return "redirect:/boards/list";
	}
	
	//6-2. 답글 작성 method:post URL:/boards/replyPro
	@PostMapping("/replyPro")
	public String replyPro(BoardsDTO boardsDTO, @RequestParam("page") String page) {
		log.info("boardsDTO: " + boardsDTO);
		log.info("page: " + page);
		service.replyBoard(boardsDTO);
		return "redirect:/boards/list?page="+page;
	}
	
	//--------------------------------------------------------------------------//
	//7. 삭제 처리 
	@GetMapping("/deletePro")
	public String deletePro(@RequestParam("bno") int bno, 
							@RequestParam("page") int page,
							@RequestParam("keyword") String keyword) {
		service.deleteBoard(bno);
		return "redirect:/boards/list?page="+page+"&keyword="+keyword;
	}
	//--------------------------------------------------------------------------//
	
	//--------------------------------------------------------------------------//
	//8. 최상위 글을 삭제
	@GetMapping("/deleteParentPro")
	public String deleteParentPro(@RequestParam("bgroup") int bgroup, @RequestParam("page") int page) {
		service.deleteParentBoard(bgroup);
		return "redirect:/boards/list?page="+page;
	}
	//--------------------------------------------------------------------------//
	//--------------------------------------------------------------------------//
	//--------------------------------------------------------------------------//
	//--------------------------------------------------------------------------//
	//--------------------------------------------------------------------------//
	//--------------------------------------------------------------------------//
	//--------------------------------------------------------------------------//
	//--------------------------------------------------------------------------//
	//--------------------------------------------------------------------------//
}
