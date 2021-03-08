package org.zerock.time;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.common.config.CommonConfig;
import org.zerock.time.config.TimeConfig;
import org.zerock.time.mapper.TimeMapper;
import org.zerock.time.service.TimeService;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfig.class, TimeConfig.class})
@Log4j
public class TimeTests {

	@Autowired
	TimeMapper timeMapper;
	
	@Autowired
	TimeService timeService;
	
	@Test
	public void textExist() {
		
		log.info("--------------");
		log.info(timeMapper);
		
		log.info(timeMapper.getNow());
		
		log.info(timeMapper.getNow2());
	}
	
	@Test
	public void testService() {
		
		log.info(timeService.getTime());
	}
}
