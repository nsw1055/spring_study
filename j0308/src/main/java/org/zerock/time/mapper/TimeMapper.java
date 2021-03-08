package org.zerock.time.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

	@Select("select now()")
	String getNow();
	
	String getNow2();
}
