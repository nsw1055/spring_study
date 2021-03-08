package org.zerock.time.service;

import org.springframework.stereotype.Service;
import org.zerock.time.mapper.TimeMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j 
@RequiredArgsConstructor
public class TimeServiceImpl implements TimeService {

	private final TimeMapper timeMapper;
	
	@Override
	public String getTime() {
		log.info("getTime............");
		return timeMapper.getNow();
	}

}
