package healthclub.tra.tra1000.tra1000.service;

public class Tra1000VO {

	// 트레이너 id
	private String id;
	// 트레이너 pw
	private String pw;
	// 트레이너명
	private String name;
	// 트레이너 성별 (1:남자, 2:여자)
	private String gender;
	// 트레이너 연락처
	private String phone;
	// 트레이너 주소
	private String address;
	// 입사 일자
	private String joinDate;
	// 퇴사 일자
	private String expireDate;
	// 근무시작 시간
	private String startWorkTime;
	// 근무종료 시간 
	private String endtWorkTime;

	// 개인 수업 횟수
	private int classNumber;

	// 사용 유무
	private String use;
	// 삭제 유무
	private String delete;
	// 비고
	private String note;
	// 최초 등록자 id
	private String registerId;
	// 최초 등록자 ip
	private String registerIP;
	// 최종 등록자 id
	private String modifyId;
	// 최종 등록자 ip
	private String modifyIP;

	
	
	// getter
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
	public String getPhone() {
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
	public String getStartWorkTime() {
		return startWorkTime;
	}
	public String getEndtWorkTime() {
		return endtWorkTime;
	}
	public int getClassNumber() {
		return classNumber;
	}
	public String getUse() {
		return use;
	}
	public String getDelete() {
		return delete;
	}
	public String getNote() {
		return note;
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
	public void setPhone(String phone) {
		this.phone = phone;
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
	public void setStartWorkTime(String startWorkTime) {
		this.startWorkTime = startWorkTime;
	}
	public void setEndtWorkTime(String endtWorkTime) {
		this.endtWorkTime = endtWorkTime;
	}
	public void setClassNumber(int classNumber) {
		this.classNumber = classNumber;
	}
	public void setUse(String use) {
		this.use = use;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	public void setNote(String note) {
		this.note = note;
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
