import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

//TCP
public class Client {
	public static void main(String[] args) {
		String ip = "192.168.110.221"; //server address
		int port = 8009; // server port 
		Socket sc = null;
		Scanner sn = null;
		try {
			sc = new Socket(ip, port); //connecting server
			sn = new Scanner(System.in);
			System.out.println("아이디 : ");
			String mid = sn.next() + "님 입장";
			InputStream is = sc.getInputStream();
			OutputStream os = sc.getOutputStream();
			System.out.println("접속성공~");
			os.write(mid.getBytes());
			while (true) {
				sc = new Socket(ip, port); // maintaining Connection
				InputStream is2 = sc.getInputStream();
				OutputStream os2 = sc.getOutputStream();
				System.out.println("입력하실 내용 입력");
				String msg = mid + "님 : " + sn.nextLine();
				os2.write(msg.getBytes());
				os2.flush();
				byte[] data = new byte[1024];
				int n = is2.read(data);
				String smsg = new String (data,0,n);
				System.out.println(smsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
