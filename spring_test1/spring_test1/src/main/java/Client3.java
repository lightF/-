//스레드활용 클라이언트

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client3 {
	public static void main(String[] args) {
		Client3 c3 = new Client3();
		c3.start();
	}

	public void start() {
		InputStream is = null;
		OutputStream os = null;
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		InputStreamReader ir = null;
		Scanner sc = null;
		try {
			socket = new Socket("192.168.110.221", 8000);
			System.out.println("[서버 접속 완료]");
			sc = new Scanner(System.in);
			System.out.println("접속할 아이디 입력 : ");
			String name = sc.next();
			// 스레드로 값을 넘김
			Thread ct = new Clientt(socket, name);
			ct.start();
			// 스레드 실행 및 종료
			is = socket.getInputStream();
			ir = new InputStreamReader(is);
			in = new BufferedReader(ir);
			while (in != null) {
				String inputMsg = in.readLine().intern();
				if (inputMsg == "[" + name + "] Chatting Out") {
					break;
				}
				System.out.println("전송 : " + inputMsg);
			}
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		} finally {
			try {
				socket.close();
				sc.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		System.out.println("서버 접속 종료");
	}
}

class Clientt extends Thread {
	Socket socket = null;
	Scanner sc = null;
	String name = "";

	public Clientt(Socket socket, String name) {
		this.socket = socket;
		this.name = name;
	}

	@Override
	public void run() {
		try {
			//PrintStream 바이너리기반, Stream 자료를 모두 받을 ㅅ ㅜ 잇음
			PrintStream out = new PrintStream(socket.getOutputStream());
			out.println(name);
			out.flush();
			while (true) {
				sc = new Scanner(System.in);
				System.out.println("채팅메세지 : ");
				String outputMsg = sc.nextLine().intern();
				out.println(outputMsg);
				out.flush();
				if (outputMsg == "exit") {
					break;
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}