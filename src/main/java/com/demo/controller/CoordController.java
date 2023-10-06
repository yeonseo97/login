package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.service.point.CoordService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor // 생성자 만들어줌 
@Controller
public class CoordController {
	
	private final CoordService coordService;
	
    @GetMapping("/coord")
    public void insertData() {
    	coordService.insertCoord();
    }     
	
}
