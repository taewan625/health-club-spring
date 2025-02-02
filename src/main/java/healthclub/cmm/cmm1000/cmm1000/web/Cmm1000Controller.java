package healthclub.cmm.cmm1000.cmm1000.web;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;

import healthclub.cmm.cmm1000.cmm1000.service.Cmm1000Service;


/**
 * @Class Name : Cmm1000Controller.java
 * @Description 로그인 처리 컨트롤러
 * @version 1.0
 * @author 권태완
 * @Since 2024.01.12.
 * @Modification Information
 * @
 * @  수정일            수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2024.01.12	   권태완		  최초생성
 * @ 2024.03.09	   권태완		 로그인 조회 시, 권한 조회 방식을 sql에서 받아오는 형식으로 변경
 * 
 * @see Copyright (C) All right reserved.
 */
@Controller
@RequestMapping(value="/cmm/cmm1000/cmm1000/")
public class Cmm1000Controller {

	@Resource(name="messageSource")
    private MessageSource messageSource;
	
	@Resource(name = "cmm1000Service")
	private Cmm1000Service cmm1000Service;
	
	
	/**
	 * Func 로그인 화면 반환 메서드
	 * 
	 * @desc 로그인 화면 경로 반환
	 * @param 
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="selectCmm1000View.do", method=RequestMethod.GET)
	public String selectCmm1000View() throws Exception {
		return "cmm/cmm1000/cmm1000/cmm1000";
	}

	
	/**
	 * Func 로그인 확인 후 성공 여부에 따라 이동하는 메서드
	 * 
	 * @desc 로그인 확인 후 성공 여부에 따라 경로 이동
	 * @param HttpServletRequest request, HttpSession session, ModelMap model
	 * @return String
	 * @throws Exception
	 * TODO 
	 * 	이거 controller 로직이 여기에 있으면 안되서 service에서'1', '-1' 이런 처리 하고 실제 컨트롤러에서는 딱 단순 분기처리만 하도록 해야 역할분담이 존재하도록 된다.
	 * 	변수를 inline으로 하는 경우와 분리하는 경우를 고려할 필요가 있다.
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="selectCmm1000loginInfo.do", method=RequestMethod.POST)
	public String selectCmm1000loginInfo(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
		try {
			// json 요청 전처리
			Map<String, String> loginFormData = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            
            // 비밀번호 sha256으로 변환
            loginFormData.put("loginPw", Hashing.sha256().hashString(loginFormData.get("loginPw"), StandardCharsets.UTF_8).toString());
            
			// 사용자 정보 조회
			Map<String, String> memberInfo = cmm1000Service.selectUsr1000Tra1000LoginInfo(loginFormData);
			
			// 해당 사용자 정보가 존재하지 않을 경우 실패 처리
			if (memberInfo == null) {
				model.addAttribute("responseStatus", -1);
				model.addAttribute("failMsg", messageSource.getMessage("fail.login", null, null));
				return "jsonView"; 
			}
			
			// 회원이 이동하는 페이지 경로
			if ("user".equals(memberInfo.get("memberLevel"))) {
				model.addAttribute("url", "/ucs/ucs1000/ucs1000/selectUcs1000View.do");
				
			} 
			// 관리자, 트레이너가 이동하는  페이지 경로
			else {
				model.addAttribute("url", "/usr/usr1000/usr1000/selectUsr1000View.do");
			}
			
			// meta data : 등록자 ip
			String registerIP = request.getHeader("X-FORWARDED-FOR");  
			if (registerIP == null) {
				registerIP = request.getRemoteAddr();
			}  
			
			// 사용자 정보에 현재 접속 ip 등록
			memberInfo.put("loginIp", registerIP);
			
			// 사용자 정보 세션 저장
			session.setAttribute("memberInfo", memberInfo);
			
			// 성공 코드 전송
			model.addAttribute("responseStatus", 1);
			
		} catch (Exception e) {
			// 예외 발생 시, message.properties 내용 반환
			model.addAttribute("responseStatus", -1);
			model.addAttribute("failMsg", messageSource.getMessage("error.login", null, null));
		}

		return "jsonView";
	}
	

	/**
	 * Func : 사용자 id 확인  메서드
	 * 
	 * @desc 사용 가능한 회원 id 인지 확인 하는 업무처리
	 * @param HttpServletRequest request, ModelMap model
	 * @return String
	 * @throws Exception 
	 */
	@RequestMapping(value="selectCmm1000checkMemberId.do",  method=RequestMethod.POST)
	public String selectCmm1000checkMemberId(HttpServletRequest request, ModelMap model) throws Exception {
		try {
			// json 요청 전처리
			String memberId = new ObjectMapper().readValue(request.getInputStream(), String.class);

	        // 중복 데이터가 존재 시, 실패 문구와 실패 상태를 json에 담아서 보냄
	        if(cmm1000Service.selectUsr1000Tra1000Id(memberId)) {
	        	model.addAttribute("responseStatus", -1);
	        	model.addAttribute("failMsg", messageSource.getMessage("fail.checkId", null, null));
	        	
	        } 
	        // 중복 데이터가 없을 시, 성공 문구와 성공 상태를 json에 담아서 보냄
	        else {
	        	model.addAttribute("responseStatus", 1);
	        	model.addAttribute("successMsg", messageSource.getMessage("success.checkId", null, null));
	        }
	        
		} catch (Exception e) {
			model.addAttribute("responseStatus", -1);
			model.addAttribute("failMsg", messageSource.getMessage("error.checkId", null, null));
		}
		
		return "jsonView";
	}
	
	
	/**
	 * Func : user pw 확인  메서드
	 * 
	 * @desc 올바른 pw 인지 확인 
	 * @param HttpServletRequest request, ModelMap model
	 * @return String 
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="selectCmm1000CheckMemberPw.do",  method=RequestMethod.POST)
	public String selectCmm1000CheckMemberPw(HttpServletRequest request, ModelMap model) throws Exception {
		try {
			// json 요청 전처리
			Map<String, String> memberIdPw = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            
            memberIdPw.put("memberPw", Hashing.sha256().hashString(memberIdPw.get("memberPw"), StandardCharsets.UTF_8).toString());
            
            // 기존 패스워드와 동일하면 성공 메시지 전송
	        if (cmm1000Service.selectUsr1000Tra1000Pw(memberIdPw)) {
	        	model.addAttribute("responseStatus", 1);
	        	model.addAttribute("successMsg", messageSource.getMessage("success.checkPw", null, null));
	        	
	        } 
	        // 기존 패스워드와 다르면 실패 메시지 전송
	        else {
	        	model.addAttribute("responseStatus", -1);
	        	model.addAttribute("failMsg", messageSource.getMessage("fail.checkPw", null, null));
	        }
	        
		} catch (Exception e) {
			model.addAttribute("responseStatus", -1);
			model.addAttribute("failMsg", messageSource.getMessage("error.checkPW", null, null));
		}

		return "jsonView";
	}
	
	
	/**
	 * Func 로그아웃 확인 후 성공 여부에 따라 이동하는 메서드
	 * 
	 * @desc 로그아웃 확인 후 성공 여부에 따라 이동
	 * @param HttpSession session,  ModelMap model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="selectCmm1000Logout.do", method=RequestMethod.GET)
	public String selectCmm1000Logout(HttpSession session,  ModelMap model) throws Exception {		
		try {
			if (session != null) {
				// 로그아웃 시, 세션 강제 제거
				session.invalidate();
			}
			
			// 로그인 화면 경로로 redirect
			return "redirect:/cmm/cmm1000/cmm1000/selectCmm1000View.do";
					
		} catch (Exception e) {
			// 예외 발생 시, message.properties 내용 반환
			model.addAttribute("responseStatus", -1);
			model.addAttribute("failMsg", messageSource.getMessage("error.logout", null, null));
			return "jsonView";
		}
	}
}
