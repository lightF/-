//외부 URL 접속 정보 알아오기

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class Net3 {
	public static void main(String[] args) throws Exception {
		String url = "https://dragonflight.blizzard.com/ko-kr/?_gl=1*8t2703*_ga*OTUxOTAxODg3LjE2OTQ0ODQzMTg.*_ga_VYKNV7C0S3*MTY5NDQ4NDMxNy4xLjEuMTY5NDQ4NDQ1Mi42MC4wLjA.#masthead";
		URL url2 = new URL(url);
		URLConnection con = url2.openConnection();
		System.out.println(url2.getProtocol()); // getProtocol http인지? https인지?
		System.out.println(url2.getPort());// 포트정보
		System.out.println(url2.getHost());// 도메인정보
		System.out.println(url2.getFile());// 경로 + 파라미터값
		System.out.println(url2.getPath());// 현재 경로
		System.out.println(url2.getQuery());// 파라미터값만
		InputStream is = url2.openStream();
		InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		BufferedReader br = new BufferedReader(isr);
		PrintWriter pw = new PrintWriter(new FileOutputStream("main.html"));
		String str = "";
		while((str=br.readLine())!=null) {
			pw.println(str);
		}
		pw.close();
		br.close();
		isr.close();
		is.close();
		
	}
}
