package healthclub.pcs.pcs1000.pcs1000.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import healthclub.pcs.pcs1000.pcs1000.service.Pcs1000Service;


/**
 * @Class Name : Pcs1000ServiceImpl.java
 * @Description 수업관리 서비스
 * @version 1.0
 * @author 권태완
 * @Since 2024.2.20.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
@Service("pcs1000Service")
public class Pcs1000ServiceImpl extends EgovAbstractServiceImpl implements Pcs1000Service {
	@Resource(name="pcs1000DAO")
	private Pcs1000DAO pcs1000DAO;

	/**
	 * Func : 수업 정보 반환 메서드
	 * 
	 * @desc 수업 수정에 필요한 정보를 반환하는 메서드
	 * @param 
	 * @return List<Map<String, String>>
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> selectPCS1000PtList() throws Exception {
		return pcs1000DAO.selectPCS1000PtList();
		
	}
	
	/**
	 * Func : 수업 상세 정보 반환 메서드
	 * 
	 * @desc 수업 상세 필요한 정보를 반환하는 메서드
	 * @param String ptId
	 * @return Map<String, String>
	 * @throws Exception
	 */
	@Override
	public Map<String, String> selectPcs1000DetailPtInfo(String ptId) throws Exception {
		return pcs1000DAO.selectPcs1000DetailPtInfo(ptId);
		
	}
	

	/**
	 * Func : 수업 등록 메서드
	 * 
	 * @desc 중복 아이디가 존재하지 않을 시, 수업을 등록한다. expireDate
	 * @param Map<String, String> ptInfo
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void insertPcs1000Pt(Map<String, String> ptInfo) throws Exception {
		// 수업 정보 등록
		pcs1000DAO.insertPcs1000Pt(ptInfo);			
		// 회원 pt 개수 : - 1
		pcs1000DAO.updateUsr1000UserPtCount(ptInfo);
	}
	
	
	/**
	 * Func : 수업 정보 수정 메서드
	 * 
	 * @desc 수업 정보 수정 메서드
	 * @param Map<String, String> ptInfo
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void updatePcs1000Pt(Map<String, String> ptInfo) throws Exception {
		// 수업 정보 수정
		pcs1000DAO.updatePcs1000Pt(ptInfo);
		
		// 수업 내용 수정
		//[ptCode : 00] : 수업 예정
		if ("00".equals(ptInfo.get("initPtStatus"))) {
			
			// 수업 내용 수정 중  내용 수정
			if (ptInfo.get("initPtStatus").equals(ptInfo.get("ptStatus"))) {
				// 변경으로 수업 생긴 것 -1
				ptInfo.put("userPtAdd", "-1");
				pcs1000DAO.updateUsr1000UserPtCount(ptInfo);
				// 변경되어 사라진 것 +1
				ptInfo.put("userId", ptInfo.get("initUserId"));
				ptInfo.put("userPtAdd", "1");
				pcs1000DAO.updateUsr1000UserPtCount(ptInfo);
				
			}
			
			// 수업 내용 수정 중 상태만 변경 : 예정 -> 취소
			else {
				pcs1000DAO.updateUsr1000UserPtCount(ptInfo);
			}
			
		}
		
		// 취소 -> 수업 예정으로 상태 변경
		else {
			// initUser update
			pcs1000DAO.updateUsr1000UserPtCount(ptInfo);
		}
	}

	
	/**
	 * Func : 수업 상태 변경 및 상태 변경에 따른 회원, 트레이너 수업 횟수 변경 메서드
	 * 
	 * @desc 수업 상태 변경, 상태 변경에 따른 회원 수업 차감 횟수 계산, 트레이너 수업 완료 횟수 계산 및 업데이트
	 * @param Map<String, String> ptInfo
	 * @return void
	 * @throws Exception
	 */
	@Override
	public void updatePcs1000PtStatus(Map<String, String> ptInfo) throws Exception {
		// 1. 수업 상태 업데이트
		pcs1000DAO.updatePcs1000Pt(ptInfo);
		pcs1000DAO.updateUsr1000UserPtCount(ptInfo);
		pcs1000DAO.updateTra1000TrainerPtCount(ptInfo);
		
	}
	
}
