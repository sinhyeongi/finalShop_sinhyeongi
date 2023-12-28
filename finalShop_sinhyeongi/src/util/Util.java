package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Util {
	private static Scanner scanner = new Scanner(System.in);

	public static String getValue(String s) {
		System.out.print(s + "입력 : ");
		return scanner.next();
	}

	public static int getValue(String s, int min, int max) {
		System.out.print(s + "입력 : ");
		try {
			int inp = scanner.nextInt();
			if (inp < min || inp > max) {
				System.out.println(min + " ~ " + max + " 값 까지 입력해주세요");
			}
			return inp;
		} catch (InputMismatchException e) {
			scanner.nextLine();
			System.out.println("숫자를 입력해주세요");
		}
		return -1;
	}
}
