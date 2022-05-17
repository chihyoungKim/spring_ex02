package com.wdkim.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wdkim.domain.CriteriaReply;
import com.wdkim.domain.ReplyVO;

public interface ReplyMapper {
	int insert(ReplyVO vo);
	
	int insertSelectKey(ReplyVO vo);
	
	ReplyVO read(Long rno);
	
	int delete(Long rno);
	
	int deleteAll(Long bno);
	
	int update(ReplyVO vo);
	
	List<ReplyVO> getListWithPaging(@Param("bno") Long bno, @Param("cri") CriteriaReply cri);
	//param어노테이션은 기본값이 없으므로 반드시 지정해줘야한다.
	
}
