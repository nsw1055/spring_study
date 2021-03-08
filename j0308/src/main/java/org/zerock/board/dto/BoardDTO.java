package org.zerock.board.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BoardDTO {
	private Integer bno;
	private String title, content, writer;
	private Date regDate, updateDate;
	
	
}
