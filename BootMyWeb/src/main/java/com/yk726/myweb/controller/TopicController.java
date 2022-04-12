package com.yk726.myweb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yk726.myweb.command.TopicVO;
import com.yk726.myweb.topic.TopicService;

@Controller
@RequestMapping("/topic")
public class TopicController {
	
	@Autowired
	@Qualifier("topicService")
	private TopicService topicService;
	
	
	@GetMapping("/topicReg")
	public String topicReg() {
		
		return "topic/topicReg";
	}
	
	@GetMapping("/topicListMe")
	public String topicList(Model model) {
		
		ArrayList<TopicVO> list = topicService.getList();
		
		model.addAttribute("list", list);
		
		return "topic/topicListMe";
	}
	
	@GetMapping("/topicListAll")
	public String topicListAll(Model model) {
		
		ArrayList<TopicVO> list = topicService.getList1();
		
		model.addAttribute("list", list);
		
		return "topic/topicListAll";
	}
	
	@GetMapping("/topicDetail")
	public String topicDetail(@RequestParam("topic_bno")int topic_bno,
							  Model model) {
		
		TopicVO topicVO = topicService.getDetail(topic_bno);
		model.addAttribute("topicVO", topicVO);
		
		return null;
	}
	
	@GetMapping("/topicModify")
	public String TopicModify(@RequestParam("topic_bno")int topic_bno,
							  Model model) {
	
		TopicVO topicVO = topicService.getModify(topic_bno);
		model.addAttribute("topicVO", topicVO);
		
		System.out.println(topicVO.getTopic_regdate());
		return "topic/topicModify";
	}
	
	
	//등록 폼		
	@PostMapping("/topicForm")
	public String topicForm(TopicVO vo) {
		
		int result = topicService.regist(vo);
		
		return "redirect:/topic/topicListMe";
	}
	
	@PostMapping("/topicUpdate")
	public String topicUpdate(@Valid TopicVO vo,
							   Errors errors,
							   RedirectAttributes RA,
							   Model model) {
		if(errors.hasErrors()) {
			List<FieldError> list = errors.getFieldErrors();
			
			for(FieldError err : list) {
				if(err.isBindingFailure()) {
					model.addAttribute("valid_" + err.getField(), "형식을 확인하세요");
				} else {
					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
				}
			}
			
			model.addAttribute("topicVO", vo);
			return "topic/topicModify";
		}
		int result = topicService.update(vo);
		if(result == 1) {
			RA.addFlashAttribute("msg", vo.getTopic_title() + "이 수정됨");
		} else {
			RA.addFlashAttribute("msg", "수정에 실패함");
		}
		System.out.println(vo.getTopic_regdate());
		return "redirect:/topic/topicListMe";
	}
	
	@PostMapping("/topicDelete")
	public String TopicDelete(@RequestParam("topic_bno") int topic_bno,
							   RedirectAttributes RA) {
		
		int result = topicService.delete(topic_bno);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "삭제 성공");
		} else {
			RA.addFlashAttribute("msg", "삭제 실패");
		}
		System.out.println(result);
		return "redirect:/topic/topicListMe";
}
}
