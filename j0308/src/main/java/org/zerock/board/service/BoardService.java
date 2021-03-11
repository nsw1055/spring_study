package org.zerock.board.service;

import java.util.List;

import org.zerock.board.domain.Board;
import org.zerock.board.dto.BoardDTO;
import org.zerock.common.dto.PageDTO;

public interface BoardService {

	int getTotalCount(PageDTO pageDTO);
	
	List<BoardDTO> getPageList(PageDTO pageDTO);
	
	void register(BoardDTO boardDTO);
	
	BoardDTO readOne(Integer bno);
	
	default BoardDTO toDTO(Board board) {

		BoardDTO dto = new BoardDTO();
		dto.setBno(board.getBno());
		dto.setTitle(board.getTitle());
		dto.setContent(board.getContent());
		dto.setWriter(board.getWriter());
		dto.setRegDate(board.getRegDate());
		dto.setUpdateDate(board.getUpdateDate());
		return dto;
		
	}
	
	default Board toDomain(BoardDTO dto) {
		
		return Board.builder().bno(dto.getBno())
		.title(dto.getTitle())
		.content(dto.getContent())
		.writer(dto.getWriter())
		.regDate(dto.getRegDate())
		.updateDate(dto.getUpdateDate()).build();
	}
	
	
	
	
}
