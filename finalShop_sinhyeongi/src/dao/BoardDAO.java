package dao;

import java.util.ArrayList;
import java.util.List;

import dto.Board;

public class BoardDAO {
	private static BoardDAO instance;
	private List<Board> board;
	private int start;
	private int end;
	private int page;

	public BoardDAO() {
		board = new ArrayList<Board>();
		start = 0;
		end = 4;
		page = 1;
	}

	public static BoardDAO getInstance() {
		if (instance == null)
			instance = new BoardDAO();
		return instance;
	}

	/**
	 * 다음 페이지 이동
	 * @return page 가 마지막에 도달 하였다면 true
	 */
	public boolean NextPage() {
		if (page == getMaxpage()) {
			return true;
		}
		start = ++end;
		end += 4;
		page++;
		return false;
	}
	/**
	 * 이전 페이지 이동
	 * @return 현재 페이지가 1이라면 true
	 */
	public boolean beforePage() {
		if (page == 1) {
			return true;
		}
		end = --start;
		start -= 4;
		page--;
		return false;
	}
	/**
	 * 페이지에 해당하는 게시글 목록 출력
	 */
	public void PrintBoard() {
		for (int i = start; i <= Math.min(end, board.size() - 1); i++) {
			System.out.println(board.get(i).toBoard());
		}
	}
	
	/**
	 * 
	 * @return 현재 페이지 시작 번호 리턴
	 */
	public int getStart() {
		return board.get(start).getBoradNum();
	}
	
	/**
	 * 
	 * @param id 유저 아이디
	 * @return 유저가 작성한 글 첫번째 번호
	 */
	public int getStart(String id) {
		for(int i = 0 ; i < board.size(); i++) {
			if(id.equals(board.get(i).getId())) {
				return board.get(i).getBoradNum();
			}
		}
		return -1;
	}
	
	
	/**
	 * 
	 * @return 현재 페이지 마지막 번호
	 */
	public int getEnd() {
		if( end > board.size()) {
			return board.get(board.size()-1).getBoradNum();
		}
		return board.get(end).getBoradNum();
	}
	
	/**
	 * 
	 * @param id 유저아이디
	 * @return 게시판에서 유저가 작성한 마지막 글 번호
	 */
	public int getEnd(String id) {
		for(int i = board.size() -1; i >= 0 ; i--) {
			if(board.get(i).getId().equals(id)) {
				return board.get(i).getBoradNum();
			}
		}
		return -1;
	}
	/**
	 * idx와 같은 게시판 번호 게시판 출력
	 * @param idx -> 게시판 넘버
	 */
	public void PrintBoard(int idx) {
		for(int i = 0 ; i < board.size(); i++) {
			if(board.get(i).getBoradNum() == idx) {
				System.out.println(board.get(i).toBoard());
				System.out.println("------------------------");
				System.out.println(board.get(i).getContents());
				System.out.println();
				board.get(i).setHits(board.get(i).getHits() +1);
				return;
			}
		}
		System.out.println("찾는 번호의 게시글이 없습니다.");
	}
	/**
	 * 유저의 아이디 값으로 있는 게시판 출력
	 * @param id -> 유저 id
	 */
	public void PrintBoard(String id) {
		for (int i = 0; i < board.size(); i++) {
			if (id.equals(board.get(i).getId()))
				System.out.println(board.get(i).toBoard());
		}
	}
	
	/**
	 * 첫 게시글 번호 리턴
	 * @return 게시글이없다면 0 / 첫번째 게시판 번호 리턴
	 */
	public int getBoardStartdNum() {
		if(board.size() == 0)
			return 0;
		return board.get(0).getBoradNum();
	}
	/**
	 * 마지막 게시글 번호 리턴
	 * @return게시글이 없다면 0 / 마지막 게시글 번호 리턴
	 */
	public int getBoardendNum() {
		if(board.size() == 0)
			return 0;
		return board.get(board.size()-1).getBoradNum();
	}
	/**
	 * 
	 * @param id -> 유저 아이디
	 * @return 게시글중 id 에 해당하는 글의 갯수 리턴 기본 0
	 */
	public int getBoardSize(String id) {
		int count = 0;
		for (int i = 0; i < board.size(); i++) {
			if (id.equals(board.get(i).getId())) {
				count++;
			}
		}
		return count;
	}
	/**
	 * 새로운 글 생성
	 * @param title 타이틀
	 * @param content 본문
	 * @param id 글쓴이
	 */
	public void NewBoard(String title, String content, String id) {
		board.add(new Board(title, content, id));
	}
	/**
	 * 마지막 페이지 리턴
	 * @return 게시글이 4개이하라면 1 / 게시글 / 4  + 게시글 %4 == 0 ? 0 : 1
	 */
	private int getMaxpage() {
		if (board.size() <= 4)
			return 1;
		return (board.size() - 1) / 4 + ((board.size() - 1) % 4 == 0 ? 0 : 1);
	}

	/**
	 * 게시글 삭제
	 * @param id -> 삭제요청 아이디
	 * @param idx -> 게시판 번호
	 * @return 삭제 성공시 true 
	 */
	public boolean DeleteUserBoard(String id, int idx) {
		for (int i = 0; i < board.size(); i++) {
			if (board.get(i).getBoradNum() == idx && (id.equals(board.get(i).getId()) || id.equals("admin"))) {
				System.out.println("게시글 삭제 완료");
				if (board.size() == 1) {
					board.clear();
					return false;
				}
				board.remove(i);
				return false;
			}
		}
		return true;
	}
	/**
	 * 데이터 로드
	 * @param data  
	 */
	public void LoadData(String data) {
		if (data == null || data.length() < 1)
			return;
		if (board.size() != 0)
			board.clear();
		String[] data2 = data.split("\n");
		for (int i = 0; i < data2.length; i++) {
			String[] t = data2[i].split("/");
			board.add(new Board(Integer.parseInt(t[0]), t[1], t[2], t[3], t[4], Integer.parseInt(t[5])));
		}
	}
	/**
	 * 데이터 세이브 string type 으로 리턴
	 * @return n/n......n 의 정보 리턴
	 */
	public String SaveData() {
		String data = "";

		for (int i = 0; i < board.size(); i++) {
			data += board.get(i).Save();
		}
		if (data.length() > 1)
			data = data.substring(0, data.length() - 1);
		return data;
	}

}
