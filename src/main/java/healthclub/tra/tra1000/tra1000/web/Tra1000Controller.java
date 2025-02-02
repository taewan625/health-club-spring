package healthclub.tra.tra1000.tra1000.web;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
import com.google.common.hash.Hashing;

import healthclub.com.util.MyUtil;
import healthclub.com.vo.SearchVO;
import healthclub.tra.tra1000.tra1000.service.Tra1000Service;


/**
 * @Class Name : Tra1000Controller.java
 * @Description  Tra1000Controller 컨트롤러
 * @version 1.0
 * @author 권태완
 * @Since 2024.01.18.
 * @Modification Information
 * @
 * @  수정일            수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2024.01.18	   권태완		  최초생성
 * @ 2024.03.09	   권태완		 트레이너 상세 로직을 cmm2000 공통 관리로 분리
 * 
 * @see Copyright (C) All right reserved.
 */
@Controller
@RequestMapping(value="/tra/tra1000/tra1000/")
public class Tra1000Controller {

	@Resource(name="messageSource")
    private MessageSource messageSource;
	
	@Resource(name = "tra1000Service")
	private Tra1000Service tra1000Service;
	
	/**
	 * Func : 트레이너정보관리 메서드
	 * 
	 * @desc 트레이너정보관리 페이지 표출
	 * @param 
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="selectTra1000View.do", method=RequestMethod.GET)
	public String selectTra1000View() throws Exception {
		return "/tra/tra1000/tra1000/tra1000";
	}
	
	/**
	 * Func : 트레이너정보 목록 메서드
	 * 
	 * @desc 트레이너정보관리 목록을 전달
	 * @param HttpServletRequest request, ModelMap model
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="selectTra1000TrainerList.do", method=RequestMethod.POST)
	public String selectTra1000TrainerList(HttpServletRequest request, ModelMap model) throws Exception {		
		try {
			// json 요청 전처리
			Map<String, String> jsonRequestDatas = new ObjectMapper().readValue(request.getInputStream(), Map.class);

			// DAO에서 항상 공통으로 사용하는 클래스
			model.addAttribute("responseStatus", 1);
			model.addAttribute("jsonResponseData", tra1000Service.selectTra1000TrainerList(new SearchVO(jsonRequestDatas)));
	        
		} catch (Exception e) {
			// 예외 발생 시, message.properties 내용 반환
			model.addAttribute("responseStatus", -1);
			model.addAttribute("failMsg", messageSource.getMessage("error.response.data", null, null));
		}

		return "jsonView";
	}
	

	
	
	
	/**
	 * Func : 트레이너 정보 등록,수정 화면 메서드
	 * 
	 * @desc 트레이너정보 등록, 수정 페이지를 보여준다.
	 * @param @RequestParam(name="postPopupValue") String trainerId, ModelMap model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="selectTra1001View.do", method=RequestMethod.POST)
	public String selectTra1001View(@RequestParam(name="postPopupValue") String trainerId, ModelMap model) throws Exception {
		// 트레이너 수정 정보
		if(!trainerId.isEmpty()) {
			model.addAttribute("trainerInfo", tra1000Service.selectTra1000EditTrainerInfo(trainerId));
		}
		
		return "tra/tra1000/tra1000/tra1001";
	}
	
	

	/**
	 * Func : 트레이너 정보 등록 메서드
	 * 
	 * @desc 트레이너정보 등록을 한다.
	 * @param HttpServletRequest request, ModelMap model
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveTra1000.do", method=RequestMethod.POST)
	public String saveTra1000(HttpServletRequest request, ModelMap model) throws Exception {
		try {
			// json 요청 전처리
			Map<String, String> trainerInfo = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            
		    // 등록 & 수정자 id, ip 데이터 추가
            MyUtil.addMetaData(request, trainerInfo);
			
			/* 실제 수행 로직 */
		    String saveType = trainerInfo.get("saveType");

			// 등록 로직 처리
		    if ("insert".equals(saveType)) {
				// 비밀번호 암호화 SHA(secure hash algorithm) 256
		    	trainerInfo.put("trainerPw", Hashing.sha256().hashString(trainerInfo.get("trainerPw"), StandardCharsets.UTF_8).toString());
				
		    	model.addAttribute("successMsg", messageSource.getMessage("success.insert.trainer", null, null));
				tra1000Service.insertTra1000TrainerInfo(trainerInfo);
				
			} 
		    // 수정로직 처리
		    else if ("update".equals(saveType)) {
				String newPw = trainerInfo.get("trainerPw");
				
				if (newPw != null) {
					trainerInfo.put("trainerPw", Hashing.sha256().hashString(newPw, StandardCharsets.UTF_8).toString());
				}
				
				model.addAttribute("successMsg", messageSource.getMessage("success.update.trainer", null, null));
				tra1000Service.updateTra1000TrainerInfo(trainerInfo);
			}
			
