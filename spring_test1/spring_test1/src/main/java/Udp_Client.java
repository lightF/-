import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Udp_Client {
	public static void main(String[] args) {
		client_chat2 cc = new client_chat2();
		cc.chat();
	}
}

class client_chat2 {
	private int port = 0;
	private int myport = (int) Math.ceil(Math.random() * 10000) + 10000;;
	private String ip = "";
	private DatagramSocket ds = null; // udp소켓
	private InetAddress ia = null; // ip
	private DatagramPacket dp = null;
	private BufferedReader br = null;
	private String msg = "";

	/*
	 * UDP는 포트가 중복되면 안된다, 서버포트 별도 클라이언트 포트가 서로 달라야한다 동일할 경우 에러
	 */
	public client_chat2() {
		ip = "192.168.110.221";
		port = 8000;
	}

	public int getMyport() {
		return myport;
	}

	public void chat() {
		String id = "hong";
		String pw = "a1234";
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("ID ? : ");
			String ipid = sc.next().intern();
			if (ipid == id) {
				System.out.println("PW ? : ");
				String ippw = sc.next().intern();
				if (ippw == pw) {
					ds = new DatagramSocket(myport);// 자신의 포트에 대한 소켓 오픈
					ia = InetAddress.getByName(ip); // IP가져옴
					while (true) {
						br = new BufferedReader(new InputStreamReader(System.in));
						System.out.println("메세지를 입력하세요 : ");
						msg = br.readLine();
						byte[] by = msg.getBytes();
						// 서버로 해당 메세지를 패킷을 이용해서 전송
						// DatagramPacket(byte배열,배열크기,서버ip,서버포트)
						dp = new DatagramPacket(by, by.length, ia, port);
						ds.send(dp);// 서버로 메세지 전송
						// 서버에서 전송된 값을 받는 역할
						byte by2[] = new byte[200];
						dp = new DatagramPacket(by2, by2.length);
						ds.receive(dp);
						System.out.println("server : " + new String(dp.getData()));
					}
				}else {
					System.out.println("invalid information");
				}
			}
			sc.close();
			ds.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}