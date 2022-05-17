package com.wdkim.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wdkim.domain.BoardVO;
import com.wdkim.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	@Setter @Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testExist() {
		assertNotNull(boardMapper);
	}
	
	@Test
	public void testGetList() {
		List<BoardVO> result = boardMapper.getList();
		assertNotNull(result);
		result.forEach(log::info);
	}
	
	@Test
	public void testGetListWithPaging() {
		Criteria cri = new Criteria();
		cri.setPageNum(1);
		cri.setAmount(10);
//		cri.setType("TW");
//		cri.setKeyword("테스트");
		log.info(cri);
		
		List<BoardVO> result = boardMapper.getListWithPaging(cri);
		//rn을 바인딩할 property가 없기 때문에 콘솔에 unread로 출력된다.
		assertNotNull(result);
		result.forEach(log::info);
	}
	
	@Test
	public void testRead() {
		BoardVO result = boardMapper.read(4L);
		assertNotNull(result);
		log.info(result);
	}
	
	@Test
	public void testInsert() {
		
		//init
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("mapper test title");
		boardVO.setContent("mapper test content");
		boardVO.setWriter("user00");
		log.info(boardVO);
		
		//expect
		int exp = 1;
		
		//result
		int result = boardMapper.insert(boardVO);
		
		assertEquals("게시글 추가", exp, result);
		log.info(boardVO);
	}
	
	@Test
	public void testInsertSelectKey() {
		
		//init
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("mapper test title selectKey");
		boardVO.setContent("mapper test content selectKey");
		boardVO.setWriter("user00");
		log.info(boardVO);
		
		//expect
		int exp = 1;
		
		//result
		int result = boardMapper.insertSelectKey(boardVO);
		
		assertEquals("게시글 추가", exp, result);
		log.info(boardVO);
	}
	
	@Test
	public void testDelete() {
		Long bno = 8L;
		
		log.info(bno);
		
		int exp = 1;
		
		int result = boardMapper.delete(bno);
		
		assertEquals("게시글 삭제", exp, result);
	}
	
	@Test
	public void testUpdate() {
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("update test");
		boardVO.setContent("update test");
		boardVO.setBno(8L);
		log.info(boardVO);
		
		int exp = 1;
		int result = boardMapper.update(boardVO);
		
		assertEquals("게시글 수정", exp, result);
		
	}
	
	@Test
	public void testGetTotalCount() {
		Criteria cri = new Criteria();
		cri.setPageNum(4);
		cri.setAmount(3);
		cri.setType("TW");
		cri.setKeyword("테스트");
		log.info(boardMapper.getTotalCount(cri));
	}
	
	@Test
	public void testGetLisXtDynamicTest() {
		Criteria cri = new Criteria();
		//제목 & 내용 검색
//		cri.setType("TC");
		log.info(Arrays.toString(cri.getTypeArr())); // 후에 foreach로 탐색하게할것이다.
		
		
//		cri.setType("T");//제목 테스트
		cri.setKeyword("테스트");
		boardMapper.getListDynamicTest(cri);
	}
	
	@Test
	public void testStringJoin() {
		String[] arr = {"1", "2", "3", "4"};
		System.out.println(String.join("", arr));
		
		
	}
	
}
