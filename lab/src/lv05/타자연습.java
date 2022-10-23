
package lv05;

import java.util.Random;
import java.util.Scanner;

public class 타자연습 {
	/*
	 * # 타자연습 게임[1단계]
	 * 1. 문제를 섞는다.(shuffle)
	 * 2. 순서대로 문제를 출제하고, 문제를 다 맞추면 게임 종료
	 * 예)
	 * 문제 : mysql
	 * 입력 : mydb
	 * 문제 : mysql
	 * 입력 : mysql	<--- 정답을 맞추면, 다음 문제 제시
	 * 문제 : jsp
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
		
		while(count < words.length) {
			System.out.println("문제: " + words[count]);
			System.out.print("입력: ");
			String answer = scan.next();
			
			if(answer.equals(words[count]))
				count++;				
		}
		
		System.out.println("CLEAR!");

		scan.close();
	}
}
