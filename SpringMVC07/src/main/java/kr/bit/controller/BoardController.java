package kr.bit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.bit.entity.Board;
import kr.bit.service.BoardService;

@Controller //POJO
@RequestMapping("/board/*") //url localhost:8080/board/ 모든요청은 컨트롤러가 받겟다.
public class BoardController {
	//톰캣 실행안하고 가상으로 테스트 하는방법
	@Autowired
	BoardService boardService; //부모 type으로 받아준다.
	
	@GetMapping("/list")
	public String getList(Model model) { //객체 바인딩을 해야하므로 Model을 선언해준다.
	List<Board> list=boardService.getList();
		//객체 바인딩
		model.addAttribute("list", list);
		return "board/list"; //이값을 jsp로 포워딩해줘야한다.
	}
	@GetMapping("/register")
	public String regiser() {
		return "board/register";
	}
	
}
