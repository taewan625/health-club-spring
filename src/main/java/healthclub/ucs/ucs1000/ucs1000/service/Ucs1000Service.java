package healthclub.ucs.ucs1000.ucs1000.service;

import java.util.List;
import java.util.Map;

public interface Ucs1000Service {

	List<Map<String, String>> selectUcs1000PtList(String userId) throws Exception;

	Map<String, String> selectUcs1000DetailUserPtInfo(String ptId) throws Exception;
	
}
