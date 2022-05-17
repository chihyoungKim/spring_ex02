package com.wdkim.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wdkim.domain.BoardAttachVO;
import com.wdkim.domain.BoardVO;
import com.wdkim.domain.Criteria;
import com.wdkim.domain.PageDTO;
import com.wdkim.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("board/*")
@AllArgsConstructor
@Log4j
public class BoardController {
	private final BoardService boardService; 
	
	
	@GetMapping("list")
	public String list(Model model, Criteria cri) {
		model.addAttribute("boards", boardService.getList(cri));
		model.addAttribute("page", new PageDTO(boardService.getTotalCount(cri), cri));
		return "/board/list";
	}
	
	@GetMapping("list2") @ResponseBody
	public List<BoardVO> list(Criteria cri) {
		return boardService.getList(cri);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("register")
	public void register(@ModelAttribute("cri") Criteria cri) {
	}
	
	@PreAuthorize("isAuthenticated() and principal.username == #boardVO.writer")
	@PostMapping("register")
	public String register(BoardVO boardVO, RedirectAttributes rttr, Criteria cri) {
		log.info(boardVO);
		
		boardService.register(boardVO);
		rttr.addFlashAttribute("result", boardVO.getBno());
		cri.setPageNum(1);
		return "redirect:/board/list" + cri.getParams();
	}
	
	@GetMapping({"get", "modify"})
	public void get(Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		model.addAttribute("board", boardService.get(bno));
	}
	
	@PreAuthorize("isAuthenticated() and principal.username == #boardVO.writer")
	@PostMapping("modify")
	public String modify(BoardVO boardVO, RedirectAttributes rttr, Criteria cri) {
		log.info(cri);
		log.info(boardVO);
		if(boardService.modify(boardVO)) {
			rttr.addFlashAttribute("result", "수정");
		}
//		rttr.addAllAttributes(new ObjectMapper().convertValue(cri, Map.class));
		return "redirect:/board/list" + cri.getParams();
	}
	
	@PreAuthorize("isAuthenticated() and principal.username == #writer")
	@PostMapping("remove")
	public String remove(Long bno, RedirectAttributes rttr, Criteria cri, String writer, UploadController uc) {
		log.info(cri);
		List<BoardAttachVO> attachs = boardService.getAttachs(bno);
		if(boardService.remove(bno)) {
			rttr.addFlashAttribute("result", "삭제");
//			forEach(attach -> uc.deleteFile(attach));
			if(attachs != null) attachs.forEach(uc::deleteFile);
		}
		return "redirect:/board/list" + cri.getParams();
	}
	
	@GetMapping("attachs") @ResponseBody
	public List<BoardAttachVO> getAttachs(Long bno) {
		log.info(bno);
		return boardService.getAttachs(bno);
	}
	
}
