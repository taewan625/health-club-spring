package healthclub.sta.sta1000.sta1000.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import healthclub.sta.sta1000.sta1000.service.Sta1000Service;


/**
 * @Class Name : Sta1000ServiceImpl.java
 * @Description 회원 통계 관련 작업을 수행 서비스
 * @version 1.0
 * @author 권태완
 * @Since 2023.12.28.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
@Service("sta1000Service")
public class Sta1000ServiceImpl extends EgovAbstractServiceImpl implements Sta1000Service {
	
	@Resource(name="sta1000DAO")
	private Sta1000DAO sta1000DAO;

	
	/**
	 * Func : 통계 정보 반환 메서드
	 * 
	 * @desc 통계에 필요한 정보를 반환하는 메서드
	 * @param String searchCondition
	 * @return Map<String, List<Map<String, String>>>
	 * @throws Exception
	 */
	@Override
	public Map<String, List<EgovMap>> selectSta1000StaticList() throws Exception {
		Map<String, List<EgovMap>> staticList = new HashMap<>();

		//  핵심 통계

		// 최근 등록 회원 , 기간 임박 회원 - 회원 테이블
		List<EgovMap> newUser = sta1000DAO.selectUsr1000NewUser();
		List<EgovMap> finishUser = sta1000DAO.selectUsr1000FinishUser();
		
		// 30분 , 50분 수업 최대 회원 - 수업 테이블
		List<EgovMap> user30Most = sta1000DAO.selectPcs1000MostUserPt("01");
		List<EgovMap> user50Most = sta1000DAO.selectPcs1000MostUserPt("02");
		
		// 30분 , 50분 최대 수업 트레이너 - 수업 테이블
		List<EgovMap> trainer50Most = sta1000DAO.selectPcs1000MostTrainerPt("01");
		List<EgovMap> trainer30Most = sta1000DAO.selectPcs1000MostTrainerPt("02");

		// 최근 수행된 개인 수업 일정 - 수업 테이블
		List<EgovMap> finishPtSchedule = sta1000DAO.selectPcs1000MostFinishPtSchedule();
		
		
		staticList.put("newUser", newUser);
		staticList.put("finishUser", finishUser);
		staticList.put("user30Most", user30Most);
		staticList.put("user50Most", user50Most);
		staticList.put("trainer30Most", trainer30Most);
		staticList.put("trainer50Most", trainer50Most);
		staticList.put("finishPt", finishPtSchedule);
			
		
		
		// 운영 통계
		// 월별 회원 증가율, 회원 만료률, 트레이너 수 변화, pt 수업 수행률

		
		return staticList;
	}
	
	
}
