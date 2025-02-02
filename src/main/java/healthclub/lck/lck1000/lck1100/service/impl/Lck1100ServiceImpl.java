package healthclub.lck.lck1000.lck1100.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import healthclub.com.util.Pagination;
import healthclub.com.vo.SearchVO;
import healthclub.lck.lck1000.lck1100.service.Lck1100Service;


/**
 * @Class Name : Lck1100ServiceImpl.java
 * @Description 사물함 이력 서비스 클래스
 * @version 1.0
 * @author 권태완
 * @Since 2024.01.18.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
@Service("lck1100Service")
public class Lck1100ServiceImpl extends EgovAbstractServiceImpl implements Lck1100Service {

	@Resource(name="lck1100DAO")
	Lck1100DAO lck1100DAO;

	/**
	 * Func : 사물함 화면의 default 정보를 보여주는 메서드
	 * 
	 * @desc 필요 사물함  이력 정보 가져옴
	 * @param SearchVO searchVO
	 * @return Object
	 * @throws Exception
	 */
	@Override
	public Object selectLck1100LockerList(SearchVO searchVO) throws Exception {
		// 사용자 정보 및 페이징 정보를 담을 모델 객체
		Map<String, Object> result = new HashMap<>();

		// 조건에 맞는 데이터 목록 개수를 가져옴
		int totalRecordCount = lck1100DAO.selectLck1100TotalCount(searchVO);

		// 조회할 데이터가 없는 경우
		if (totalRecordCount < 1) {
			result.put("pagination", null);
			result.put("lockerHistorys", null);

		} 
		// 조회할 데이터가 존재하는 경우
		else {
			// 페이징 처리
			result.put("pagination", new Pagination(totalRecordCount, searchVO));

			// 조건에 맞는 데이터 목록 처리
			result.put("lockerHistorys", lck1100DAO.selectLck1100List(searchVO));
		}
		
		return result;
	}
}
