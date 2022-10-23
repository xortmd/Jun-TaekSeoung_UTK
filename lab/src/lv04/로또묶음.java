package lv04;

import java.util.Random;
/*
 * # 당첨복권 1세트
 * 1. 3이 연속으로 3번 등장하면 당첨복권이다.
 * 2. 랜덤으로 5개의 복건을 생성하되, 당첨복권은 한 개만 생성되도록 설정한다.
 */

public class 로또묶음 {
	public static void main(String[] args) {
		Random ran = new Random();
		
		int[][] lottoSet = new int[5][7];
		int count = 0;
		int lottoCount = 0;
		int index = -1;
		
		// 복권 생성
		while(lottoCount != 1) {
			lottoCount = 0;
			for(int i = 0; i < lottoSet.length; i++) {
				count = 0;
				for(int j = 0; j < lottoSet[i].length; j++) {
					lottoSet[i][j] = ran.nextInt(2)*3;
					if(lottoSet[i][j] == 3)
						count++;
					else
						count = 0;
					if(count >= 3) {
						lottoCount++;
						index = i;
						break;
					}
				}
			}
		}
		
		// 화면 출력
		for(int i = 0; i < lottoSet.length; i++) {
			System.out.print("복권" + (i + 1) + " = ");
			for(int j = 0; j < lottoSet[i].length; j++)
				System.out.print(lottoSet[i][j] + " ");
			if(i == index)
				System.out.print("<-- 당첨");
			System.out.println();
		}
		
	}
}