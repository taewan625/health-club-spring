package healthclub.tra.tra1000.tra1000.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import healthclub.com.vo.SearchVO;
import healthclub.tra.tra1000.tra1000.service.Tra1000VO;

/**
 * @Class Name : Tra1000DAO.java
 * @Description 트레이너 DAO 클래스
 * @version 1.0
 * @author 권태완
 * @Since 2024.01.18.
 * @Modification Information
 * @
 * @  수정일            수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2024.01.18	   권태완		  최초생성
 * @ 2024.03.09	   권태완		 트레이너 상세 로직을 cmm2000 공통 관리로 분리
 * 
 * @see Copyright (C) All right reserved.
 */
@Repository("tra1000DAO")
public class Tra1000DAO extends EgovAbstractDAO {

	/**
	 * Func : 페이징 처리를 위한 조건에 따른 목록 개수 반환 메서드
	 * 
	 * @desc 조건에 따라서 유저 목록 개수 반환
	 * @param SearchVO searchVO
	 * @return int
	 * @throws Exception
	 */
	public int selectTra1000TrainerListTotalCount(SearchVO searchVO) throws Exception {
		return (int) select("tra1000DAO.selectTra1000TrainerListTotalCount", searchVO);	
	}

	/**
	 * Func : trainer list를 반환하는 메서드
	 * 
	 * @desc 조건에 따라서 유저의 정보를 반환한다.
	 * @param SearchVO searchVO
	 * @return List<Tra1000VO>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Tra1000VO> selectTra1000TrainerInfoList(SearchVO searchVO) throws Exception {
		return (List<Tra1000VO>) list("tra1000DAO.selectTra1000TrainerInfoList", searchVO);
	}

	
	/**
	 * Func : 수정할 트레이너 정보 반환 메서드
	 * 
	 * @desc 트레이너 수정에 필요한 정보를 반환하는 메서드
	 * @param String userId
	 * @return Map<String, String>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> selectTra1000EditTrainerInfo(String trainerId) throws Exception {
		return (Map<String, String>) select("tra1000DAO.selectTra1000EditTrainerInfo", trainerId);

	}

	/**
	 * Func : 트레이너 등록 메서드
	 * 
	 * @desc 트레이너 등록
	 * @param Map<String, String> trainerInfo
	 * @return void
	 * @throws Exception
	 */
	public void insertTra1000TrainerInfo(Map<String, String> trainerInfo) throws Exception {
		insert("tra1000DAO.insertTra1000TrainerInfo", trainerInfo);
		
	}

	
	/**
	 * Func : 트레이너 근무시간 수정 가능 여부 확인 메서드
	 * 
	 * @desc 트레이너 근무시간 수정 가능 여부 확인
	 * @param Map<String, String> requestDatas
	 * @return boolean
	 * @throws Exception
	 */
	public int selectTra1000CheckWorkTime(Map<String, String> requestDatas) throws Exception {
		return (int) select("tra1000DAO.selectTra1000CheckWorkTime", requestDatas);
	}

	
	/**
	 * Func : 트레이너 수정 메서드
	 * 
	 * @desc 중복 아이디가 존재하지 않을 시, 트레이너을 수정한다. expireDate
	 * @param Map<String, String> trainerInfo
	 * @return void
	 * @throws Exception
	 */
	public void updateTra1000TrainerInfo(Map<String, String> trainerInfo) throws Exception {
		update("tra1000DAO.updateTra1000TrainerInfo", trainerInfo);
	}

	
	/**
	 * Func : 삭제 불가한 트레이너 찾는 메서드
	 * 
	 * @desc 삭제 불가한 트레이너 찾는 메서드
	 * @param Map<String, String> requestDatas
	 * @return List<String>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<String> selectTra1000HavePt(List<String> requestDatas) throws Exception {
		return (List<String>) list("tra1000DAO.selectTra1000HavePt", requestDatas);
	}

	
	/**
	 * Func : 트레이너 삭제 메서드
	 * 
	 * @desc 트레이너 삭제 메서드
	 * @param Map<String, String> deleteInfo
	 * @return void
	 * @throws Exception
	 */
	public void deleteTra1000TrainerList(Map<String, String> deleteInfo) throws Exception {
		update("tra1000DAO.deleteTra1000TrainerList", deleteInfo);
	}
}
