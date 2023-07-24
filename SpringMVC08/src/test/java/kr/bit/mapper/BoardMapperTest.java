package kr.bit.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.bit.entity.Board;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardMapperTest {
	
	@Autowired
	private BoardMapper boardMapper;
	
	/*public void testGetList() {
		List<Board> list = boardMapper.getList();
		for (Board vo : list) {
			//System.out.println(vo);
			log.info(vo);
		}
	}*/
	@Test
	public void testInsert() {
		Board vo = new Board();
		vo.setMemID("hyunje");
		vo.setTitle("D");
		vo.setContent("작성글5");
		vo.setWriter("피카추");
		//boardMapper.insert(vo);
		boardMapper.insertSelectKey(vo);
		log.info(vo);
	}
	

}
