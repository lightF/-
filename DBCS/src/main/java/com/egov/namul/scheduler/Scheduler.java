package com.egov.namul.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class Scheduler {
	
	/** cron 파라미터 순서 */
	//	초 0-59 , - * / 
	//	분 0-59 , - * / 
	//	시 0-23 , - * / 
	//	일 1-31 , - * ? / L W
	//	월 1-12 or JAN-DEC , - * / 
	//	요일 1-7 or SUN-SAT , - * ? / L # 
	//	년(옵션) 1970-2099 , - * /
	
	/** cron 값 설명 */
	//	* : 모든 값
	//	? : 특정 값 없음
	//	- : 범위 지정에 사용
	//	, : 여러 값 지정 구분에 사용
	//	/ : 초기값과 증가치 설정에 사용
	//	L : 지정할 수 있는 범위의 마지막 값
	//	W : 월~금요일 또는 가장 가까운 월/금요일
	//	# : 몇 번째 무슨 요일 2#1 => 첫 번째 월요일	
	
//	@Scheduled(cron="0 0/1 * * * *")
	public void Test() throws Exception{
		try {
		}catch(Exception e) {
		}
    }
}
