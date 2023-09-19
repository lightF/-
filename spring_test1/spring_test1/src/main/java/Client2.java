import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
	public static void main(String[] args) {
		new Client_Chat().chat();
	}
}

class Client_Chat {
	private final String ip = "192.168.110.221"; //서버 IP
	final int port = 8009; // 서버 port
	Scanner sc = null; // 메세지 입력 
	ServerSocket sk = null; // 
	Socket so = null; // 소켓
	InputStream is = null; //서버측 메세지 받는 역할
	OutputStream os = null; //서버에 클라이언트 메세지 전달
	String mid = "";
	String msg = ""; //스캐너에서 받은 메세지 내용
	String smsg = ""; //서버에 메세지를 담는 변수
	String check = "";//exit으로 종료하기 위한 체크

	public void chat() {
		try {
			sc = new Scanner(System.in);
			System.out.println("ID?");
			mid = sc.next();
			int ck = 0;
			for (;;) { // 무한 반복문으로 접속 유지
				so = new Socket(ip, port);
				is = so.getInputStream();
				os = so.getOutputStream();
				if (ck == 0) { // 최초 접속시 서버측에 입장메세지 출력 위해
					msg = "[" + mid + "] : 님 입장";
				} else {
					sc = new Scanner(System.in);
					System.out.println("메세지?");
					check = sc.nextLine().intern();
					msg = "[" + mid + "] : " + check;
				}
				if (check == "exit") {
					msg = "[" + mid + "] : 님 퇴장";
					os.flush();
					os.close();
					is.close();
					so.close();
					sk.close();
					sc.close();
				} else {
					os.write(msg.getBytes());
					os.flush();
				}
				byte data[] = new byte[1024 * 2];
				int n = is.read(data);
				smsg = new String(data, 0, n);
				System.out.println(smsg);
				ck++;
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}