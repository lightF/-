package kr.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.company.entity.Board;
import kr.company.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	BoardService boardservice;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<Board> list = boardservice.getList();
		model.addAttribute("list", list);	//${list}
		return "/list";	//WEB-INF/BOARD/LIST.JSP
	}
	@GetMapping("/register")
	public String register() {
		return "register";	//WEB-INF/board/register.jsp
	}
	@PostMapping("/register")	//파라미터 받는 register
	public String register(Board vo) {
		boardservice.register(vo);
		return "redirect:/list";
	}
	

}
