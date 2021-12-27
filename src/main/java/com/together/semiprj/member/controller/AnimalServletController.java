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
import com.together.semiprj.member.model.vo.Board;
import com.together.semiprj.member.model.vo.User;


@WebServlet("/member/*")
public class AnimalServletController extends HttpServlet{
	
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
						
						
						aniPro.setAnimalImgNm("basis-profile-img.png");
						aniPro.setAnimalImgOriginal("basis-profile-img.png");
						aniPro.setAnimalImgPath("/resources/images/main/");
						
						
						while(files.hasMoreElements()) {
							
							String name = files.nextElement(); 
							
							System.out.println("얻어온 file name: "+name);
							System.out.println("변환된 파일명: "+mReq.getFilesystemName(name));
							System.out.println("원본파일명 : "+mReq.getOriginalFileName(name));
							
							
							if(mReq.getFilesystemName(name) != null) {
								
								//파일 파라미터 값 가지고 오기
								aniPro.setAnimalImgNm(mReq.getFilesystemName(name));
								aniPro.setAnimalImgOriginal(mReq.getOriginalFileName(name));
								aniPro.setAnimalImgPath(filePath);
								
							}
							
							
						} // end while
						
						

						Animal animal = new Animal(animalNm, animalVariety, animalGender,aniBirthday,memberNo,animalCategoryCode);
						
						System.out.println("등록 start");
						
						System.out.println(animal);
						System.out.println(aniPro);
						
						
						
						int result = service.addAnimal(animal,aniPro);

						new Gson().toJson(result,resp.getWriter());
						
					}
				}
				
				//수정하기 반려동물
				else if(command.equals("updateAnimal")) {
						
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
						int animalNo =  Integer.parseInt(mReq.getParameter("animalNo"));
						int animalCategoryCode =  Integer.parseInt(mReq.getParameter("animalCategory"));
						String animalVariety = mReq.getParameter("animalVariety");
						String animalNm = mReq.getParameter("animalNm");
						String aniBirthday = mReq.getParameter("aniBirthday");                				
						String animalGender = mReq.getParameter("animalGender");
						

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
						
						
						Animal animal = new Animal(animalNo,animalNm,animalVariety,animalGender,aniBirthday,animalCategoryCode);

						System.out.println("수정 start");
						int result = service.updateAnimal(animal,aniPro);

						resp.getWriter().print(result);
						
					}
				}
				 
				 
				 // 반려동물삭제하기 
				 else if(command.equals("deleteAnimal")) {
						
					if(method.equals("POST")) {
						
						int animalNo =  Integer.parseInt(req.getParameter("animalNo"));
						
						System.out.println("삭제 start");
						int result = service.deleteAnimal(animalNo);
						
						resp.getWriter().print(result);
					}
				} 
				 
				 
				
				 
				 // 비밀번호 변경
				 if(command.equals("mypagePwUpdate")) {
						
						if(method.equals("POST")) {
							
							String nowPw = req.getParameter("nowPw");
							String updatePw2 = req.getParameter("userPw2");
							int memberNo = loginMember.getMemberNo();						
							
							int result = service.mypagePwUpdate(updatePw2, nowPw, memberNo);
							
							
							if(result > 0) {
								message = "비밀번호 변경 완료";
								path = "mypage";
							}else {
								message = "현재 비밀번호가 일치하지 않습니다.";
								path = "mypage";
							}
							req.getSession().setAttribute("message",message);			
							resp.sendRedirect(path);
						}
						
					}
				 
				 
				 // 회원 탈퇴
				 if(command.equals("mypagePwDelete")) {
						
						if(method.equals("POST")) {
							
							String nowPw = req.getParameter("myPagedelete");
							int memberNo = loginMember.getMemberNo();
							
							System.out.println("test22"+nowPw);
							System.out.println("test22"+memberNo);	
							
							int result = service.mypagePwDelete(nowPw, memberNo);
							
							
							System.out.println("탈퇴"+result);
							
							if(result > 0) {
								message = "회원 탈퇴 완료";
								path = req.getContextPath(); // 메인 페이지
								session.invalidate();
							}else {
								message = "현재 비밀번호가 일치하지 않습니다.";
								path = "mypage";
							}
							req.getSession().setAttribute("message",message);			
							resp.sendRedirect(path);
						}
						
					}
				 
				 // 게시판 리스트
				 else if(command.equals("selectBoardList")) {
						
					 if(method.equals("GET")) {
						 
						 int boardCd =  Integer.parseInt(req.getParameter("boardCd"));
						 int memberNo = loginMember.getMemberNo();
						 
						 List<Board> bList = service.selectBoardList(memberNo,boardCd);
						 
						 System.out.println(bList);
						 
						 new Gson().toJson(bList,resp.getWriter());
						 
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
