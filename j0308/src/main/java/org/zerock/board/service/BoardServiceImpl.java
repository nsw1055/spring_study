package org.zerock.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.zerock.board.domain.Board;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.mapper.BoardMapper;
import org.zerock.common.dto.PageDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {


	private final BoardMapper mapper;
	
	@Override
	public List<BoardDTO> getPageList(PageDTO pageDTO) {
		
		return mapper.getList(pageDTO.getSkip(), pageDTO.getPerSheet(), pageDTO.getArr(), pageDTO.getKeyword())
		.stream().map(board -> toDTO(board)).collect(Collectors.toList());
	}
	
	@Override
	public int getTotalCount(PageDTO pageDTO) {
		// TODO Auto-generated method stub
		return mapper.getTotalCount(pageDTO.getArr(), pageDTO.getKeyword());
	}

	@Override
	public void register(BoardDTO boardDTO) {

		Board vo = toDomain(boardDTO);
		
		mapper.insert(vo);
		
	}

	@Override
	public BoardDTO readOne(Integer bno) {
		// TODO Auto-generated method stub
		return toDTO(mapper.selectOne(bno));
	}
	
	

}
