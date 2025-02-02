package healthclub.sta.sta1000.sta1000.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface Sta1000Service {

	Map<String, List<EgovMap>> selectSta1000StaticList() throws Exception;

}
