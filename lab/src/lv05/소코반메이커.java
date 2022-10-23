package lv05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class 소코반메이커 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		final int SIZE = 10;
		final int WALL = 9;
		final int PLAYER = 1;
		final int BALL = 2;
		final int GOAL = 3;
		final int GOALBALL = 5;
		
		int[][] map = new int[SIZE][SIZE];
		
		int pY = 0;
		int pX = 0;
		int goalCnt = 0; // GOAL 개수
		int ballCnt = 0; // BALL 개수
		
		boolean gameClear = false;
		
		String fileName = "sokoban.txt";
		File file = new File(fileName);
		FileWriter fw = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		while(true) {
			System.out.println("[메인 메뉴]");
			System.out.println("1. 맵 만들기");
			System.out.println("2. 게임 실행");
			System.out.println("3. 게임 종료");
			System.out.print("입력: ");
			int sel = scan.nextInt();
			
			if(sel == 1) {
				while(true) {
					System.out.println("1. 맵 새로 만들기");
					System.out.println("2. 맵 불러오기");
					System.out.println("3. 뒤로가기");
					System.out.print("입력: ");
					sel = scan.nextInt();
					
					if(sel == 1) {
						// 맵 초기화
						pY = 0;
						pX = 0;
						goalCnt = 0;
						ballCnt = 0;
						
						map = new int[SIZE][SIZE];				
						
					} else if(sel == 2) {
						if(file.exists()) {
							try {
								fr = new FileReader(file);
								br = new BufferedReader(fr);
								
								for(int i = 0; i < SIZE; i++) {
									String[] tempMap = br.readLine().split(", ");
									map[i] = new int[SIZE];
									for(int j = 0; j < SIZE; j++) {
										map[i][j] = Integer.parseInt(tempMap[j]);
									}
								}
								
								goalCnt = 0;
								ballCnt = 0;
								
								for(int i = 0; i < SIZE; i++) {
									for(int j = 0; j < SIZE; j++) {
										if(map[i][j] == PLAYER) {
											map[i][j] = 0;
											pY = i;
											pX = j;
										}
										if(map[i][j] == GOAL || map[i][j] == GOALBALL)
											goalCnt++;
										if(map[i][j] == BALL || map[i][j] == GOALBALL)
											ballCnt++;
									}
								}
								
								br.close();
								
								System.out.println("기존의 맵을 불러옵니다.");
								
							} catch(Exception e) {
								e.printStackTrace();
								System.err.println("맵 불러오기 실패!");
							}
							
						} else {
							System.out.println("불러올 맵이 없습니다.");
							continue;
						}
					} else if(sel == 3) {
						System.out.println("홈 화면으로 돌아갑니다.");
						break;
					} else {
						System.out.println("화면의 번호 중에 입력하세요.");
					}
					
					if(sel == 1 || sel == 2) {
						while(true) {
							System.out.println("┌──────────[sokoban]───────────┐");
							for(int i = 0; i < SIZE; i++) {
								System.out.print("│");
								for(int j = 0; j < SIZE; j++) {
									if(i == pY && j == pX) {
										System.out.print(" 8 ");
									} else if(map[i][j] == GOAL) {
										System.out.print(" * ");
									} else if(map[i][j] == BALL) {
										System.out.print(" ○ ");
									} else if(map[i][j] == GOALBALL) {
										System.out.print(" @ ");
									} else if(map[i][j] == WALL) {
										System.out.print(" ■ ");
									} else {
										System.out.print(" + ");
									}
								}
								System.out.println("│");
							}
							System.out.println("└──────────────────────────────┘");
							
							
							System.out.println("a(←) w(↑) d(→) s(↓)");
							System.out.println("g(골 설치) b(볼 설치) h(벽 설치)");
							System.out.println("n(저장) m(뒤로가기)");
							String move = scan.next();
							
							if(move.equals("a")) {
								if(pX == 0)
									System.out.println("이동할 수 없습니다.");
								else
									pX--;
								
							} else if(move.equals("w")) {
								if(pY == 0)
									System.out.println("이동할 수 없습니다.");
								else
									pY--;
								
							} else if(move.equals("d")) {
								if(pX == SIZE - 1)
									System.out.println("이동할 수 없습니다.");
								else
									pX++;
								
							} else if(move.equals("s")) {
								if(pY == SIZE - 1)
									System.out.println("이동할 수 없습니다.");
								else
									pY++;
								
							} else if(move.equals("g")) {
								// 동일 위치에 재입력시 삭제
								// BALL위치에 입력시 GOALBALL
								// GOALBALL위치에 입력시 BALL
								if(map[pY][pX] == GOAL) {
									map[pY][pX] = 0;
									goalCnt--;
								} else if(map[pY][pX] == BALL) {
									map[pY][pX] = GOALBALL;
									goalCnt++;
								} else if(map[pY][pX] == GOALBALL) {
									map[pY][pX] = BALL;
									goalCnt--;
								} else {
									map[pY][pX] = GOAL;
									goalCnt++;
								}
								
							} else if(move.equals("b")) {
								// 동일 위치에 재입력시 삭제
								// GOAL위치에 입력시 GOALBALL
								// GOALBALL위치에 입력시 GOAL
								if(map[pY][pX] == BALL) {
									map[pY][pX] = 0;
									ballCnt--;
								} else if(map[pY][pX] == GOAL) {
									map[pY][pX] = GOALBALL;
									ballCnt++;
								} else if(map[pY][pX] == GOALBALL) {
									map[pY][pX] = GOAL;
									ballCnt--;
								} else {
									map[pY][pX] = BALL;
									ballCnt++;
								}
								
							} else if(move.equals("h")) {
								// 동일 위치에 재입력시 삭제
								if(map[pY][pX] == WALL)
									map[pY][pX] = 0;
								else
									map[pY][pX] = WALL;
								
							} else if(move.equals("n")) {
								if(goalCnt != ballCnt) {
									System.out.println("GOAL개수와 BALL개수를 맞춰주세요.");
								} else {
									try {
										fw = new FileWriter(file);
										
										String mapData = "";
										
										for(int i = 0; i < SIZE; i++) {
											for(int j = 0; j < SIZE; j++) {
												if(i == pY && j == pX)
													mapData += PLAYER;
												else
													mapData += map[i][j];
												
												if(j < SIZE - 1)
													mapData += ", ";
												else if(i < SIZE - 1)
													mapData += "\n";
											}
										}
										
										fw.write(mapData);
										
										fw.close();
										
										System.out.println("맵이 저장되었습니다.");
										
									} catch(Exception e) {
										e.printStackTrace();
										System.err.println("맵 저장 실패!");
									}
								}
								
							} else if(move.equals("m")) {
								System.out.println("맵 만들기 메뉴로 돌아갑니다.");
								break;
							} else {
								System.out.println("화면의 번호 중에 입력하세요.");
							}
						}
					}
				}
				
			} else if(sel == 2) {
				if(file.exists()) {
					try {
						fr = new FileReader(file);
						br = new BufferedReader(fr);
						
						for(int i = 0; i < SIZE; i++) {
							String[] tempMap = br.readLine().split(", ");
							map[i] = new int[SIZE];
							for(int j = 0; j < SIZE; j++) {
								map[i][j] = Integer.parseInt(tempMap[j]);
							}
						}
						
						goalCnt = 0;
						
						// 플레이어 위치 저장, GOAL 개수세기   
						for(int i = 0; i < SIZE; i++) {
							for(int j = 0; j < SIZE; j++) {
								if(map[i][j] == PLAYER) {
									map[i][j] = 0;
									pY = i;
									pX = j;
								}
								if(map[i][j] == GOAL || map[i][j] == GOALBALL)
									goalCnt++;
							}
						}						
						
						br.close();
						
						System.out.println("게임을 실행합니다.");
						
					} catch(Exception e) {
						e.printStackTrace();
						System.err.println("게임 실행 실패!");
					}
					
					while(true) {
						System.out.println("┌──────────[sokoban]───────────┐");
						for(int i = 0; i < SIZE; i++) {
							System.out.print("│");
							for(int j = 0; j < SIZE; j++) {
								if(i == pY && j == pX) {
									System.out.print(" 8 ");
								} else if(map[i][j] == GOAL) {
									System.out.print(" * ");
								} else if(map[i][j] == BALL) {
									System.out.print(" ○ ");
								} else if(map[i][j] == GOALBALL) {
									System.out.print(" @ ");
								} else if(map[i][j] == WALL) {
									System.out.print(" ■ ");
								} else {
									System.out.print(" + ");
								}
							}
							System.out.println("│");
						}
						System.out.println("└──────────────────────────────┘");  
						
						// 골인 볼 개수 세기
						int goalBallCnt = 0;
						for(int i = 0; i < SIZE; i++)
							for(int j = 0; j < SIZE; j++)
								if(map[i][j] == GOALBALL)
									goalBallCnt++;
						
						// 게임 클리어
						if(goalBallCnt == goalCnt) {
							System.out.println("GAME CLEAR!");
							System.out.println("게임을 종료할까요?");
							System.out.println("1. 예");
							System.out.println("2. 아니오(홈 화면으로 돌아감)");
							sel = scan.nextInt();
							
							while(sel != 1 && sel != 2) {
								System.out.println("화면의 번호 중에 입력하세요.");
								sel = scan.nextInt();
							}
							
							if(sel == 1) {
								System.out.println("게임을 종료합니다.");
								gameClear = true;
								break;
							} else if(sel == 2) {
								System.out.println("홈 화면으로 돌아갑니다.");
								break;
							}
						}
						
						System.out.println("a(←) w(↑) d(→) s(↓)");
						System.out.println("m(뒤로가기)");
						String move = scan.next();
						
						if(move.equals("a")) {
							if(pX == 0 || map[pY][pX - 1] == WALL)
								System.out.println("이동할 수 없습니다.");
							else {
								if(map[pY][pX - 1] == BALL || map[pY][pX - 1] == GOALBALL) {
									if(pX - 1 == 0 || map[pY][pX - 2] == WALL
											|| map[pY][pX - 2] == BALL || map[pY][pX - 2] == GOALBALL)
										System.out.println("이동할 수 없습니다.");
									else {
										if(map[pY][pX - 1] == GOALBALL)
											map[pY][pX - 1] = GOAL;
										else
											map[pY][pX - 1] = 0;
										
										if(map[pY][pX - 2] == GOAL)
											map[pY][pX - 2] = GOALBALL;
										else
											map[pY][pX - 2] = BALL;
										
										pX--;
									}
								} else {
									pX--;									
								}
							}
							
						} else if(move.equals("w")) {
							if(pY == 0 || map[pY - 1][pX] == WALL)
								System.out.println("이동할 수 없습니다.");
							else {
								if(map[pY - 1][pX] == BALL || map[pY - 1][pX] == GOALBALL) {
									if(pY - 1 == 0 || map[pY - 2][pX] == WALL
											|| map[pY - 2][pX] == BALL || map[pY - 2][pX] == GOALBALL)
										System.out.println("이동할 수 없습니다.");
									else {										
										if(map[pY - 1][pX] == GOALBALL)
											map[pY - 1][pX] = GOAL;
										else
											map[pY - 1][pX] = 0;
										
										if(map[pY - 2][pX] == GOAL)
											map[pY - 2][pX] = GOALBALL;
										else
											map[pY - 2][pX] = BALL;
										
										pY--;
									}
								} else {
									pY--;
								}
							}
							
						} else if(move.equals("d")) {
							if(pX == SIZE - 1 || map[pY][pX + 1] == WALL)
								System.out.println("이동할 수 없습니다.");
							else {
								if(map[pY][pX + 1] == BALL || map[pY][pX + 1] == GOALBALL) {
									if(pX + 1 == SIZE - 1 || map[pY][pX + 2] == WALL
											|| map[pY][pX + 2] == BALL || map[pY][pX + 2] == GOALBALL)
										System.out.println("이동할 수 없습니다.");
									else {
										if(map[pY][pX + 1] == GOALBALL)
											map[pY][pX + 1] = GOAL;
										else
											map[pY][pX + 1] = 0;
										
										if(map[pY][pX + 2] == GOAL)
											map[pY][pX + 2] = GOALBALL;
										else
											map[pY][pX + 2] = BALL;
										
										pX++;
									}
								} else {
									pX++;									
								}
							}
							
						} else if(move.equals("s")) {
							if(pY == SIZE - 1 || map[pY + 1][pX] == WALL)
								System.out.println("이동할 수 없습니다.");
							else {
								if(map[pY + 1][pX] == BALL || map[pY + 1][pX] == GOALBALL) {
									if(pY + 1 == SIZE - 1 || map[pY + 2][pX] == WALL
											|| map[pY + 2][pX] == BALL || map[pY + 2][pX] == GOALBALL)
										System.out.println("이동할 수 없습니다.");
									else {										
										if(map[pY + 1][pX] == GOALBALL)
											map[pY + 1][pX] = GOAL;
										else
											map[pY + 1][pX] = 0;
										
										if(map[pY + 2][pX] == GOAL)
											map[pY + 2][pX] = GOALBALL;
										else
											map[pY + 2][pX] = BALL;
										
										pY++;
									}
								} else {
									pY++;
								}
							}
							
						} else if(move.equals("m")) {
							try {
								fw = new FileWriter(file);
								
								String mapData = "";
								
								for(int i = 0; i < SIZE; i++) {
									for(int j = 0; j < SIZE; j++) {
										if(i == pY && j == pX)
											mapData += PLAYER;
										else
											mapData += map[i][j];
										
										if(j < SIZE - 1)
											mapData += ", ";
										else if(i < SIZE - 1)
											mapData += "\n";
									}
								}
								
								fw.write(mapData);
								
								fw.close();
								
								System.out.println("게임이 저장되었습니다.");
								System.out.println("홈 화면으로 돌아갑니다.");
								
							} catch(Exception e) {
								e.printStackTrace();
								System.err.println("게임 저장 실패!");
							}
							break;
							
						} else {
							System.out.println("화면의 번호 중에 입력하세요.");
						}
					}
					
				} else {
					System.out.println("실행할 맵이 없습니다.");
				}
				
			} else if(sel == 3) {
				System.out.println("게임을 종료합니다.");
				break;
				
			} else {
				System.out.println("화면의 번호 중에 입력하세요.");
			}
			
			if(gameClear)
				break;
		}
		
		scan.close();
	}
}
