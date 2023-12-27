package dto;

public class Member {
	private static int num = 1000;
	private int memberNum;
	private String id;
	private String pw;
	private String memberName;
	
	
	public Member(int memberNum,String id, String pw, String memberName) {
		this.memberNum = memberNum;
		this.id = id;
		this.pw = pw;
		this.memberName = memberName;
		num++;
	}
	public Member(String id, String pw, String memberName) {
		this.memberNum = num++;
		this.id = id;
		this.pw = pw;
		this.memberName = memberName;
	}
	public static int getNum() {
		return num;
	}
	public static void setNum(int num) {
		Member.num = num;
	}
	public int getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String Info() {
		return "[%6d] [%10s] [%12s] [%12s]".formatted(memberNum,id,pw,memberName);
	}
	public String Save() {
		return "%s/%s/%s/%s\n".formatted(memberName,id,pw,memberName);
	}
	
}