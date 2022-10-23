package lv05;

import java.util.Random;
import java.util.Scanner;

public class 크레이지아케이드 {
	
	public static void main(String[] args) {
		Random ran = new Random();
		Scanner scan = new Scanner(System.in);
		
		final int SIZE = 7;
		final int WALL = 3;
		final int ITEM = 4;
		final int BOMB = 9;
		
		int pY;
		int pX;
		int diePY = -1;
		int diePX = -1;
		int bombMax = 3; // 최대 폭탄
		int bombCnt = bombMax; // 설치가능 폭탄
		int[][] bombs = new int[bombMax][2];
		for(int i = 0; i < bombs.length; i++) { // -1로 초기화(0,0이 폭탄일 수도 있어서)
			bombs[i][0] = -1;
			bombs[i][1] = -1;
		}
		
		int[][] map = new int[SIZE][SIZE];
		boolean gameOver = false;
		
		System.out.print("벽 개수 입력(0 ~ 10): ");
		int wallCount = scan.nextInt();
		
		while(wallCount < 0 || 10 < wallCount) {
			System.out.println("0 ~ 10의 숫자를 입력하세요.");
			System.out.print("다시 입력: ");
			wallCount = scan.nextInt();
		}
		
		// 벽 생성
		while(wallCount > 0) {
			int wallY = ran.nextInt(SIZE);
			int wallX = ran.nextInt(SIZE);
			if(map[wallY][wallX] == 0) {
				map[wallY][wallX] = WALL;
				wallCount--;
			}
		}
		
		// player 생성
		while(true) {
			pY = ran.nextInt(SIZE);
			pX = ran.nextInt(SIZE);
			if(map[pY][pX] != WALL)
				break;
		}
		
		while(true) {
			for(int i = 0; i < SIZE; i++) {
				for(int j = 0; j < SIZE; j++) {
					if(i == diePY && j == diePX) {
						System.out.print("∞ ");
					} else if(i == pY && j == pX) {
						System.out.print("8 ");
					} else if(map[i][j] == WALL) {
						System.out.print("■ ");
					} else if(map[i][j] == ITEM) {
						System.out.print("★ ");
					} else if(map[i][j] == BOMB) {
						System.out.print("● ");
					} else {
						System.out.print("+ ");
					}
				}
				System.out.println();
			}
			
			if(gameOver) {
				System.out.println("GAME OVER!");
				break;
			}
			
			System.out.println("설치 가능한 폭탄 수: " + bombCnt);
			System.out.println("a(←) w(↑) d(→) s(↓)");
			System.out.println("o(폭탄설치) p(폭파)");
			String play = scan.next();
			
			if(play.equals("a")) {
				if(pX - 1 < 0 || map[pY][pX - 1] == WALL)
					System.out.println("움직일 수 없습니다.");
				else
					pX--;
			} else if(play.equals("w")) {
				if(pY - 1 < 0 || map[pY - 1][pX] == WALL)
					System.out.println("움직일 수 없습니다.");
				else
					pY--;
			} else if(play.equals("d")) {
				if(pX + 1 > SIZE - 1 || map[pY][pX + 1] == WALL)
					System.out.println("움직일 수 없습니다.");
				else
					pX++;
			} else if(play.equals("s")) {
				if(pY + 1 > SIZE - 1 || map[pY + 1][pX] == WALL)
					System.out.println("움직일 수 없습니다.");
				else
					pY++;
			} else if(play.equals("o")) {
				if(bombCnt > 0) {
					for(int i = 0; i < bombs.length; i++) {
						if(bombs[i][0] == -1) {
							bombs[i][0] = pY;
							bombs[i][1] = pX;
							map[bombs[i][0]][bombs[i][1]] = BOMB;
							bombCnt--;
							break;
						}
					}
				} else {
					System.out.println("설치할 폭탄이 없습니다.");
				}
				
			} else if(play.equals("p")) {
				if(bombs[0][0] == -1)
					System.out.println("폭파할 폭탄이 없습니다.");
				else {
					if(bombs[0][0] < SIZE - 1 && map[bombs[0][0] + 1][bombs[0][1]] == WALL) {
						int ranItem = ran.nextInt(2);
						if(ranItem == 1)
							map[bombs[0][0] + 1][bombs[0][1]] = ITEM;
						else
							map[bombs[0][0] + 1][bombs[0][1]] = 0;
					}
					if(bombs[0][0] > 0 && map[bombs[0][0] - 1][bombs[0][1]] == WALL) {
						int ranItem = ran.nextInt(2);
						if(ranItem == 1)
							map[bombs[0][0] - 1][bombs[0][1]] = ITEM;
						else
							map[bombs[0][0] - 1][bombs[0][1]] = 0;
					}
					if(bombs[0][1] < SIZE - 1 && map[bombs[0][0]][bombs[0][1] + 1] == WALL) {
						int ranItem = ran.nextInt(2);
						System.out.println(ranItem);
						if(ranItem == 1)
							map[bombs[0][0]][bombs[0][1] + 1] = ITEM;
						else
							map[bombs[0][0]][bombs[0][1] + 1] = 0;
					}
					if(bombs[0][1] > 0 && map[bombs[0][0]][bombs[0][1] - 1] == WALL) {
						int ranItem = ran.nextInt(2);
						if(ranItem == 1)
							map[bombs[0][0]][bombs[0][1] - 1] = ITEM;
						else
							map[bombs[0][0]][bombs[0][1] - 1] = 0;
					}
					
					if(bombs[0][0] == pY && bombs[0][1] == pX
							|| bombs[0][0] + 1 == pY && bombs[0][1] == pX
							|| bombs[0][0] - 1 == pY && bombs[0][1] == pX
							|| bombs[0][0] == pY && bombs[0][1] + 1 == pX
							|| bombs[0][0] == pY && bombs[0][1] - 1 == pX) {
						diePY = pY;
						diePX = pX;
						gameOver = true;
					}
					
					map[bombs[0][0]][bombs[0][1]] = 0;
					
					for(int i = 0; i < bombs.length; i++) {
						if(i < bombs.length - 1) {
							bombs[i][0] = bombs[i + 1][0];
							bombs[i][1] = bombs[i + 1][1];
						} else {
							bombs[i][0] = -1;
							bombs[i][1] = -1;
						}
					}
					
					bombCnt++;
				}
				
			} else {
				System.out.println("화면의 키중 하나를 입력하세요.");
			}
			
			if(map[pY][pX] == ITEM) {
				map[pY][pX] = 0;
				int[][] tempBomb = bombs;
				
				bombMax++;
				bombCnt++;
				bombs = new int[bombMax][2];
				
				for(int i = 0; i < bombs.length; i++) {
					if(i < bombs.length - 1) {
						bombs[i][0] = tempBomb[i][0];
						bombs[i][1] = tempBomb[i][1];
					} else {
						bombs[i][0] = -1;
						bombs[i][1] = -1;
					}
				}
				System.out.println("폭탄이 추가되었습니다.");
			}
		}
		
		scan.close();
	}
}