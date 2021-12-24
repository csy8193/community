package com.together.semiprj.member.controller;

import java.io.IOException;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.together.semiprj.common.MyRenamePolicy;
import com.together.semiprj.member.model.service.UserService;
import com.together.semiprj.member.model.vo.Animal;
import com.together.semiprj.member.model.vo.AnimalCategory;
import com.together.semiprj.member.model.vo.AnimalProfile;
import com.together.semiprj.member.model.vo.User;


@WebServlet("/member/*")
public class MyPageServletController extends HttpServlet{
	
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			UserService service = new UserService();
			
			// 데이터 전달 방식 저장용 변수
			String method = req.getMethod();
			
			// 요청 주소 뒷부분을 잘라내어 구분 방법 만들기
			String uri = req.getRequestURI();
			String contextPath = req.getContextPath();
			
			String command = uri.substring(  (contextPath + "/member/").length()  );
			// -> 요청 주소에서 /semi/board/ 의 길이만큼 잘라낸 후 
			//    나머지 문자열을 command 변수에 저장	
			
			
			String path = null;
			RequestDispatcher dispatcher = null;
			String message = null;
			
			HttpSession session = req.getSession();
			
			try {
				
				// BoardService service = new BoardService();
				
				// ***(중요)***
				// 게시판 관련 기능 수행 시 "현재 페이지"는 여러 의미로 많이 사용되기 때문에
				// 미리 얻어와서 준비 시켜두는 것이 좋다
				int cp = req.getParameter("cp") == null ? 1 : Integer.parseInt(req.getParameter("cp"));     
				
				User loginMember = (User)req.getSession().getAttribute("loginMember");
				

				
				// 반려동물 조회 리스트
				 if(command.equals("selectAnimal")) {
					
					if(method.equals("GET")) {
						
						int memberNo = loginMember.getMemberNo();
						
						List<Animal> aniList = service.selectanimalList(memberNo);

						new Gson().toJson(aniList,resp.getWriter());
						
					}
				
				}
				 
				 // 반려동물 대표이미지 변경
				 else if(command.equals("insertProfile")) {
					 
					 if(method.equals("POST")) {
						 
						 String profilePath = req.getParameter("animalProfilePath");
						 int memberNo = loginMember.getMemberNo();
						 int result = service.insertDelegateProfile(profilePath,memberNo);
						 
						 loginMember.setProfilePath(profilePath);
						 
						 
						 new Gson().toJson(result,resp.getWriter());
						
					 }
					 
				 }
				
				// 반려동물 등록
				else if(command.equals("addAnimal")) {
					
					if(method.equals("POST")) {
						
						int maxSize = 1024 * 1024 * 100; //100MB
						
						// 프로젝트의 webapp 폴더의 컴퓨터상 실제 절대 경로
						String root = session.getServletContext().getRealPath("/");
						
						// 나머지 파일경로 (DB에 저장되어 주소 경로로 사용할 예정)
						String filePath = "/resources/images/animalProfileImg/";
						
						// 실제 경로
						String realPath = root+filePath;
						
						MultipartRequest mReq = 
								new MultipartRequest(req,realPath,maxSize,"UTF-8",new MyRenamePolicy());
						
						// 반려동물 정보 파라미터 가지고 오기
						int animalCategoryCode =  Integer.parseInt(mReq.getParameter("animalCategory"));
						String animalVariety = mReq.getParameter("animalVariety");
						String animalNm = mReq.getParameter("animalNm");
						String aniBirthday = mReq.getParameter("aniBirthday");                				
						String animalGender = mReq.getParameter("animalGender");
						
						// 로그인한 멤버 번호 값
						int memberNo = loginMember.getMemberNo();

						// 파일 형식의 파라미터
						Enumeration<String> files = mReq.getFileNames();
						
						// 업로드된 이미지 정보를 담을 객체 생성
						AnimalProfile aniPro = new AnimalProfile();
						
						while(files.hasMoreElements()) {
							
							String name = files.nextElement(); 
							
							if(mReq.getFilesystemName(name) != null) {
								
								//파일 파라미터 값 가지고 오기
								aniPro.setAnimalImgNm(mReq.getFilesystemName(name));
								aniPro.setAnimalImgOriginal(mReq.getOriginalFileName(name));
								aniPro.setAnimalImgPath(filePath);
								
							}// end if
							
						} // end while

						Animal animal = new Animal(animalNm, animalVariety, animalGender,aniBirthday,memberNo,animalCategoryCode);
						
						//System.out.println(animal);
						//System.out.println(aniPro);
						
						int result = service.addAnimal(animal,aniPro);

						new Gson().toJson(result,resp.getWriter());
						
					}
				}
				// 마이페이지 이동
				 else if(command.equals("mypage")) {
					
					if(method.equals("GET")) {
						
						if(loginMember != null) {
							
							int memberNo = loginMember.getMemberNo();
							
							List<AnimalCategory> animalCategory = service.selectAnimalCategory();
							List<Animal> aniList = service.selectanimalList(memberNo);

							
							req.setAttribute("aniList", aniList);
							req.setAttribute("animalCategory", animalCategory);
							req.setAttribute("loginMember", loginMember);
							
							path = "/WEB-INF/views/member/mypage.jsp";

						}else {
							path = "/WEB-INF/views/member/login.jsp";
						}
							
						dispatcher = req.getRequestDispatcher(path);
						dispatcher.forward(req, resp);
						
					}else { // post 방식

					}
	
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
		}
		
}
