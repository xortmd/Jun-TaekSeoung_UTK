package lv03;

import java.util.Scanner;

public class 미니마블 {
	/*
	 * # 미니마블
	 * 1. 플레이어는 p1과 p2 2명이다.
	 * 2. 번갈아가며 1~3 사이의 숫자를 입력해 이동한다.
	 * 3. 이동하다가 다음 플레이어와 같은 위치에 놓이게 되면,
	 *    상대 플레이어는 잡히게 되어 원점으로 되돌아간다.
	 * 4. 먼저 3바퀴를 돌면 이긴다.
	 *    
	 *  [p2]1~3 입력 : 1
	 *  1 2 3 4 5 6 7 8 
	 *  0 1 0 0 0 0 0 0 
	 *  0 0 0 2 0 0 0 0 
	 *  
	 *  [p1]1~3 입력 : 2
	 *  [p1]이 p2를 잡았다!
	 *  1 2 3 4 5 6 7 8 
	 *  0 0 0 1 0 0 0 0 
	 *  2 0 0 0 0 0 0 0 
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int[] game = {1, 2, 3, 4, 5, 6, 7, 8};
		int[] p1   = {0, 0, 0, 0, 0, 0, 0, 0};
		int[] p2   = {0, 0, 0, 0, 0, 0, 0, 0};
		
		int turn = 1;
		int idx1 = 0;
		int idx2 = 0;
		p1[idx1] = 1;
		p2[idx2] = 2;
		int move;
		int goalCount1 = 0;
		int goalCount2 = 0;
		
		while(true) {
			// 게임 출력
			System.out.print("game = ");
			for(int i = 0; i < 8; i++) {
				System.out.print(game[i] + " ");
			}
			System.out.print("\n  p1 = ");
			for(int i = 0; i < 8; i++) {
				System.out.print(p1[i] + " ");
			}
			System.out.print("\n  p2 = ");
			for(int i = 0; i < 8; i++) {
				System.out.print(p2[i] + " ");
			}
			System.out.println();
			
			// 개임 종료
			if(goalCount1 == 3 || goalCount2 == 3) {
				int winner = turn == 1 ? 2 : 1;
				System.out.println("p" + winner + " 승리!");
				break;
			}
			
			// 입력 화면
			System.out.print("p[" + turn + "]1~3 입력: ");
			move = scan.nextInt();
			
			// 범위를 벗어난 인덱스 입력
			while(move < 1 || move > 3) {
				System.out.print("1~3의 값을 입력해주세요.");
				move = scan.nextInt();
			}
	
			// 이동
			p1[idx1] = 0;
			p2[idx2] = 0;
			if(turn == 1) {
				idx1 += move;
			} else {
				idx2 += move;
			}
			
			// 바퀴 수 세기
			if(idx1 >= 8) {
				idx1 -= 8;
				goalCount1++;
				System.out.println("p1 " + goalCount1 + "바퀴 완료");
			}
			if(idx2 >= 8) {
				idx2 -= 8;
				goalCount2++;
				System.out.println("p2 " + goalCount2 + "바퀴 완료");
			}
			
			// 인덱스 입력
			p1[idx1] = 1;
			p2[idx2] = 2;
			
			// 말 잡기
			if(goalCount1 == goalCount2) {
				if(idx1 == idx2) {
					System.out.println("상대 말을 잡았습니다. 한번 더 플레이합니다.");
					System.out.println("상대 바퀴 수를 초기화합니다.");
					if(turn == 1) {
						p2[idx2] = 0;
						idx2 = 0;
						goalCount2 = 0;
						p2[idx2] = 2;
					} else {
						p1[idx1] = 0;
						idx1 = 0;
						goalCount1 = 0;
						p1[idx1] = 1;
					}
					continue;
				}
			}
			
			// 턴 바꾸기
			turn = turn == 1 ? 2 : 1;
			System.out.println("====================");
		}
		
		scan.close();
	}
}