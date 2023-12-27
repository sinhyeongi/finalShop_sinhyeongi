package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import util.Util;

public class AdminBoard  implements MenuCommand {
	private MallController cont;
	private BoardDAO board;
	@Override
	public void init() {
		cont = MallController.getInstance();
		board = BoardDAO.getInstance();
		System.out.println("========[ 관리자 게시판 ]========");
	}

	@Override
	public boolean update() {
		System.out.println("[1] 게시글목록");
		System.out.println("[2] 게시글삭제");
		System.out.println("[3] 뒤로가기");
		System.out.println("[0] 종료");
		int sel = Util.getValue("► 메뉴 선택 [0 - 3]", 0, 3);
		if(sel == 0) {
			
		}else if(sel ==1) {
			while(true) {
				board.PrintBoard();
				System.out.println("[1] 이전");
				System.out.println("[2] 이후");
				System.out.println("[3] 게시글 보기");
				System.out.println("[0] 종료");
				sel = Util.getValue("► 메뉴 입력 [0 - 4] ", 0, 3);
				if(sel == 0) {
					break;
				}else if( sel == 1) {
					if(board.beforePage()) {
						System.out.println("첫 페이지 입니다.");
					}
				}else if( sel == 2) {
					if(board.NextPage()) {
						System.out.println("마지막 페이지 입니다.");
					}
				}else if( sel == 3) {
					if(board.getBoardendNum() == 0) {
						System.out.println("게시글이 없습니다!");
						continue;
					}
					sel = Util.getValue("게시글 번호 입력 ["+(board.getStart()+1 )+ " - "+( board.getEnd()+1)+"]", board.getStart()+1, board.getEnd()+1);
					board.PrintBoard(sel);
					break;
				}
			}
		}
		else if(sel ==2) {
			if(board.getBoardendNum() == 0) {
				System.out.println("게시글이 없습니다!");
				return false;
			}
			sel = Util.getValue("► 삭제할 게시글 번호 입력 ["+board.getBoardStartdNum()+" - "+board.getBoardendNum()+"]", board.getBoardStartdNum(), board.getBoardendNum());
			board.DeleteUserBoard(cont.getLoginId(), sel);
		}
		else if(sel ==3) {
			cont.setNext("AdminMain");
		}
		return false;
	}

}
