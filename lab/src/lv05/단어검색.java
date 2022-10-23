package lv05;

import java.util.Scanner;

public class 단어검색 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String text = "Life is, too short!";
		System.out.println(text);
		
		System.out.print("검색할 단어를 입력하세요.: ");
		String word = scan.next();
		
		// 배열에 단어 저장
		String[] textArr = text.split(" ");
		
		// 특수기호 떼기
		for(int i = 0; i < textArr.length; i++)
			for(int j = 0; j < textArr[i].length(); j++)
				if(textArr[i].charAt(j) < 65 || 90 < textArr[i].charAt(j) && textArr[i].charAt(j) < 97 || 122 < textArr[i].charAt(j))
					textArr[i] = textArr[i].substring(0, j) + textArr[i].substring(j + 1, textArr[i].length());
		
		// 단어 확인
		boolean result = false;
		for(int i = 0; i < textArr.length; i++)
			if(textArr[i].equals(word))
				result = true;
		
		System.out.println(result);
		
		scan.close();
	}
}
