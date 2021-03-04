package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.dto.TodoDTO;
import org.zerock.entity.Todo;
import org.zerock.mapper.TodoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/todo")
@Log4j
@RequiredArgsConstructor
public class TodoController {

	private final TodoMapper mapper;
	
	@GetMapping("/add")
	public void add() {
		log.info("get........");
	}
	@PostMapping("/add")
	public String addPost(TodoDTO todoDTO) {
		log.info(todoDTO);
		
		Todo todo = Todo.builder().title(todoDTO.getTitle()).complete(todoDTO.isComplete()).build();
		
		mapper.insert(todo);
		
		return "redirect:/todo/list";
	}
	@GetMapping("/list")
	public void list() {
		log.info("list........");
	}
}
