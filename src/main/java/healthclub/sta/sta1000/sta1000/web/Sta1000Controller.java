package healthclub.sta.sta1000.sta1000.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import healthclub.sta.sta1000.sta1000.service.Sta1000Service;


/**
 * @Class Name : Sta1000Controller.java
 * @Description 통계  컨트롤러
 * @version 1.0
 * @author 권태완
 * @Since 2024.03.02.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
@Controller
@RequestMapping(value="/sta/sta1000/sta1000/")
public class Sta1000Controller  {
	
	@Resource(name="messageSource")
	private MessageSource messageSource;
	
	@Resource(name = "sta1000Service")
	private Sta1000Service sta1000Service;
	
	
	/**
	 * Func : 통계 화면 반환 메서드
	 * 
	 * @desc : 통계 화면 반환
	 * @param ModelMap model
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="selectSta1000View.do", method=RequestMethod.GET)
	public String selectSta1000View(ModelMap model) throws Exception {
		Map<String, List<EgovMap>> staticInfo = sta1000Service.selectSta1000StaticList();
		model.addAttribute("staticInfo", staticInfo);
		return "/sta/sta1000/sta1000/sta1000";
	}
}
