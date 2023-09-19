import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) {
		int port = 8009; //TCP , UDP port num
		ServerSocket ss = null; // TCP serverconn
		Socket sc = null; // socketconn 
		Scanner sn = null;
		try {
			ss = new ServerSocket(port); // port open
			System.out.println("챗잉서버오픈");
			while (true) { // iteration for connection 
				sc = ss.accept(); // 
				//value from client
				InputStream is = sc.getInputStream();
				//value for client
				OutputStream os = sc.getOutputStream();
				
				byte[] data = new byte[1024];
				int n = is.read(data); //read clients vlaue
				String message = new String(data,0,n); // casting byte to string
				System.out.println(message); // print on server
				sn = new Scanner(System.in);
				System.out.println("관리자 메세지 : "); // server to client
				String msg = "방장 : "+sn.nextLine();
				os.write(msg.getBytes());
				os.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
