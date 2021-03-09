package org.zerock.board.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.service.BoardService;
import org.zerock.common.dto.PageDTO;
import org.zerock.common.dto.PageMaker;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@Log4j
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService service;
	
	@GetMapping({"/", "/list"})
	public String list(@ModelAttribute("pageDTO") PageDTO pageDTO, Model model) {
		log.info("list.........................");
		
		model.addAttribute("list", service.getPageList(pageDTO));
		model.addAttribute("pageMaker", new PageMaker(pageDTO, service.getTotalCount()));
		//mockMVC
		return "/board/list";
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping(value = "/register", produces = {"text/plain"})
	@ResponseBody
	//						@RequestBody: json데이터를 java의 객체로 변경해 주는 어노테이션
	public String registerPost(@RequestBody @Valid BoardDTO dto, BindingResult result) {
		
		log.info(dto);
		
		if(result.hasErrors()) {
			log.info(result.getAllErrors());
			
			return "fail";
		}
		
		return "success";
	}
}
