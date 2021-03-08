package org.zerock.time.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.time.service.TimeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/time")
@Log4j
@RequiredArgsConstructor
public class TimeController {

	private final TimeService service;
	
	@GetMapping("/now")
	public void getTime(Model model) {
		log.info("TimeController now..................");
		log.info(service);
		model.addAttribute("time", service.getTime());
	}
}
