package com.wdkim.controller;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.wdkim.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration // controller test라는 것을 알려주는 어노테이션
@Log4j
public class BoardControllerTests {
	@Setter @Autowired
	private WebApplicationContext ctx;
//	private BoardController boardController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testList() throws Exception {
		ModelAndView mav = mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
				.param("pageNum", "4").param("amount", "15")
				.param("type", "TCW").param("keyword", "테스트")
				)
		.andReturn()
		.getModelAndView();
		
		Map<String, Object> map = mav.getModel();
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) map.get("boards");
		list.forEach(log::info);
	}
	
	@Test
	public void testRegister() throws Exception {
		ModelAndView mav = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
					.param("title", "controller title test")
					.param("content", "controller content test")
					.param("writer", "user00"))
				.andReturn()
				.getModelAndView();
		log.info(mav.getViewName());
	}
	
	@Test
	public void testGet() throws Exception {
		ModelAndView mav = mockMvc.perform(MockMvcRequestBuilders.get("/board/get")
				.param("bno", "6"))
		.andReturn()
		.getModelAndView();
		
		Map<String, Object> map = mav.getModel();
		Object board = map.get("board");
		log.info(board);
	}
	
	@Test
	public void testModify() throws Exception {
		ModelAndView mav = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
					.param("title", "modify controller title test")
					.param("content", "modify controller content test")
					.param("writer", "user00")
					.param("bno", "6"))
				.andReturn()
				.getModelAndView();
		log.info(mav.getViewName());
	}
	
	@Test
	public void testRemove() throws Exception {
		ModelAndView mav = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
					.param("bno", "6"))
				.andReturn()
				.getModelAndView();
		log.info(mav.getViewName());
	}
	
	
}
