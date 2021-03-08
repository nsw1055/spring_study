package org.zerock.board.service;

import org.zerock.board.domain.Board;
import org.zerock.board.dto.BoardDTO;

public interface BoardService {

	default Board toDomain(BoardDTO dto) {
		
		return Board.builder().bno(dto.getBno())
		.title(dto.getTitle())
		.content(dto.getContent())
		.writer(dto.getWriter())
		.regDate(dto.getRegDate())
		.updateDate(dto.getUpdateDate()).build();
	}
	
}
