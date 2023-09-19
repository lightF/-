package sp1;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class webpage2 {

	PrintWriter pw = null;
	Login_Etc le = null;
	
	// 카카오로그인
	@PostMapping("/klogin.do")
	public String klogin(HttpServletRequest req, Model model) throws Exception {
		String part = req.getParameter("part");
		int call = 0;
		if (part.equals("kakao")) {
			String kid = req.getParameter("kakaoid");
			String kemail = req.getParameter("kakaomail");
			String knick = req.getParameter("kakaonick");
			le = new Login_Etc(kid, kemail, knick, part);
		} else {
			String name = req.getParameter("mname");
			String pw = req.getParameter("mpw");
			le = new Login_Etc(name, pw, "", part);
		}
		le.join();
		if (le.getCall() > 0) {
			System.out.println("ok");

		} else {
			System.out.println("error");
		}

		return null;
	}

	// JSTL로 뷰페이지 출력 파트
	@RequestMapping("/product_list.do")
	public String pd_list(HttpServletRequest req, Model m) {
		List<ArrayList<String>> product_data = null;
		try {
			product_list pl = new product_list();
			int ea = pl.data_ea();
			// product_data = pl.listdata();
			// JSP 형태로 View 출력
			// 회원가입 카운팅 전체 숫자값
			req.setAttribute("product_data", product_data);
			req.setAttribute("ea", ea);

		} catch (Exception e) {
			System.out.println(e);
		}

		return "/WEB-INF/jsp/product_list";
	}

	// 상품삭제
	@GetMapping("/product_delete.do")
	public void delete_pd(HttpServletRequest req, HttpServletResponse res) {
		Product_del pd = new Product_del();
		res.setContentType("text/html;charset=utf-8");
		try {
			pw = res.getWriter();
			String no = req.getParameter("idx");
			int result = pd.delete_ok(no);
			if (result == 1) {
				pw.write("<script>alert('삭제완료');location.href='./product_list.do'</script>");
			} else {
				pw.write("<script>alert('올바른 데이터 값 아님');location.href='./product_list.do'</script>");
			}

		} catch (Exception e) {
			pw.write("<script>alert('잘못된 접근 방식');history.go(-1)</script>");
		}

	}

	// 상품수정
	@GetMapping("/product_modify.do")
	public String pd_modify(HttpServletRequest req, Model model) {
		String idx = req.getParameter("idx");
		try {
			Product_Modify pm = new Product_Modify();
			ArrayList<String> data = pm.view_ok(idx);
			model.addAttribute("data", data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/product_modify";
	}

	// spring1.html에서 넘어온 값을 view를 통해서 핸들링 하는 형태
	@RequestMapping("/spring1ok.do")
	public String product(HttpServletRequest req, HttpServletResponse res, Model m) {
		String pdcode = "";
		String pdname = "";
		try {
			pdcode = req.getParameter("pdcode").intern();
			pdname = req.getParameter("pdname").intern();
			m.addAttribute("name", pdname);
			m.addAttribute("code", pdcode);
		} catch (Exception e) {
			System.out.println(e);
		}

		return "/WEB-INF/jsp/spring1ok";
	}

	// 수정완료
	@PostMapping("product_modifyok.do")
	public String okModify(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html;charset=utf-8");
		String pidx = req.getParameter("pidx");
		String pcode = req.getParameter("pcode");
		String pname = req.getParameter("pname");
		String pmoney = req.getParameter("pmoney");
		String psale = req.getParameter("psale");
		String puse = req.getParameter("puse");
		ProductOk pk = new ProductOk();
		try {
			pw = res.getWriter();
			int result = pk.modify_sql(pidx, pcode, pname, pmoney, psale, puse);
			if (result == 1) {
				pw.write("<script>alert('수정완료');location.href='./product_list.do'</script>");
			} else {
				pw.write("<script>alert('올바른 데이터 값 아님');history.go(-1)</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		pw.close();
		return "WEB-INF/jsp/product_list";
	}

	@PostMapping("/spring2ok.do")
	public String agree(HttpServletRequest req, Model m) {
		String ag = req.getParameter("mail");
		// checkbox 사용시 intern()사용하지 않음(checkbox의 값으론 null이넘어오지만 radio value 값엔 null이
		// 없어서일까?)
		String ad = req.getParameter("ad").intern();
		System.out.println(ad);
		if (ad == "N") {
			System.out.println("동의안함");
		} else {
			System.out.println("동의함");
		}

		if (ag == null) {
			ag = "N";
		}

		System.out.println(ag);

		return null;
	}
	/*
	 * 이미지 삭제 예시 String url = getServletContext().getRealPath("/"); String filename
	 * = db저장값 File file = new File(url + filename); file.delete();
	 */

	// Getter와 Setter를 이용해서 값을 로드
	@PostMapping("/spring3ok.do")
	public String user(HttpServletRequest req, Model m) {
		String mid = req.getParameter("mid");
		String mname = req.getParameter("mname");

		userdata ud = new userdata(mid, mname);
		System.out.println(ud.getMid());
		System.out.println(ud.getMname());

		return null;
	}

	@PostMapping("/spring4ok.do")
	public String mails(HttpServletRequest req, Model m) {
		String mname = req.getParameter("mname");// 보낸이
		String mail = req.getParameter("mail"); // 회신받을 메일
		String mtitle = req.getParameter("mtitle");// 제목
		String mcontent = req.getParameter("mcontent");// 내용
		// 실제 메일 api 서버 정보를 입력
		String host = "smtp.naver.com";
		String user = "fluctuating5493@naver.com";
		String password = "sk85712564!";
		String to_mail = "fluctuating5493@naver.com";

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.socketFactory", 587);
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");

		// SMTP서버에 로그인을 시키기 위한 작업(암호화)
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getpassPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		try {
			// MimeMessage : okcall 발생함 아이디/패스워드, 포트모두 맞을경우
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(to_mail, mname)); // 보낸이
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mail)); // 받는 주소
			msg.setSubject(mtitle); // 제목
			msg.setText(mcontent); // 내용
			Transport.send(msg);
		} catch (Exception e) {
			System.out.println("메일 서버 통신 오류");
		}

		return null;
	}

	// Controller에서 배열을 JSP(view)로 출력하는 형태
	@GetMapping("/spring5ok.do")
	public String datalist(HttpServletRequest req, Model m) {
		dbconfig db = new dbconfig();
		try {
			Connection ct = db.info();
			System.out.println(ct.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		String data[] = { "이순신", "홍길동", "강감찬", "이산", "한석봉" };
		ArrayList<String> al = new ArrayList<String>(Arrays.asList(data));

		// jsp방식 (제일 구식)
		// req.setAttribute("person_list", al);
		// return "/WEB-INF/jsp/spring5ok"; //일반JSP View

		// 표현식 jsp 방식 (그다음 구식)
		m.addAttribute("person_list", al); // 표현식방식
		m.addAttribute("person_delete", "10");

		// 표현식 값을 javascript 전달(Front-end) Node 형태로 출력

		return "/WEB-INF/jsp/spring5_2ok"; // 표현식JSP View
	}

	@RequestMapping("/spring6ok.do")
	public String userlist(HttpServletRequest req, Model m) {
		// 파라미터값으로 검색어가 적용되는 경우
		String search = req.getParameter("search");
		String part = req.getParameter("part");

		List<ArrayList<String>> member_data = null;
		try {
			// 검색이 없을경우
			if (search == "" || search == null || search == "null") {
				user_list ul = new user_list();
				member_data = ul.listdata();
			} else {// 검색 단어가 있을 경우
				user_list ul = new user_list();
				member_data = ul.listdata(search, part);
			}
			// JSP 형태로 View 출력
			// 회원가입 카운팅 전체 숫자값
			req.setAttribute("total", new user_list().total_member());
			req.setAttribute("member_data", member_data);
			req.setAttribute("part", part);
		} catch (Exception e) {
			System.out.println(e);
		}

		return "/WEB-INF/jsp/member_list";
	}

	@PostMapping("/fileuploadok.do")
	public void fileupload(MultipartFile mfile, HttpServletRequest req, Model model) throws Exception {
		String filename = mfile.getOriginalFilename();
		long filesize = mfile.getSize();
		String url = req.getServletContext().getRealPath("/fileup/") + filename;
		// 파일저장
		File f = new File(url);
		FileCopyUtils.copy(mfile.getBytes(), f);
		System.out.println("파일업로두성공");

	}

	@PostMapping("/reserveok.do")
	public String air_reserve(Model model, @RequestParam String aplane_number, @RequestParam String acorp,
			@RequestParam String anation, @RequestParam String adate, @RequestParam String adate2,
			@RequestParam String adate3, @RequestParam String amoney, @RequestParam String areserve) {
		Simplify sp = new Simplify();
		// adate = sp.day(adate);
		// adate2 = sp.day(adate2);
		// adate3 = sp.day(adate3);
		int result = new AirQuery().result(aplane_number, acorp, anation, adate, areserve, amoney, adate2, adate3);
		if (result == 0) {
			System.out.println("ok");
		} else {
			System.out.println("error");
		}
		model.addAttribute("msg", result);
		return "/WEB-INF/jsp/air_reserveok";
	}
	@RequestMapping("/air_list.do")
	public String air_list(Model model) {
		
		
		return "/air_list";
}

	
	

	@PostMapping("/personok.do")
	public String air_person(
			@RequestParam("mid") String mid, 
			@RequestParam("mname") String mname,
			@RequestParam("mpost") String mpost, 
			@RequestParam("mtel") String mtel,
			@RequestParam("mperson") String mperson, 
			@RequestParam(value="totalmoney", required=false) String totalmoney,Model model)
			throws Exception {
		int result = new air_sql().insert(mid, mname, mpost, mtel, mperson, totalmoney);
		try {
			if (result == 0) {
				System.out.println("ok");
			} else {
				System.out.println("error");
			}

		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("msg", result);
		}
		return null;
	}
}
