package lv04;

import java.util.Random;
import java.util.Scanner;

public class 숫자야구 {
	public static void main(String[] args) {
		Random ran = new Random();
		Scanner scan = new Scanner(System.in);
		
		//int[] com = {1, 7, 3};
		int[] me = new int[3];
		// com 숫자 랜덤 저장
		int[] com = new int[3];
		for(int i = 0; i < com.length; i++) {
			com[i] = ran.nextInt(9) + 1;
			for(int j = 0; j < i; j++) {
				if(com[i] == com[j])
					i--;
			}
		}
		
		// 검사용
//		System.out.print("com = ");
//		for(int i = 0; i < com.length; i++)
//			System.out.print(com[i] + " ");
//		System.out.println();
		
		while(true) {
			int strike = 0;
			int ball = 0;
			
			// 숫자 입력
			while(true) {
				System.out.print("숫자 3개 입력: ");
				for(int i = 0; i < me.length; i++) {
					me[i] = scan.nextInt();
				}
				
				// 숫자 중복 확인
				boolean overlap = false;
				for(int i = 0; i < me.length; i++)
					for(int j = 0; j < me.length; j++)
						if(me[i] == me[j] && i != j)
							overlap = true;
				if(overlap)
					System.out.println("중복된 숫자는 입력할 수 없습니다.");
				else
					break;
			}
			
			// strike
			for(int i = 0; i < com.length; i++)
				if(com[i] == me[i])
					strike++;
			
			// ball
			for(int i = 0; i < me.length; i++)
				for(int j = 0; j < com.length; j++)
					if(me[i] == com[j] && i != j)
						ball++;
			
			// out
			if(strike == 3) {
				System.out.println("out!");
				break;
			}
			
			// 결과 출력
			if(strike != 0)
				System.out.print(strike + "s");
			if(ball != 0)
				System.out.print(ball + "b");			
			System.out.println();
		}
		
		scan.close();
	}
}
