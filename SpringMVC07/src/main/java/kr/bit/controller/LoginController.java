package kr.bit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.bit.entity.Member;
import kr.bit.service.BoardService;

@Controller
@RequestMapping("/login/*")
public class LoginController {
	
	@Autowired
	BoardService boardservice;
	
	//member vo -> vo 파라미터로 값을 전달받는다.
	@RequestMapping("/loginProcess")
	public String login(Member vo) {
		
		
		return "redirect:/board/list";
	}
}
