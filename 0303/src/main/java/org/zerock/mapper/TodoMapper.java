package org.zerock.mapper;

import org.apache.ibatis.annotations.Insert;
import org.zerock.entity.Todo;

public interface TodoMapper {
	
	@Insert("insert into tbl_todo (title, complete) values(#{title},#{complete})")
	void insert(Todo todo);
	
}
