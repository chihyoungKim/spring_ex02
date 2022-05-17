package com.wdkim.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wdkim.domain.BoardAttachVO;
import com.wdkim.domain.BoardVO;
import com.wdkim.domain.Criteria;
import com.wdkim.mapper.BoardAttachMapper;
import com.wdkim.mapper.BoardMapper;
import com.wdkim.mapper.ReplyMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service @AllArgsConstructor @Log4j
public class BoardServiceImpl implements BoardService{
	private BoardMapper boardMapper;
	private BoardAttachMapper boardAttachMapper;
	private ReplyMapper replyMapper;
	
	@Transactional
	public int register(BoardVO boardVO) {
		log.info("register(" + boardVO + ")");
		// boardVO.bno = null;
		int result = boardMapper.insertSelectKey(boardVO);
		//여긴 널아님
		
//		for(BoardAttachVO attach : boardVO.getAttachs()) {
//			attach.setBno(boardVO.getBno());
//			boardAttachMapper.insert(attach);
//		}
		
		boardVO.getAttachs().forEach(attach->{
			attach.setBno(boardVO.getBno());
			boardAttachMapper.insert(attach);
		});
		// 포이치는 컨슈머가 오므로 입력1출력0
		// 람다로하면 외부 변수 값을 변경할 수가 없다.
		return result;
	}

	@Override
	public BoardVO get(Long bno) {
		return boardMapper.read(bno);
	}

	@Override
	@Transactional
	public boolean modify(BoardVO boardVO) {
		//게시글의 첨부파일 일괄삭제
		boardAttachMapper.deleteAll(boardVO.getBno());
		// 첨부파일 재등록
		boardVO.getAttachs().forEach(attach->{
			attach.setBno(boardVO.getBno());
			boardAttachMapper.insert(attach);
		});
		
		return boardMapper.update(boardVO) > 0;
	}

	@Override @Transactional
	public boolean remove(Long bno) {
		replyMapper.deleteAll(bno);
		boardAttachMapper.deleteAll(bno);
		return boardMapper.delete(bno) > 0;
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		return boardMapper.getListWithPaging(cri);
	}

	@Override
	public int getTotalCount(Criteria cri) {
		return boardMapper.getTotalCount(cri);
	}

	@Override
	public List<BoardAttachVO> getAttachs(Long bno) {
		return boardAttachMapper.findBy(bno);
	}
	
}
