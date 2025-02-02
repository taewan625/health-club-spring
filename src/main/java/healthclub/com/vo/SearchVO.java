package healthclub.com.vo;

import java.util.Map;

/**
 * @Class Name : SearchVO.java
 * @Description 조건에 부합한 목록을 뿌릴 때 사용되는 공통 VO
 * @version 1.0
 * @author 권태완
 * @Since 2024.01.12.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class SearchVO {

	// 현재 페이지 번호
    private int currentPage;
    
    // 페이지당 출력할 데이터 개수
    private int recordSize = 10;
    
	// offset에서 skip 해야되는 데이터 개수 == 시작할 열의 번호
	private int offsetStart;

	// 검색 키
    private String searchKey = "";
    
    // 검색 값
    private String searchValue = "";
    
    // 추가 검색 조건 : cmm2000 회원, 트레이너 검색 조건
    private String ptDate;
    private String ptTime;
    private String classType;
    private String beforePtDate; 
    private String afterPtDate;
    private String startTime;
    private String endTime;
    
    
    // 생성자
    public SearchVO(Map<String, String> jsonRequestDatas) {
    	this.currentPage = Integer.parseInt(jsonRequestDatas.get("currentPage"));
    	this.offsetStart = (this.currentPage -1) * this.recordSize;
    	
    	// 추가 검색 조건 : cmm2000 회원, 트레이너 검색 조건
    	this.ptDate = jsonRequestDatas.get("ptDate");
    	this.ptTime = jsonRequestDatas.get("ptTime");
    	this.classType = jsonRequestDatas.get("classType");
    	this.beforePtDate = jsonRequestDatas.get("beforePtDate");
    	this.afterPtDate = jsonRequestDatas.get("afterPtDate");
    	this.startTime = jsonRequestDatas.get("startTime");
    	this.endTime = jsonRequestDatas.get("endTime");
    	
    	// 어떻게 저장되는지 확인 필요
    	String inputKey = jsonRequestDatas.get("searchKey");
    	String inputValue = jsonRequestDatas.get("searchValue");
    	
    	if ( inputKey != null && inputValue != null) {
    		this.searchKey = inputKey;
    		this.searchValue = inputValue;
    	}
    }
    

    // getter
	public int getCurrentPage() {
		return currentPage;
	}
	
	public int getRecordSize() {
		return recordSize;
	}
	
    public int getOffsetStart() {
		return offsetStart;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public String getPtDate() {
		return ptDate;
	}

	public String getPtTime() {
		return ptTime;
	}

	public String getClassType() {
		return classType;
	}

	public String getBeforePtDate() {
		return beforePtDate;
	}

	public String getAfterPtDate() {
		return afterPtDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setRecordSize(int recordSize) {
		this.recordSize = recordSize;
	}

	public void setOffsetStart(int offsetStart) {
		this.offsetStart = offsetStart;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public void setPtDate(String ptDate) {
		this.ptDate = ptDate;
	}

	public void setPtTime(String ptTime) {
		this.ptTime = ptTime;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public void setBeforePtDate(String beforePtDate) {
		this.beforePtDate = beforePtDate;
	}

	public void setAfterPtDate(String afterPtDate) {
		this.afterPtDate = afterPtDate;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
    
}
