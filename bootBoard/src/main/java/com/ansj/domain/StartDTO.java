package com.ansj.domain;

import lombok.Data;

@Data
public class StartDTO {
	private int page;
	private String keyword;
	
	public StartDTO() {
		this(1, null);
	}
	
	public StartDTO(int page, String keyword) {
		this.page=page;
		this.keyword=keyword;
	}
}
