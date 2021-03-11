package org.zerock.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.board.domain.Board;

public interface BoardMapper {

	List<Board> getList(@Param("skip") int skip, 
			@Param("count") int count, 
			@Param("arr") String[] arr,
			@Param("keyword") String keyword);
	
	int getTotalCount( 
			@Param("arr") String[] arr,
			@Param("keyword") String keyword);
	
	void insert(Board board);
	
	Board selectOne(Integer bno);
	
//	List<Board> ex1(Map<String, String> map);
}
