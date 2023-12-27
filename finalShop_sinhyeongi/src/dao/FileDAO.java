package dao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
	/**
	 * 
	 * @param name
	 * @param data
	 */
	private void SaveFile(FileName name, String data) {
		Charset caharset = StandardCharsets.UTF_8;
		Path path = Paths.get("src/files/" + name.getName());
		try(FileOutputStream fos = new FileOutputStream(path.toString());
				OutputStreamWriter ow = new OutputStreamWriter(fos,caharset);
			BufferedWriter bw = new BufferedWriter(ow)){
			bw.write(data);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static  String ReadFile(FileName name) {
		Charset caharset = StandardCharsets.UTF_8;
		Path path = Paths.get("src/files/" + name.getName());
		StringBuilder data2 =  new StringBuilder();
		try(InputStreamReader bf = new InputStreamReader(new FileInputStream(path.toString()),caharset);
				BufferedReader bf2 = new BufferedReader(bf)) {
			String line;
			while((line = bf2.readLine()) != null) {
				data2.append(line);
				data2.append("\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(data2.toString().length() > 1) {
			return data2.toString().substring(0,data2.toString().length()-1);
		}
		return null;
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
	public void SaveAllFiles(String member, String item, String cart,String Board) {
		SaveFile(FileName.MEMBER,member);
		SaveFile(FileName.ITEM,item);
		SaveFile(FileName.CART,cart);
		SaveFile(FileName.BOARD,Board);
	}
}