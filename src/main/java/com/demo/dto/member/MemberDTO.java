package com.demo.dto.member;

import lombok.Data;

@Data
public class MemberDTO {

	private String loginId;
	private String password;
	private String salt; // 비밀번호 해싱용 솔트
}
