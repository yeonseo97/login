package com.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.dto.member.MemberDTO;
import com.demo.service.member.MemberService;
import com.demo.util.PasswordEncoderUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class LoginController {
	
	private final MemberService memberService;
	
    //private final PasswordEncoderUtil passwordEncoder;
    
    @Autowired
    public LoginController(MemberService memberService) {
        this.memberService = memberService;
        //this.passwordEncoder = passwordEncoder;
    }
    
    // 1. 로그인 / 로그아웃
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	

    @PostMapping("/login")
    public String loginProcess(
    		@RequestParam String loginId,
    		@RequestParam String password, 
    		HttpSession session,
    		RedirectAttributes attributes) {
        MemberDTO member = memberService.findByLoginId(loginId);
        log.info("아이디: {}", loginId);
        log.info("세션: {}", session);
        if (member != null && member.getPassword().equals(password)) {
            session.setAttribute("member", member);
            return "redirect:/"; // 로그인 성공 시 메인 페이지로 이동
        } else {
            attributes.addFlashAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "redirect:/login"; // 로그인 실패 시 다시 로그인 페이지로 이동
        }
     
    }
	
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // 세션에서 사용자 정보 삭제
        request.getSession().invalidate();
        return "redirect:/login"; // 로그아웃 후 로그인 페이지로 이동
    }
    
	@GetMapping("/")
	public String memberPage(HttpServletRequest request) {
		// 세션에서 사용자 정보 가져오기
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("member");

        // 사용자가 로그인되어 있지 않으면 로그인 페이지로 리다이렉트
        if (member == null) {
        	log.info("회원이 아님");
            return "redirect:/login";
        }
        // 로그인된 사용자만 접근 가능한 페이지
        log.info("세션: {}", session);
		return "member";
	}
	
	@GetMapping("/member")
	public String memberPage2(HttpServletRequest request) {
		// 세션에서 사용자 정보 가져오기
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("member");

        // 사용자가 로그인되어 있지 않으면 로그인 페이지로 리다이렉트
        if (member == null) {
        	log.info("회원이 아님");
            return "redirect:/login";
        }
        // 로그인된 사용자만 접근 가능한 페이지
        log.info("세션: {}", session);
		return "member2";
	}
	
	
	// 2. 회원가입
	@GetMapping("/signup")
	public String signupPage() {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String singupProcess(
			@RequestParam String loginId,
            @RequestParam String password,
            RedirectAttributes attributes,
            MemberDTO member) {
		// 중복되는 아이디 확인
		if(memberService.existMember(member.getLoginId()) == false) {
			// 비밀번호 암호화
			
			// 처리 
			memberService.insertMember(member);
			log.info("가입 아이디 : {}", member.getLoginId());
		} else {
			attributes.addFlashAttribute("error", "* 이미 사용 중인 아이디입니다.");
			log.info("중복 ID : {}", memberService.existMember(member.getLoginId()));
			return "redirect:/signup";
			
		}
		return "redirect:/login";
	}
}
