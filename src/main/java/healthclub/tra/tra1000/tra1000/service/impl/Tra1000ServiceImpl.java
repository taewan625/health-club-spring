package healthclub.tra.tra1000.tra1000.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import healthclub.com.util.Pagination;
import healthclub.com.vo.SearchVO;
import healthclub.tra.tra1000.tra1000.service.Tra1000Service;


/**
 * @Class Name : Tra1000ServiceImpl.java
 * @Description 트레이너 서비스 클래스
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
@Service("tra1000Service")
public class Tra1000ServiceImpl extends EgovAbstractServiceImpl implements Tra1000Service {
	
	@Resource(name="tra1000DAO")
	private Tra1000DAO tra1000DAO;
	
	/**
	 * Func : 트레이너관리 화면의 정보를 보여주는 메서드
	 * 
	 * @desc 트레이너관리 화면의 정보 반환
	 * @param SearchVO searchVO
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> selectTra1000TrainerList(SearchVO searchVO) throws Exception {
			// 사용자 정보 및 페이징 정보를 담을 모델 객체
			Map<String, Object> result = new HashMap<>();

			// 조건에 맞는 데이터 목록 개수를 가져옴
			int totalRecordCount = tra1000DAO.selectTra1000TrainerListTotalCount(searchVO);

			// 조회할 데이터가 없는 경우
			if (totalRecordCount < 1) {
				result.put("pagination", null);
				result.put("trainers", null);

			} 
			// 조회할 데이터가 존재하는 경우
			else {
				// 페이징 처리
				result.put("pagination", new Pagination(totalRecordCount, searchVO));

				// 조건에 맞는 데이터 목록 처리
				result.put("trainers", tra1000DAO.selectTra1000TrainerInfoList(searchVO));
			}

			return result;
	}

	/**
	 * Func : 트레이너 등록 메서드
	 * 
	 * @desc 트레이너 등록 
	 * @param Map<String, String> trainerInfo,
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void insertTra1000TrainerInfo(Map<String, String> trainerInfo) throws Exception {
		// 트레이너 정보 등록
		tra1000DAO.insertTra1000TrainerInfo(trainerInfo);			
	}
	
	
	/**
	 * Func : 트레이너 정보 반환 메서드
	 * 
	 * @desc 트레이너 수정에 필요한 정보를 반환하는 메서드
	 * @param String trainerId
	 * @return Map<String, String>
	 * @throws Exception
	 */
	@Override
	public Map<String, String> selectTra1000EditTrainerInfo(String trainerId) throws Exception {
		 return tra1000DAO.selectTra1000EditTrainerInfo(trainerId);
	}
	

	/**
	 * Func : 트레이너 근무시간 수정 가능 여부 확인 메서드
	 * 
	 * @desc 트레이너 근무시간 수정 가능 여부 확인
	 * @param Map<String, String> requestDatas
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public boolean selectTra1000CheckWorkTime(Map<String, String> requestDatas) throws Exception {
		// 예정된 수업 개수와 변경된 근무시간에 포함되는 수업 개수간의 차이값
		return  tra1000DAO.selectTra1000CheckWorkTime(requestDatas) == 0 ;
	}

	
	/**
	 * Func : 트레이너 수정 메서드
	 * 
	 * @desc 중복 아이디가 존재하지 않을 시, 트레이너을 수정한다. expireDate
	 * @param Map<String, String> trainerInfo
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void updateTra1000TrainerInfo(Map<String, String> trainerInfo) throws Exception {
		tra1000DAO.updateTra1000TrainerInfo(trainerInfo);

	}

	/**
	 * Func : 삭제 불가한 트레이너 찾는 메서드
	 * 
	 * @desc 삭제 불가한 트레이너 찾는 메서드
	 * @param List<String> requestDatas
	 * @return List<String>
	 * @throws Exception
	 */
	@Override
	public List<String> selectTra1000HavePt(List<String> requestDatas) throws Exception {
		return tra1000DAO.selectTra1000HavePt(requestDatas);
	}

	/**
	 * Func : 트레이너 삭제 메서드
	 * 
	 * @desc 트레이너 삭제 메서드
	 * @param List<Map<String, String>> requestDatas
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void deleteTra1000TrainerList(List<Map<String, String>> requestDatas) throws Exception {
		for (Map<String, String> deleteInfo : requestDatas) {
			tra1000DAO.deleteTra1000TrainerList(deleteInfo);
		}
	}
}
