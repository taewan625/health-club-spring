package healthclub.pcs.pcs1000.pcs1000.service;

import java.util.List;
import java.util.Map;

public interface Pcs1000Service {

	List<Map<String, String>> selectPCS1000PtList() throws Exception;

	Map<String, String> selectPcs1000DetailPtInfo(String ptId) throws Exception;

	void insertPcs1000Pt(Map<String, String> ptInfo)  throws Exception;

	void updatePcs1000Pt(Map<String, String> ptInfo) throws Exception;

	void updatePcs1000PtStatus(Map<String, String> ptInfo) throws Exception;


}
