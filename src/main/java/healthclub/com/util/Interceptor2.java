package healthclub.com.util;

import java.util.Arrays;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @Class Name : Interceptor.java
 * @Description dispatcher에서 controller로 넘어가기 전 수행하는 공통 작업 영역
 * @version 1.0
 * @author 권태완
 * @Since 2024.01.14.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Interceptor2 extends HandlerInterceptorAdapter {
	@Resource(name="messageSource")
    private MessageSource messageSource;
	
	// 로그인 정보 유무 확인 세션 key 값
	private final String LOGIN_SESSION_KEY = "memberInfo";
	// 로그인 사용자 권한
	private final String MEMBER_LEVEL = "memberLevel";
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 로그인,로그아웃 공통 경로
		String loginPath = messageSource.getMessage("loginPath", null, null);
		// 로그인 창 경로
		String loginViewPath = messageSource.getMessage("loginViewPath", null, null);
		
		//세션 존재 유무 확인, false로 세션 생성 방지 
		HttpSession session = request.getSession(false);
		// 요청 uri 정보
		String requestURI = request.getRequestURI();

		// 로그인이 되지 않아 세션이 없고 로그인 경로 이동하려는 경우
		if (session == null && requestURI.contains(loginPath)) {
			return true;
			
		}
		
		// 로그인이 되지 않은 사용자가 로그인 외 페이지로 이동하려는 경우 
		if (session == null && !requestURI.contains(loginPath)) {
			response.sendRedirect(loginViewPath);
			return false;
		}

		
		// 세션 중 로그인에 관한 세션 객체 value 가져오기
		Map<String, String> memberInfo = (Map<String, String>) session.getAttribute(LOGIN_SESSION_KEY);
		
		// 로그인이 되지 않아 세션이 없고 로그인 경로 이동하려는 경우
		if (memberInfo == null && requestURI.contains(loginPath)) {
			return true;
		}
		
		// 로그인이 되지 않은 사용자가 로그인 외 페이지로 이동하려는 경우 
		if (memberInfo == null && !requestURI.contains(loginPath)) {
			response.sendRedirect(loginViewPath);
			return false;
		}
		
		
		
		// 사용자 권한 정보		
		String memberLevel =  memberInfo.get(MEMBER_LEVEL);

		
		// 세션이 존재하는 경우 메타정보 전처리
		String registerIP = request.getHeader("X-FORWARDED-FOR");  
		
		if (registerIP == null) {  
			// 요청 최초 IP 를 가져옴
			registerIP = request.getRemoteAddr();
		}
		
		request.setAttribute("metaId", memberInfo.get("id"));
		request.setAttribute("metaIp", registerIP);
		
		
		
		// memberLevel이 admin인 경우 (로그인 경로, 개인 회원 경로를 제외하고 모든 경로 접근 가능)
		if (memberLevel.equals("admin")) {
			for (String inaccessiblePath : Arrays.asList(messageSource.getMessage("adminInaccessiblePath", null, null).split(","))) {
				if (requestURI.contains(inaccessiblePath.trim())) {
					return false;
				}
			}
			
			return true;
		}
		
		// memberLevel이 trainer인 경우 (로그인 경로, 트레이너 관리, 통계 관리 , 수업 수정, 수업 상태 변경, 빼고 접근 가능)
		if (memberLevel.equals("trainer")) {
			
			// 접근 불가능한 경로
			for (String inaccessiblePath : Arrays.asList(messageSource.getMessage("trainerInaccessiblePath", null, null).split(","))) {
				if (requestURI.contains(inaccessiblePath.trim())) {
					return false;
					
				}
			}
			
			
			// 트레이너가 접근 가능한 대메뉴 중 실제 접근 가능한 소메뉴 분류가 존재하는 경우 
			for (String accessiblePath : Arrays.asList(messageSource.getMessage("trainerAccessiblePathDetail", null, null).split(","))) {
				
				// 요청 경로가 특정 소메뉴에만 접근 가능한 경로를 포함하는 경우
				if (requestURI.contains((accessiblePath.trim()))) {
					
					// 요청 경로와 접근 가능한 소메뉴 경로가 정확히 동일한 경우 true, 아니면 false
					if (requestURI.equals((accessiblePath.trim()))) {
						return true;
						
					} else {
						return false;
						
					}
				}
			}
			
			// 요청 경로가 대메뉴 다 허용하는 경로 인 경우 true
			return true;

		}

		
		
		
		// memberLevel이 user인 경우 접근 가능한 경로
		if (memberLevel.equals("user")) {
			for (String accessiblePath : Arrays.asList(messageSource.getMessage("userAccessiblePath", null, null).split(","))) {
				if (requestURI.contains(accessiblePath.trim())) {
					return true;
				}
			}
			
			return false;
		}
		
		

		
		// 만에 하나 잡지 못한 경우 로그인 페이지로 이동 시킨다.
		response.sendRedirect(loginViewPath);
		return false;
		
	}
	
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	// 로그아웃 후 뒤로가기 방지
    	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
    	response.setHeader("expires","0"); 
    	response.setHeader("pragma","no-cache");
    }
	
    

	
}
