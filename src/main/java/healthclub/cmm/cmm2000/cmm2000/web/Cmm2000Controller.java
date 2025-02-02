package healthclub.cmm.cmm2000.cmm2000.web;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

import healthclub.cmm.cmm2000.cmm2000.service.Cmm2000Service;
import healthclub.com.vo.SearchVO;


/**
 * @Class Name : Cmm2000Controller.java
 * @Description 공통처리 컨트롤러
 * @version 1.0
 * @author 권태완
 * @Since 2024.01.12.
 * @Modification Information
 * @
 * @  수정일            수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2024.01.12	   권태완		  최초생성
 * @ 2024.03.09	   권태완		 회원, 트레이너, 수업 상세 조회 공통 로직 추가
 * @see Copyright (C) All right reserved.
 */
@Controller
@RequestMapping(value="/cmm/cmm2000/cmm2000/")
public class Cmm2000Controller {

	@Resource(name="messageSource")
    private MessageSource messageSource;
	
	@Resource(name = "cmm2000Service")
	private Cmm2000Service cmm2000Service;
	
	
	/**
	 * Func : 수업 등록 form에서 회원 선택하는 팝업창 호출 메서드
	 * 
	 * @desc 수업 등록 form에서 회원 선택하는 팝업창 호출
	 * @param
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="selectCmm2001View.do", method=RequestMethod.GET)
	public String selectCmm2001View() throws Exception {
		return "cmm/cmm2000/cmm2000/cmm2001";
	}
	
	
	/**
	 * Func : 사물함 등록에 필요한 회원정보 및 수업 등록에 필요한 회원정보 목록 메서드
	 * 
	 * @desc 사물함 등록에 필요한 회원정보 및 수업 등록에 필요한 회원정보 목록 조회
	 * @param HttpServletRequest request, ModelMap model
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="selectCmm2000UserList.do" , method=RequestMethod.POST)
	public String selectCmm2000UserList(HttpServletRequest request, ModelMap model) throws Exception {
		try {
			// json 요청 전처리
			Map<String, String> jsonRequestDatas = new ObjectMapper().readValue(request.getInputStream(), Map.class);
			
			// 사용하는 목적에 따른 분기 처리
			String subType = jsonRequestDatas.get("subType");
			
			// 사물함 선택하는 회원 정보 목록
			if ("locker".equals(subType)) {
				model.addAttribute("jsonResponseData", cmm2000Service.selectUsr1000ListForLocker(new SearchVO(jsonRequestDatas)));
			
			} 
			// 수업 등록하는 회원 정보 목록
			else if ("pt".equals(subType)) {
				setAdditionData(jsonRequestDatas);
				model.addAttribute("jsonResponseData", cmm2000Service.selectUsr1000UserListForPT(new SearchVO(jsonRequestDatas)));
			}

			model.addAttribute("responseStatus", 1);
	        
		} 
		// 예외 발생 시, message.properties 내용 반환
		catch (Exception e) {
			model.addAttribute("responseStatus", -1);
			model.addAttribute("failMsg", messageSource.getMessage("error.response.data", null, null));
		}

		return "jsonView";
	}

	
	/**
	 * Func : 수업 등록 form에서 트레이너 선택하는 팝업창 호출 메서드
	 * 
	 * @desc 수업 등록 form에서 트레이너 선택하는 팝업창 호출
	 * @param 
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="selectCmm2002View.do", method=RequestMethod.GET)
	public String selectCmm2002View() throws Exception {
		return "cmm/cmm2000/cmm2000/cmm2002";
	}
	
	
	
	/**
	 * Func : 수업 등록에 필요한 트레이너정보 목록 메서드
	 * 
	 * @desc 수업 등록에 필요한 트레이너정보 목록 반환
	 * @param HttpServletRequest request, ModelMap model
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="selectCmm2000TrainerList.do")
	public String selectCmm2000TrainerList(HttpServletRequest request, ModelMap model) throws Exception {
		try {
			// json 요청 전처리
			Map<String, String> jsonRequestDatas = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            
            setAdditionData(jsonRequestDatas);
			model.addAttribute("jsonResponseData", cmm2000Service.selectUsr1000TrainerListForPT(new SearchVO(jsonRequestDatas)));
			model.addAttribute("responseStatus", 1);
	        
		} catch (Exception e) {
			// 예외 발생 시, message.properties 내용 반환
			model.addAttribute("responseStatus", -1);
			model.addAttribute("failMsg", messageSource.getMessage("error.response.data", null, null));
		}

		return "jsonView";
	}

	
	/**
	 * Func : 회원, 트레이너 정보 목록에 필요한 데이터 추출 메서드
	 * 
	 * @desc 전날, 다음날 , pt 시작 시간과 pt 종료 시간을 분으로 표시
	 * @param Map<String, String> classCondition
	 * @return void
	 * @throws Exception
	 */
	private void setAdditionData(Map<String, String> classCondition) throws Exception {
		// 'YYYY-MM-DD' 형식으로 변환
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		// 전날, 다음날 구하기
		classCondition.put("beforePtDate", LocalDate.parse(classCondition.get("ptDate")).minusDays(1).format(formatter));
		classCondition.put("afterPtDate", LocalDate.parse(classCondition.get("ptDate")).plusDays(1).format(formatter));

		// 수업 시작 시간 과 수업 종료 시간을 분으로 구하기
		LocalTime localTime = LocalTime.parse(classCondition.get("ptTime"), DateTimeFormatter.ofPattern("HH:mm"));
		
		// 시간을 분 단위로 변환
		int startTime = localTime.getHour() * 60 + localTime.getMinute();
		int endTime = 0;
		
		if ("01".equals(classCondition.get("classType"))) {
			endTime = startTime + 30;
		} else {
			endTime = startTime + 50;
		}
		
		// 시작 minute
		classCondition.put("startTime", startTime + "");
		// 시작 minute + 수업 minute
		classCondition.put("endTime", endTime + "");
	}
	

