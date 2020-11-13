package com.ansj.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageDTO {
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int total;
	private StartDTO st;
	
	public PageDTO(StartDTO st, int total) {
		this.st = st;
		this.total = total;
		
		this.endPage = (int)(Math.ceil(st.getPage()/10.0))*10;
		this.startPage = endPage - 9;
		
		int realEnd = (int)(Math.ceil((total*1.0)/10));
		
		if(realEnd < endPage) {
			endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
}
