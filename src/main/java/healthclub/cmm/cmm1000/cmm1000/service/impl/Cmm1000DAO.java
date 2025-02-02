package healthclub.cmm.cmm1000.cmm1000.service.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;


/**
 * @Class Name : Cmm1000DAO.java
 * @Description 로그인 관련 정보 반환을 위한 sqlmap 의 인터페이스 역할 수행
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
@Repository("cmm1000DAO")
public class Cmm1000DAO extends EgovAbstractDAO {

	/**
	 * Func : 로그인 정보를 이용해서 존재하면 로그인 권한 및 id를 반환하는 메서드
	 * 
	 * @desc 로그인 정보를 이용해서 존재하면 로그인 권한 및 id를 반환하는 메서드
	 * @param Map<String, String> memberIdPw
	 * @return  Map<String, String>
	 * @throws Exception
	 */
	public EgovMap selectUsr1000Tra1000LoginInfo(Map<String, String> loginFormData) throws Exception {
		return (EgovMap) select("cmm1000DAO.selectUsr1000Tra1000AliveMemberInfo", loginFormData);
	}
	
	/**
	 * Func : 중복 회원 아이디를 확인하기 위한  메서드
	 * 
	 * @desc 사용자별 id를 모두 확인해야하므로 join으로 모든 사용자 id와 비교
	 * @param String id
	 * @return int
	 * @throws Exception
	 */
	public int selectUsr1000Tra1000CheckId(String memberId) throws Exception {
		return (int) select("cmm1000DAO.selectUsr1000Tra1000CheckId", memberId);
	}

	/**
	 * Func : 현재 회원 아이디가 가지고 있는 비밀번호가 옳은지 확인하는 메서드
	 * 
	 * @desc 기존 비밀번호가 맞으면 1, 틀리면 0
	 * @param Map<String, String> memberIdPw
	 * @return int
	 * @throws Exception
	 */
	public int selectUsr1000Tra1000CheckPw(Map<String, String> memberIdPw) throws Exception {
		return (int) select("cmm1000DAO.selectUsr1000Tra1000CheckPw", memberIdPw);
	}

}