	/**
	 * Func : 사물함 등록 form에서 사물함을 선택하는 팝업창 호출 메서드
	 * 
	 * @desc 사물함 등록 form에서 사물함을 선택하는 팝업창 호출
	 * @param 
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="selectCmm2003View.do", method=RequestMethod.GET)
	public String selectCmm2003View() throws Exception {
		return "cmm/cmm2000/cmm2000/cmm2003";
	}
	
	/**
	 * Func : 사물함 등록에 필요한 사물함정보 목록 메서드
	 * 
	 * @desc 사물함 등록에 필요한 사물함정보 목록 표출
	 * @param HttpServletRequest request, ModelMap model
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="getCmm2000LckList.do", method=RequestMethod.POST)
	public String getCmm2000LckList(HttpServletRequest request, ModelMap model) throws Exception {
		try {
			// json 요청 전처리
			Map<String, String> jsonRequestDatas = new ObjectMapper().readValue(request.getInputStream(), Map.class);

            // DAO에서 항상 공통으로 사용하는 클래스
            model.addAttribute("jsonResponseData", cmm2000Service.selectLck1000List(new SearchVO(jsonRequestDatas)));
            model.addAttribute("responseStatus", 1);
	        
		} catch (Exception e) {
			// 예외 발생 시, message.properties 내용 반환
			model.addAttribute("responseStatus", -1);
			model.addAttribute("failMsg", messageSource.getMessage("error.response.data", null, null));
		}
		
		return "jsonView";
	}
	
	
	/**
	 * Func : 회원 정보 상세보기 메서드
	 * 
	 * @desc 회원정보상세 페이지를 보여준다.
	 * @param @RequestParam(name="postPopupValue") String userId, ModelMap model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="selectCmm2004View.do", method=RequestMethod.POST)
	public String selectUsr1002View(@RequestParam(name="postPopupValue") String userId, ModelMap model) throws Exception {
		model.addAttribute("userInfo", cmm2000Service.selectUsr1000UserDetail(userId)); 
		return "cmm/cmm2000/cmm2000/cmm2004";
	}
	
	
	/**
	 * Func : 트레이너 정보 상세보기 메서드
	 * 
	 * @desc 트레이너정보상세 페이지를 보여준다.
	 * @param @RequestParam(name="postPopupValue") String userId, ModelMap model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="selectCmm2005View.do", method=RequestMethod.POST)
	public String selectTra1002View(@RequestParam(name="postPopupValue") String trainerId, ModelMap model) throws Exception {
		model.addAttribute("trainerInfo", cmm2000Service.selectTra1000TrainerDetail(trainerId));
		return "cmm/cmm2000/cmm2000/cmm2005";
	}
	
	
	/**
	 * Func : 수업상세 화면 메서드
	 * 
	 * @desc 수업상세 화면을 보여준다.
	 * @param @RequestParam(name="postPopupValue") String userId, ModelMap model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="selectCmm2006View.do", method=RequestMethod.POST)
	public String selectUcs1001View(@RequestParam(name="postPopupValue") String ptId, ModelMap model) throws Exception {
		// 해당 회원 정보 가져 오기
		model.addAttribute("userPtInfo", cmm2000Service.selectPcs1000DetailUserPtInfo(ptId));
		return "cmm/cmm2000/cmm2000/cmm2006";
	}
}
