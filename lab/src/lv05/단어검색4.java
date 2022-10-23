package lv05;

import java.util.Scanner;

public class 단어검색4 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String text = "Life is, too short!";
		System.out.println(text);
		
		System.out.print("검색할 단어를 입력하세요.: ");
		String word = scan.next();
		
//		text = text.toLowerCase();
//		word = word.toLowerCase();
		
		String temp = text;
		text = "";
		for(int i = 0; i < temp.length(); i++) {
			if(65 <= temp.charAt(i) && temp.charAt(i) <= 90)
				text += (char)(temp.charAt(i) + 32);
			else
				text += temp.charAt(i);
		}
		
		temp = word;
		word = "";
		for(int i = 0; i < temp.length(); i++) {
			if(65 <= temp.charAt(i) && temp.charAt(i) <= 90)
				word += (char)(temp.charAt(i) + 32);
			else
				word += temp.charAt(i);
		}
		
		System.out.println("text = " + text);
		System.out.println("word = " + word);
		
		boolean result = false;
		int count = word.length();
		for(int i = 0; i < text.length() - count - 1; i++) {
			int check = 0;
			for(int j = 0; j < count; j++) {
				if(text.charAt(i + j) == word.charAt(j))
					check++;
			}
			if(check == count)
				result = true;
		}
		
		System.out.println(result);
		
		scan.close();
	}
}
