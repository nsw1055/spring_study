package org.zerock.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.board.config.BoardConfig;
import org.zerock.board.mapper.BoardMapper;
import org.zerock.common.config.CommonConfig;
import org.zerock.time.config.TimeConfig;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfig.class, TimeConfig.class, BoardConfig.class})
@Log4j
public class BoardTests {

	@Autowired
	BoardMapper mapper;
	
	@Test
	public void testList() {
		
		mapper.getList().forEach(b -> log.info(b));
	}
}
