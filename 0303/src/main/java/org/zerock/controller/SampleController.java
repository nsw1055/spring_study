package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.mapper.TimeMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/sample")
@RequiredArgsConstructor
public class SampleController {

	private final TimeMapper timeMapper;
	
	@RequestMapping("/doA")
	public void doA(Model model) {
		log.info("doA.....");
		String now = timeMapper.getTime2();
		log.info(now);
		model.addAttribute("time", now); //request.setAttribute와 같다
	}
}
