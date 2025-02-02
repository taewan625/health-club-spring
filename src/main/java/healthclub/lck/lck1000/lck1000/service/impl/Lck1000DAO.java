package healthclub.lck.lck1000.lck1000.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import healthclub.com.vo.SearchVO;


/**
 * @Class Name : Lck1000DAO.java
 * @Description 사물함 DAO 클래스
 * @version 1.0
 * @author 권태완
 * @Since 2024.01.12.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
@Repository("lck1000DAO")
public class Lck1000DAO extends EgovAbstractDAO {

	
	/**
	 * Func : 페이징 처리를 위한 조건에 따른 목록 개수 반환 메서드
	 * 
	 * @desc 페이징 처리를 위한 조건에 따른 목록 개수 반환
	 * @param SearchVO searchVO
	 * @return int
	 * @throws Exception
	 */
	public int selectLck1000LockerListTotalCount(SearchVO searchVO) throws Exception {
		return (int) select("lck1000DAO.selectLck1000LockerListTotalCount", searchVO);	
	}

	/**
	 * Func : 사물함 리스트를 반환하는 메서드
	 * 
	 * @desc 조건에 따라서 유저의 정보를 반환한다.
	 * @param SearchVO searchVO
	 * @return List Map<String, String>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectLck1000LockerInfoList(SearchVO searchVO) throws Exception {
		return (List<Map<String, String>>) list("lck1000DAO.selectLck1000LockerInfoList", searchVO);
	}

	
	/**
	 * Func : 수정할 사물함 정보 반환 메서드
	 * 
	 * @desc 사물함 수정에 필요한 정보를 반환하는 메서드
	 * @param String lockerId
	 * @return Map<String, String>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> selectLck1000EditLockerInfo(String lockerId) throws Exception {
		return (Map<String, String>) select("lck1000DAO.selectLck1000EditLockerInfo", lockerId);
	}
	
	
	
	/**
	 * Func : 사물함 조회 메서드
	 * 
	 * @desc 사물함 조회
	 * @param String lockerId
	 * @return Map<String, String>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> selectLck1000LockerInfo(String lockerId) throws Exception {
		return (Map<String, String>) select("lck1000DAO.selectLck1000LockerInfo", lockerId);
	}

	
	/**
	 * Func : 사물함 이력 등록 메서드
	 * 
	 * @desc 사물함 이력 등록
	 * @param Map<String, String> lockerData
	 * @return void
	 * @throws Exception
	 */
	public void insertLck1100LockerInfo(Map<String, String> lockerData) throws Exception {
		insert("lck1000DAO.insertLck1100LockerInfo", lockerData);
		
	}
	
	
	/**
	 * Func : 비어있는 사물함 정보 반환 메서드
	 *
	 * @desc 비어있는 사물함 정보 반환 메서드
	 * @param 
	 * @return Map<String, String>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> selectLck1000EmptyLocker() throws Exception {
		return (Map<String, String>) select("lck1000DAO.selectLck1000EmptyLocker");		
	}
	
	
	/**
	 * Func : 사물함 정보 수정 메서드
	 *
	 * @desc 사물함 정보 수정 메서드
	 * @param Map<String, String> lockerData
	 * @return void
	 * @throws Exception
	 */
	public void updateLck1000LockerInfo(Map<String, String> lockerData) throws Exception {
		update("lck1000DAO.updateLck1000LockerInfo", lockerData);
		
	}
	
	
	/**
	 * Func : 사물함 탈취 당한 인원 이슈 정보 업데이트 메서드
	 *
	 * @desc 사물함 탈취 당한 인원 이슈 정보 업데이트 메서드
	 * @param Map<String, String> userIssueInfo
	 * @return void
	 * @throws Exception
	 */
	public void updateUsr1000LockerIssue(Map<String, String> userIssueInfo) throws Exception {
		update("lck1000DAO.updateUsr1000LockerIssue", userIssueInfo);
		
	}

	/**
	 * Func : 사물함 수정 중 사물함 단건 제거
	 *
	 * @desc 사물함 수정 중 사물함 단건 제거
	 * @param Map<String, String> removeHistoryMap
	 * @return void
	 * @throws Exception
	 */
	public void deleteLck1000LockerInfo(Map<String, String> lockerInfo) throws Exception {
		update("lck1000DAO.deleteLck1000LockerInfo", lockerInfo);
		
	}

	
	/**
	 * Func : 사물함 삭제 전 이력에 쌓은 데이터 조회
	 *
	 * @desc 사물함 수정 중 사물함 단건 제거
	 * @param Map<String, String> removeHistoryMap
	 * @return void
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectLck1000LockerDeleteInfo(List<String> lockerIds) throws Exception {
		return (List<Map<String, String>>) list("lck1000DAO.selectLck1000LockerDeleteInfo", lockerIds);
	}
}