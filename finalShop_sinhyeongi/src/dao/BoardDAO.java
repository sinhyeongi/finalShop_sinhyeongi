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

	public boolean NextPage() {
		if (page == getMaxpage()) {
			return true;
		}
		start = ++end;
		end += 4;
		page++;
		return false;
	}

	public boolean beforePage() {
		if (page == 1) {
			return true;
		}
		end = --start;
		start -= 4;
		page--;
		return false;
	}

	public void PrintBoard() {
		for (int i = start; i <= Math.min(end, board.size() - 1); i++) {
			System.out.println(board.get(i).toBoard());
		}
	}

	public int getStart() {
		return board.get(start).getBoradNum();
	}

	public int getEnd() {
		if( end > board.size()) {
			return board.get(board.size()-1).getBoradNum();
		}
		return board.get(end).getBoradNum();
	}

	public void PrintBoard(int idx) {
		
		for(int i = 0 ; i < board.size(); i++) {
			if(board.get(i).getBoradNum() == idx) {
				System.out.println(board.get(i).toBoard());
				System.out.println("------------------------");
				System.out.println(board.get(i).getContents());
				System.out.println();
				board.get(i).setHits(board.get(i).getHits() +1);
				break;
			}
		}
	}

	public void PrintBoard(String id) {
		for (int i = 0; i < board.size(); i++) {
			if (id.equals(board.get(i).getId()))
				System.out.println(board.get(i).toBoard());
		}
	}
	public int getBoardStartdNum() {
		if(board.size() == 0)
			return 0;
		return board.get(0).getBoradNum();
	}
	public int getBoardendNum() {
		if(board.size() == 0)
			return 0;
		return board.get(board.size()-1).getBoradNum();
	}

	public int getBoardSize(String id) {
		int count = 0;
		for (int i = 0; i < board.size(); i++) {
			if (id.equals(board.get(i).getId())) {
				count++;
			}
		}
		return count;
	}

	public void NewBoard(String title, String content, String id) {
		board.add(new Board(title, content, id));
	}

	public int getMaxpage() {
		if (board.size() <= 4)
			return 1;
		return (board.size() - 1) / 4 + ((board.size() - 1) % 4 == 0 ? 0 : 1);
	}

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

	public String SaveData() {
		String data = "";

		for (int i = 0; i < board.size(); i++) {
			data += board.get(i).Save();
		}
		if (data.length() > 1)
			data = data.substring(0, data.length() - 1);
		return data;
	}

	public void DeleteUser(String id) {
		for (int i = 0; i < board.size(); i++) {
			if (board.get(i).getId().equals(id)) {
				if (board.size() == 1) {
					board.clear();
					break;
				}
				board.remove(i);
			}
		}
	}
}
