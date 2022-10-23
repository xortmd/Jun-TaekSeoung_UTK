package lv04;

import java.util.Random;
import java.util.Scanner;

public class 소코반2 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Random ran = new Random();

		final int SIZE = 15;
//		final int PLAYER = 2;
//		final int BALL = 3;
//		final int GOAL = 7;
		final int WALL = 9;
		
		int[][] map = new int[SIZE][SIZE];
		
		int wallCount = 0;
		int ballCount = 0;
		int pY = 0;
		int pX = 0;
//		int ballY = 0;
//		int ballX = 0;
//		int goalY = 0;
//		int goalX = 0;
		int ranNum;
		
		// PLAYER 랜덤 자리 지정
		ranNum = ran.nextInt(SIZE*SIZE);
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if(i*SIZE + j == ranNum) {
					pY = i;
					pX = j;
				}				
			}
		}
		
		// BALL 개수 입력
		System.out.print(">>> 공의 개수를 입력하세요.(1 ~ 3): ");
		ballCount = scan.nextInt();
		
		// 너무 많은 BALL 금지
		while(ballCount < 1 || 3 < ballCount) {
			System.out.println("1 ~ 3의 숫자를 입력하세요. ");
			ballCount = scan.nextInt();
		}
		
		int goalCount = ballCount;
		int[][] ballArr = new int[ballCount][2]; // ballArr[][0]: ballY, ballArr[1]: ballX
		int[][] goalArr = new int[goalCount][2]; // goalArr[][0]: goalY, goalArr[1]: goalX
		
		// BALL 랜덤 자리 지정
		while(ballCount > 0) {
			// BALL 배열에 하나씩 담기
			for(int k = 0; k < ballArr.length; k++) {
				ranNum = ran.nextInt(SIZE*SIZE);
				for(int i = 0; i < SIZE; i++) {
					boolean check = false;
					for(int j = 0; j < SIZE; j++) {
						if(i*SIZE + j == ranNum) {
							if(i != pY && j != pX && i != 0 && j != 0 && i != SIZE - 1 && j != SIZE - 1) {
								check = true;
								// BALL 배열 원소 중복 확인
								for(int l = 0; l < k; l++) {
									if(ballArr[l][0] == ballArr[k][0] && ballArr[l][1] == ballArr[k][1]) {
										check = false;
									}
								}
							}
							if(check) {
								ballArr[k][0] = i;
								ballArr[k][1] = j;
								ballCount--;
							} else {
								ranNum = ran.nextInt(SIZE*SIZE);
								i = -1;
								break;
							}
						}
					}
				}
			}
		}
		
		// GOAL 랜덤 자리 지정
		while(goalCount > 0) {
			// GOAL 배열에 하나씩 담기
			for(int k = 0; k < goalArr.length; k++) {
				ranNum = ran.nextInt(SIZE*SIZE);
				for(int i = 0; i < SIZE; i++) {
					boolean check = false;
					for(int j = 0; j < SIZE; j++) {
						if(i*SIZE + j == ranNum) {
							if(i != pY && j != pX) {
								check = true;
								// GOAL 배열 원소 중복 확인
								for(int l = 0; l < k; l++) {
									if(goalArr[l][0] == goalArr[k][0] && goalArr[l][1] == goalArr[k][1]) {
										check = false;
									}
								}
								// BALL 배열 원소 중복 확인
								for(int l = 0; l < ballArr.length; l++) {
									if(ballArr[l][0] == i && ballArr[l][1] == j) {
										check = false;
									}
								}
							} 
							if(check) {
								goalArr[k][0] = i;
								goalArr[k][1] = j;
								goalCount--;
							} else {
								ranNum = ran.nextInt(SIZE*SIZE);
								i = -1;
								break;
							}
						}
					}
				}
			}
		}		
		
		// 검사용
