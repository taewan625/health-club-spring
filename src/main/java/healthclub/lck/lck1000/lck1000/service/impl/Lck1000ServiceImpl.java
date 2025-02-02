package healthclub.lck.lck1000.lck1000.service.impl;

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
import healthclub.lck.lck1000.lck1000.service.Lck1000Service;


/**
 * @Class Name : Lck1000ServiceImpl.java
 * @Description 사물함 서비스 클래스
 * @version 1.0
 * @author 권태완
 * @Since 2024.01.18.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
@Service("lck1000Service")
public class Lck1000ServiceImpl extends EgovAbstractServiceImpl implements Lck1000Service {

	@Resource(name="lck1000DAO")
	private Lck1000DAO lck1000DAO;
	
	
	/**
	 * Func : 사물함 화면의 default 정보를 보여주는 메서드
	 * 
	 * @desc 필요 사물함  정보 가져옴
	 * @param SearchVO searchVO
	 * @return Object
	 * @throws Exception
	 */
	@Override
	public Object selectLck1000LockerList(SearchVO searchVO) throws Exception {
		// 사용자 정보 및 페이징 정보를 담을 모델 객체
		Map<String, Object> result = new HashMap<>();

		// 조건에 맞는 데이터 목록 개수를 가져옴
		int totalRecordCount = lck1000DAO.selectLck1000LockerListTotalCount(searchVO);

		// 조회할 데이터가 없는 경우
		if (totalRecordCount < 1) {
			result.put("pagination", null);
			result.put("lockers", null);

		} 
		// 조회할 데이터가 존재하는 경우
		else {
			// 페이징 처리
			result.put("pagination", new Pagination(totalRecordCount, searchVO));

			// 2. 조건에 맞는 데이터 목록 처리
			result.put("lockers", lck1000DAO.selectLck1000LockerInfoList(searchVO));

		}
		
		return result;
	}


	/**
	 * Func : 사물함 정보 반환 메서드
	 * 
	 * @desc 사물함 배정 수정에 필요한 정보를 반환하는 메서드
	 * @param String lockerId
	 * @return void
	 * @throws Exception
	 */
	@Override
	public Map<String, String> selectLck1000EditTrainerInfo(String lockerId) throws Exception {
		return lck1000DAO.selectLck1000EditLockerInfo(lockerId);

	}


	/**
	 * Func : 사물함 배정 메서드
	 * 
	 * @desc 사물함을 배정 받지 않은 회원의 사물함 배정
	 * @param Map<String, String> lockerInfo, Map<String, String> lockerInfo
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void insertLck1000Locker(Map<String, String> lockerInfo) throws Exception {
		// 사물함 배정 로직
		assignLocker(lockerInfo);
	}
	
	
	
	/**
	 * Func : 사물함 배정 메서드
	 * 
	 * @desc 사물함을 배정 받지 않은 회원의 사물함 배정
	 * @param Map<String, String> lockerInfo, Map<String, String> lockerInfo
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void updateLck1000Locker(Map<String, String> lockerInfo) throws Exception {
		// 자기 사물함 존재하는지 찾기
		String beforeLockerId = lockerInfo.get("initLockerId");
		String beforeLockerExpire = lockerInfo.get("initLockerExpire");
		
		// 제거 이력에 사용하는 map
		Map<String, String> removeHistoryMap = new HashMap<>();

		removeHistoryMap.put("lockerId", beforeLockerId);
		removeHistoryMap.put("userId", lockerInfo.get("userId"));
		removeHistoryMap.put("lockerExpire", beforeLockerExpire);

		removeHistoryMap.put("metaId", lockerInfo.get("metaId"));
		removeHistoryMap.put("metaIp", lockerInfo.get("metaIp"));
		
		// 자기 사물함 있으면 기존 사물함 과 정보 동일한지 확인 
		if (beforeLockerId.equals(lockerInfo.get("lockerId"))) {

			// 1. 사물함 정보 업데이트 
			lck1000DAO.updateLck1000LockerInfo(lockerInfo);
			
			if (LocalDate.parse(beforeLockerExpire, DateTimeFormatter.ISO_LOCAL_DATE).isBefore(LocalDate.now())) {
				// [endCode: 03] : 자의적 변경 해제
				removeHistoryMap.put("endCode", "03");
				
				lck1000DAO.insertLck1100LockerInfo(removeHistoryMap);
				// [endCode: 01] : 자의적 사물함 배정
				lockerInfo.put("endCode", "01");
				lck1000DAO.insertLck1100LockerInfo(lockerInfo);
				
				
			} 
			
			// 자기 사물함인데 만료되지 않은 사물함
			else {
				// [endCode: 02] : 자의적으로 변경
				lockerInfo.put("endCode", "02");
				lck1000DAO.insertLck1100LockerInfo(lockerInfo);
			}

		}
		
		// 자기 사물함 있는데 기존 사물함과 정보 다름
		else {
			// 1. 자기 사물함 제거용 업데이트 
			lck1000DAO.deleteLck1000LockerInfo(removeHistoryMap);
			
			// 2. 제거 이력 남기기
			// [endCode: 03] : 자의적 변경 해제
			removeHistoryMap.put("endCode", "03");
			
			lck1000DAO.insertLck1100LockerInfo(removeHistoryMap);
			
			// 3. 로직 private으로 분리해서 공통으로 사용하기
			assignLocker(lockerInfo);				
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
		Map<String, String> beforelockerData = lck1000DAO.selectLck1000LockerInfo(lockerInfo.get("lockerId"));
		beforelockerData.put("metaId", lockerInfo.get("metaId"));
		beforelockerData.put("metaIp", lockerInfo.get("metaIp"));
		
		// {lockerExpire=null, lockerId=1, lockerStatus=empty, userId=null}
		
		// 사물함이 비어있는 정상적 등록인 경우
		if ("empty".equals(beforelockerData.get("lockerStatus"))) {
			
			// 정상적 사물함 등록 중 - 만료된 기존 회원 정보가 있는 경우 이력만 추가 (만료로 인한 정상적 배정 해제 코드 : "03")
			if (beforelockerData.get("lockerExpire") != null) {
				// [endCode: 03] : 자의적 변경 해제
				beforelockerData.put("endCode", "03");
				
				lck1000DAO.insertLck1100LockerInfo(beforelockerData);
				
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
			lck1000DAO.insertLck1100LockerInfo(beforelockerData);
			
			// 1.2. 탈취당한 회원이 찾은 비어있는 사물함
			Map<String, String> emptyLocker = lck1000DAO.selectLck1000EmptyLocker();

			// 1.2.1. 탈취당한 회원이 찾은 사물함에 기존 회원 정보가 있는 경우 이력만 추가 (만료로 인한 정상적 배정 해제 코드 : "03")
			if(emptyLocker.get("lockerExpire") != null) {
				// [endCode: 03] : 자의적 변경 해제
				emptyLocker.put("endCode", "03");
				
				emptyLocker.put("metaId", lockerInfo.get("metaId"));
				emptyLocker.put("metaIp", lockerInfo.get("metaIp"));
				
				lck1000DAO.insertLck1100LockerInfo(emptyLocker);
			
			}
			
			// 이슈 발생한 사물함 정보 저장
			String beforeLockerId = beforelockerData.get("lockerId");
			String emptyLockerId = emptyLocker.get("lockerId");
			
			// 3. 비어있는 사물함에 탈취당한 회원의 사물함 정보를 업데이트
			beforelockerData.put("lockerId", emptyLockerId);

			lck1000DAO.updateLck1000LockerInfo(beforelockerData);

			// 4. 탈취당한 회원이 변경한 사물함의 이력 등록
			// [endCode: 05] : 탈취로 인한 변경
			beforelockerData.put("endCode", "05");
			lck1000DAO.insertLck1100LockerInfo(beforelockerData);

			// 5. 회원의 이슈 업데이트
			Map<String, String> userIssueInfo= new HashMap<>();
			userIssueInfo.put("userId", beforelockerData.get("userId"));
			userIssueInfo.put("lockerIssue", beforeLockerId + "," + emptyLockerId);
			userIssueInfo.put("metaId", lockerInfo.get("metaId"));
			userIssueInfo.put("metaIp", lockerInfo.get("metaIp"));
			
			lck1000DAO.updateUsr1000LockerIssue(userIssueInfo);
			
		}
		
		 /* 정상적 사물함 등록 공통 흐름
		 *  1. 회원 정보를 사물함에 등록
		 *  2. 등록된 정보를 사물함 이력에 등록 (정상 사물함 배정 코드 : "01")
		 *  3. 커밋 및 트랜잭션 종료
		 */
		lck1000DAO.updateLck1000LockerInfo(lockerInfo);	
		// [endCode: 01] : 자의적 사물함 배정
		lockerInfo.put("endCode", "01");
		lck1000DAO.insertLck1100LockerInfo(lockerInfo);
		
	}


	/**
	 * Func : 사물함 삭제 및 이력 등록
	 * 
	 * @desc 사물함 삭제 및 이력 등록
	 * @param List<String> lockers, Map<String, String> metaData
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void deleteLck1000LockerList(List<String> lockerIds, Map<String, String> metaDatas) throws Exception {
		// 회원 정보를 가지는 모든 사물함 정보 조회
		List<Map<String,String>> deleteDatas = lck1000DAO.selectLck1000LockerDeleteInfo(lockerIds);
		
		// 삭제 이력 다중 등록
		for (Map<String,String> deleteData : deleteDatas) {
			deleteData.put("metaId", metaDatas.get("metaId"));
			deleteData.put("metaIp", metaDatas.get("metaIp"));
			
			lck1000DAO.deleteLck1000LockerInfo(deleteData);
			lck1000DAO.insertLck1100LockerInfo(deleteData);
			
        }
	}
}
