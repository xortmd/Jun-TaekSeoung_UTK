package lv04;

import java.util.Random;
import java.util.Scanner;

public class 소코반 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Random ran = new Random();

		final int SIZE = 7;
//		final int PLAYER = 2;
//		final int BALL = 3;
//		final int GOAL = 7;
		final int WALL = 9;
		
		int[][] map = new int[SIZE][SIZE];
		
		int wallCount = 0;
		int pY = 0;
		int pX = 0;
		int ballY = 0;
		int ballX = 0;
		int goalY = 0;
		int goalX = 0;
		int ranNum;
		boolean clear = false;
		
		
		// 벽 개수 입력
		System.out.print(">>> 설치할 벽의 개수를 입력하세요.(0 ~ 10): ");
		wallCount = scan.nextInt();
		
		// 너무 많은 벽 금지
		while(wallCount < 0 || 10 < wallCount) {
			System.out.println("0 ~ 10의 숫자를 입력하세요. ");
			wallCount = scan.nextInt();
		}
		
		// 벽 랜덤 생성
		while(wallCount > 0) {
			ranNum = ran.nextInt(SIZE*SIZE);
			for(int i = 0; i < SIZE; i++) {
				for(int j = 0; j < SIZE; j++) {
					if(i*SIZE + j == ranNum && map[i][j] == 0) {
						map[i][j] = WALL;
						wallCount--;
					}
				}
			}
		}
		
		// PLAYER 랜덤 자리 지정
		ranNum = ran.nextInt(SIZE*SIZE);
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if(i*SIZE + j == ranNum) {
					if(map[i][j] != WALL) {
						pY = i;
						pX = j;
					} else {
						ranNum = ran.nextInt(SIZE*SIZE);
						i = -1;
						break;
					}
				}				
			}
		}
		
		// BALL 랜덤 자리 지정
		ranNum = ran.nextInt(SIZE*SIZE);
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if(i*SIZE + j == ranNum) {
					if(map[i][j] != WALL && i != pY && j != pX) {
						ballY = i;
						ballX = j;
					} else {
						ranNum = ran.nextInt(SIZE*SIZE);
						i = -1;
						break;
					}
				}
			}
		}
		
		// GOAL 랜덤 자리 지정
		ranNum = ran.nextInt(SIZE*SIZE);
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if(i*SIZE + j == ranNum) {
					if(map[i][j] != WALL && i != pY && j != pX && i != ballY && j != ballX) {
						goalY = i;
						goalX = j;
					} else {
						ranNum = ran.nextInt(SIZE*SIZE);
						i = -1;
						break;
					}
				}
			}
		}
		
		// 게임 실행
		while(true) {
			// 화면 출력
			System.out.println("┌──────[Sokoban]──────┐");
			for(int i = 0; i < SIZE; i++) {
				System.out.print("│");
				for(int j = 0; j < SIZE; j++) {
					if(i == pY && j == pX) {
						System.out.print(" ♂ ");
					} else if(i == ballY && j == ballX && i == goalY && j == goalX) {	
						System.out.print(" @ ");
						clear = true;
					} else if(i == ballY && j == ballX) {
						System.out.print(" ○ ");
					} else if(i == goalY && j == goalX) {
						System.out.print(" * ");
					} else if(map[i][j] == WALL) {
						System.out.print(" ■ ");
					} else {
						System.out.print(" □ ");
					}
				}
				System.out.print("│");
				System.out.println();
			}
			System.out.println("└─────────────────────┘");
			
			// 게임 클리어
			if(clear) {
				System.out.println("CLEAR!");
				break;
			}
			
			// 플레이어 이동 입력
			System.out.println("게임 재시작(Ctrl + F11)");
			System.out.println("게임 포기(gg)");
			System.out.println("←(a) ↑(w) ↓(s) →(d)");
			System.out.print("입력: ");
			String move = scan.next();
			
			// 이동
			if(move.equals("a")) {
				if(pX - 1 < 0 || map[pY][pX - 1] == WALL) {
					System.out.println("이동할 수 없습니다.");
					continue;
				} else if(pY == ballY && pX - 1 == ballX) {
					if(ballX - 1 < 0 || map[ballY][ballX - 1] == WALL) {
						System.out.println("이동할 수 없습니다.");
						continue;
					}
					ballX--;
				}
				pX--;
				
			} else if(move.equals("w")) {
				if(pY - 1 < 0 || map[pY - 1][pX] == WALL) {
					System.out.println("이동할 수 없습니다.");
					continue;
				} else if(pY - 1 == ballY && pX == ballX) {
					if(ballY - 1 < 0 || map[ballY - 1][ballX] == WALL) {
						System.out.println("이동할 수 없습니다.");
						continue;
					}
					ballY--;
				}
				pY--;

			} else if(move.equals("s")) {
				if(pY + 1 > SIZE - 1 || map[pY + 1][pX] == WALL) {
					System.out.println("이동할 수 없습니다.");
					continue;
				} else if(pY + 1 == ballY && pX == ballX) {
					if(ballY + 1 > SIZE - 1 || map[ballY + 1][ballX] == WALL) {
						System.out.println("이동할 수 없습니다.");
						continue;
					}
					ballY++;
				}
				pY++;

			} else if(move.equals("d")) {
				if(pX + 1 > SIZE - 1 || map[pY][pX + 1] == WALL) {
					System.out.println("이동할 수 없습니다.");
					continue;
				} else if(pY == ballY && pX + 1 == ballX) {
					if(ballX + 1 > SIZE - 1 || map[ballY][ballX + 1] == WALL) {
						System.out.println("이동할 수 없습니다.");
						continue;
					}
					ballX++;
				}
				pX++;
			
			// 게임 포기
			} else if(move.equals("gg")){
				System.out.println("LOSE!");
				break;
			} else {
				System.out.println("화면의 키 중에 입력하세요.");
			}		
		}
		scan.close();
	}
}