//		System.out.println("p = " + pY + " " + pX);
//		for(int i = 0; i < ballArr.length; i++) {
//			System.out.print("ball = ");
//			for(int j = 0; j < ballArr[i].length; j++) {
//				System.out.print(ballArr[i][j] + " ");
//			}
//			System.out.println();
//		}
//		for(int i = 0; i < goalArr.length; i++) {
//			System.out.print("goal = ");
//			for(int j = 0; j < goalArr[i].length; j++) {
//				System.out.print(goalArr[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		// 벽 개수 입력
		System.out.print(">>> 설치할 벽의 개수를 입력하세요.(0 ~ 20): ");
		wallCount = scan.nextInt();
		
		// 너무 많은 벽 금지
		while(wallCount < 0 || 20 < wallCount) {
			System.out.println("0 ~ 20의 숫자를 입력하세요. ");
			wallCount = scan.nextInt();
		}
		
		// 벽 랜덤 생성
		while(wallCount > 0) {
			ranNum = ran.nextInt(SIZE*SIZE);
			for(int i = 0; i < SIZE; i++) {
				boolean check = false;
				for(int j = 0; j < SIZE; j++) {
					if(i*SIZE + j == ranNum && map[i][j] == 0 && i != pY && j != pX) {
						check = true;
						// BALL, GOAL 중복 확인
						for(int k = 0; k < ballArr.length; k++) {
							if((ballArr[k][0] - 1 <= i && i <= ballArr[k][0] + 1) &&
									(ballArr[k][1] - 1 <= j && j <= ballArr[k][1] + 1) ||
									(goalArr[k][0] - 1 <= i && i <= goalArr[k][0] + 1) &&
									(goalArr[k][1] - 1 <= j && j <= goalArr[k][1] + 1)) {
								check = false;
								break;
							}
						}
						if(check) {
							map[i][j] = WALL;
							wallCount--;
						} else {
							ranNum = ran.nextInt(SIZE*SIZE);
							i = -1;
							break;
						}
					}
				}
			}
		}
		
		// 게임 실행
		while(true) {
			// 화면 출력
			System.out.println("┌─┬──┬──┬──┬──┬──┬─[Sokoban]─┬──┬──┬──┬──┬──┬─┐");
			for(int i = 0; i < SIZE; i++) {
				System.out.print("├");
				for(int j = 0; j < SIZE; j++) {
					boolean check = false;
					boolean checkBallGoal = false;
					if(i == pY && j == pX) {
						System.out.print(" ♂ ");
					} else if(map[i][j] == WALL) {
						System.out.print(" ■ ");
					} else {
						for(int k = 0; k < ballArr.length; k++) {
							if(ballArr[k][0] == i && ballArr[k][1] == j) {
								for(int l = 0; l < goalArr.length; l++) {
									if(goalArr[l][0] == i && goalArr[l][1] == j) {
										System.out.print(" @ ");
										checkBallGoal = true;
									}
								}
							}
						}
						if(!checkBallGoal) {
							for(int k = 0; k < ballArr.length; k++) {
								if(ballArr[k][0] == i && ballArr[k][1] == j) {
									System.out.print(" ○ ");
									check = true;
								}
							}
							for(int k = 0; k < goalArr.length; k++) {
								if(goalArr[k][0] == i && goalArr[k][1] == j) {
									System.out.print(" # ");
									check = true;
								}
							}
							if(!check)
								System.out.print("─┼─");
							
						}
					}
				}
				System.out.print("┤");
				System.out.println();
			}
			System.out.println("└─┴──┴──┴──┴──┴──┴──┴──┴──┴──┴──┴──┴──┴──┴──┴─┘");
			
			// 게임 클리어
			int clearCount = 0;
			for(int i = 0; i < ballArr.length; i++) {
				for(int j = 0; j < goalArr.length; j++) {
					if(ballArr[i][0] == goalArr[j][0] && ballArr[i][1] == goalArr[j][1]) {
						clearCount++;
					}
				}
			}
			if(clearCount == ballArr.length) {
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
				} else {
					boolean check = true;
					boolean checkBall = false;
					int idx = -1;
					for(int i = 0; i < ballArr.length; i++) {
						if(ballArr[i][0] == pY && ballArr[i][1] == pX - 1) {
							checkBall = true;
							idx = i;
							if(ballArr[i][1] - 1 < 0 || map[ballArr[i][0]][ballArr[i][1] - 1] == WALL) {
								System.out.println("이동할 수 없습니다.");
								check = false;
								checkBall = false;
							}
						}
					}
					if(checkBall) {
						ballArr[idx][1]--;
					}	
					if(check) {
						pX--;						
					}
				}
				
			} else if(move.equals("w")) {
				if(pY - 1 < 0 || map[pY - 1][pX] == WALL) {
					System.out.println("이동할 수 없습니다.");
					continue;
				} else {
					boolean check = true;
					boolean checkBall = false;
					int idx = -1;
					for(int i = 0; i < ballArr.length; i++) {
						if(ballArr[i][0] == pY - 1 && ballArr[i][1] == pX) {
							checkBall = true;
							idx = i;
							if(ballArr[i][0] - 1 < 0 || map[ballArr[i][0] - 1][ballArr[i][1]] == WALL) {
								System.out.println("이동할 수 없습니다.");
								check = false;
								checkBall = false;
							}
						}
					}
					if(checkBall) {
						ballArr[idx][0]--;
					}	
					if(check) {
						pY--;						
					}
				}
				
			
			} else if(move.equals("s")) {
				if(pY + 1 > SIZE - 1 || map[pY + 1][pX] == WALL) {
					System.out.println("이동할 수 없습니다.");
					continue;
				} else {
					boolean check = true;
					boolean checkBall = false;
					int idx = -1;
					for(int i = 0; i < ballArr.length; i++) {
						if(ballArr[i][0] == pY + 1 && ballArr[i][1] == pX) {
							checkBall = true;
							idx = i;
							if(ballArr[i][0] + 1 > SIZE - 1 || map[ballArr[i][0] + 1][ballArr[i][1]] == WALL) {
								System.out.println("이동할 수 없습니다.");
								check = false;
								checkBall = false;
							}
						}
					}
					if(checkBall) {
						ballArr[idx][0]++;
					}	
					if(check) {
						pY++;						
					}
				}

			} else if(move.equals("d")) {
				if(pX + 1 > SIZE - 1 || map[pY][pX + 1] == WALL) {
					System.out.println("이동할 수 없습니다.");
					continue;
				} else {
					boolean check = true;
					boolean checkBall = false;
					int idx = -1;
					for(int i = 0; i < ballArr.length; i++) {
						if(ballArr[i][0] == pY && ballArr[i][1] == pX + 1) {
							checkBall = true;
							idx = i;
							if(ballArr[i][1] + 1 > SIZE - 1 || map[ballArr[i][0]][ballArr[i][1] + 1] == WALL) {
								System.out.println("이동할 수 없습니다.");
								check = false;
								checkBall = false;
							}
						}
					}
					if(checkBall) {
						ballArr[idx][1]++;
					}	
					if(check) {
						pX++;						
					}
				}
			
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
