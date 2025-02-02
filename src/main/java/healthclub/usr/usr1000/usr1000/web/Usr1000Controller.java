package healthclub.usr.usr1000.usr1000.web;

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
import healthclub.usr.usr1000.usr1000.service.Usr1000Service;

/**
 * @Class Name : Usr1000Controller.java
 * @Description 회원 관리 controller
 * @version 1.0
 * @author 권태완
 * @Since 2024.01.18.
 * @Modification Information
 * @
 * @  수정일            수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2024.01.18	   권태완		  최초생성
 * @ 2024.03.09	   권태완		 회원 상세 로직을 cmm2000 공통 관리로 분리
 * 
 * @see Copyright (C) All right reserved.
 */
@Controller
@RequestMapping(value="/usr/usr1000/usr1000/")
public class Usr1000Controller {

	@Resource(name="messageSource")
    private MessageSource messageSource;
	
	@Resource(name = "usr1000Service")
	private Usr1000Service usr1000Service;
	
	
	/**
	 * Func : 회원정보관리 페이지 표출 메서드
	 * 
	 * @desc 회원정보관리 페이지 표출
	 * @param 
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="selectUsr1000View.do", method=RequestMethod.GET)
	public String selectUsr1000View() throws Exception {
		return "usr/usr1000/usr1000/usr1000";
	}

	/**
	 * Func : 회원정보 목록 표출 메서드
	 * 
	 * @desc 회원정보 목록 표출
	 * @param HttpServletRequest request, ModelMap model
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="selectUsr1000UserList.do", method=RequestMethod.POST)
	public String selectUsr1000UserList(HttpServletRequest request, ModelMap model) throws Exception {	
		try {
			// json 요청 전처리
			Map<String, String> jsonRequestDatas = new ObjectMapper().readValue(request.getInputStream(), Map.class);

			// DAO에서 항상 공통으로 사용하는 클래스
			model.addAttribute("jsonResponseData", usr1000Service.selectUsr1000UserList(new SearchVO(jsonRequestDatas)));
			model.addAttribute("responseStatus", 1);
	        
		} catch (Exception e) {
			// 예외 발생 시, message.properties 내용 반환
			model.addAttribute("responseStatus", -1);
			model.addAttribute("failMsg", messageSource.getMessage("error.response.data", null, null));
			
		}
		
		return "jsonView";
	}

	
	/**
	 * Func : 회원 정보 등록, 수정 화면 표출 메서드
	 * 
	 * @desc 회원정보 등록, 수정 페이지 표출
	 * @param @RequestParam(name="postPopupValue") String userId, ModelMap model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="selectUsr1001View.do", method=RequestMethod.POST)
	public String selectUsr1001View(@RequestParam(name="postPopupValue") String userId, ModelMap model) throws Exception {
		// userId 존재시 회원 수정 정보 담기
		if (!userId.isEmpty()) {
			model.addAttribute("userInfo", usr1000Service.selectUsr1000EditUserInfo(userId));
		} 

		return "usr/usr1000/usr1000/usr1001";
	}
	
	
	/**
	 * Func : 회원 정보 등록 메서드
	 * 
	 * @desc 회원정보 등록을 한다.
	 * @param HttpServletRequest request, HttpServletResponse response
	 * @return ModelView
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveUsr1000.do", method=RequestMethod.POST)
	public String saveUsr1000(HttpServletRequest request, ModelMap model) throws Exception {
		try {
			// json 요청 전처리
			Map<String, Object> jsonRequestDatas = new ObjectMapper().readValue(request.getInputStream(), Map.class);
	        
			// 사용자 정보
            Map<String, String> userInfo = (Map<String, String>) jsonRequestDatas.get("userInfo");
		    // 사물함 정보
            Map<String, String> lockerInfo = (Map<String, String>) jsonRequestDatas.get("lockerInfo");
		    
		    // 등록 & 수정자 id, ip 데이터 추가
		    MyUtil.addMetaData(request, userInfo);
		    MyUtil.addMetaData(request, lockerInfo);

			String saveType = userInfo.get("saveType");
			
			// saveType로 등록 수정 분기 처리
			if ("insert".equals(saveType)) {
				// 비밀번호 암호화 SHA(secure hash algorithm) 256
				userInfo.put("userPw", Hashing.sha256().hashString(userInfo.get("userPw"), StandardCharsets.UTF_8).toString());
				
				// 회원 등록
				usr1000Service.insertUsr1000UserLockerInfo(userInfo, lockerInfo);
				model.addAttribute("successMsg", messageSource.getMessage("success.insert.user", null, null));
				
			} else if ("update".equals(saveType)) {
				String newPw = userInfo.get("userPw");
				
				if (newPw != null) {
					userInfo.put("userPw", Hashing.sha256().hashString(newPw, StandardCharsets.UTF_8).toString());
				}
				
				// 회원 수정
				usr1000Service.updateUsr1000UserLockerInfo(userInfo, lockerInfo);
				model.addAttribute("successMsg", messageSource.getMessage("success.update.user", null, null));
				
			}

			model.addAttribute("responseStatus", 1);
			
		} catch (Exception e) {
			model.addAttribute("responseStatus", -1);
			model.addAttribute("failMsg", messageSource.getMessage("error.save.data.user", null, null));
		}
		
		return "jsonView";
	}


	
	/**
	 * Func : 회원 정보 삭제 메서드
	 * 
	 * @desc 회원 정보 삭제
	 * @param HttpServletRequest request, ModelMap model
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="deleteUsr1000UserList.do", method=RequestMethod.POST)
	public String deleteUsr1000UserList(HttpServletRequest request, ModelMap model) throws Exception {
		try {
			// json 요청 전처리
			List<String> userIds = new ObjectMapper().readValue(request.getInputStream(), List.class);
			
	        // 예정된 수업이 존재하는 회원 찾기
	        List<String> usersWithPt = usr1000Service.selectUsr1000HavePt(userIds);
	        
	        // 예정된 수업 존재하는 회원이 있는 경우 삭제 실패로 인한 반환
	        if (usersWithPt.size() > 0) {
	        	model.addAttribute("responseStatus", -1);
				model.addAttribute("failMsg", messageSource.getMessage("fail.delete.user.class.pt", usersWithPt.toArray(), null));
				return "jsonView";
	        } 
	        
	        // 개인 수업 횟수가 존재하는 경우
	        List<String> usersRemainPt = usr1000Service.selectUsr1000UserListRemainPt(userIds);
	        
	        // 개인 수업 횟수가 존재하는 회원이 있는 경우 삭제 실패로 인한 반환
	        if (usersRemainPt.size() > 0) {
	        	model.addAttribute("responseStatus", -1);
				model.addAttribute("failMsg", messageSource.getMessage("fail.delete.user.remain.pt", usersRemainPt.toArray(), null));
				return "jsonView";
	        }
	        
	        // 삭제할 수업 정보를 메타데이터와 함께 map으로 담아서 삭제 로직 수행 서비스로 전달
        	List<Map<String, String>> deleteUserInfo = new ArrayList<>();

        	for(String user : userIds) {
        		Map<String, String> map = new HashMap<>();
        		map.put("userId", user);
        		MyUtil.addMetaData(request, map);
        		
        		deleteUserInfo.add(map); 
        	}
        	
        	usr1000Service.deleteUsr1000UserList(userIds, deleteUserInfo);
        	model.addAttribute("responseStatus", 1);
			model.addAttribute("successMsg", messageSource.getMessage("success.delete.user", null, null));
	        
		} catch (Exception e) {
			model.addAttribute("responseStatus", -1);
			model.addAttribute("failMsg", messageSource.getMessage("error.delete.data", null, null));
		}
		
		return "jsonView";
	}
}
