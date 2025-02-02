package healthclub.pcs.pcs1000.pcs1000.web;

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
import healthclub.pcs.pcs1000.pcs1000.service.Pcs1000Service;




/**
 * @Class Name : Pcs1000Controller.java
 * @Description 수업 관리 controller 
 * @version 1.0
 * @author 권태완
 * @Since 2024.2.20.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
@Controller
@RequestMapping(value="/pcs/pcs1000/pcs1000/")
public class Pcs1000Controller {
	
	@Resource(name="messageSource")
	private MessageSource messageSource;
	
	@Resource(name = "pcs1000Service")
	private Pcs1000Service pcs1000Service;
	
	/**
	 * Func : 수업정보관리 메서드
	 * 
	 * @desc 수업정보관리 페이지를 보여준다.
	 * @param ModelMap model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="selectPcs1000View.do", method=RequestMethod.GET)
	public String selectPcs1000View() throws Exception {
		return "pcs/pcs1000/pcs1000/pcs1000";
	}	
	
	
	/**
	 * Func : 수업정보 목록 메서드
	 * 
	 * @desc 수업정보 목록을 보여줌
	 * @param ModelMap model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="selectPcs1000PtList.do", method=RequestMethod.POST)
	public String selectPcs1000PtList(ModelMap model) throws Exception {
		try {
			// DAO에서 항상 공통으로 사용하는 클래스
			model.addAttribute("ptList", pcs1000Service.selectPCS1000PtList());
			model.addAttribute("responseStatus", 1);
			
		} catch (Exception e) {
			model.addAttribute("responseStatus", -1);
			model.addAttribute("failMsg", messageSource.getMessage("error.save.data", new Object[] { "수업정보" }, null));
		}
		
		return "jsonView";
	}	
	
	
	
	
	/**
	 * Func : 수업등록 화면 메서드
	 * 
	 * @desc 수업등록 화면을 보여준다.
	 * @param @RequestParam(name="postPopupValue") String ptId, ModelMap model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="selectPcs1001View.do", method=RequestMethod.POST)
	public String selectPcs1001View(@RequestParam(name="postPopupValue") String ptId, ModelMap model) throws Exception {
		// 회원 수정 정보
		if (!ptId.isEmpty()) {
			model.addAttribute("ptInfo", pcs1000Service.selectPcs1000DetailPtInfo(ptId));
		} 
		
		return "pcs/pcs1000/pcs1000/pcs1001";
	}
	
	/**
	 * Func : 수업정보 등록 메서드
	 * 
	 * @desc 수업정보 등록을 한다.
	 * @param HttpServletRequest request, ModelMap model
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="savePcs1000.do", method=RequestMethod.POST)
	public String savePcs1000(HttpServletRequest request, ModelMap model) throws Exception {
		try {
			// json 요청 전처리
			Map<String, String> ptInfo = new ObjectMapper().readValue(request.getInputStream(), Map.class);
				        
			 /*등록자 id, 등록자 ip */
			MyUtil.addMetaData(request, ptInfo);
			String saveType = ptInfo.get("saveType");

			// 수업 등록 로직
			if("insert".equals(saveType)) {
				ptInfo.put("addPtCount", "-1");
				pcs1000Service.insertPcs1000Pt(ptInfo);
				model.addAttribute("successMsg", messageSource.getMessage("success.insert.pt", null, null));
				
			} 
			// 수업 수정 로직
			else if ("update".equals(saveType)){
				pcs1000Service.updatePcs1000Pt(ptInfo);
				model.addAttribute("successMsg", messageSource.getMessage("success.update.pt", null, null));
				
			} 
			// 수업 상태 변경 로직
			else if ("updateStatus".equals(saveType)){
				pcs1000Service.updatePcs1000PtStatus(ptInfo);
				model.addAttribute("successMsg", messageSource.getMessage("success.updateStatus.pt", null, null));
			}
			
			model.addAttribute("responseStatus", 1);
			
		} catch (Exception e) {
			model.addAttribute("responseStatus", -1);
			model.addAttribute("failMsg", messageSource.getMessage("error.save.data", new Object[] { "수업정보" }, null));
		}
		
		return "jsonView";
	}
}
