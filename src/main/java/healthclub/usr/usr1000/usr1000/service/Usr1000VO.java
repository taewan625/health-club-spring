package healthclub.usr.usr1000.usr1000.service;

/**
 * @Class Name : Usr1000VO.java
 * @Description 사물함 관리 controller 리스트,등록,수정,삭제,조회 존재
 * @version 1.0
 * @author 권태완
 * @Since 2024.01.18.
 * @Modification Information
 * @see Copyright (C) All right reserved.
 */
public class Usr1000VO {
	// 회원 시퀀스
	private int seq;
	// 회원 id
	private String id;
	// 회원 pw
	private String pw;
	// 회원명
	private String name;
	// 회원 성별 (1:남자, 2:여자)
	private String gender;
	// 회원 연락처
	private String phone;
	// 회원 주소
	private String address;
	// 가입 일자
	private String joinDate;
	// 만료 일자
	private String expireDate;
	// 남은 기간
	private int remainDay;
	// 개인 수업 횟수
	private String classNumber;
	// 이슈
	private String issue;
	// 사용 유무
	private String use;
	// 삭제 유무
	private String delete;
	// 비고
	private String description;
	// 최초 등록자 id
	private String registerId;
	// 최초 등록자 ip
	private String registerIP;
	// 최종 등록자 id
	private String modifyId;
	// 최종 등록자 ip
	private String modifyIP;

	// 생성자
	public Usr1000VO() {
	}
	
	

	// getter
	public int getSeq() {
		return seq;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String getPhoneNumber() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public int getRemainDay() {
		return remainDay;
	}
	
	public String getClassNumber() {
		return classNumber;
	}

	public String getIssue() {
		return issue;
	}

	public String getUse() {
		return use;
	}

	public String getDelete() {
		return delete;
	}

	public String getDescription() {
		return description;
	}

	public String getRegisterId() {
		return registerId;
	}

	public String getRegisterIP() {
		return registerIP;
	}

	public String getModifyId() {
		return modifyId;
	}

	public String getModifyIP() {
		return modifyIP;
	}

	// setter
	public void setSeq(int seq) {
		this.seq = seq;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phone = phoneNumber;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	
	public void setRemainDay(int remainDay) {
		this.remainDay = remainDay;
	}
	
	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}

	public void setRegisterIP(String registerIP) {
		this.registerIP = registerIP;
	}

	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}

	public void setModifyIP(String modifyIP) {
		this.modifyIP = modifyIP;
	}

}
