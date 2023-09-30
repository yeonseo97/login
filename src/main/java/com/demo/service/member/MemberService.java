package com.demo.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.member.MemberMapper;
import com.demo.dto.member.MemberDTO;

// 로그인 정보를 데이터베이스에서 가져오기 위해 MyBatis 매퍼를 사용
@Service
public class MemberService {

    private final MemberMapper memberMapper;
    
    @Autowired
    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public MemberDTO findByLoginId(String loginId) {
        return memberMapper.findByLoginId(loginId);
    }
}




