package healthclub.com.util;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @Class Name : Interceptor.java
 * @Description dispatcher에서 controller로 넘어가기 전 수행하는 공통 작업 영역
 * @version 1.0
 * @author 권태완
 * @Since 2024.02.16.
 * @Modification Information
 * @
 * @  수정일            수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2024.02.16	   권태완		  최초생성
 * @ 2024.03.11	   권태완		 패키지 경로에 접근 가능한 권한과 현재 접속 권한 비교로 업무컨트롤러 진입 여부 판단하도록 로직 수정
 * 
 * @see Copyright (C) All right reserved.
 */
public class Interceptor extends HandlerInterceptorAdapter {
	@Resource(name="messageSource")
    private MessageSource messageSource;
	
	// 로그인 view 경로
	private final String LOGIN_PATH = "/cmm/cmm1000/cmm1000/selectCmm1000View.do";
	// 로그인 로직 경로
	private final String LOGIN_LOGIC_PATH = "/cmm/cmm1000/cmm1000/selectCmm1000loginInfo.do";
	
	
	/**
	 * Func : 프리핸들러
	 * 
	 * @desc 접근 권한 여부 확인
	 * @param HttpServletRequest request, HttpServletResponse response, Object handler
	 * @return boolean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 전체 url
		String requestURI = request.getRequestURI();

		// session에 존재하는 권한 조회
		String level = "";

		// session이 존재하고 memberInfo라는 session이 존재하는 경우 level를 조회하고 level 변수에 해당 권한 담기
		if (request.getSession(false) != null) {
			// memberInfo라는 session이 존재하는 경우
			Object memberInfo = request.getSession(false).getAttribute("memberInfo");

			if (memberInfo != null) {
				level = ((Map<String, String>) memberInfo).get("memberLevel");
			}
		}


		// 최종 retrun으로 보낼 boolean
		boolean result = false;
		
		// 로그인 관련 경로로 이동하는 경우 : 로그인 화면 접근 경로, 로그인 처리 로직 경로
		if (LOGIN_PATH.equals(requestURI) || LOGIN_LOGIC_PATH.equals(requestURI)) {
			// 로그인 하지 않은 사용자의 접근
			if (level.isEmpty()) {
				result = true;
				
			} 
			// 이미 로그인을 완료한 사용자의 경우 사용자 권한별 초기 접근 경로로 이동시킨다.
			else {
				moveInitPath(level, response);
			}
			
		}
		// 로그인 외의 경로로 이동하는 경우
		else {
			// 로그인 하지 않은 사용자가 접근할 시, 로그인 경로로 강제 이동
			if (level.isEmpty()) {
				response.sendRedirect(LOGIN_PATH);
			
			}
			/* 
			 * "url-path.properties에 저정되어있는 경로별 접근 권한" 에 포함되는 "사용자의 권한" 일 경우 해당 경로로 이동 가능
			 * 
			 * 참고 : messageProperties에서 받아오는 값은 String 이고, null이 발생할 수 없다
			 * 이유 : messageProperties를 조회 시, 찾을 수 있는 key가 없는 경우 null을 반환 하는 것이 아닌 예외가 발생시키기 때문에 interceptor자체가 동작하지 못한다. (확인 완료)
			 */
			else if (messageSource.getMessage(requestURI.substring(0, requestURI.lastIndexOf("/")), null, null).contains(level)) {
				result = true;
				
			}
			// "url-path.properties에 저정되어있는 경로별 접근 권한" 에 포함되지 않는 "사용자의 권한" 일 경우 사용자 권한별 초기 접근 경로로 이동시킨다.
			else {
				moveInitPath(level, response);
			}
		}
		
		return result;
	}


	/**
	 * Func : 로그인 시  초기 진입하는 경로로 이동 시키는 메서드
	 * 
	 * @desc 로그인 시  초기 진입하는 경로로 이동
	 * @param String level, HttpServletResponse response
	 * @return void
	 * @throws Exception
	 */
	private void moveInitPath(String level, HttpServletResponse response) throws Exception {
		// 권한이 user 인 경우 개인 수업일정 페이지로 이동한다.
		if ("user".equals(level)) {
			response.sendRedirect("/ucs/ucs1000/ucs1000/selectUcs1000View.do");
			
		}
		// 권한이 admin, trainer 인 경우 회원관리페이지로 이동한다.
		else if ("admin".equals(level) || "trainer".equals(level)) {
			response.sendRedirect("/usr/usr1000/usr1000/selectUsr1000View.do");
		}
	}
	
	
	/**
	 * Func : 포스트핸들러
	 * 
	 * @desc 항상 캐시 지우기
	 * @param HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView
	 * @return void
	 * @throws Exception
	 */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	// no-cache : 캐시된 버전을 사용하지 말고 서버로부터 리소스를 다시 가져와야 함
    	// no-store : 요청 및 응답을 통해 전달된 데이터를 캐시하지 말아야 함
    	// no-cache : 캐시가 만료되면 원본 서버로부터 새로운 리소스를 가져와야 함
    	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
    	
    	// expires, 0 : 응답이 즉시 만료
    	response.setHeader("expires", "0"); 
    	
    	// pragma, no-cache : 캐시 사용을 금지하고, 리소스를 가져올 때마다 서버로부터 새로운 데이터를 받아야 함
    	response.setHeader("pragma", "no-cache");
    }
}