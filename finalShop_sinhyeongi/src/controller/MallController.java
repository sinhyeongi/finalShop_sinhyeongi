package controller;

import java.util.HashMap;
import java.util.Map;

import _mall.MenuCommand;
import dao.BoardDAO;
import dao.CartDAO;
import dao.FileDAO;
import dao.ItemDAO;
import dao.MemberDAO;
import menu_admin.AdminBoard;
import menu_admin.AdminItem;
import menu_admin.AdminMember;
import menu_admin._AdminMain;
import menu_mall.MallJoin;
import menu_mall.MallLogin;
import menu_mall._MallMain;
import menu_member.MemberBoard;
import menu_member.MemberCart;
import menu_member.MemberInfo;
import menu_member.MemberQuit;
import menu_member.MemberShopping;
import menu_member._MemberMain;
import util.Util;

public class MallController {
	private static  MallController instance = new MallController();
	private BoardDAO board;
	private CartDAO cart;
	private ItemDAO item;
	private MemberDAO member;
	private FileDAO file;
	private MallController() {
		board = BoardDAO.getInstance();
		cart = CartDAO.getInstance();
		item = ItemDAO.getInstance();
		member = MemberDAO.getInstance();
		file = FileDAO.getInstance();
	}
	
	
	
	static public MallController getInstance() {
		return instance;
	}

	private String loginId;
	private String next;
	private MenuCommand menuCom;
	public Map<String, MenuCommand> mapCont;

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public void DeleteUser() {
		System.out.println(member.getMemberName(loginId)+"탈퇴 완료");
		member.DeleteUser(loginId);
		cart.DeleteUser(loginId);
		loginId = null;
		this.next = "MallMain";
	}
	public void DeleteUser(String id) {
		System.out.println(member.getMemberName(id)+"탈퇴 완료");
		member.DeleteUser(id);
		cart.DeleteUser(id);
	}
	public void Save() {
		file.SaveAllFiles(member.SaveData(),item.SaveData(),cart.SaveData(),board.SaveData());
		System.out.println("파일 저장 완료");
	}
	public void DeleteItem(int No) {
		item.DeleteItem(No);
		cart.DeleteItem(No);
	}
	public void init() {
		FileDAO.getInstance().loadAllFiles();
		mapCont = new HashMap<>();
		mapCont.put("MallMain", new _MallMain());
		mapCont.put("MallJoin", new MallJoin());
		mapCont.put("MallLogin", new MallLogin());
		mapCont.put("AdminBoard", new AdminBoard());
		mapCont.put("AdminItem", new AdminItem());
		mapCont.put("AdminMain", new _AdminMain());
		mapCont.put("AdminMember", new AdminMember());
		mapCont.put("MemberBoard", new MemberBoard());
		mapCont.put("MemberCart", new MemberCart());
		mapCont.put("MemberInfo", new MemberInfo());
		mapCont.put("MemberMain", new _MemberMain());
		mapCont.put("MemberShopping", new MemberShopping());
		mapCont.put("MemberQuit", new MemberQuit());
		menuCom = mapCont.get("MallMain");
		menuCom.init();
		update();
	}

	public void update() {
		while (true) {
			if (!menuCom.update()) {
				if (next != null) {
					menuCom = mapCont.get(next);
					menuCom.init();
				} else {
					return;
				}
			}
		}
	}

}