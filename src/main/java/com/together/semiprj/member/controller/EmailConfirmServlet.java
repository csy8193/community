package com.together.semiprj.member.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/member/emailConfirm")
public class EmailConfirmServlet extends HttpServlet {
	
	  @Override
	   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		  String sendMail = req.getParameter("inputEmail");
	      System.out.println("보낼 이메일 주소입니다 :" +sendMail);
	      
	      String host = "smtp.gmail.com";
	      String user = "sseungjoon0319@gmail.com"; // 자신의  계정
	      String password = "joon0319!";// 자신의 네이버 패스워드

	      // 메일 받을 주소
	      /* String to_email = m.getEmail(); */
	      String to_email = sendMail;

	      // SMTP 서버 정보를 설정한다.
	      Properties props = new Properties();
	           
          props.put("mail.transport.protocol", "smtp");
          props.put("mail.smtp.host", "smtp.gmail.com");
          props.put("mail.smtp.port", "587");
          props.put("mail.smtp.auth", "true");
       
          props.put("mail.smtp.quitwait", "false");
          props.put("mail.smtp.socketFactory.port", "587");
          props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
          props.put("mail.smtp.socketFactory.fallback", "true");
          props.put("mail.smtp.starttls.enable","true");


	      // 인증 번호 생성기
	      StringBuffer temp = new StringBuffer();
	      Random rnd = new Random();
	      for (int i = 0; i < 10; i++) {
	         int rIndex = rnd.nextInt(3);
	         switch (rIndex) {
	         case 0:
	            // a-z
	            temp.append((char) ((int) (rnd.nextInt(26)) + 97));
	            break;
	         case 1:
	            // A-Z
	            temp.append((char) ((int) (rnd.nextInt(26)) + 65));
	            break;
	         case 2:
	            // 0-9
	            temp.append((rnd.nextInt(10)));
	            break;
	         }
	      }
	      String AuthenticationKey = temp.toString();
	      System.out.println(AuthenticationKey);

	      Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(user, password);
	         }
	      });

	      // email 전송
	      try {
	         MimeMessage msg = new MimeMessage(session);
	         msg.setFrom(new InternetAddress(user, "TOGETHER"));
	         msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));

	         // 메일 제목
	         msg.setSubject("안녕하세요 투개더 인증 메일입니다.");
	         // 메일 내용
	         msg.setText("인증 번호는 : " + temp);

	         Transport.send(msg);
	         // System.out.println("이메일 전송");

	         resp.getWriter().print(temp);
	         
	      } catch (Exception e) {
	         e.printStackTrace();// TODO: handle exception
	      }
	      HttpSession saveKey = req.getSession();
	      saveKey.setAttribute("AuthenticationKey", AuthenticationKey);
	      // 패스워드 바꿀때 뭘 바꿀지 조건에 들어가는 id
	      /*
	       * req.setAttribute("id", memberId);
	       * req.getRequestDispatcher("/views/login_myPage/searchPasswordEnd.jsp").forward
	       * (req, resp);
	       */
	      
	      
	      
	      
	   }
	   
	}