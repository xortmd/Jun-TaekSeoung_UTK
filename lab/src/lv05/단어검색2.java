package lv05;

import java.util.Scanner;

public class 단어검색2 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String text = "Life is, too short!";
		System.out.println(text);
		
		System.out.print("검색할 단어를 입력하세요.: ");
		String word = scan.next();
		
		// 배열에 단어 저장
		String[] textArr = text.split(" ");
		String[][] textArr2 = new String[textArr.length][2];
		
		for(int i = 0; i < textArr.length; i++) {
			textArr2[i][0] = textArr[i];
			for(int j = 0; j < textArr[i].length(); j++) {
				if(textArr[i].charAt(j) < 65 || 90 < textArr[i].charAt(j) && textArr[i].charAt(j) < 97 || 122 < textArr[i].charAt(j)) {
					textArr2[i][0] = textArr[i].substring(0, j);
					textArr2[i][1] = String.valueOf(textArr[i].charAt(j));
				}
			}
		}
		
		
		// 단어 확인
		boolean result = false;
		for(int i = 0; i < textArr2.length; i++) {
			for(int j = 0; j < textArr2[i].length; j++) {
				if(textArr2[i][j] != null && textArr2[i][j].equals(word))
					result = true;
			}
		}
		
		System.out.println(result);
		
		scan.close();
	}
}
