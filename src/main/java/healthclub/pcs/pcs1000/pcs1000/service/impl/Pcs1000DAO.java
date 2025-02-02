package healthclub.pcs.pcs1000.pcs1000.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @Class Name : Pcs1000DAO.java
 * @Description 수업관리 DAO
 * @version 1.0
 * @author 권태완
 * @Since 2024.2.20.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
@Repository("pcs1000DAO")
public class Pcs1000DAO extends EgovAbstractDAO {

	/**
	 * Func : 수업정보 반환하는 메서드
	 * 
	 * @desc 조건에 따라서 수업정보 반환한다.
	 * @param 
	 * @return List<Map<String, String>>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectPCS1000PtList() throws Exception {
		return (List<Map<String, String>>) list("pcs1000DAO.selectPCS1000PtList");	
	}

	
	/**
	 * Func : 수업 상세 정보 반환 메서드
	 * 
	 * @desc 수업 상세 필요한 정보를 반환하는 메서드
	 * @param String ptId
	 * @return Map<String, String>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> selectPcs1000DetailPtInfo(String ptId) throws Exception {
		return (Map<String, String>) select("pcs1000DAO.selectPcs1000DetailPtInfo", ptId);
	}

	/**
	 * Func : 수업 등록 메서드
	 * 
	 * @desc 중복 아이디가 존재하지 않을 시, 수업을 등록한다. expireDate
	 * @param Map<String, String> ptInfo
	 * @return void
	 * @throws Exception
	 */
	public void insertPcs1000Pt(Map<String, String> ptInfo) throws Exception {
		insert("pcs1000DAO.insertPcs1000Pt", ptInfo);
		
	}

	/**
	 * Func : 회원 pt권 계산 메서드
	 * 
	 * @desc 회원 pt권 계산
	 * @param Map<String, String> ptInfo
	 * @return void
	 * @throws Exception
	 */
	public void updateUsr1000UserPtCount(Map<String, String> ptInfo) throws Exception {
		update("pcs1000DAO.updateUsr1000UserPtCount", ptInfo);
		
	}

	/**
	 * Func : 수업 정보 업데이트
	 * 
	 * @desc 수업 정보 업데이트
	 * @param Map<String, String> ptInfo
	 * @return void
	 * @throws Exception
	 */
	public void updatePcs1000Pt(Map<String, String> ptInfo) throws Exception {
		update("pcs1000DAO.updatePcs1000Pt", ptInfo);
		
	}

	/**
	 * Func : 트레이너 pt 수행 횟수 계산 메서드
	 * 
	 * @desc 트레이너 pt 수행 횟수 계산
	 * @param Map<String, String> ptInfo
	 * @return void
	 * @throws Exception
	 */
	public void updateTra1000TrainerPtCount(Map<String, String> ptInfo) throws Exception {
		update("pcs1000DAO.updateTra1000TrainerPtCount", ptInfo);
		
	}
}