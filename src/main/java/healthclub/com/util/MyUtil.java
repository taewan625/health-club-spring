package healthclub.com.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * @Class Name : MyUtil.java
 * @Description 공통으로 사용하는 메서드 모음. 변경 시, 해당 메서드만 변경하여 유지보수에 편리하게 하기 위함
 * @version 1.0
 * @author 권태완
 * @Since 2024.03.05.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class MyUtil {

	
	/**
	 * Func : 메타 정보를 합치는 메서드
	 * 
	 * @desc 메타 정보를 합치는 메서드
	 * @param HttpServletRequest request, Map<String, String> requestData
	 * @return void
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void addMetaData(HttpServletRequest request, Map<String, String> requestData) throws Exception {
		Map<String, String> memberInfo = (Map<String, String>) request.getSession(false).getAttribute("memberInfo");
		
		requestData.put("metaId", memberInfo.get("loginId"));
		requestData.put("metaIp", memberInfo.get("loginIp"));
	}
}
