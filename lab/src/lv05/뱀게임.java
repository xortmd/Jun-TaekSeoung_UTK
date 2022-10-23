package lv05;

import java.util.Random;
import java.util.Scanner;

public class 뱀게임 {
	/*
	 * # 스네이크 게임
	 * 1. 10x10 배열을 0으로 채운다.
	 * 2. 스네이크는 [1]234로 표시한다.
	 * 3. 상하좌우로 이동이 가능하며, 꼬리가 따라온다.
	 * 4. 자기몸하고 부딪히면 사망한다.
	 * 5. 랜덤으로 아이템을 생성해 아이템을 먹으면 꼬리 1개가 자란다.
	 * 6. 꼬리는 최대 8개까지 증가 할 수 있다.
	 */
	public static void main(String[] args) {
		Random ran = new Random();
		Scanner scan = new Scanner(System.in);
		
		final int SIZE = 10;
		final int ITEM = 100;
		int pY = -1;
		int pX = -1;
		int itemY = -1;
		int itemX = -1;
		int tempY = -1;
		int tempX = -1;
		
		boolean die = false;
		
		int[][] map = new int[SIZE][SIZE];
		int snake = 4;
		int[][] yx = new int[snake][2];
		
		for(int i = 0; i < snake; i++) {
			yx[i][0] = 0;
			yx[i][1] = i;
		}
		
		// 아이템 생성
		boolean check = true;
		while(check) {
			check = false;
			itemY = ran.nextInt(SIZE);
			itemX = ran.nextInt(SIZE);
			
			for(int i = 0; i < yx.length; i++)
				if(yx[i][0] == itemY && yx[i][1] == itemX)
					check = true;
		}
		
		map[itemY][itemX] = ITEM;
		
		while(true) {
			for(int i = 0; i < SIZE; i++) {
				for(int j = 0; j < SIZE; j++) {
					// 뱀 출력
					check = true;
					for(int k = 0; k < yx.length; k++)
						if(yx[k][0] == i && yx[k][1] == j) {
							System.out.print(k + 1 + " ");
							check = false;
						}
					// 뱀이 아닌 부분 출력
					if(check) {
						if(map[i][j] == ITEM)
							System.out.print("@ ");
						else
							System.out.print("+ ");
					}
					
				}
				System.out.println();
			}
			
			System.out.print("a(←) w(↑) d(→) s(↓) 입력: ");
			String move = scan.next();
			
			if(move.equals("a")) {
				pY = yx[0][0];
				pX = yx[0][1] - 1;
				
			} else if(move.equals("w")) {
				pY = yx[0][0] - 1;
				pX = yx[0][1];
				
			} else if(move.equals("d")) {
				pY = yx[0][0];
				pX = yx[0][1] + 1;
				
			} else if(move.equals("s")) {
				pY = yx[0][0] + 1;
				pX = yx[0][1];
				
			} else {
				System.out.println("화면의 키중에 입력하세요.");
				continue;
			}
			
			// die 확인
			for(int i = 0; i < yx.length; i++)
				if(pY == yx[i][0] && pX == yx[i][1])
					die = true;
			
			if(pY < 0 || pX < 0 || pY > SIZE - 1 || pX > SIZE - 1) {
				System.out.println("이동할 수 없습니다.");
				
			} else if(die) {
				System.out.println("GAME OVER!");
				break;
				
			} else {
				tempY = yx[yx.length - 1][0];
				tempX = yx[yx.length - 1][1];
				for(int i = yx.length - 1; i >= 1; i--) {
					yx[i][0] = yx[i - 1][0];
					yx[i][1] = yx[i - 1][1];
				}
				yx[0][0] = pY;
				yx[0][1] = pX;
				
				if(yx.length < 8) {
					// 아이템 확인 
					if(map[pY][pX] == ITEM) {
						map[pY][pX] = 0;
						int[][] tempYX = yx;
						
						snake++;
						yx = new int[snake][2];
						
						for(int i = 0; i < yx.length - 1; i++) {
							yx[i][0] = tempYX[i][0];
							yx[i][1] = tempYX[i][1];
						}
						
						yx[snake - 1][0] = tempY;
						yx[snake - 1][1] = tempX;
						
						if(yx.length < 8) {
							// 아이템 재생성
							check = true;
							while(check) {
								check = false;
								itemY = ran.nextInt(SIZE);
								itemX = ran.nextInt(SIZE);
								
								for(int i = 0; i < yx.length; i++)
									if(yx[i][0] == itemY && yx[i][1] == itemX)
										check = true;
							}
							
							map[itemY][itemX] = ITEM;
						} else {
							System.out.println("snake가 최대 길이에 도달했습니다.");
						}
					}
				}
			}
		}
		scan.close();
	}
}
