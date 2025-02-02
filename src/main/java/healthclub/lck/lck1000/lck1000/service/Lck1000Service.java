package healthclub.lck.lck1000.lck1000.service;

import java.util.List;
import java.util.Map;

import healthclub.com.vo.SearchVO;

public interface Lck1000Service {

	Object selectLck1000LockerList(SearchVO searchVO) throws Exception;

	Map<String, String> selectLck1000EditTrainerInfo(String lockerId) throws Exception;

	void insertLck1000Locker(Map<String, String> lockerInfo) throws Exception;

	void updateLck1000Locker(Map<String, String> lockerInfo)  throws Exception;

	void deleteLck1000LockerList(List<String> lockers, Map<String, String> metaDatas) throws Exception;

}
