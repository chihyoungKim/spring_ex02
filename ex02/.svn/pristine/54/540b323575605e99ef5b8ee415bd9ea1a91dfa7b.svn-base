package com.wdkim.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wdkim.domain.CriteriaReply;
import com.wdkim.domain.ReplyVO;
import com.wdkim.mapper.BoardMapper;
import com.wdkim.mapper.ReplyMapper;

import lombok.AllArgsConstructor;
@Service @AllArgsConstructor
public class ReplyServiceImpl implements ReplyService{
	
	private BoardMapper boardMapper;
	private ReplyMapper mapper;
	
	@Transactional
	@Override
	public int register(ReplyVO vo) {
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		return mapper.insertSelectKey(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		return mapper.update(vo);
	}

	@Transactional
	@Override
	public int remove(Long rno) {
		boardMapper.updateReplyCnt(mapper.read(rno).getBno(), -1);
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Long bno, CriteriaReply cri) {
		return mapper.getListWithPaging(bno, cri);
	}
	
}
