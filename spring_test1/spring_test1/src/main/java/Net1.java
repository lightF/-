//네트워크기초

import java.net.InetAddress;

public class Net1 {
	
	public static void main(String[] args) throws Exception{
		//InetAddress : IPnetworkAddress
		//getByName : 접속할 도메인명
		InetAddress ia = InetAddress.getByName("localhost");
		System.out.println(ia);
		//접속하는 도메인의 IP를 모두 확인
		InetAddress ia2[] = InetAddress.getAllByName("m.naver.com");
		System.out.println(ia2.length);
		for(int x=0;x<ia2.length;x++) {
			System.out.println(ia2[x]);
		}
	}
}
