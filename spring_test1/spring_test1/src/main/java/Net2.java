
// 네트워크 기초 외부 이미지 및 동영상 크롤링

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Net2 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("웹에서 가져올 이미지 주소를 입력하세요");
		String url = sc.nextLine();
		try {
			URL u = new URL(url); // URL(클래스) 네트워크 경로
			URLConnection con = u.openConnection();// 해당 경로를 연결
			int imgsize = con.getContentLength(); // indexOf와 비슷하다 -1이 뜨는 경우는 연결하지 못하는 상태를 의미
			String imgtype = con.getContentType();
			long date = con.getDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String day = sdf.format(date);
			//해당 데이터를 Stream을 이용해서 가져옴
			InputStream is = u.openStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			byte data[] = new byte[bis.available()];
			//파일로 저장
			FileOutputStream fos = new FileOutputStream("123.webp");
			int imgdata = 0;
			while ((imgdata = bis.read(data)) != -1) {
				fos.write(data, 0, imgdata);
			}
			fos.flush();
			fos.close();
			bis.close();
			is.close();
			System.out.println("다운러드완료");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}
}
