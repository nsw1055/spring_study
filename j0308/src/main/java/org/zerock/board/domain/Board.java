package org.zerock.board.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class Board {
	private Integer bno;
	private String title, content, writer;
	private Date regDate, updateDate;
}