package org.zerock.board.service;

import java.util.List;

import org.zerock.board.domain.Board;
import org.zerock.board.dto.BoardDTO;
import org.zerock.common.dto.PageDTO;

public interface BoardService {

	int getTotalCount();
	
	List<BoardDTO> getPageList(PageDTO pageDTO);
	
	default Board toDomain(BoardDTO dto) {
		
		return Board.builder().bno(dto.getBno())
		.title(dto.getTitle())
		.content(dto.getContent())
		.writer(dto.getWriter())
		.regDate(dto.getRegDate())
		.updateDate(dto.getUpdateDate()).build();
	}
	
	
}
