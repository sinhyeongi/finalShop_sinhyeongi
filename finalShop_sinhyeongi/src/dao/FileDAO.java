package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDAO {


	enum FileName {
		BOARD("board.txt"), MEMBER("member.txt"), ITEM("item.txt"), CART("cart.txt");
		private String name;
		FileName(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
	}

	private FileDAO() {
		init();
	}

	private static FileDAO instance = new FileDAO();

	static public FileDAO getInstance() {
		return instance;
	}
	
	private void createFile(FileName name) {
		Path path = Paths.get("src/files/" + name.getName());
		try {
			if(Files.exists(path) == false)
				Files.createFile(path);
		} catch (IOException e) {
			//System.out.println("파일이 이미 있음");
		}
	}
	
	private void init() {
		createFile(FileName.BOARD);
		createFile(FileName.MEMBER);
		createFile(FileName.ITEM);
		createFile(FileName.CART);

	}
	private static  String ReadFile(FileName name) {
		Path path = Paths.get("src/files/" + name.getName());
		File f = new File(path.toString());
		String data = "";
		try(BufferedReader bf = new BufferedReader(new FileReader(f))) {
			int i;
			while((i = bf.read()) != -1) {
				data += (char)i;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	public static void loadAllFiles() {
		MemberDAO member= MemberDAO.getInstance();
		member.LoadData(ReadFile(FileName.MEMBER));
		ItemDAO item = ItemDAO.getInstance();
		item.LoadData(ReadFile(FileName.ITEM));
		CartDAO cart = CartDAO.getInstance();
		cart.LoadData(ReadFile(FileName.CART ));
		BoardDAO board = BoardDAO.getInstance();
		board.LoadData(ReadFile(FileName.BOARD));
	}
}