package org.zerock.common.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageMaker {

	private boolean prev;
	private boolean next;
	private int start;
	private int end;
	private PageDTO pageInfo;
	private int total;
	
	public PageMaker(PageDTO pageInfo, int total) {
		
		this.total = total;
		this.pageInfo = pageInfo;
		
		//현재 페이지 번호
		int currentPage = pageInfo.getPage();
		
		//임시 마지막 번호
		int tempEnd = (int)(Math.ceil(currentPage/10.0)*10);
		
		//시작페이지
		this.start = tempEnd - 9;

		//진짜 마지막 페이지
		int realEnd = (int)(Math.ceil(total / 10.0));
		
		end = realEnd < tempEnd ? realEnd : tempEnd; 
		
		prev = start > 1;
		
//		if(end*10 < total) {
//			next = true;
//		}else {
//			next = false;
//		}
		next = end*10 < total;
	}
}
