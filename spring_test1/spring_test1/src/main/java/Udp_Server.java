import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Udp_Server {
	public static void main(String[] args) {
		server_chat sc = new server_chat();
		sc.udp();
	}

}

class server_chat {
	private int port = 0; // 서버 포트
	private String ip = ""; // 서버 IP
	private DatagramSocket ds = null; // udp소켓
	private InetAddress ia = null; // ip, 접속자 ip 기록 등
	private DatagramPacket dp = null; // 메세지 송수신시 필요한 패킷
	private String msg = ""; // 메세지
	private BufferedReader br = null;

	public server_chat() {
		ip = "192.168.110.221";
		port = 8000;
	}

	public void udp() {
		try {
			client_chat2 uc = new client_chat2();

			ia = InetAddress.getByName(ip); // 서버IP
			System.out.println("UDP server open");
			ds = new DatagramSocket(port); // 포트오픈
			System.out.println("chat start");
			while (true) {
				byte[] by = new byte[1024]; // 메세지 크기
				dp = new DatagramPacket(by, by.length); // 클라이언트에서 오는 패킷크기
				ds.receive(dp); // 클라이언트에서 보낸 메세지를 서버에서 받는 역할
				msg = new String(dp.getData());
				System.out.println("client : "+msg);
				System.out.println("메세지를 입력하세요 : ");
				// 클라이언트 ip udp 포트 가져옴
				InetAddress ia2 = dp.getAddress();
				int port2 = dp.getPort();
				br = new BufferedReader(new InputStreamReader(System.in));
				msg = br.readLine();
				byte by2[] = msg.getBytes();
				dp = new DatagramPacket(by2, by2.length, ia2, port2);
				ds.send(dp);// 서버에서 클라이언트로 메세지 전송
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}