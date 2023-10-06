package com.demo.dao.member;

import org.apache.ibatis.annotations.Mapper;

import com.demo.dto.member.MemberDto;

@Mapper
public interface IMemberDao {
	// 로그인 아이디로 회원 찾기
	MemberDto findByLoginId(String loginId);
	
	// 회원가입
	void insertMember(MemberDto memberDTO);
}
