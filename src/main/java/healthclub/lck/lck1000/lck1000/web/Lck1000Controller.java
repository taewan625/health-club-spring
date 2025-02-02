package healthclub.lck.lck1000.lck1000.web;

import java.util.HashMap;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import healthclub.com.util.MyUtil;
import healthclub.com.vo.SearchVO;
import healthclub.lck.lck1000.lck1000.service.Lck1000Service;


/**
 * @Class Lck1000Controller.java
 * @Description 사물함 관리 controller
 * @version 1.0
 * @author 권태완
 * @Since 2024.01.18.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
@Controller
@RequestMapping(value="/lck/lck1000/lck1000/")
public class Lck1000Controller {

	@Resource(name="messageSource")
    private MessageSource messageSource;
	
	@Resource(name = "lck1000Service")
	private Lck1000Service lck1000Service;
	
		/**
		 * Func : 사물함 정보 관리 메서드
		 * 
		 * @desc 사물함정보관리 페이지를 보여준다.
		 * @param 
		 * @return String
		 * @throws Exception
		 */
		@RequestMapping(value="selectLck1000View.do", method=RequestMethod.GET)
		public String selectLck1000View() throws Exception {
			return "/lck/lck1000/lck1000/lck1000";
		}
		
		
		
		/**
		 * Func : 사물함정보 목록 메서드
		 * 
		 * @desc 사물함정보 목록을 보여줌
		 * @param HttpServletRequest request, ModelMap model
		 * @return String
		 * @throws Exception
		 */
		@SuppressWarnings("unchecked")
		@RequestMapping(value="selectLck1000LockerList.do", method=RequestMethod.POST)
		public String selectLck1000LockerList(HttpServletRequest request, ModelMap model) throws Exception {		
			try {
				// json 요청 전처리
				Map<String, String> jsonRequestDatas = new ObjectMapper().readValue(request.getInputStream(), Map.class);
				
	            // DAO에서 항상 공통으로 사용하는 클래스
				model.addAttribute("jsonResponseData", lck1000Service.selectLck1000LockerList(new SearchVO(jsonRequestDatas)));
				model.addAttribute("responseStatus", 1);
		        
			} catch (Exception e) {
				// 예외 발생 시, message.properties 내용 반환
				model.addAttribute("failMsg", messageSource.getMessage("error.response.data", null, null));
				model.addAttribute("responseStatus", -1);
			}

			return "jsonView";
		}
		
		
		
		/**
		 * Func : 사물함 정보 등록,수정 화면 메서드
		 * 
		 * @desc 사물함정보관리 페이지를 보여준다.
		 * @param @RequestParam(name="postPopupValue") String lockerId, ModelMap model
		 * @return String
		 * @throws Exception
		 */
		@RequestMapping(value="selectLck1001View.do", method=RequestMethod.POST)
		public String selectLck1001View(@RequestParam(name="postPopupValue") String lockerId, ModelMap model) throws Exception {
			// 사물함 수정 정보
			if(!lockerId.isEmpty()) {
				model.addAttribute("lockerInfo", lck1000Service.selectLck1000EditTrainerInfo(lockerId));
			} 

			return "lck/lck1000/lck1000/lck1001";
		}
		
		
		/**
		 * Func : 사물함 배정 메서드
		 * 
		 * @desc 사물함 배정을 한다.
		 * @param HttpServletRequest request, ModelMap model
		 * @return String
		 * @throws Exception
		 */
		@SuppressWarnings("unchecked")
		@RequestMapping(value="saveLck1000.do", method=RequestMethod.POST)
		public String saveLck1000(HttpServletRequest request, ModelMap model) throws Exception {
			try {
				// json 요청 전처리
				Map<String, String> lockerInfo = new ObjectMapper().readValue(request.getInputStream(), Map.class);
				/*  등록자 id, 등록자 ip */
				MyUtil.addMetaData(request, lockerInfo);

				// 이전 데이터 존재 유무로 등록 수정 분기 처리
				if ("edit".equals(lockerInfo.get("submitType"))) {
					lck1000Service.updateLck1000Locker(lockerInfo);
					
				} else {
					lck1000Service.insertLck1000Locker(lockerInfo);
				}

				model.addAttribute("successMsg", messageSource.getMessage("success.save.locker", null, null));
				model.addAttribute("responseStatus", 1);
				
			} catch (Exception e) {
 				model.addAttribute("responseStatus", -1);
				model.addAttribute("failMsg", messageSource.getMessage("error.save.data", new Object[] { "사물함 정보" }, null));
			}

			return "jsonView";
		}
		
		
		/**
		 * Func : 사물함 해제 메서드
		 * 
		 * @desc 사물함 해제를 한다.
		 * @param HttpServletRequest request, ModelMap model
		 * @return String
		 * @throws Exception
		 */
		@SuppressWarnings("unchecked")
		@RequestMapping(value="deleteLck1000LockerList.do", method=RequestMethod.POST)
		public String deleteLck1000LockerList(HttpServletRequest request, ModelMap model) throws Exception {
			try {
				// JSON 문자열을 Map으로 변환
				List<String> lockers = new ObjectMapper().readValue(request.getInputStream(), List.class);
				
			    // 등록자 id, 등록자 ip
			    Map<String, String> metaDatas = new HashMap<>();
			    MyUtil.addMetaData(request, metaDatas);
				
				lck1000Service.deleteLck1000LockerList(lockers, metaDatas);
				model.addAttribute("responseStatus", 1);
				model.addAttribute("successMsg", messageSource.getMessage("success.delete.locker", null, null));
				
			} catch (Exception e) {
				model.addAttribute("responseStatus", -1);
				model.addAttribute("failMsg", messageSource.getMessage("error.delete.data", null, null));
			}
			
			return "jsonView"; 
		}
}
