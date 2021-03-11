package org.zerock.board;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.board.config.BoardConfig;
import org.zerock.board.mapper.BoardMapper;
import org.zerock.board.service.BoardService;
import org.zerock.common.config.CommonConfig;
import org.zerock.common.dto.PageDTO;
import org.zerock.time.config.TimeConfig;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfig.class, TimeConfig.class, BoardConfig.class})
@Log4j
public class BoardTests {

	@Autowired
	BoardMapper mapper;
	
	@Autowired
	BoardService service;
	
	@Test
	public void testList() {
		
		service.getPageList(PageDTO.builder().page(1).type("tcw").keyword("10").build())
		.forEach(dto -> log.info(dto));
	}
	
	@Test
	public void testList2() {
		service.getPageList(PageDTO.builder().page(2).build())
		.forEach(dto -> log.info(dto));
	}
//	@Test
//	public void testEx1() {
//		
//		PageDTO dto = new PageDTO();
//		
//		mapper.ex1(dto).forEach(board -> log.info(board));
//	}
}
