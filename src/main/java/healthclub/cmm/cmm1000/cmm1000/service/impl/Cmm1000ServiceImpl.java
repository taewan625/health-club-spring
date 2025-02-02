package healthclub.cmm.cmm1000.cmm1000.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import healthclub.cmm.cmm1000.cmm1000.service.Cmm1000Service;

/**
 * @Class Name : Cmm1000ServiceImpl.java
 * @Description 로그인 관련 정보 검증 서비스
 * @version 1.0
 * @author 권태완
 * @Since 2024.01.12.
 * @Modification Information
 * @
 * @  수정일            수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2024.01.12	   권태완		  최초생성
 * @ 2024.03.09	   권태완		 로그인 조회 시, 권한 조회 방식을 sql에서 받아오는 형식으로 변경
 * @ 2024.03.12	   권태완		 기존 pw 확인 시, user와 trainer를 각각 조회하던 로직을 한번에 확인 하는 방식으로 변경
 * 
 * @see Copyright (C) All right reserved.
 */
@Service("cmm1000Service")
public class Cmm1000ServiceImpl extends EgovAbstractServiceImpl implements Cmm1000Service {
	
	@Resource(name="cmm1000DAO")
	private Cmm1000DAO cmm1000DAO;
	
	
	/**
	 * Func : 로그인 하려는 id, pw가 올바른 값인지 확인 메서드
	 * 
	 * @desc 로그인 정보 존재 시 권한 정보와 함께 정보 반환, 없을 시 null 반환
	 * @param Map<String, String> loginFormData
	 * @return Map<String, String>
	 * @throws Exception
	 */
	public EgovMap selectUsr1000Tra1000LoginInfo(Map<String, String> loginFormData) throws Exception {
		return cmm1000DAO.selectUsr1000Tra1000LoginInfo(loginFormData);
	}

	
	/**
	 * Func : 아이디 중복체크 확인 메서드
	 * 
	 * @desc 중복 아이디가 존재 시, true 반환
	 * @param String memberId
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public boolean selectUsr1000Tra1000Id(String memberId) throws Exception {
		return 0 < cmm1000DAO.selectUsr1000Tra1000CheckId(memberId);
	}

	
	/**
	 * Func : 기존 비밀번호체크 확인 메서드
	 * 
	 * @desc id에 부합한 pw 존재 시, true 반환
	 * @param Map<String, String> memberIdPw
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public boolean selectUsr1000Tra1000Pw(Map<String, String> memberIdPw) throws Exception {
		return cmm1000DAO.selectUsr1000Tra1000CheckPw(memberIdPw) == 1;
	}
	
}
