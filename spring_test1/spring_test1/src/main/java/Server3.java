
//스레드활용 서버
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server3 {
	public static void main(String[] args) {
		Server3 sv = new Server3();
		sv.start();
	}

	// 소켓 오픈 역할(접속환경)
	//접속자가 여러명일때 가상 포트를 이용하여 계속적으로 추가할 수 있도록 하는 클래스
	public void start() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(8000);
			System.out.println("[채팅서버 오푼]");
			while (true) {
				socket = serverSocket.accept();
				// 각 클라이언트 접속할 때마다 새로운 스레스 생성
				ChatRoom chatroom = new ChatRoom(socket);
				chatroom.start();
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally { // 소켓통신 오류 발생시 처리하는 과정ㄲ
			if (serverSocket != null) {
				try {
					System.out.println("서버를 강제 종료합니다");
					serverSocket.close();// 강제종료
				} catch (Exception e) {
					System.out.println("서버 망햇음" + e);
					System.exit(0);
				}
			}
		}
	}
}
//접속자 현황 세팅, 스레드로 관리

class ChatRoom extends Thread {
	InputStream is = null;
	OutputStream os = null;
	Socket socket = null;
	BufferedReader in = null;
	PrintWriter out = null;
	// 서버 메모리에 사용자 리스트를 저장하는 공간
	static List<PrintWriter> list = new ArrayList<>();

	public ChatRoom(Socket socket) {
		this.socket = socket;
		try {
			out = new PrintWriter(this.socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			list.add(out);
			System.out.println(socket.getInetAddress());
			System.out.println(socket.getLocalAddress());
			System.out.println(socket.getLocalSocketAddress());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void run() {
		String name = "";
		try {
			name = in.readLine();
			System.out.println("[" + name + "]");
			sendAll("Hello [" + name + "]");
			while (in != null) {
				String inputMsg = in.readLine().intern();
				if (inputMsg == "exit") {
					break;
				} else {
					sendAll("[" + name + "] : " + inputMsg);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			sendAll("[" + name + "] 강티 ");
			list.remove(out);
			try {
				socket.close();
				
			} catch (Exception e) {
				System.out.println("chatserverdead");
			}
		}
		System.out.println("[" + name + "] EXIT");
	}

	// 사용자 정보 리스트 출력
	private void sendAll(String s) {
		for (PrintWriter out : list) {
			out.println(s);
			out.flush();
		}
	}
}
