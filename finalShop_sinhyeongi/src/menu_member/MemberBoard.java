package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import util.Util;

public class MemberBoard  implements MenuCommand {
	private MallController cont;
	private BoardDAO board;
	@Override
	public void init() {
		cont = MallController.getInstance();
		board = BoardDAO.getInstance();
		System.out.println("==========[ 게시판 ]==========");
		System.out.println("[1] 게시글보기");
		System.out.println("[2] 게시글 추가");
		System.out.println("[3] 내개시글(삭제)");
		System.out.println("[4] 뒤로가기");
		System.out.println("[0] 종료");
	}

	@Override
	public boolean update() {
		int sel = Util.getValue("► 메뉴 입력 [0 - 4] ", 0, 4);
		if(sel == 0) {
			cont.setNext(null);
		}else if(sel == 1) {
			if(board.getBoardSize() == 0) {
				System.out.println("게시글이 없습니다!");
				return false;
			}
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
					sel = Util.getValue("게시글 번호 입력 ["+(board.getStart()+1 )+ " - "+( board.getEnd()+1)+"]", board.getStart()+1, board.getEnd()+1);
					board.PrintBoard(sel);
					break;
				}
			}
			
		}else if(sel == 2) {
//			System.out.println("[2] 게시글 추가");
			String title = Util.getValue("게시글의 제목 입력");
			String content = Util.getValue("게시글의 내용 입력");
			board.NewBoard(title, content, cont.getLoginId());
		}
		else if(sel == 3) {
			 if(board.getBoardSize(cont.getLoginId()) == 0) {
				 System.out.println("게시글이 없습니다.");
				 return false;
			 }
			board.PrintBoard(cont.getLoginId());
			System.out.println("[1]삭제");
			System.out.println("[0]돌아가기");
			 sel = Util.getValue("► 메뉴 입력 [0 - 1] ", 0, 1);
			 if(sel == 1) {
				 sel = Util.getValue("► 삭제할 게시글 번호 입력  [1 - "+(board.getBoardSize() -1) +"] ", 1, board.getBoardSize() -1 );
				 if(board.DeleteUserBoard(cont.getLoginId(), sel)) {
					 System.out.println("본인 게시글만 삭제하실 수 있습니다.");
				 }
			 }
		}
		else if(sel == 4) {
			cont.setNext("MemberMain");
		}
		return false;
	}

}
