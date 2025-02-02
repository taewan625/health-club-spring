package healthclub.cmm.cmm2000.cmm2000.service;

import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import healthclub.com.vo.SearchVO;

public interface Cmm2000Service {

	Map<String, Object> selectLck1000List(SearchVO searchVO) throws Exception;

	Map<String, Object> selectUsr1000UserListForPT(SearchVO searchVO) throws Exception;

	Map<String, Object> selectUsr1000TrainerListForPT(SearchVO searchVO) throws Exception;

	Map<String, Object> selectUsr1000ListForLocker(SearchVO searchVO) throws Exception;

	EgovMap selectPcs1000DetailUserPtInfo(String ptId) throws Exception;

	EgovMap selectUsr1000UserDetail(String userId) throws Exception;

	EgovMap selectTra1000TrainerDetail(String trainerId) throws Exception;


}
