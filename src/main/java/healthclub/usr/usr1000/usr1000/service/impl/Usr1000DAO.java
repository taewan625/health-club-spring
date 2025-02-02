package healthclub.usr.usr1000.usr1000.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import healthclub.com.vo.SearchVO;
import healthclub.usr.usr1000.usr1000.service.Usr1000VO;


/**
 * @Class Name : Usr1000DAO.java
 * @Description 회원 DAO 클래스
 * @version 1.0
 * @author 권태완
 * @Since 2024.01.18.
 * @Modification Information
 * @
 * @  수정일            수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2024.01.18	   권태완		  최초생성
 * @ 2024.03.09	   권태완		 회원 상세 로직을 cmm2000 공통 관리로 분리
 * 
 * @see Copyright (C) All right reserved.
 */
@Repository("usr1000DAO")
public class Usr1000DAO extends EgovAbstractDAO {
	
	/**
	 * Func : 페이징 처리를 위한 조건에 따른 목록 개수 반환 메서드
	 * 
	 * @desc 조건에 따라서 유저 목록 개수 반환
	 * @param SearchVO searchVO
	 * @return int
	 * @throws Exception
	 */
	public int selectUsr1000UserListTotalCount(SearchVO searchVO) throws Exception {
		return (int) select("usr1000DAO.selectUsr1000UserListTotalCount", searchVO);
	}
	
	
	/**
	 * Func : user list를 반환하는 메서드
	 * 
	 * @desc 조건에 따라서 유저의 정보를 반환한다.
	 * @param SearchVO searchVO
	 * @return List<Usr1000VO>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Usr1000VO> selectUsr1000UserInfoList(SearchVO searchVO) throws Exception {
		return (List<Usr1000VO>) list("usr1000DAO.selectUsr1000UserInfoList", searchVO);
	}

	
	/**
	 * Func : 수정할 회원 정보 반환 메서드
	 * 
	 * @desc 회원 수정에 필요한 정보를 반환하는 메서드
	 * @param String userId
	 * @return Map<String, String>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> selectUsr1000EditUserInfo(String userId) throws Exception {
		return (Map<String, String>) select("usr1000DAO.selectUsr1000EditUserInfo", userId);
	}

	/**
	 * Func : 회원 등록 메서드
	 * 
	 * @desc 회원 등록
	 * @param Map<String, String> userInfo
	 * @return void
	 * @throws Exception
	 */
	public void insertUsr1000UserInfo(Map<String, String> userInfo) throws Exception {
		 insert("usr1000DAO.insertUsr1000UserInfo", userInfo);
	}


	/**
	 * Func : 회원 수정 메서드
	 * 
	 * @desc 회원 수정
	 * @param Map<String, String> userInfo
	 * @return void
	 * @throws Exception
	 */
	public void updateUsr1000UserInfo(Map<String, String> userInfo) throws Exception {
		update("usr1000DAO.updateUsr1000UserInfo", userInfo);
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
		return (Map<String, String>) select("usr1000DAO.selectLck1000LockerInfo", lockerId);
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
		insert("usr1000DAO.insertLck1100LockerInfo", lockerData);
		
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
		update("usr1000DAO.updateLck1000LockerInfo", lockerData);
		
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
		return (Map<String, String>) select("usr1000DAO.selectLck1000EmptyLocker");		
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
		update("usr1000DAO.updateUsr1000LockerIssue", userIssueInfo);
		
	}

	
	/**
	 * Func : 사물함 탈취 당한 인원 이슈 정보 업데이트 메서드
	 *
	 * @desc 사물함 탈취 당한 인원 이슈 정보 업데이트 메서드
	 * @param  Map<String, String> lockerInfo
	 * @return void
	 * @throws Exception
	 */
	public void deleteLck1000LockerInfo(Map<String, String> lockerInfo) throws Exception {
		update("usr1000DAO.deleteLck1000LockerInfo", lockerInfo);
		
	}

	
	/**
	 * Func : 삭제 불가한 회원 찾는 메서드
	 * 
	 * @desc 예정된 pt가 있는 회원
	 * @param List<String> requestDatas
	 * @return List<String>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<String> selectUsr1000HavePt(List<String> requestDatas) throws Exception {
		return (List<String>) list("usr1000DAO.selectUsr1000HavePt", requestDatas);
	}

	
	/**
	 * Func : 삭제 불가한 회원 찾는 메서드
	 * 
	 * @desc pt 횟수가 남은 회원
	 * @param List<String> requestDatas
	 * @return List<String>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<String> selectUsr1000UserListRemainPt(List<String> requestDatas) throws Exception {
		return (List<String>) list("usr1000DAO.selectUsr1000UserListRemainPt", requestDatas);
	}

	
	/**
	 * Func : 회원 삭제 메서드
	 * 
	 * @desc 회원 삭제 및 수업 0 처리, 이슈 없음 처리
	 * @param Map<String, String> deleteInfo
	 * @return void
	 * @throws Exception
	 */
	public void deleteUsr1000UserList(Map<String, String> deleteUserInfo) throws Exception {
		update("usr1000DAO.deleteUsr1000UserList", deleteUserInfo);
	}
	
	
	/**
	 * Func : 회원들 중 사물함 정보를 가진 회원의 사물함 정보 반환 
	 * 
	 * @desc 회원 삭제 및 수업 0 처리, 이슈 없음 처리
	 * @param List<String> userIds
	 * @return List<Map<String, String>>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectUsr1000UserLockerList(List<String> userIds) throws Exception {
		return (List<Map<String, String>>) list("usr1000DAO.selectUsr1000UserLockerList", userIds);
	}

}
