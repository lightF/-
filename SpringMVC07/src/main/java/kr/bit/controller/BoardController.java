package kr.bit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	//PARAMETER수집 vo의 값을 서비스의 넘긴다.
	@PostMapping("/register")
	public String register(Board vo, RedirectAttributes rttr) { //파라미터 수집(vo)<-- 한글인코딩
		boardService.register(vo);	  //게시물등록
		System.out.println(vo);
		rttr.addFlashAttribute("result",vo.getIdx()); //${result}
		return "redirect:/board/list";
	}
	
	@GetMapping("/get")
	public String get(@RequestParam("idx")int idx,Model model) {	//객체 바인딩을 해야하므로 model 선언
		Board vo = boardService.get(idx);
		model.addAttribute("vo",vo);
		return "board/get";	//WEB-INF/views/board/get/jsp ->으로 연결된다
		
		
	}
	
}
