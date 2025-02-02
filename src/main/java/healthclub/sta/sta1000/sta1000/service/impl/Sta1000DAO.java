package healthclub.sta.sta1000.sta1000.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : Sta1000DAO.java
 * @Description 회원 data로 통계정보를 출력
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.28.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
@Repository("sta1000DAO")
public class Sta1000DAO extends EgovAbstractDAO {

	
	/**
	 * Func : 새로운 회원 반환 메서드
	 * 
	 * @desc 새로운 회원 반환
	 * @param 
	 * @return List<Map<String, String>>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<EgovMap> selectUsr1000NewUser() throws Exception {
		return (List<EgovMap>)list("sta1000DAO.selectUsr1000NewUser");
	}
	
	/**
	 * Func : 임박회원 반환 메서드
	 * 
	 * @desc 임박회원 반환 메서드
	 * @param 
	 * @return List<Map<String, String>>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<EgovMap> selectUsr1000FinishUser() throws Exception {
		return (List<EgovMap>)list("sta1000DAO.selectUsr1000FinishUser");
	}

	/**
	 * Func : 종류별 pt 최대 수행한 트레이너 반환 메서드
	 * 
	 * @desc 종류별 pt 최대 수행한 트레이너 반환 메서드
	 * @param Map<String, String> ptInfo
	 * @return List<Map<String, String>>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<EgovMap> selectPcs1000MostTrainerPt(String ptCode) throws Exception {
		return (List<EgovMap>)list("sta1000DAO.selectPcs1000MostTrainerPt", ptCode);
	}
	
	
	/**
	 * Func : 종류별 pt 최대 수행한 회원 반환 메서드
	 * 
	 * @desc 종류별 pt 최대 수행한  회원 반환 메서드
	 * @param Map<String, String> ptInfo
	 * @return List<Map<String, String>>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<EgovMap> selectPcs1000MostUserPt(String ptCode) throws Exception {
		return (List<EgovMap>)list("sta1000DAO.selectPcs1000MostUserPt", ptCode);
	}

	/**
	 * Func : 최근 수행된 수업 통계 반환 메서드
	 * 
	 * @desc 최근 수행된 수업 통계 반환 메서드
	 * @param 
	 * @return List<Map<String, String>>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<EgovMap> selectPcs1000MostFinishPtSchedule() throws Exception {
		return (List<EgovMap>)list("sta1000DAO.selectPcs1000MostFinishPtSchedule");
	}
	
}
