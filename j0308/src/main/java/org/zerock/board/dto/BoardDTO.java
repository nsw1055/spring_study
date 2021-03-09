package org.zerock.board.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class BoardDTO {
	private Integer bno;
	private String title;
	private String content;
	@NotEmpty
	private String writer;
	private Date regDate, updateDate;
	
	
}
