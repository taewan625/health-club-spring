package healthclub.cmm.cmm2000.cmm2000.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import healthclub.cmm.cmm2000.cmm2000.service.Cmm2000Service;
import healthclub.com.util.Pagination;
import healthclub.com.vo.SearchVO;


/**
 * @Class Name : Cmm2000ServiceImpl.java
 * @Description 공통 로직 처리 서비스
 * @version 1.0
 * @author 권태완
 * @Since 2024.01.12.
 * @Modification Information
 * @
 * @  수정일            수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2024.01.12	   권태완		  최초생성
 * @ 2024.03.09	   권태완		 회원, 트레이너, 수업 상세 조회 공통 로직 추가
 * 
 * @see Copyright (C) All right reserved.
 */
@Service("cmm2000Service")
public class Cmm2000ServiceImpl extends EgovAbstractServiceImpl implements Cmm2000Service {

	@Resource(name="cmm2000DAO")
	private Cmm2000DAO cmm2000DAO;

	/**
	 * Func : 수업을 위한 유저 팝업 정보 반환 메서드
	 * 
	 * @desc 수업을 위한 유저 팝업 정보 반환 메서드
	 * @param SearchVO searchVO
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> selectUsr1000UserListForPT(SearchVO searchVO) throws Exception {
		// 사용자 정보 및 페이징 정보를 담을 모델 객체
		Map<String, Object> result = new HashMap<>();

		// 조건에 맞는 데이터 목록 개수를 가져옴
		int totalRecordCount = cmm2000DAO.selectUsr2000TotalCountForPT(searchVO);

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
			result.put("users", cmm2000DAO.selectUsr1000ListForPT(searchVO));
		}

		return result;
	};

	/**
	 * Func : 사물함을 위한 유저 팝업 정보 반환 메서드
	 * 
	 * @desc 사물함을 위한 유저 팝업 정보 반환 메서드
	 * @param SearchVO searchVO
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> selectUsr1000ListForLocker(SearchVO searchVO) throws Exception {
		// 사용자 정보 및 페이징 정보를 담을 모델 객체
		Map<String, Object> result = new HashMap<>();

		// 조건에 맞는 데이터 목록 개수를 가져옴
		int totalRecordCount = cmm2000DAO.selectUsr1000UserListForLockerCount(searchVO);

		// 조회할 데이터가 없는 경우
		if (totalRecordCount < 1) {
			result.put("pagination", null);
			result.put("users", null);

		}
		// 조회할 데이터가 있는 경우
		else {
			// 1. 페이징 처리
			result.put("pagination", new Pagination(totalRecordCount, searchVO));

			// 2. 조건에 맞는 데이터 목록 처리
			result.put("users", cmm2000DAO.selectUsr1000UserListForLocker(searchVO));
		}
		
		return result;
	}
	
	
	/**
	 * Func : 수업을 위한 트레이너 팝업 정보 반환 메서드
	 * 
	 * @desc 수업을 위한 트레이너 팝업 정보 반환 메서드
	 * @param SearchVO searchVO
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> selectUsr1000TrainerListForPT(SearchVO searchVO) throws Exception {
		// 사용자 정보 및 페이징 정보를 담을 모델 객체
		Map<String, Object> result = new HashMap<>();

		// 조건에 맞는 데이터 목록 개수를 가져옴
		int totalRecordCount = cmm2000DAO.selectTra1000TrainerTotalCountForPT(searchVO);

		// 조회할 데이터가 없는 경우
		if (totalRecordCount < 1) {
			result.put("pagination", null);
			result.put("trainers", null);

		} 
		// 조회할 데이터가 있는 경우
		else {
			// 1. 페이징 처리
			result.put("pagination", new Pagination(totalRecordCount, searchVO));

			// 2. 조건에 맞는 데이터 목록 처리
			result.put("trainers", cmm2000DAO.selectTra1000ListForPT(searchVO));

		}
		
		return result;
	}
	
	
	/**
	 * Func : 사물함 화면  정보를 보여주는 메서드
	 * 
	 * @desc 사물함  정보 가져옴
	 * @param SearchVO searchVO
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> selectLck1000List(SearchVO searchVO) throws Exception {
		// 사용자 정보 및 페이징 정보를 담을 모델 객체
		Map<String, Object> result = new HashMap<>();

		// 조건에 맞는 데이터 목록 개수를 가져옴
		int totalRecordCount = cmm2000DAO.selectLck1000LockerInfoTotalCount(searchVO);
		
		// 조회할 데이터가 없는 경우
		if (totalRecordCount < 1) {
			result.put("pagination", null);
			result.put("lockers", null);

		} 
		// 조회할 데이터가 있는 경우
		else {
			// 1. 페이징 처리
			result.put("pagination", new Pagination(totalRecordCount, searchVO));

			// 2. 조건에 맞는 데이터 목록 처리
			result.put("lockers", cmm2000DAO.selectLck1000LockerInfoList(searchVO));
		}

		return result;
	}

	
	/**
	 * Func : 회원상세 정보 반환 메서드
	 * 
	 * @desc 회원상세에 필요한 정보를 반환
	 * @param String userId
	 * @return EgovMap
	 * @throws Exception
	 */
	@Override
	public EgovMap selectUsr1000UserDetail(String userId) throws Exception {
		return cmm2000DAO.selectUsr1000UserDetail(userId);
	}
	
	
	/**
	 * Func : 트레이너상세 정보 반환 메서드
	 * 
	 * @desc 트레이너상세에 필요한 정보를 반환
	 * @param String trainerId
	 * @return EgovMap
	 * @throws Exception
	 */
	@Override
	public EgovMap selectTra1000TrainerDetail(String trainerId) throws Exception {
		return cmm2000DAO.selectTra1000TrainerDetail(trainerId);
		
	}
	
	
	/**
	 * Func : 개인 pt 정보를 보여주는 메서드
	 * 
	 * @desc 개인 pt 정보 반환
	 * @param String ptId
	 * @return EgovMap
	 * @throws Exception
	 */
	@Override
	public EgovMap selectPcs1000DetailUserPtInfo(String ptId) throws Exception {
		return cmm2000DAO.selectPcs1000DetailUserPtInfo(ptId);
	}
}
