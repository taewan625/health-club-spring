package healthclub.usr.usr1000.usr1000.service.impl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import healthclub.com.util.Pagination;
import healthclub.com.vo.SearchVO;
import healthclub.usr.usr1000.usr1000.service.Usr1000Service;

/**
 * @Class Name : Usr1000ServiceImpl.java
 * @Description 회원 서비스 클래스
 * @version 1.0
 * @author 권태완
 * @Since 2024.1.18.
 * @Modification Information
 * @
 * @  수정일            수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2024.01.18	   권태완		  최초생성
 * @ 2024.03.09	   권태완		 회원 상세 로직을 cmm2000 공통 관리로 분리
 * 
 * @see Copyright (C) All right reserved.
 */
@Service("usr1000Service")
public class Usr1000ServiceImpl extends EgovAbstractServiceImpl implements Usr1000Service {
	
	@Resource(name="usr1000DAO")
	private Usr1000DAO usr1000DAO;
	
	/**
	 * Func : 회원관리 화면의 default 정보를 보여주는 메서드
	 * 
	 * @desc 회원 관리 정보 리스트 표출
	 * @param
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> selectUsr1000UserList(SearchVO searchVO) throws Exception {
		// 사용자 정보 및 페이징 정보를 담을 모델 객체
		Map<String, Object> result = new HashMap<>();

		// 조건에 맞는 데이터 목록 개수를 가져옴
		int totalRecordCount = usr1000DAO.selectUsr1000UserListTotalCount(searchVO);
		
		// 조회할 데이터가 없는 경우
		if (totalRecordCount < 1) {
			result.put("pagination", null);
			result.put("users", null); 
			
		} 
		// 조회할 데이터가 존재하는 경우
		else {
			// 페이징 처리
			result.put("pagination", new Pagination(totalRecordCount, searchVO));
			
			// 조건에 맞는 데이터 목록 처리
			result.put("users", usr1000DAO.selectUsr1000UserInfoList(searchVO));
		}
		
		return result;
	};


	
	/**
	 * Func : 회원 등록 메서드
	 * 
	 * @desc 회원 등록 및 사물함 배정
	 * @param Map<String, String> userInfo, Map<String, String> lockerInfo
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void insertUsr1000UserLockerInfo(Map<String, String> userInfo, Map<String, String> lockerInfo) throws Exception {
		// 회원 정보 등록
		usr1000DAO.insertUsr1000UserInfo(userInfo);			
		
		// 배정할 사물함 정보가 존재하면 사물함 배정 로직 수행
		if (lockerInfo.size() > 2) {
			assignLocker(lockerInfo);
		} 
	}
	
	
	/**
	 * Func : 수정할 회원 정보 반환 메서드
	 * 
	 * @desc 회원 수정에 필요한 정보를 반환하는 메서드
	 * @param String userId
	 * @return Map<String, String>
	 * @throws Exception
	 */
	@Override
	public Map<String, String> selectUsr1000EditUserInfo(String userId) throws Exception {
		return usr1000DAO.selectUsr1000EditUserInfo(userId);
	}

