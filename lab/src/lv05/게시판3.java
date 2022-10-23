package lv05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class 게시판3 {
	/*
	 * # 콘솔 게시판
	 * 1. [이전] 또는 [이후] 버튼을 누르면 페이지 번호가 변경된다.
	 * 2. 현재 페이지 번호에 해당되는 게시글만 볼 수 있다.
	 * 3. 2차원 배열 board에 0열에는 제목을 1열에는 게시글의 내용을 저장한다.
	 * 4. 게시글을 추가하고 삭제할 때마다 파일 입출력을 통해 데이터가 바로바로 저장된다.
	 * 5. 실행시 저장되어 있는 파일이 존재한다면 바로 파일을 불러오도록 설계한다.
	 */
	// String input = sc.nextLine()
	// ln -> enter 버리기 
	// sc.nextLine() 
	// . 마침표 -> 입력 루프 종료 
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String fileName = "board3.txt";
		File file = new File(fileName);
		FileWriter fw = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		String[][] board = null;
		String[][] tempBoardArr = null;
		int count = 0; // 전체 게시글 수
		int pageSize = 5; // 한 페이지에 보여줄 게시글 수
		int curPageNum = 1; // 현재 페이지 번호
		int pageCount = 1; // 전체 페이지 개수
		int startRow = 1; // 현재 페이지의 게시글 시작 번호
		int endRow = startRow + pageSize - 1; // 현재 페이지의 게시글 마지막 번호
		
		while(true) {
			if(file.exists()) {
				try {
					fr = new FileReader(file);
					br = new BufferedReader(fr);
					
					count = 0;
					while(true) { // br.ready() 활용
						String tempData = br.readLine();
						if(tempData == null)
							break;
						count++;
					}
					
					if(count != 0) {
						board = new String[count][2];
						pageCount = (count - 1)/pageSize + 1;
						
						FileReader fr2 = new FileReader(file);
						BufferedReader br2 = new BufferedReader(fr2);
						
						for(int i = 0; i < count; i++) {
							String[] tempBoard = br2.readLine().split(", ");
							board[i][0] = tempBoard[0];
							
							board[i][1] = "";
							String brCheck = tempBoard[1];
							String[] brCheckArr = brCheck.split("<br>");
							
							for(int j = 0; j < brCheckArr.length; j++) {
								board[i][1] += brCheckArr[j];
								if(j < brCheckArr.length - 1)
									board[i][1] += "\n";
							}
						}
						
						tempBoardArr = board;
						
						fr2.close();
						br2.close();
					}
					
					fr.close();
					br.close();
					
				} catch(Exception e) {
					e.printStackTrace();
					System.err.println("파일 불러오기 실패!");
				}
				
				System.out.println("=====[게시판]=====");
				if(board == null) {
					System.out.println("(비어 있음)");
				} else {
					for(int i = startRow; i <= endRow; i++) {
						if(i > board.length)
							break;
						System.out.printf("%2d. %s\n", i,board[i - 1][0]);
					}
					System.out.println("현재 페이지/전체 페이지: " + curPageNum + "/" + pageCount);
				}
				System.out.println("================");
				
				System.out.println("[1] 이전");
				System.out.println("[2] 이후");
				System.out.println("[3] 추가하기");
				System.out.println("[4] 수정하기");
				System.out.println("[5] 삭제하기");
				System.out.println("[6] 내용확인");
				System.out.println("[7] 종료");
				System.out.print("메뉴 선택: ");
				int sel = scan.nextInt();
				
				if(sel == 1) {
					if(curPageNum == 1) {
						System.out.println("현재 시작 페이지입니다.");
					} else {
						startRow -= pageSize;
						endRow -= pageSize;
						curPageNum--;
					}
					
				} else if(sel == 2) {
					if(curPageNum == pageCount) {
						System.out.println("현재 마지막 페이지입니다.");
					} else {
						startRow += pageSize;
						endRow += pageSize;
						curPageNum++;
					}
					
				} else if(sel == 3) {
					System.out.print("추가할 제목 입력: ");
					String title = scan.next();
					
					scan.nextLine();
					String content = "";
					
					System.out.print("내용 입력: ");
					while(true) {
						content += scan.nextLine();
						if(content.charAt(content.length() - 1) == '.')
							break;
						content += "\n";
					}
					
					count++;
					board = new String[count][2];
					
					for(int i = 0; i < count; i++) {
						board[i][0] = tempBoardArr[i][0];
						board[i][1] = tempBoardArr[i][1];
					}
					
					board[count - 1][0] = title;
					board[count - 1][1] = content;
					
				} else if((sel == 4 || sel == 5 || sel == 6) && board == null) {
					System.out.println("게시글이 존재하지 않습니다.");
					continue;
					
				} else if(sel == 4) {
					System.out.print("수정할 제목 번호 입력: ");
					int titleIdx = scan.nextInt();
					
					while(titleIdx < 1 || board.length < titleIdx) {
						System.out.println("번호가 범위를 벗어났습니다.");
						System.out.print("다시 입력: ");
						titleIdx = scan.nextInt();
					}
					
					System.out.print("수정할 제목 입력: ");
					String title = scan.next();
					
					scan.nextLine();
					String content = "";
					
					System.out.print("내용 입력: ");
					while(true) {
						content += scan.nextLine();
						if(content.charAt(content.length() - 1) == '.')
							break;
						content += "\n";
					}
					
					board[titleIdx - 1][0] = title;
					board[titleIdx - 1][1] = content;
					
				} else if(sel == 5) {
					System.out.print("삭제할 제목 번호 입력: ");
					int titleIdx = scan.nextInt();
					
					while(titleIdx < 1 || board.length < titleIdx) {
						System.out.println("번호가 범위를 벗어났습니다.");
						System.out.print("다시 입력: ");
						titleIdx = scan.nextInt();
					}
					
					count--;
					
					if(count != 0) {
						board = new String[count][2];
						
						int idx = 0;
						for(int i = 0; i < count; i++) {
							board[i][0] = tempBoardArr[idx][0];
							board[i][1] = tempBoardArr[idx][1];
							if(i == titleIdx - 1)
								idx++;
							idx++;
						}
						
					} else {
						board = null;
					}
					
				} else if(sel == 6) {
					System.out.print("제목 번호 입력: ");
					int titleIdx = scan.nextInt();
					
					while(titleIdx < startRow || endRow < titleIdx || board.length < titleIdx) {
						System.out.println("화면의 번호중에 입력하세요.");
						System.out.print("다시 입력: ");
						titleIdx = scan.nextInt();
					}
					
					System.out.println("=====[게시글]=====");
					System.out.println("제목: " + board[titleIdx - 1][0]);
					System.out.println("내용:\n" + board[titleIdx - 1][1]);
					System.out.println("================");
					System.out.print("뒤로가기(아무키나 입력하세요): ");
					String back = scan.next();
					
					if(back != null)
						System.out.println("홈 화면으로 돌아갑니다.");
					
				} else if(sel == 7) {
					System.out.println("프로그램을 종료합니다.");
					break;
				} else {
					System.out.println("화면의 번호중에 입력하세요.");
				}
				
				if(3 <= sel && sel <= 5) {
					try {
						fw = new FileWriter(file);
						
						String boardData = "";
						
						if(board != null) {
							for(int i = 0; i < board.length; i++) {
								boardData += board[i][0] + ", ";
								String[] brCheckArr = board[i][1].split("\n");
								for(int j = 0; j < brCheckArr.length; j++) {
									boardData += brCheckArr[j];
									if(j < brCheckArr.length - 1)
										boardData += "<br>";
								}
								if(i < board.length - 1)
									boardData += "\n";
							}
						}
						
						fw.write(boardData);
						fw.close();
						
					} catch(Exception e) {
						e.printStackTrace();
						System.err.println("파일 저장 실패!");
					}
				}
				
			} else {
				System.out.println("불러올 파일이 없습니다.");
				System.out.println("새로운 파일을 생성합니다.");
				
				try {
					fw = new FileWriter(file);
					
					fw.close();
					
				} catch(Exception e) {
					e.printStackTrace();
					System.err.println("파일 생성 실패");
				}
			}
		}
		scan.close();
	}
}
