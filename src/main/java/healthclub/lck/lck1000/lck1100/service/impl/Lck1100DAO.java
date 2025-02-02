package healthclub.lck.lck1000.lck1100.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import healthclub.com.vo.SearchVO;


/**
 * @Class Name : Lck1100DAO.java
 * @Description 사물함 이력 DAO 클래스
 * @version 1.0
 * @author 권태완
 * @Since 2024.01.18.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
@Repository("lck1100DAO")
public class Lck1100DAO extends EgovAbstractDAO {

	/**
	 * Func : 페이징 처리를 위한 조건에 따른 목록 개수 반환 메서드
	 * 
	 * @desc 페이징 처리를 위한 조건에 따른 목록 개수 반환
	 * @param SearchVO searchVO
	 * @return int
	 * @throws Exception
	 */
	public int selectLck1100TotalCount(SearchVO searchVO) throws Exception {
		return (int) select("lck1100DAO.selectLck1100TotalCount", searchVO);
	}

	/**
	 * Func : 사물함 리스트를 반환하는 메서드
	 * 
	 * @desc 조건에 따라서 유저의 정보를 반환한다.
	 * @param SearchVO searchVO
	 * @return List<EgovMap>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<EgovMap> selectLck1100List(SearchVO searchVO) throws Exception {
		return (List<EgovMap>) list("lck1100DAO.selectLck1100List", searchVO);
	}


}
