package com.simple.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simple.basic.memo.MemoService;

@Controller
@RequestMapping("/memo")
public class MemoController {

	@Autowired
	@Qualifier("memoService")
	private MemoService memoService;
	
	//예제코드 (컨트롤러 서비스 매퍼 연결)
	@GetMapping("/ex")
	public void ex(Model model) {
		
		String time = memoService.getTime();
		
		model.addAttribute("time", time);
	}
	//화면처리
	@GetMapping("/")
	public String memoList() {
		
		return "memo/memoList";
	}
	//화면처리
	@GetMapping("/memoWrite")
	public String memoWrite() {
		
		return "memo/memoWrite";
	}
	
	
}
