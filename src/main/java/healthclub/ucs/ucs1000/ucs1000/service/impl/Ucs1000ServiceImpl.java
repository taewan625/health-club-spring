package healthclub.ucs.ucs1000.ucs1000.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import healthclub.ucs.ucs1000.ucs1000.service.Ucs1000Service;

/**
 * @Class Name : Ucs1000ServiceImpl.java
 * @Description 개인 회원 수업 일정 서비스
 * @version 1.0
 * @author 권태완
 * @Since 2024.1.18.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
@Service("ucs1000Service")
public class Ucs1000ServiceImpl extends EgovAbstractServiceImpl implements Ucs1000Service {
	
	@Resource(name="ucs1000DAO")
	private Ucs1000DAO ucs1000DAO;

	/**
	 * Func : 개인 pt 정보 목록을 보여주는 메서드
	 * 
	 * @desc 개인 pt 정보 목록을 보여주는 메서드
	 * @param String userId
	 * @return List<Map<String, String>>
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> selectUcs1000PtList(String userId) throws Exception {
		return ucs1000DAO.selectUcs1000PtList(userId);
	}

	
	/**
	 * Func : 개인 pt 정보를 보여주는 메서드
	 * 
	 * @desc 개인 pt 정보 가져옴
	 * @param String ptId
	 * @return Map<String, String>
	 * @throws Exception
	 */
	@Override
	public Map<String, String> selectUcs1000DetailUserPtInfo(String ptId) throws Exception {
		return ucs1000DAO.selectUcs1000DetailUserPtInfo(ptId);
	}
}
