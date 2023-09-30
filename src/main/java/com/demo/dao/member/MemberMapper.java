package com.demo.dao.member;

import org.apache.ibatis.annotations.Mapper;

import com.demo.dto.member.MemberDTO;

@Mapper
public interface MemberMapper {
	// 로그인 아이디로 회원 찾기
	MemberDTO findByLoginId(String loginId);
}
