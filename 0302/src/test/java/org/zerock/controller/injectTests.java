package org.zerock.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.service.Restaurant;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class injectTests {
	
	//테스트 autowired된 객체를 가져옴
	@Autowired
	private Restaurant restaurant;
	
	@Test
	public void testExist() {
		log.info(restaurant);
	}
	
	
}
