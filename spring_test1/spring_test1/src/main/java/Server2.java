import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server2 {
	public static void main(String[] args) {
		new Chat().chatServer();
	}
}

class Chat {
	private int port = 8009;
	ServerSocket sk = null;
	Socket so = null;
	Scanner sc = null;
	InputStream is = null; // client의 메세지 받기
	OutputStream os = null;// client에 메세지 보내기
	String mid = "";// 서버측의 아이디
	String msg = "";// 서버의 메세지
	String cmsg = "";
	String check = "";

	public void chatServer() {
		try {
			sk = new ServerSocket(port);
			sc = new Scanner(System.in);
			System.out.println("ID?");
			mid = sc.next();
			System.out.println("chat start");
			for (;;) {
				so = sk.accept();
				is = so.getInputStream();
				os = so.getOutputStream();
				// 클라이언트의 내용을 받아오는 역할
				byte data[] = new byte[1024 * 2];
				int n = is.read(data);
				msg = new String(data, 0, n);
				System.out.println("message : " + msg);
				check = sc.nextLine().intern();
				if (check == "exit") {
					System.out.println("exit");
					os.close();
					is.close();
					so.close();
					sk.close();
					sc.close();
				}
				cmsg = "[" + mid + "] : " + check;
				os.write(cmsg.getBytes());// 보내기완료
				os.flush();// 메모리비워둔다

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}