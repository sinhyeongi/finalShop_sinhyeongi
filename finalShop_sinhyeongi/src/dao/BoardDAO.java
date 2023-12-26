package dao;

import java.util.ArrayList;
import java.util.List;

import dto.Board;

public class BoardDAO {
	private static BoardDAO instance;
	private List<Board> board;
	public BoardDAO() {
		board = new ArrayList<Board>();
	}
	
	public static BoardDAO getInstance() {
		if(instance == null)
			instance = new BoardDAO();
		return instance;
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
