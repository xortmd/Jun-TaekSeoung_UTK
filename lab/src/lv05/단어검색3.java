package lv05;

import java.util.Scanner;

public class 단어검색3 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String text = "Life is, too short!";
		System.out.println(text);
		
		System.out.print("검색할 단어를 입력하세요.: ");
		String word = scan.next();
		
		// 배열에 단어 저장
		String[] textArr = text.split(" ");
		
		// 단어 확인
		boolean result = true;
		for(int i = 0; i < textArr.length; i++) {
			result = false;
			if(word.length() <= textArr[i].length()) {
				for(int j = 0; j < textArr[i].length(); j++) {
					if(textArr[i].charAt(j) == word.charAt(0)) {
						if(j + word.length() <= textArr[i].length() && word.equals(textArr[i].substring(j, j + word.length()))) {
							result = true;
							break;
						}
					}
				}
			}
			if(result)
				break;
		}
		
		System.out.println(result);
		
		scan.close();
	}
}
