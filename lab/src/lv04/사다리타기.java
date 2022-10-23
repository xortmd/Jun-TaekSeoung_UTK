package lv04;

import java.util.Scanner;

public class 사다리타기 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String menu[] = {"떡라면", "돈까스", "자장면", "쫄면", "된장찌개"};
		int ladder[][] = {
				{0, 0, 0, 0, 0},
				{1, 2, 0, 1, 2},
				{0, 1, 2, 0, 0},
				{0, 0, 1, 2, 0},
				{1, 2, 0, 0, 0},
				{0, 1, 2, 0, 0},
				{1, 2, 0, 0, 0},
				{0, 0, 0, 1, 2},
				{0, 0, 0, 0, 0}
		};
		
		// 사다리 출력
		System.out.println(" 1     2     3     4     5");
		for(int i = 0; i < ladder.length; i++) {
			for(int j = 0; j < ladder[i].length; j++) {
				if(ladder[i][j] == 0)
					System.out.print(" │    ");				
				else if(ladder[i][j] == 1)
					System.out.print(" ├────");
				else if(ladder[i][j] == 2)
					System.out.print("─┤    ");
			}
			System.out.println();
		}
		for(int i = 0; i < menu.length; i++)
			System.out.printf("%-7s ", menu[i]);
		System.out.println();
		System.out.println("==============================");
		
		// 숫자 입력
		System.out.print("1~5의 숫자를 입력하세요. ");
		int num = scan.nextInt();
		
		// 범위를 벗어난 숫자 입력
		while(num < 1 || 5 < num) {
			System.out.print("1~5에서 입력하라고 ");
			num = scan.nextInt();
		}
		scan.close();
		
		// 시작점 저장
		int x = num - 1;
		int y = 0;
		
		// 사다리 이동
		for(; y < ladder.length; y++) {
			if(ladder[y][x] == 1)
				x++;
			else if(ladder[y][x] == 2)
				x--;
			// 사다리 출력
			System.out.println(" 1     2     3     4     5");
			for(int i = 0; i < ladder.length; i++) {
				for(int j = 0; j < ladder[i].length; j++) {
					if(i == y && j == x)
						System.out.print(" *    ");
					else if(ladder[i][j] == 0)
						System.out.print(" │    ");				
					else if(ladder[i][j] == 1)
						System.out.print(" ├────");
					else if(ladder[i][j] == 2)
						System.out.print("─┤    ");
				}
				System.out.println();
			}
			for(int i = 0; i < menu.length; i++)
				System.out.printf("%-7s ", menu[i]);
			System.out.println();
			System.out.println("==============================");
		
		}
		
		// 결과 출력
		System.out.println("오늘 점심은 " + menu[x] + "입니다.");
		
	}
}
