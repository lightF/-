package kr.board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.board.entity.Member;
import kr.board.entity.MemberUser;
import kr.board.mapper.MemberMapper;

public class MemberUserDeailsService implements UserDetailsService{
	@Autowired
	private MemberMapper membermapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//사용자 정보 , 권한정보 
		// UserDetails implements 가 인터페이스 -->user 객체에 정보를 담아야한다. USER -> EXTENDS -> MEMBERUSER
	    Member mvo=membermapper.memLogin(username);
	    //mvo가 null이 아니면
	    if (mvo != null) {
	    	//return mvo; // new MemberUser(mvo); //즉 멤버정보와, authVO정보를 MVO에 넣어준다.
	    	return new MemberUser(mvo);
		}else {
			//id가없으면 null 처리한다. throw exception 
			throw new UsernameNotFoundException("user with username" + username + "does not exist.");
		}
	}

}
