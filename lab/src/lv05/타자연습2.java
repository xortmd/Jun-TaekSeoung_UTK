package lv05;

import java.util.Random;
import java.util.Scanner;

public class 타자연습2 {
	/*
	 * # 타자연습 게임[2단계]
	 * 1. 문제를 섞는다.(shuffle)
	 * 2. 순서대로 문제를 출제하고, 문제를 다 맞추면 게임 종료
	 * 3. 단 문제를 출제할 때, 단어의 랜덤한 위치 한 곳만 *로 출력
	 * 예)
	 * 문제 : mys*l
	 * 입력 : mysql	<--- 정답을 맞추면, 다음 문제 제시
	 * 문제 : *sp
	 * 입력 : jsp
	 * ...
	 */
	public static void main(String[] args) {
		Random ran = new Random();
		Scanner scan = new Scanner(System.in);

		String[] words = {"java", "mysql", "jsp", "spring"};
		
		for(int i = 0; i < 1000; i++) {
			int idx = ran.nextInt(words.length);
			String temp = words[0];
			words[0] = words[idx];
			words[idx] = temp;
		}
		
		int count = 0;
		int wordsIdx = ran.nextInt(words[count].length());
		
		while(true) {
			System.out.println("문제: " + words[count].substring(0, wordsIdx) + "*"
			+ words[count].substring(wordsIdx + 1, words[count].length()));
			
			System.out.print("입력: ");
			String answer = scan.next();
			
			if(answer.equals(words[count])) {
				count++;
				if(count == 4)
					break;
				wordsIdx = ran.nextInt(words[count].length());
			}
		}
		
		System.out.println("CLEAR!");

		scan.close();
	}
}
