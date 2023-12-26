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
		if(instance == null)
			instance = new BoardDAO();
		return instance;
	}
	public boolean NextPage() {
		if(page == getMaxpage()) {
			return true;
		}
		start = ++end;
		end  +=4 ;
		page++;
		return false;
	}
	
	public boolean beforePage() {
		if(page == 1) {
			return true;
		}
		end = --start;
		start -= 4;
		page--;
		return false;
	}
	public void PrintBoard() {
		for(int i = start; i <= Math.min(end, board.size() - 1); i++) {
			System.out.println(board.get(i).toBoard());
		}
	}
	public int getStart() {
		return start;
	}
	public int getEnd() {
		return Math.min(end, board.size()-1);
	}
	public void PrintBoard(int idx) {
		System.out.println(board.get(idx-1).toBoard());
		System.out.println("------------------------");
		System.out.println(board.get(idx-1).getContents());
		System.out.println();
		board.get(idx-1).setHits(board.get(idx-1).getHits() + 1);
	}
	public void PrintBoard(String id) {
		for(int i = 0; i < board.size(); i++) {
			if(id.equals(board.get(i).getId()))
				System.out.println(board.get(i).toBoard());
		}
	}
	public int getBoardSize() {
		return board.size();
	}
	public int getBoardSize(String id) {
		int count = 0;
		for(int i = 0 ; i < board.size(); i++) {
			if(id.equals(board.get(i).getId())) {
				count++;
			}
		}
		return count;
	}
	public void NewBoard(String title, String content,String id) {
		board.add(new Board( title, content,id));
	}
	public int getMaxpage() {
		if(board.size() <=4)
			return 1;
		return (board.size()-1) / 4 + ((board.size()-1) % 4 == 0? 0 : 1);
	}
	public boolean DeleteUserBoard(String id, int i) {
		if(board.get(i-1).getId().equals(id)) {
			System.out.println("게시글 삭제 완료");
			if(board.size() == 1) {
				board.clear();
				return false;
			}
			board.remove(i);
			return false;
		}
		return true;
	}
	public void LoadData(String data) {
		if(data == null || data.length() < 1)return;
		if(board.size() != 0)
			board.clear();
		String[] data2 = data.split("\n");
		for(int i = 0 ; i < data2.length ;i++) {
			String[] t = data2[i].split("/");
			board.add(new Board(Integer.parseInt(t[0]),t[1],t[2],t[3],t[4],Integer.parseInt(t[5])));
		}
	}
}
