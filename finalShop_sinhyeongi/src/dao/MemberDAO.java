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

	public void PrintInfo(String id) {
		for (Member m : list) {
			if (m.getId().equals(id)) {
				System.out.println(m.Info());
				return;
			}
		}
	}

	public static MemberDAO getInstance() {
		if (instance == null)
			instance = new MemberDAO();
		return instance;
	}

	public boolean Login(String id, String pw) {
		for (Member m : list) {
			if (m.getId().equals(id) && pw.equals(m.getPw())) {
				return true;
			}
		}
		return false;
	}

	public String getMemberName(String id) {
		for (Member m : list) {
			if (m.getId().equals(id))
				return m.getMemberName();
		}
		return null;

	}

	public String getMemberById(String id) {
		for (Member m : list) {
			if (m.getId().equals(id))
				return m.getId();
		}
		return null;
	}

	public boolean insertMember(String id, String pw, String name) {
		try {
			list.add(new Member(id, pw, name));
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

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

	public void UpdatePw(String id, String pw) {
		for (int i = 0; i < list.size(); i++) {
			if(id.equals(list.get(i).getId())) {
				list.get(i).setPw(pw);
				return;
			}
		}
	}
}
