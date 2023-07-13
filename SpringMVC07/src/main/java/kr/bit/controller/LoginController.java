package kr.bit.controller;

import javax.servlet.http.HttpSession;

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
	public String login(Member vo, HttpSession session) {
		Member mvo=boardservice.login(vo);
		if(mvo!=null) { 	//null이아니면
			session.setAttribute("mvo", mvo); //객체 바인딩해야한다.
		}
		return "redirect:/board/list";
	}
	
	@RequestMapping("/logoutProcess")
		public String logout(HttpSession session) {
		session.invalidate();	//세션 무효화(로그가웃)
			return "redirect:/board/list";
	}
}
