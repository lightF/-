package kr.com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.com.entity.Board;

@Controller
public class BoardController {

	@RequestMapping("/boardList.do") //request로 객체 바인딩
	public String boardList(Model model) { //model 바인딩
		Board vo = new Board();
		vo.setContent("하이");
		vo.setIdx(0);
		vo.setIndate("dd");
		vo.setWriter("작성자");
		//vo로 객체를 만들어서 LIST로 뿌려줌(GETTER,SETTER)
		
		List<Board> list = new ArrayList<>();
		list.add(vo);
		list.add(vo);
		return "boardList";
		
	}
}
