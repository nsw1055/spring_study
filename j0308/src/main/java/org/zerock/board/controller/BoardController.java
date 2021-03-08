package org.zerock.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@Log4j
public class BoardController {
	
	@GetMapping({"/", "/list"})
	public String list() {
		log.info("list.........................");
		
		return "/board/list";
	}
}
