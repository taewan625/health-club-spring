package healthclub.ucs.ucs1000.ucs1000.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


/**
 * @Class Name : Ucs1000DAO.java
 * @Description 개인 회원 조회 DAO
 * @version 1.0
 * @author 권태완
 * @Since 2024.01.18.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
@Repository("ucs1000DAO")
public class Ucs1000DAO extends EgovAbstractDAO {

	/**
	 * Func : 개인 pt 정보 목록을 보여주는 메서드
	 * 
	 * @desc 개인 pt 정보 목록을 보여주는 메서드
	 * @param String userId
	 * @return List<Map<String, String>>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectUcs1000PtList(String userId) throws Exception {
		return (List<Map<String, String>>) list("ucs1000DAO.selectUcs1000PtList", userId);	
	}

	/**
	 * Func : 개인 pt 정보를 보여주는 메서드
	 * 
	 * @desc 개인 pt 정보 가져옴
	 * @param String ptId
	 * @return Map<String, String>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> selectUcs1000DetailUserPtInfo(String ptId) throws Exception {
		return (Map<String, String>) select("ucs1000DAO.selectUcs1000DetailUserPtInfo", ptId);	
	}
}
