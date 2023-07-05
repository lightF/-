package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//POJO
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller
public class BoardController {

	@Autowired
	private BoardMapper mapper;

	// boardList.do 요청
	// handlermapping
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {

		List<Board> list = mapper.getLists();
		model.addAttribute("list", list);// 객체바인딩
		return "boardList";
		// 논리적인이름
		// WEB-INF/VIEWS
		// 뷰리졸버 //WEB-INF/views/boardlist.jsp 포워딩
	}

	// 글쓰기 화면만 리턴해주면된다.
	// 글쓰기 화면만 출력해주면됨
	@GetMapping("/boardForm.do")
	public String boarForm() {
		return "boardForm"; // WEB-INF/views/boardForm.jsp -> forward
	}

	@PostMapping("/boardInsert.do")
	public String boardInsert(Board vo) { // paramerter(board) title,content, writer
		mapper.boardInsert(vo); // 등록
		return "redirect:/boardList.do";// redirect
	}

	// get,post 상관없이 MAPPING 하려면 REQUESTMAPPING 해야 한다.
	//mapper로 넘어감
	//객체 바인딩 model 
	//content에 idx가 넘어오면 requestparam으로 넘어오는 파라미터에 
	@GetMapping("/boardContent.do")
	public String boardContent(int idx, Model model) {
		Board vo = mapper.boardContent(idx);
		//조회수증가
		mapper.boardCount(idx);
		model.addAttribute("vo", vo);
		return "boardContent"; //boardContent.jsp
	}
	//변수이름을 클라이언트에서 지정할 필요가 없다.
	//서버에서 지정함
	//id값을 pathvariable로 받아서 대입을 시켜놓으면된다.
	@GetMapping("/boardDelete.do/{idx}") //넘어온값을 받는다.
	public String boardDelete(@PathVariable("idx") int idx) { // ?idx=6 
		mapper.boardDelete(idx);
		return "redirect:/boardList.do"; //데이터 삭제하고 리스트페이지로 돌아가야함
	}
	
	//수정화면
	@GetMapping("/boardUpdateForm.do/{idx}")
	public String boardUpdateForm(@PathVariable("idx") int idx, Model model) {
		Board vo=mapper.boardContent(idx);
		model.addAttribute("vo", vo);		
		return "boardUpdate"; // boardUpdate.jsp
	}
	@PostMapping("/boardUpdate.do")
	public String boardUpdate(Board vo) {
		mapper.boardUpdate(vo);
		return "redirect:/boardList.do"; // 파라미터 idx,title,content
	}
}
	