	/**
	 * Func : 회원 정보 수정 메서드
	 * 
	 * @desc 회원 정보 수정 메서드
	 * @param Map<String, String> userInfo, Map<String, String> lockerInfo, Map<String, String> metaData
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void updateUsr1000UserLockerInfo(Map<String, String> userInfo, Map<String, String> lockerInfo) throws Exception {
		// 회원 정보 수정 : 사물함 수정 정보 존재 시, 사물함 이슈값도 초기화 됨.
		usr1000DAO.updateUsr1000UserInfo(userInfo);
		
		// 수정할 사물함이 존재하지 않으면 로직 종료
		if (lockerInfo.size() == 2) {
			return;
		} 
		
		// 자기 사물함 존재하는지 찾기
		String beforeLockerId = lockerInfo.get("beforeLockerId");
		String beforeLockerExpire = lockerInfo.get("beforeLockerExpire");
		
		// 자기 사물함 없으면 기존 사물함 등록 로직 수행
		if (beforeLockerId == null || beforeLockerExpire == null) {
			assignLocker(lockerInfo);

		}
		// 자기 사물함 존재 시, 수정하는 로직 수행
		else {
			// 제거 이력에 사용하는 map
			Map<String, String> removeHistoryMap = new HashMap<>();
			
			removeHistoryMap.put("lockerId", beforeLockerId);
			removeHistoryMap.put("userId", userInfo.get("userId"));
			removeHistoryMap.put("lockerExpire", beforeLockerExpire);
			
			removeHistoryMap.put("metaId", userInfo.get("metaId"));
			removeHistoryMap.put("metaIp", userInfo.get("metaIp"));
			
			
			// 자기 사물함 있으면 기존 사물함과 정보 동일한지 확인 
			if (beforeLockerId.equals(lockerInfo.get("lockerId"))) {
				// 1. 사물함 정보 업데이트 
				usr1000DAO.updateLck1000LockerInfo(lockerInfo);
				
				// 자기 사물함인데 만료된 사물함
				if (LocalDate.parse(beforeLockerExpire, DateTimeFormatter.ISO_LOCAL_DATE).isBefore(LocalDate.now())) {
					// [endCode: 03] : 자의적 변경 해제
					removeHistoryMap.put("endCode", "03");
					
					usr1000DAO.insertLck1100LockerInfo(removeHistoryMap);
					// [endCode: 01] : 자의적 사물함 배정
					lockerInfo.put("endCode", "01");
					usr1000DAO.insertLck1100LockerInfo(lockerInfo);
					
				} 
				
				// 자기 사물함인데 만료되지 않은 사물함
				else {
					// [endCode: 02] : 자의적으로 변경
					lockerInfo.put("endCode", "02");
					usr1000DAO.insertLck1100LockerInfo(lockerInfo);
				}
			
			} 
			// 자기 사물함 있는데 기존 사물함과 정보 다름
			else {
				// 1. 자기 사물함 제거용 업데이트 
				usr1000DAO.deleteLck1000LockerInfo(removeHistoryMap);
				
				// 2. 제거 이력 남기기
				// [endCode: 03] : 자의적 변경 해제
				removeHistoryMap.put("endCode", "03");
				
				usr1000DAO.insertLck1100LockerInfo(removeHistoryMap);
				
				// 3. 로직 private으로 분리해서 공통으로 사용하기
				assignLocker(lockerInfo);				
			}
		}
	}

	
	/**
	 * Func : 사물함 배정 메서드
	 * 
	 * @desc 해당 회원이 사물함 정보가 없다고 가정 하고 사물함을 배정하는 로직
	 * @param Map<String, String> lockerInfo, Map<String, String> metaData
	 * @return void
	 * @throws Exception
	 */
	private void assignLocker(Map<String, String> lockerInfo) throws Exception, SQLException {
		// 등록할 사물함 정보 찾기 {lockerId=2, lockerExpire=2024-02-08, userId=최종적으로 사물함에 등록 될 회원id}
		Map<String, String> beforelockerData = usr1000DAO.selectLck1000LockerInfo(lockerInfo.get("lockerId"));
		beforelockerData.put("metaId", lockerInfo.get("metaId"));
		beforelockerData.put("metaIp", lockerInfo.get("metaIp"));

		// 사물함이 비어있는 정상적 등록인 경우
		if (beforelockerData.get("lockerStatus").equals("empty")) {
			// 정상적 사물함 등록 중 - 만료된 기존 회원 정보가 있는 경우 이력만 추가 (만료로 인한 정상적 배정 해제 코드 : "03")
			if (beforelockerData.get("lockerExpire") != null) {
				// [endCode: 03] : 자의적 변경 해제
				beforelockerData.put("endCode", "03");
				
				usr1000DAO.insertLck1100LockerInfo(beforelockerData);
			}
			
		} 
		// 사용회원이 존재하는 사물함을 탈취하는 비정상적 등록인 경우
		else {
			 
			 /* 비정상적 사물함 등록 흐름
			 *  1. (박힌돌)  기존 회원 사물함 처리 로직
			 *  	1. 기존 사물함 삭제 이력 등록 							(탈취로 인한 만료 코드 : "04")
			 *  	2. 비어있는 사물함을 찾는다.
			 *  		1. 비어있는 사물함이 만료된 회원 정보 존재 시 이력 등록		(만료로 인한 정상적 배정 해제 코드 : "03")
			 *  	3. 비어있는 사물함에 기존 회원의 사물함 정보를 업데이트
			 *		4. 사물함 변경 이력 등록								(탈취로 인한 사물함 변경 코드 : "05")
			 *  	5. 기존 인원 이슈 컬럼 상태 업데이트 						( 'N' -> 'Y' )

			 *  2. (굴러온돌) 사물함 처리 로직
			 *  	1. 회원 정보를 사물함에 등록
			 *  	2. 등록된 정보를 사물함 이력에 등록						(정상 사물함 배정 코드 : "01")
			 *  	3. 커밋 및 트랜잭션 종료
			 */
			
			// 1.1. 기존 사물함 회원 삭제 이력 등록
			// [endCode: 04] : 탈취로 인한 사물함 해제
			beforelockerData.put("endCode", "04");
			usr1000DAO.insertLck1100LockerInfo(beforelockerData);
			
			// 1.2. 탈취당한 회원이 찾은 비어있는 사물함
			Map<String, String> emptyLocker = usr1000DAO.selectLck1000EmptyLocker();

			// 1.2.1. 탈취당한 회원이 찾은 사물함에 기존 회원 정보가 있는 경우 이력만 추가 (만료로 인한 정상적 배정 해제 코드 : "03")
			if(emptyLocker.get("lockerExpire") != null) {
				// [endCode: 03] : 자의적 변경 해제
				emptyLocker.put("endCode", "03");
				
				emptyLocker.put("metaId", lockerInfo.get("metaId"));
				emptyLocker.put("metaIp", lockerInfo.get("metaIp"));
				
				usr1000DAO.insertLck1100LockerInfo(emptyLocker);
			}
			
			// 이슈 발생한 사물함 정보 저장
			String beforeLockerId = beforelockerData.get("lockerId");
			String emptyLockerId = emptyLocker.get("lockerId");
			
			// 3. 비어있는 사물함에 탈취당한 회원의 사물함 정보를 업데이트
			beforelockerData.put("lockerId", emptyLockerId);

			usr1000DAO.updateLck1000LockerInfo(beforelockerData);

			// 4. 탈취당한 회원이 변경한 사물함의 이력 등록
			// [endCode: 05] : 탈취로 인한 변경
			beforelockerData.put("endCode", "05");
			usr1000DAO.insertLck1100LockerInfo(beforelockerData);

			// 5. 회원의 이슈 업데이트
			Map<String, String> userIssueInfo= new HashMap<>();
			userIssueInfo.put("userId", beforelockerData.get("userId"));
			userIssueInfo.put("lockerIssue", beforeLockerId + "," + emptyLockerId);
			userIssueInfo.put("metaId", lockerInfo.get("metaId"));
			userIssueInfo.put("metaIp", lockerInfo.get("metaIp"));
			
			usr1000DAO.updateUsr1000LockerIssue(userIssueInfo);
		}
		
		 /* 정상적 사물함 등록 공통 흐름
		 *  1. 회원 정보를 사물함에 등록
		 *  2. 등록된 정보를 사물함 이력에 등록 (정상 사물함 배정 코드 : "01")
		 *  3. 커밋 및 트랜잭션 종료
		 */
		usr1000DAO.updateLck1000LockerInfo(lockerInfo);	
		// [endCode: 01] : 자의적 사물함 배정
		lockerInfo.put("endCode", "01");
		usr1000DAO.insertLck1100LockerInfo(lockerInfo);
	}


