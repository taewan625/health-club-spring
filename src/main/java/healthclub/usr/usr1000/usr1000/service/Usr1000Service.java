package healthclub.usr.usr1000.usr1000.service;

import java.util.List;
import java.util.Map;

import healthclub.com.vo.SearchVO;


public interface Usr1000Service {
	
	Map<String, Object> selectUsr1000UserList(SearchVO searchVO) throws Exception;

	Map<String, String> selectUsr1000EditUserInfo(String updateUserId) throws Exception;

	void insertUsr1000UserLockerInfo(Map<String, String> userInfo, Map<String, String> lockerInfo) throws Exception;

	void updateUsr1000UserLockerInfo(Map<String, String> userInfo, Map<String, String> lockerInfo) throws Exception;

	List<String> selectUsr1000HavePt(List<String> requestDatas) throws Exception;

	void deleteUsr1000UserList(List<String> userIds, List<Map<String, String>> deleteUserInfo) throws Exception;

	List<String> selectUsr1000UserListRemainPt(List<String> userIds) throws Exception;

}
