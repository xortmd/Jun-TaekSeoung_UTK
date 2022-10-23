package lv04;

import java.util.Random;
import java.util.Scanner;

public class 카드뒤집기 {
	public static void main(String[] args) {
		Random ran = new Random();
		Scanner scan = new Scanner(System.in);
		
		final int SIZE = 9;
		int[] front = new int[SIZE];
		int[] back = new int[SIZE];
		int cardCount = 1;
		
		// front 숫자 저장
		for(int i = 0; i < SIZE; i++) {
			front[i] = ran.nextInt(9) + 1;
			for(int j = 0; j < i; j++)
				if(front[i] == front[j])
					i--;
		}
		
		// back 숫자 저장
		for(int i = 0; i < SIZE; i++) {
			back[i] = ran.nextInt(9) + 10;
			for(int j = 0; j < i; j++)
				if(back[i] == back[j])
					i--;
		}
		
		while(true) {
			// 화면 출력
			for(int i = 0; i < SIZE; i++)
				System.out.print(front[i] + " ");
			System.out.println();
			
			// 게임 종료
			if(cardCount == 19) {
				System.out.println("CLEAR!");
				break;
			}
			
			// 입력
			System.out.print("인덱스 입력: ");
			int index = scan.nextInt();
			
			// 범위를 벗어난 인덱스
			if(index < 0 || 8 < index) {
				System.out.println("인덱스의 범위는 0~8입니다.");
			} else {
				// 카드 뒤집기
				if(front[index] == cardCount) {
					front[index] = back[index];
					back[index] = 0;
					cardCount++;
				// 잘못된 카드 선택
				} else {
					System.out.println("땡!");
				}
			}
			
			System.out.println("--------------------");
		}
		
		scan.close();
	}
}
