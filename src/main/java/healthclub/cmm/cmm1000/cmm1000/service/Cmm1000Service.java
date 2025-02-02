package healthclub.cmm.cmm1000.cmm1000.service;

import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface Cmm1000Service {

	EgovMap selectUsr1000Tra1000LoginInfo(Map<String, String> loginFormData) throws Exception;

	boolean selectUsr1000Tra1000Id(String memberId) throws Exception;

	boolean selectUsr1000Tra1000Pw(Map<String, String> memberIdPw) throws Exception;

}