			model.addAttribute("responseStatus", 1);
			
		} catch (Exception e) {
			model.addAttribute("responseStatus", -1);
			model.addAttribute("failMsg", messageSource.getMessage("error.save.data", new Object[] { "트레이너정보" }, null));
		}
		
		return "jsonView";
	}
	

	/**
	 * Func : 트레이너 근무 시간 수정 메서드
	 * 
	 * @desc 트레이너 근무 가능 시간 수정
	 * @param HttpServletRequest request, ModelMap model
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="selectTra1000WorkTime.do", method=RequestMethod.POST)
	public String selectTra1000WorkTime(HttpServletRequest request, ModelMap model) throws Exception {
		try {
			// json 요청 전처리
			Map<String, String> requestDatas = new ObjectMapper().readValue(request.getInputStream(), Map.class);
			
			// 시간을 미리 분으로 변경
	        setAdditionData(requestDatas);
	        
	        // 중복 데이터 여부 확인
	        boolean changeWorkTime = tra1000Service.selectTra1000CheckWorkTime(requestDatas);

	        if (changeWorkTime) {
	        	model.addAttribute("responseStatus", 1);
				model.addAttribute("successMsg", messageSource.getMessage("success.checkWorkTime", null, null));
	        	
	        } else {
	        	model.addAttribute("responseStatus", -1);
				model.addAttribute("failMsg", messageSource.getMessage("fail.checkWorkTime", null, null));
	        
	        }
	        
		} catch (Exception e) {
			model.addAttribute("responseStatus", -1);
			model.addAttribute("failMsg", messageSource.getMessage("error.checkWorkTime", null, null));
		}
	
		return "jsonView";
	}
	
	
	/**
	 * Func : 트레이너 삭제 메서드
	 * 
	 * @desc 수업 존재시, 트레이너 삭제 불가
	 * @param Map<String, String> classCondition
	 * @return void
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="deleteTra1000TrainerList.do", method=RequestMethod.POST)
	public String deleteTra1000TrainerList(HttpServletRequest request, ModelMap model) throws Exception {
		
		try {
			// json 요청 전처리
			List<String> requestDatas = new ObjectMapper().readValue(request.getInputStream(), List.class);
			
	        // 예정된 수업이 존재하는 트레이너 찾기
	        List<String> trainers = tra1000Service.selectTra1000HavePt(requestDatas);
	        List<Map<String, String>> deleteTrainerInfo = new ArrayList<>();
	        
	        if (trainers.size() == 0) {
	        	for(String trainer : requestDatas) {
	        		Map<String, String> map = new HashMap<>();
	        		map.put("trainerId", trainer);
	        		MyUtil.addMetaData(request, map);
	        		deleteTrainerInfo.add(map); 
	        	}
	        	
	        	tra1000Service.deleteTra1000TrainerList(deleteTrainerInfo);
	        	model.addAttribute("responseStatus", 1);
				model.addAttribute("successMsg", messageSource.getMessage("success.delete.trainer", null, null));
	        	
	        } else {
	        	model.addAttribute("responseStatus", -1);
				model.addAttribute("failMsg", messageSource.getMessage("fail.delete.trainer", trainers.toArray(), null));
	        
	        }
	        
		} catch (Exception e) {
			model.addAttribute("responseStatus", -1);
			model.addAttribute("failMsg", messageSource.getMessage("error.delete.data", null, null));
		}
	
		return "jsonView";
	}
	
	
	/**
	 * Func : 트레이너, 트레이너 정보 목록에 필요한 데이터 추출 메서드
	 * 
	 * @desc 전날, 다음날 , pt 시작 시간과 pt 종료 시간을 분으로 표시
	 * @param Map<String, String> classCondition
	 * @return void
	 * @throws Exception
	 */
	private void setAdditionData(Map<String, String> trainerCondition) throws Exception {
		// 'YYYY-MM-DD' 형식으로 변환
		String[] workStartTime = trainerCondition.get("workStartTime").split(":");
		
		int workStartMinute = ( Integer.parseInt(workStartTime[0]) * 60 ) + Integer.parseInt(workStartTime[1]);
		
		// 시작 분
		trainerCondition.put("workStartMinute", workStartMinute + "");
		// 종료 분
		trainerCondition.put("workEndMinute", workStartMinute + ( Integer.parseInt(trainerCondition.get("workTime")) * 60 ) + "");
	}
}
