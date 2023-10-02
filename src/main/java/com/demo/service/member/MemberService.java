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
    
    public void insertMember(MemberDTO memberDTO) {
    	memberMapper.insertMember(memberDTO);
    }
    
    // 존재하면 true 반환, 그렇지 않으면 false를 반환
    public boolean existMember(String loginId) {
    	// 데이터베이스에 존재하는 로그인 아이디 확인하여 변수에 담음
        MemberDTO existMember = memberMapper.findByLoginId(loginId);
        // 동일한 아이디가 데이터베이스에 존재하는 경우(true)
        return existMember != null;
    }
}




