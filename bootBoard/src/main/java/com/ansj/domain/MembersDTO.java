package com.ansj.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MembersDTO {
	private int mno;
	private String mid;
	private String mpw;
	private String mname;
	private String memail;
	private Date mregdate;
	private List<MembersRole> roles;
}
