package kr.board.entity;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;
//스프링에서 제공해주는 USER 클래스 상속
//생성자를 만들어서 스프링에서 제공해주는 user클래스를 제공한다.
//패스워드 ,권한정보, 이름
//MemberUSER는 private member정보도 가지고있다.
//username, passoword, authlist정보를 user에 가지고있음.
// 인증후 사용자 정보를 저장하기 - 3번째 UserDetails
@Data
public class MemberUser extends User{ //UserDetails
	
	private Member member;
	
	public MemberUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}
	
	//멤버로 받아서 멤버 vo에있는 이름.,패스워드.권한정보를 넘겨준다.
	//SUPER로 받아서 부모CLASS에 정보를 넘겨준다. MEMORY에 객체 바인딩
	public MemberUser(Member mvo) {
        super(mvo.getMemID(), mvo.getMemPassword(), mvo.getAuthList().stream()
       	      .map(auth->new SimpleGrantedAuthority(auth.getAuth())).
       	      collect(Collectors.toList()));		//stream으로 받아야한다. 
        this.member=mvo;
		//List<AuthVO> -> SimpleGrantedAuthority  스프링에서 제공하는 Collection 타입으로 변경해야한다.
		
	}
}
