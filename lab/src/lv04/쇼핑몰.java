package lv04;

import java.util.Scanner;

public class 쇼핑몰 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String[][] items = new String[100][2];
		for(int i = 0; i < items.length; i++) {
			items[i][0] = ""; // null -> "" (연산 하려고)
			items[i][1] = "";
		}
		
		int itemCount = 0;
		
		while(true) {
			// 메뉴 화면
			System.out.println("[관리자 모드]");
			System.out.println("[1] 카테고리 관리");
			System.out.println("[2] 아이템 관리");
			System.out.println("[3] 전체품목 출력");
			System.out.println("[4] 종료");
			System.out.print("번호 선택: ");
			int sel = scan.nextInt();
			
			// 카테고리 관리
			if(sel == 1) {
				System.out.println("[1] 카테고리 추가");
				System.out.println("[2] 카테고리 삭제");
				System.out.println("[3] 카테고리 확인");
				System.out.print("번호 선택: ");
				sel = scan.nextInt();
				if(sel == 1) {
					System.out.print("추가할 카테고리를 입력해주세요. ");
					String category = scan.next();
					
					for(int i = 0; i < items.length; i++) {
						// 카테고리 중복 확인
						if(category.equals(items[i][0])) {
							System.out.println("이미 있는 카테고리입니다.");
							break;
						// 카테고리 저장
						} else if(i == items.length - 1) {
							items[itemCount][0] = category;
							itemCount++;
						}
					}
				
				} else if(sel == 2) {
					System.out.print("삭제할 카테고리를 입력해주세요. ");
					String category = scan.next();
					
					for(int i = 0; i < items.length; i++) {
						// 카테고리 삭제
						if(category.equals(items[i][0])) {
							for(int j = i; j < items.length - 1; j++) {
								items[j][0] = items[j + 1][0];
								items[j][1] = items[j + 1][1];
							}
							items[items.length - 1][0] = "";
							items[items.length - 1][1] = "";
							System.out.println(category + "이(가) 삭제되었습니다.");
							itemCount--;
							break;
						// 없는 카테고리 확인
						} else if(i == items.length - 1) {
							System.out.println("유효하지 않은 카테고리입니다.");
						}
					}
				
				} else if(sel == 3) {
					// 카테고리 출력
					System.out.println("[카테고리]");
					for(int i = 0; i < items.length; i++) {
						if(!items[i][0].equals(""))
							System.out.println(i + 1 + ") " + items[i][0]);
					}
				
				// 범위를 벗어난 숫자 입력
				} else {
					System.out.println("1~3의 숫자를 입력해주세요.");
				}
			
			// 아이템 관리
			} else if(sel == 2) {
				// 관리할 카테고리 입력
				System.out.println("[카테고리]");
				for(int i = 0; i < items.length; i++)
					if(!items[i][0].equals(""))
						System.out.println(i + 1 + ") " + items[i][0]);
				System.out.print("관리할 아이템의 카테고리 번호를 입력해주세요. ");
				int categoryIndex = scan.nextInt();
				
				// 아이템 관리
				for(int i = 0; i < items.length; i++) {
					if(categoryIndex > 0 && items[categoryIndex - 1][0].equals(items[i][0]) && items[i][0] != "") {
						System.out.println("[1] 아이템 추가");
						System.out.println("[2] 아이템 확인");
						System.out.print("번호 선택: ");
						sel = scan.nextInt();
						// 아이템 추가
						if(sel == 1) {
							System.out.print("추가할 아이템을 입력해주세요. ");
							String item = scan.next();
							items[i][1] += item + "/";
						// 아이템 출력
						} else if(sel == 2) {
							System.out.println(items[i][0] + ") " + items[i][1]);
						} else {
							System.out.println("1~2의 숫자를 입력해주세요.");
						}
						break;
					// 범위를 벗어난 숫자 입력
					} else if(i == items.length - 1) {
						System.out.println("화면의 번호 중에서 골라주세요.");
					}
				}
			
			// 전체 품목 출력
			} else if(sel == 3) {
				System.out.println("[전체 품목]");
				for(int i = 0; i < items.length; i++)
					if(!items[i][0].equals(""))
						System.out.println(items[i][0] + ") " + items[i][1]);
				
			// 관리자 모드 종료
			} else if(sel == 4) {
				System.out.println("관리자 모드를 종료합니다.");
				break;
			
			// 범위를 벗어난 숫자 입력
			} else {
				System.out.println("1~4의 숫자를 입력해주세요.");
			}
			System.out.println("====================");
		}
		
		scan.close();
	}
}
