package kr.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.board.entity.AuthVO;
import kr.board.entity.Member;

//@mapper -mybatis API
//MEMBER가 NULL이면 사용할수있고 NULL아니면 가입X
@Mapper
public interface MemberMapper {
	public Member registerCheck(String memID);

	public int register(Member m); // 회원등록(성공이면 1 실패면 0)

	public Member memLogin(Member mvo);

	public int memUpdate(Member mvo); // 회원정보 수정하기

	public Member getMember(String memID); // 파일업로드

	public void memProfileUpdate(Member mvo);

	public void authInsert(AuthVO saveVO);

	public void authDelete(String memID);
}