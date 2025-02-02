package healthclub.tra.tra1000.tra1000.service;

import java.util.List;
import java.util.Map;

import healthclub.com.vo.SearchVO;

public interface Tra1000Service {

	Object selectTra1000TrainerList(SearchVO searchVO) throws Exception;

	Map<String, String> selectTra1000EditTrainerInfo(String trainerId) throws Exception;

	void insertTra1000TrainerInfo(Map<String, String> trainerInfo) throws Exception;

	boolean selectTra1000CheckWorkTime(Map<String, String> requestDatas) throws Exception;

	void updateTra1000TrainerInfo(Map<String, String> trainerInfo) throws Exception;

	List<String> selectTra1000HavePt(List<String> requestDatas) throws Exception;

	void deleteTra1000TrainerList(List<Map<String, String>> requestDatas) throws Exception; 

}
