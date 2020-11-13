package com.ansj.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardsDTO {
	private int bno;
	private String mid;
	private String bsubject;
	private String bcontent;
	private Date bregdate;
	private String bstatus;
	private int bgroup;
	private String bpmid;
}
