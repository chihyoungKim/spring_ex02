package com.wdkim.service;

import java.util.List;

import com.wdkim.domain.BoardAttachVO;
import com.wdkim.domain.BoardVO;
import com.wdkim.domain.Criteria;

public interface BoardService {
	int register(BoardVO boardVO);
	
	BoardVO get(Long bno);
	
	boolean modify(BoardVO boardVO);
	
	boolean remove(Long bno);
	
	List<BoardVO> getList(Criteria cri);

	int getTotalCount(Criteria cri);
	
	List<BoardAttachVO> getAttachs(Long bno);

}
