package com.demo.service.member;

import org.springframework.stereotype.Service;

import com.demo.dao.member.IMemberDao;
import com.demo.dto.member.MemberDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

// 로그인 정보를 데이터베이스에서 가져오기 위해 MyBatis 매퍼를 사용
@Log4j2
@RequiredArgsConstructor
@Service
public class MemberService {

    private final IMemberDao memberDao;
    
    public MemberDto findByLoginId(String loginId) {
        return memberDao.findByLoginId(loginId);
    }
        
    public void insertMember(MemberDto memberDTO) {
    	memberDao.insertMember(memberDTO);
    }
    
    // 존재하면 true 반환, 그렇지 않으면 false를 반환
    public boolean existMember(String loginId) {
    	// 데이터베이스에 존재하는 로그인 아이디 확인하여 변수에 담음
        MemberDto existMember = memberDao.findByLoginId(loginId);
        // 동일한 아이디가 데이터베이스에 존재하는 경우(true)
        return existMember != null;
    }
}




