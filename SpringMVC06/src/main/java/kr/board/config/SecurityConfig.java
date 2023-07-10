package kr.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import kr.board.security.MemberUserDeailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	//SECURITYCONFIG에 등록을 해줘야한다.
	@Bean
	public UserDetailsService MemberUserDeailsService() {
		return new MemberUserDeailsService();
	}
	
	//configure override 등록
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(MemberUserDeailsService()).passwordEncoder(passwordEncoder());
		super.configure(auth);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//요청에대한 보안 설정~~
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter,CsrfFilter.class);	
		//클라이언트 요청이 왔을때 요청에 따른 권한을 설정합니다.
		//어떤 권한을 가지고있는 회원에게 요청함
		//permitall ->특별한 권한이 없어도 요청
		//and ->다음권한을 또 걸고싶을때
		//formLogin -> 로그인화면이동
		//스프링은 security 내부에 회원 ui 이동
		//loginprocessingurl->스프링 내부 api를 거치고 간다.
		//loginProcessingUrl ->요청이 왔을때 memLoginForm.do로 넘어간다.
		//logout선언후 내부적으로 logout처리된다.
		http
			.authorizeRequests()
			.antMatchers("/")
			.permitAll()
			.and()
		.formLogin()
			.loginPage("/memLoginForm.do")
			.loginProcessingUrl("/memLogin.do")
			.permitAll()
			.and()
		.logout()
			.invalidateHttpSession(true)
			.logoutSuccessUrl("/")
			.and()
		.exceptionHandling().accessDeniedPage("/access-denied");
		
	}	
	//패스워드 인코딩 객체 설정
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
}