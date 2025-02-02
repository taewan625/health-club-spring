package healthclub.ucs.ucs1000.ucs1000.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import healthclub.ucs.ucs1000.ucs1000.service.Ucs1000Service;


/**
 * @Class Name : Ucs1000Controller.java
 * @Description 개인 회원 수업 일정 컨트롤러
 * @version 1.0
 * @author 권태완
 * @Since 2024.01.18.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
@Controller
@RequestMapping(value="/ucs/ucs1000/ucs1000/")
public class Ucs1000Controller {

	@Resource(name="messageSource")
    private MessageSource messageSource;
	
	@Resource(name = "ucs1000Service")
	private Ucs1000Service ucs1000Service;
	
	
	/**
	 * Func : 개인 수업정보 화면 메서드
	 * 
	 * @desc 개인 수업정보
	 * @param 
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="selectUcs1000View.do", method=RequestMethod.GET)
	public String selectUcs1000View() throws Exception {
		return "ucs/ucs1000/ucs1000/ucs1000";
	}

	/**
	 * Func : 개인 수업정보 목록 메서드
	 * 
	 * @desc 수업정보 목록을 보여줌
	 * @param HttpServletRequest request, ModelMap model
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="selectUcs1000PtList.do", method=RequestMethod.POST)
	public String selectUcs1000PtList(HttpServletRequest request, ModelMap model) throws Exception {
		try {
			Map<String, String> memberInfo = (Map<String, String>) request.getSession(false).getAttribute("memberInfo");
			String userId = memberInfo.get("loginId");
			
			// DAO에서 항상 공통으로 사용하는 클래스
			List<Map<String, String>> userPtList = ucs1000Service.selectUcs1000PtList(userId);
			model.addAttribute("responseStatus", 1);
			model.addAttribute("userPtList", userPtList);
			
		} catch (Exception e) {
			model.addAttribute("responseStatus", -1);
			model.addAttribute("failMsg", messageSource.getMessage("error.save.data", new Object[] { "수업정보" }, null));
		}
		return "jsonView";

	}	
	
	
	/**
	 * Func : 수업상세 화면 메서드
	 * 
	 * @desc 수업상세 화면을 보여준다.
	 * @param @RequestParam(name="postPopupValue") String ptId, ModelMap model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="selectUcs1001View.do", method=RequestMethod.POST)
	public String selectUcs1001View(@RequestParam(name="postPopupValue") String ptId, ModelMap model) throws Exception {
		// 해당 회원 정보 가져 오기
		model.addAttribute("userPtInfo", ucs1000Service.selectUcs1000DetailUserPtInfo(ptId));
		return "ucs/ucs1000/ucs1000/ucs1001";
	}
}
