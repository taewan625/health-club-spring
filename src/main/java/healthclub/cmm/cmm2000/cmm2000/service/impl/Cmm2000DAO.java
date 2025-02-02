package healthclub.cmm.cmm2000.cmm2000.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import healthclub.com.vo.SearchVO;


/**
 * @Class Name : Cmm2000DAO.java
 * @Description 공통 로직 처리 DAO
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
@Repository("cmm2000DAO")
public class Cmm2000DAO extends EgovAbstractDAO {
	
	
	/**
	 * Func : 페이징 처리를 위한 조건에 따른 목록 개수 반환 메서드
	 * 
	 * @desc 조건에 따라서 회원 목록 개수 반환
	 * @param SearchVO searchVO
	 * @return int
	 * @throws Exception
	 */
	public int selectUsr2000TotalCountForPT(SearchVO searchVO) throws Exception {
		return (int) select("cmm2000DAO.selectUsr2000TotalCountForPT", searchVO);	
		
	}

	
	/**
	 * Func : user list를 반환하는 메서드
	 * 
	 * @desc 조건에 따라서 사물함의 정보를 반환한다.
	 * @param SearchVO searchVO
	 * @return List<Map<String, String>>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectUsr1000ListForPT(SearchVO searchVO) throws Exception {
		return (List<Map<String, String>>) list("cmm2000DAO.selectUsr1000ListForPT", searchVO);
		
	}
	
	
	/**
	 * Func : 페이징 처리를 위한 조건에 따른 목록 개수 반환 메서드
	 * 
	 * @desc 조건에 따라서 사물함 목록 개수 반환
	 * @param SearchVO searchVO
	 * @return int
	 * @throws Exception
	 */
	public int selectLck1000LockerInfoTotalCount(SearchVO searchVO) throws Exception {
		return (int) select("cmm2000DAO.selectLck1000LockerInfoTotalCount", searchVO);	
		
	}
	
	
	/**
	 * Func : 사물함 list를 반환하는 메서드
	 * 
	 * @desc 조건에 따라서 사물함의 정보를 반환한다.
	 * @param SearchVO searchVO
	 * @return List<Map<String, String>>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectLck1000LockerInfoList(SearchVO searchVO) throws Exception {
		return (List<Map<String, String>>) list("cmm2000DAO.selectLck1000LockerInfoList", searchVO);
		
	}


	/**
	 * Func : 페이징 처리를 위한 조건에 따른 목록 개수 반환 메서드
	 * 
	 * @desc 조건에 따라서 사물함 목록 개수 반환
	 * @param SearchVO searchVO
	 * @return int
	 * @throws Exception
	 */
	public int selectTra1000TrainerTotalCountForPT(SearchVO searchVO) throws Exception {
		return (int) select("cmm2000DAO.selectTra1000TrainerTotalCountForPT", searchVO);	
		
	}


	/**
	 * Func : 트레이너 list를 반환하는 메서드
	 * 
	 * @desc 조건에 따라서 사물함의 정보를 반환한다.
	 * @param SearchVO searchVO
	 * @return List<Map<String, String>>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectTra1000ListForPT(SearchVO searchVO) throws Exception {
		return (List<Map<String, String>>) list("cmm2000DAO.selectTra1000ListForPT", searchVO);
		
	}

	
	
	/**
	 * Func : 회원 list를 반환하는 메서드
	 * 
	 * @desc 조건에 따라서 회원 정보 수를 반환한다.
	 * @param SearchVO searchVO
	 * @return int
	 * @throws Exception
	 */
	public int selectUsr1000UserListForLockerCount(SearchVO searchVO) throws Exception {
		return (int) select("cmm2000DAO.selectUsr1000UserListForLockerCount", searchVO);	
	}

	/**
	 * Func : 회원 list를 반환하는 메서드
	 * 
	 * @desc 조건에 따라서 회원 정보를 반환한다.
	 * @param SearchVO searchVO
	 * @return List<Map<String, String>>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<EgovMap> selectUsr1000UserListForLocker(SearchVO searchVO) throws Exception {
		return (List<EgovMap>) list("cmm2000DAO.selectUsr1000UserListForLocker", searchVO);
	}


	/**
	 * Func : 회원상세 정보 반환 메서드
	 * 
	 * @desc 회원상세에 필요한 정보를 반환 : 회원, 사물함, 수업 정보
	 * @param String userId
	 * @return Map<String, String>
	 * @throws Exception
	 */
	public EgovMap selectUsr1000UserDetail(String userId) throws Exception {
		return (EgovMap) select("cmm2000DAO.selectUsr1000UserDetail", userId);
	}
	
	
	/**
	 * Func : 트레이너상세 정보 반환 메서드
	 * 
	 * @desc 트레이너상세에 필요한 정보를 반환
	 * @param String userId
	 * @return Map<String, String>
	 * @throws Exception
	 */
	public EgovMap selectTra1000TrainerDetail(String trainerId) throws Exception {
		return (EgovMap) select("cmm2000DAO.selectTra1000TrainerDetail", trainerId);
	}
	
	
	/**
	 * Func : 개인 pt 정보를 보여주는 메서드
	 * 
	 * @desc 개인 pt 정보 가져옴
	 * @param String ptId
	 * @return Map<String, String>
	 * @throws Exception
	 */
	public EgovMap selectPcs1000DetailUserPtInfo(String ptId) throws Exception {
		return (EgovMap) select("cmm2000DAO.selectPcs1000DetailUserPtInfo", ptId);	
	}
}
