package com.simple.basic.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simple.basic.command.SimpleVO;


@Controller
@RequestMapping("/view")
public class ThymeleafController {

		//화면출력 (get 맵핑)
		@GetMapping("/ex01")
		public void ex01() {
			
		}
		
		@GetMapping("/ex02")
		public String ex02(Model model) {
			
//			//1st
//			ArrayList<SimpleVO> list = new ArrayList<>();
//			for(int i = 1; i <= 20; i++) {
//				
//				SimpleVO vo = SimpleVO.builder()
//									  .a(i)
//									  .first("first" + i)
//									  .last("last" + i)
//									  .regdate( LocalDateTime.now())
//									  .build();
//				list.add(vo);
//			}
			
			//2nd (rangeClosed(이하까지), range(미만까지)) 람다로 표현함
			List<SimpleVO> list = IntStream.rangeClosed(1,20).mapToObj((i) -> {
				SimpleVO vo = SimpleVO.builder()
						  			  .a(i)
									  .first("first" + i)
									  .last("last" + i)
									  .regdate( LocalDateTime.now())
									  .build();
				return vo;
			}).collect(Collectors.toList());
			
			//데이터저장
			model.addAttribute("list", list);
			return "/view/ex02"; //templates 하위폴더
		}
		
		//화면출력
		@RequestMapping("/ex03")
		public String ex03(Model model) {
			
			List<SimpleVO> list = IntStream.rangeClosed(1,20).mapToObj((i) -> {
				SimpleVO vo = SimpleVO.builder()
						  			  .a(i)
									  .first("first" + i)
									  .last("last" + i)
									  .regdate( LocalDateTime.now())
									  .build();
				return vo;
			}).collect(Collectors.toList());
			model.addAttribute("list", list);
			return "view/ex03";
		}
		
		//키넘기기 방법 1
		@GetMapping("/result")
		public String result(@RequestParam("a") int a,
							 @RequestParam("first") String first) {
			
			System.out.println("a로 넘어온값 : " + a);
			System.out.println("a로 넘어온값 : " + first);
			
			return "view/ex03_result";
		}
		//키넘기기 방법2
		@GetMapping("/result02/{key}")
		public String result02(@PathVariable("key") String key) {
			
			
			System.out.println("a로 넘어온값 :" + key);
			return "view/ex03_result";
		}
		
		//화면처리 ex04
		@GetMapping("/ex04")
		public String ex04(Model model) {
			
			SimpleVO vo = SimpleVO.builder()
								  .a(1)
								  .first("admin")
								  .last("test")
								  .regdate(LocalDateTime.now())
								  .build();
			
			model.addAttribute("vo", vo);
			model.addAttribute("arr", new int[] {1,2,3});
			model.addAttribute("name", "이순신");
			
			return "view/ex04";
		}
		
		//화면처리ex05
		@GetMapping("/ex05")
		public String ex05(Model model) {
			
			List<SimpleVO> list = IntStream.rangeClosed(1,10).mapToObj((i) -> {
			
				SimpleVO vo = SimpleVO.builder()
						  			  .a(i)
									  .first("first" + i)
									  .last("last" + i)
									  .regdate( LocalDateTime.now())
									  .build();
				return vo;
			}).collect(Collectors.toList());

			model.addAttribute("list", list);
			
			return "view/ex05";
		}
		
		//화면처리 ex06
		@GetMapping("/ex06")
		public void ex06() {
			
		}
		
		//화면처리 ex07 (템플릿방법)
		@GetMapping("/ex07")
		public void ex07() {
			
		}
		
		//화면처리 quiz01
		@GetMapping("/quiz01")
		public String quiz01(Model model) {
			
			SimpleVO vo = SimpleVO.builder()
								  .a(1)
								  .first("아지")
								  .last("강")
								  .regdate(LocalDateTime.now())
								  .build();
			
			model.addAttribute("vo", vo);
			
			return "view/quiz01";
		}
		
		@GetMapping("/quiz01_result")
		public String quiz01_result(@ModelAttribute("a") int a,
									@ModelAttribute("name") String name) {
			
			return "view/quiz01_result";
		}
}
