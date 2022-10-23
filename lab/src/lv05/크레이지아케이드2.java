package lv05;

import java.util.Random;
import java.util.Scanner;

public class 크레이지아케이드2 {
	
	public static void main(String[] args) {
		Random ran = new Random();
		Scanner scan = new Scanner(System.in);
		
		final int SIZE = 7;
		final int WALL = 3;
		final int ITEM = 4;
		final int BOMB = 9;
		final int BOOM = 10;
		final int BOOMWALL = 11;
		
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
			// 화면 출력
			System.out.println("┌───[crazy arcade]────┐");
			for(int i = 0; i < SIZE; i++) {
				System.out.print("│");
				for(int j = 0; j < SIZE; j++) {
					if(i == diePY && j == diePX) {
						System.out.print(" ∞ ");
					} else if(i == pY && j == pX) {
						System.out.print(" 8 ");
					} else if(map[i][j] == WALL) {
						System.out.print(" ■ ");
					} else if(map[i][j] == ITEM) {
						System.out.print(" * ");
					} else if(map[i][j] == BOMB) {
						System.out.print(" ● ");
					} else {
						System.out.print(" + ");
					}
				}
				System.out.println("│");
			}
			System.out.println("└─────────────────────┘");
			
			// 플레이어 사망
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
					// 폭파시 폭파자리 체크
					if(bombs[0][0] < SIZE - 1) {
						if(map[bombs[0][0] + 1][bombs[0][1]] == WALL)
							map[bombs[0][0] + 1][bombs[0][1]] = BOOMWALL;
						else
							map[bombs[0][0] + 1][bombs[0][1]] = BOOM;
					}
					
					if(bombs[0][0] > 0) {
						if(map[bombs[0][0] - 1][bombs[0][1]] == WALL)
							map[bombs[0][0] - 1][bombs[0][1]] = BOOMWALL;
						else
							map[bombs[0][0] - 1][bombs[0][1]] = BOOM;
					}
					
					if(bombs[0][1] < SIZE - 1) {
						if(map[bombs[0][0]][bombs[0][1] + 1] == WALL)
							map[bombs[0][0]][bombs[0][1] + 1] = BOOMWALL;
						else
							map[bombs[0][0]][bombs[0][1] + 1] = BOOM;
					}
					
					if(bombs[0][1] > 0) {
						if(map[bombs[0][0]][bombs[0][1] - 1] == WALL)
							map[bombs[0][0]][bombs[0][1] - 1] = BOOMWALL;
						else
							map[bombs[0][0]][bombs[0][1] - 1] = BOOM;
					}
					
					map[bombs[0][0]][bombs[0][1]] = BOOM;
					bombs[0][0] = -1;
					bombs[0][1] = -1;
					bombCnt++;
					
					for(int i = 0; i < bombs.length; i++) {
						// 폭파자리에 폭탄이 있는지 확인
						if(bombs[i][0] != -1 && map[bombs[i][0]][bombs[i][1]] == BOOM) {
							if(bombs[i][0] < SIZE - 1) {
								if(map[bombs[i][0] + 1][bombs[i][1]] == WALL)
									map[bombs[i][0] + 1][bombs[i][1]] = BOOMWALL;
								else
									map[bombs[i][0] + 1][bombs[i][1]] = BOOM;
							}
							
							if(bombs[i][0] > 0) {
								if(map[bombs[i][0] - 1][bombs[i][1]] == WALL)
									map[bombs[i][0] - 1][bombs[i][1]] = BOOMWALL;
								else
									map[bombs[i][0] - 1][bombs[i][1]] = BOOM;
							}
							
							if(bombs[i][1] < SIZE - 1) {
								if(map[bombs[i][0]][bombs[i][1] + 1] == WALL)
									map[bombs[i][0]][bombs[i][1] + 1] = BOOMWALL;
								else
									map[bombs[i][0]][bombs[i][1] + 1] = BOOM;
							}
							
							if(bombs[i][1] > 0) {
								if(map[bombs[i][0]][bombs[i][1] - 1] == WALL)
									map[bombs[i][0]][bombs[i][1] - 1] = BOOMWALL;
								else
									map[bombs[i][0]][bombs[i][1] - 1] = BOOM;
							}
							
							map[bombs[i][0]][bombs[i][1]] = BOOM;
							bombs[i][0] = -1;
							bombs[i][1] = -1;
							
							i = -1;
							bombCnt++;
						} 
					}
					
					// 아이템 생성
					for(int i = 0; i < map.length; i++) {
						for(int j = 0; j < map[i].length; j++) {
							if(map[i][j] == BOOMWALL) {
								int ranItem = ran.nextInt(2);
								if(ranItem == 1)
									map[i][j] = ITEM;
								else
									map[i][j] = 0;
							}
							if(i == pY && j == pX && map[i][j] == BOOM) {
								diePY = i;
								diePX = j;
								gameOver = true;
							}
						}
					}
					
					// 폭탄 배열 땡기기
					int idx = 0;
					for(int i = 0; i < bombs.length; i++) {
						if(bombs[i][0] != -1) {
							int temp = bombs[i][0];
							bombs[i][0] = bombs[idx][0];
							bombs[idx][0] = temp;
							temp = bombs[i][1];
							bombs[i][1] = bombs[idx][1];
							bombs[idx][1] = temp;
							idx++;
						}
					}
					
					// 맵 원래대로
					for(int i = 0; i < map.length; i++)
						for(int j = 0; j < map[i].length; j++)
							if(map[i][j] == BOOM || map[i][j] == BOOMWALL)
								map[i][j] = 0;
							
				}
				
			} else {
				System.out.println("화면의 키중 하나를 입력하세요.");
			}
			
			// 아이템 획득
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
			
			// 검사용
//			for(int i = 0; i < bombs.length; i++) {
//				for(int j = 0; j < bombs[i].length; j++) {
//					System.out.print(bombs[i][j] + " ");
//				}
//				System.out.println();
//			}
		}
		
		scan.close();
	}
}