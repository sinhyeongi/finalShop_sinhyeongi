package dao;

import java.util.ArrayList;
import java.util.List;

import dto.Member;

public class MemberDAO {
	private List<Member> list;
	private static MemberDAO instance;

	public MemberDAO() {
		list = new ArrayList<Member>();
	}
	
	/**
	 * 아이디 값에 해당하는 유저 정보 출력
	 * @param id 유저 아이디
	 */
	public void PrintInfo(String id) {
		for (Member m : list) {
			if (m.getId().equals(id)) {
				System.out.println(m.Info());
				return;
			}
		}
	}
	/**
	 * member 세이브 데이터 
	 * 
	 * @return member 세이브 데이터 (String type)
	 */
	public String SaveData() {
		String data ="";
		for(int i = 0 ; i < list.size(); i++) {
			data += list.get(i).Save();
		}
		if(data.length() > 1)
			data = data.substring(0,data.length() -1);
		return data;
	}
	public void PrintInfo() {
		for (Member m : list) {
			System.out.println(m.Info());
		}
	}
	public static MemberDAO getInstance() {
		if (instance == null)
			instance = new MemberDAO();
		return instance;
	}

	/**
	 * 로그인 확인
	 * 
	 * @param id 아이디
	 * @param pw 패스워드
	 * @return id,pw가 같은 것이 있다면 true 없다면 false
	 */
	public boolean Login(String id, String pw) {
		for (Member m : list) {
			if (m.getId().equals(id) && pw.equals(m.getPw())) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 해당 유저 이름 찾기
	 * 
	 * @param id 유저 아이디
	 * @return 해당 유저 이름 리턴 없다면 null
	 */
	public String getMemberName(String id) {
		for (Member m : list) {
			if (m.getId().equals(id))
				return m.getMemberName();
		}
		return null;

	}
	/**
	 * 로그인시 id값에 해당하는 id가 존재 하는지 확인
	 * 
	 * @param id 유저 아이디
	 * @return id값 리턴 없다면 null
	 */
	public String getMemberById(String id) {
		for (Member m : list) {
			if (m.getId().equals(id))
				return m.getId();
		}
		return null;
	}

	/**
	 * 새로운 유저 가입
	 * 
	 * @param id 유저 아이디
	 * @param pw 유저 비밀번호
	 * @param name 유저 이름
	 * @return 추가 성공시 true
	 */
	public boolean insertMember(String id, String pw, String name) {
		try {
			list.add(new Member(id, pw, name));
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	/**
	 * 데이터 로드
	 * @param data Member관련 데이터 (String)
	 */
	public void LoadData(String data) {
		if (data == null || data.length() < 1)
			return;
		if (list.size() != 0)
			list.clear();
		String[] data2 = data.split("\n");
		for (int i = 0; i < data2.length; i++) {
			String[] t = data2[i].split("/");
			list.add(new Member(Integer.parseInt(t[0]), t[1], t[2], t[3]));
		}
	}
	/**
	 *  유저 삭제
	 * @param id 유저 아이디 값
	 */
	public void DeleteUser(String id) {
		for (int i = 0; i < list.size(); i++) {
			if (id.equals(list.get(i).getId())) {
				if (list.size() == 1) {
					list.clear();
					break;
				}
				list.remove(i);
				break;
			}
		}
	}
	/**
	 * 비밀번호 변경
	 * @param id 아이디 
	 * @param pw 비밀번호
	 */
	public void UpdatePw(String id, String pw) {
		for (int i = 0; i < list.size(); i++) {
			if(id.equals(list.get(i).getId())) {
				list.get(i).setPw(pw);
				return;
			}
		}
	}
}