	/**
	 * Func : 삭제 불가한 회원 찾는 메서드
	 * 
	 * @desc 예정된 pt가 있는 회원
	 * @param List<String> requestDatas
	 * @return List<String>
	 * @throws Exception
	 */
	@Override
	public List<String> selectUsr1000HavePt(List<String> requestDatas) throws Exception {
		return usr1000DAO.selectUsr1000HavePt(requestDatas);
	}


	/**
	 * Func : 삭제 불가한 회원 찾는 메서드
	 * 
	 * @desc pt 횟수가 남은 회원
	 * @param List<String> requestDatas
	 * @return List<String>
	 * @throws Exception
	 */
	@Override
	public List<String> selectUsr1000UserListRemainPt(List<String> requestDatas) throws Exception {
		return usr1000DAO.selectUsr1000UserListRemainPt(requestDatas);
	}
	
	
	/**
	 * Func : 회원 삭제 메서드
	 * 
	 * @desc 회원 삭제 및 보유 사물함 삭제
	 * @param List<String> userIds, List<Map<String, String>> deleteUserInfos
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void deleteUsr1000UserList(List<String> userIds, List<Map<String, String>> deleteUserInfos) throws Exception {
		// 회원 삭제 및 - 수업 0 처리, 이슈 없음 처리
		for (Map<String, String> deleteUserInfo : deleteUserInfos) {
			usr1000DAO.deleteUsr1000UserList(deleteUserInfo);
			
		}
		
		// 해당 회원의 정보를 가지고 있는 locker 정보 가져오기 List<Map>
		List<Map<String, String>> lockerInfos = usr1000DAO.selectUsr1000UserLockerList(userIds);
		
		// 해당 회원 사물함 정보 삭제
		for (Map<String, String> deleteInfo : deleteUserInfos) {
			usr1000DAO.deleteLck1000LockerInfo(deleteInfo);
			
		}
		
		// 해당 회원 사물함 이력 쌓기
		for (Map<String, String> lockerInfo : lockerInfos) {
			lockerInfo.put("metaId", deleteUserInfos.get(0).get("metaId"));
			lockerInfo.put("metaIp", deleteUserInfos.get(0).get("metaIp"));
			
			usr1000DAO.insertLck1100LockerInfo(lockerInfo);
		}
		
	}
}
