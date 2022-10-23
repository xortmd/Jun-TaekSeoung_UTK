package lv04;

import java.util.Scanner;

public class 오목 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int size = 10;
		int[][] omok = new int[size][size];
		
//		int p1Y = 0;
//		int p1X = 0;
//		int p2Y = 0;
//		int p2X = 0;
		
		int turn = 1;
		int win = 0;
		
		while(true) {
			// 오목판 출력
			System.out.println("┌─────────────[omok]─────────────┐");
			System.out.print("│   ");
			for(int i = 0; i < size - 1; i++)
				System.out.print(i + "  ");
			System.out.print(size - 1 + " ");
			System.out.println("│");
			for(int i = 0; i < size; i++) {
				System.out.print("│" + i + " ");
				for(int j = 0; j < size; j++) {
					if(omok[i][j] == 1) {
						System.out.print("─●─");
					} else if(omok[i][j] == 2) {
						System.out.print("─○─");
					} else {
						System.out.print("─┼─");
					}
				}
				System.out.print("│");
				System.out.println();
			}
			System.out.println("└────────────────────────────────┘");
			
			// 게임 종료
			if(win != 0) {
				System.out.println("[p" + win + "] 승리!");
				break;
			}
			
			// 입력
			System.out.print("[p" + turn + "] y축 입력(0~9): ");
			int idxY = scan.nextInt();
			System.out.print("[p" + turn + "] x축 입력(0~9): ");
			int idxX = scan.nextInt();
			
			// 좌표값 예외처리
			while(true) {
				if(idxY < 0 || size <= idxY || idxX < 0 || size <= idxX) {
					System.out.println("입력값이 범위를 벗어났습니다.");
					System.out.print("[p" + turn + "] y축 입력: ");
					idxY = scan.nextInt();
					System.out.print("[p" + turn + "] x축 입력: ");
					idxX = scan.nextInt();
					continue;
				}
				if(omok[idxY][idxX] != 0) {
					System.out.println("이미 말이 놓여져 있습니다.");
					System.out.print("[p" + turn + "] y축 입력: ");
					idxY = scan.nextInt();
					System.out.print("[p" + turn + "] x축 입력: ");
					idxX = scan.nextInt();
					continue;
				}
				break;
			}
			
			// 말 놓기
			omok[idxY][idxX] = turn == 1 ? 1 : 2;
			
			// 오목 확인
			// 가로
			int count = 0;
			for(int i = 0; i < size; i++) {
				if(omok[idxY][i] == turn)
					count++;
				else
					count = 0;
				if(count == 5)
					win = turn;
			}
			
			// 세로
			count = 0;
			for(int i = 0; i < size; i++) {
				if(omok[i][idxX] == turn)
					count++;
				else
					count = 0;
				if(count == 5)
					win = turn;			
			}
			
			// 대각선(\)
			int tempY = idxY;
			int tempX = idxX;
			count = 0;
			while(true) {
				if(tempY == 0 || tempX == 0)
					break;
				tempY--;
				tempX--;
			}
			while(tempY < size && tempX < size) {
				if(omok[tempY][tempX] == turn)
					count++;
				else
					count = 0;
				if(count == 5)
					win = turn;
				tempY++;
				tempX++;
			}			
			
			// 대각선(/)
			count = 0;
			while(true) {
				if(idxY == 0 || idxX == size - 1)
					break;
				idxY--;
				idxX++;
			}
			while(idxY < size && idxX > -1) {
				if(omok[idxY][idxX] == turn)
					count++;
				else
					count = 0;
				if(count == 5)
					win = turn;
				idxY++;
				idxX--;
			}
			
			turn = turn == 1 ? 2 : 1;
		}
		
		scan.close();
	}
}