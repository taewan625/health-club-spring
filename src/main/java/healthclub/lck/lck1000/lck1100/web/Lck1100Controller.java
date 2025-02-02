package healthclub.lck.lck1000.lck1100.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

import healthclub.com.vo.SearchVO;
import healthclub.lck.lck1000.lck1100.service.Lck1100Service;

/**
 * @Class Name : Lck1100Controller.java
 * @Description 사물함 이력 관리 controller 
 * @version 1.0
 * @author 권태완
 * @Since 2024.01.18.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
@Controller
@RequestMapping(value="/lck/lck1000/lck1100/")
public class Lck1100Controller {

	@Resource(name="messageSource")
    private MessageSource messageSource;
	
	@Resource(name = "lck1100Service")
	private Lck1100Service lck1100Service;
		
	/**
	 * Func : 사물함이력 정보 관리 메서드
	 * 
	 * @desc 사물함이력정보관리 페이지를 보여준다.
	 * @param 
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="selectLck1100View.do", method=RequestMethod.GET)
	public String selectLck1100View() throws Exception {
		return "/lck/lck1000/lck1100/lck1100";
	}
		
		
		
	/**
	 * Func : 사물함이력정보 목록 메서드
	 * 
	 * @desc 사물함이력정보 목록을 보여줌
	 * @param HttpServletRequest request, ModelMap model
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="selectLck1100LockerList.do", method=RequestMethod.POST)
	public String selectLck1100LockerList(HttpServletRequest request, ModelMap model) throws Exception {		
		try {
			// json 요청 전처리
			Map<String, String> jsonRequestDatas = new ObjectMapper().readValue(request.getInputStream(), Map.class);
			// DAO에서 항상 공통으로 사용하는 클래스
			model.addAttribute("jsonResponseData", lck1100Service.selectLck1100LockerList(new SearchVO(jsonRequestDatas)));
			model.addAttribute("responseStatus", 1);
	        
		} catch (Exception e) {
			model.addAttribute("failMsg", messageSource.getMessage("error.response.data", null, null));
			model.addAttribute("responseStatus", -1);
		}

		return "jsonView";
	}
}
