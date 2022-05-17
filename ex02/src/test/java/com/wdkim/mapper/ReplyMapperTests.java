package com.wdkim.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wdkim.domain.CriteriaReply;
import com.wdkim.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	@Autowired @Setter
	private ReplyMapper mapper;
	
	@Test
	public void testExist() {
		assertNotNull(mapper);
		log.info(mapper);
	}
	
	@Test
	public void testInsert() {
		ReplyVO vo = new ReplyVO();
		vo.setBno(174141L);
		vo.setReply("댓글내용");
		vo.setReplyer("작성자");
//		174141
//		174124
//		174123
//		174122
//		174121
		mapper.insert(vo);
	}
	
	@Test
	public void testCreate() {
		Long[] bnoArr = {174141L, 174124L, 174123L, 174122L, 174121L};
		IntStream.rangeClosed(1, 100).forEach(r -> {
			ReplyVO vo = new ReplyVO();
			vo.setBno(bnoArr[r%5]);
			vo.setReply("댓글내용" + r);
			vo.setReplyer("작성자" + r);
			
			mapper.insert(vo);
		});
	}
	
	@Test
	public void testRead() {
		
		ReplyVO replyVO = mapper.read(1L);
		log.info(replyVO);
	}
	
	@Test
	public void testDelete() {
		mapper.delete(2L);
	}
	
	@Test
	public void testUpdate() {
		ReplyVO vo = mapper.read(1L);
		vo.setReply("수정된 내용");
		
		
		mapper.update(vo);
	}
	
	@Test
	public void testList() {
		CriteriaReply cri = new CriteriaReply();
		cri.setLastRno(100L);
		
		mapper.getListWithPaging(174141L, cri);
		
	}
	
}
