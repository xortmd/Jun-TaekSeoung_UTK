package lv04;

import java.util.Scanner;

public class 쇼핑몰4 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String[] ids = {"qwer", "javaking", "abcd"};
		String[] pws = {"1111", "2222", "3333"};
		
		int MAX_SIZE = 100;
		int[][] jang = new int[MAX_SIZE][2];
		
		int count = 0;
		
		String[] items = {"사과", "바나나", "딸기"};
		
		int log = -1;
		
		while(true) {
			// 메뉴 화면
			System.out.println("[MEGA MART]");
			System.out.println("[1] 로그인");
			System.out.println("[2] 로그아웃");
			System.out.println("[3] 쇼핑");
			System.out.println("[4] 장바구니");
			System.out.println("[0] 종료");
			
			System.out.print("메뉴 선택: ");
			int sel = scan.nextInt();
			
			// 로그인
			if(sel == 1) {
				// 로그인 상태일 때 
				if(log != -1)
					System.out.println("이미 로그인 상태입니다.");
				else {
					System.out.print("아이디 입력: ");
					String id = scan.next();
					
					for(int i = 0; i < ids.length; i++) {
						if(id.equals(ids[i])) {
							System.out.print("비밀번호 입력: ");
							String pw = scan.next();
							if(pw.equals(pws[i])) {
								log = i;
								System.out.println(id + "님 환영합니다.");
							} else
								System.out.println("비밀번호가 맞지 않습니다.");
							break;
						} else if(i == ids.length - 1)
							System.out.println("아이디를 확인해주세요.");
					}
				}
			
			// 로그아웃 상태일 때
			} else if(2 <= sel && sel <= 4 && log == -1) {
				System.out.println("로그인 후 이용해주세요.");
				
			} else if(sel == 2) {
				log = -1;
				System.out.println("로그아웃 되었습니다.");
				
			// 쇼핑
			} else if(sel == 3) {
				for(int i = 0; i < items.length; i++)
					System.out.println(i + 1 + ") " + items[i]);
				System.out.print("품목 선택: ");
				sel = scan.nextInt();
				
				while(sel < 1 || 3 < sel) {
					System.out.print("화면의 번호 중에서 입력해주세요. 다시 입력: ");
					sel = scan.nextInt();
				}
				
				jang[count][0] = log;
				jang[count][1] = sel;
				count++;
			
			// 장바구니 담기
			} else if(sel == 4) {
				// 품목별 개수 세기
				int[][] countArr = new int[items.length][2];
				// countArr[아이템 번호 - 1][0]: 아이템 개수, countArr[아이템 번호 - 1][1]: 장바구니 번호
				int itemBasketCount = 0;
				for(int i = 0; i < ids.length; i++) {
					if(log == i) {
						System.out.println("[" + ids[i] + "님의 장바구니]");
						for(int j = 0; j < count; j++)
							if(i == jang[j][0])
								countArr[jang[j][1] - 1][0]++;
					}
				}
				
				for(int i = 0; i < items.length; i++)
					if(countArr[i][0] != 0) {
						itemBasketCount++;
						countArr[i][1] = itemBasketCount;
						System.out.println(itemBasketCount + ") " + items[i] + " " + countArr[i][0] + "개");
					}
				
				System.out.println("[1] 수량 변경(추가)");
				System.out.println("[2] 수량 변경(삭제)");
				System.out.println("홈 화면으로 가시려면 아무키나 입력하세요.");
				System.out.print("메뉴 선택: ");
				sel = scan.nextInt();
				
				if(sel == 1) {
					System.out.print("추가할 품목번호 입력: ");
					int item = scan.nextInt();
					
					for(int i = 0; i < countArr.length; i++) {
						if(item == countArr[i][1] && item != 0) {
							System.out.print("추가할 수량 입력: ");
							int pluse = scan.nextInt();
							
							while(pluse <= 0) {
								System.out.print("수량은 1이상 입력해야 합니다. 다시 입력: ");
								pluse = scan.nextInt();
							}
							
							for(int j = 0; j < pluse; j++) {
								jang[count][0] = log;
								jang[count][1] = i + 1;
								count++;
							}
							
							System.out.println(items[i] + "이(가) " + pluse + "개 추가되었습니다.");
							break;
						} else if(i == countArr.length - 1) {
							System.out.print("화면의 번호 중에서 입력하세요. ");
							item = scan.nextInt();
							i = -1;
						}
					}
					
					
				} else if(sel == 2) {
					System.out.print("삭제할 품목번호 입력: ");
					int item = scan.nextInt();
					
					for(int i = 0; i < countArr.length; i++) {
						if(item == countArr[i][1] && item != 0) {
							System.out.print("삭제할 수량 입력: ");
							int minus = scan.nextInt();
							
							while(minus <= 0 || countArr[i][0] < minus) {
								System.out.print("수량은 1이상 " + countArr[i][0] + "이하 로 입력해야 합니다. 다시 입력: ");
								minus = scan.nextInt();
							}
							
							System.out.println(items[i] + "이(가) " + minus + "개 삭제되었습니다.");
							
							count -= minus;
							
							for(int j = 0; j < jang.length; j++) {
								if(jang[j][0] == log && jang[j][1] == i + 1) {
									jang[j][0] = -1;
									jang[j][1] = -1;
									minus--;
								}
								if(minus == 0)
									break;
							}
							
							int[][] temp = null;
							temp = jang;
							
							jang = new int[MAX_SIZE][2];
							
							int index = 0;
							for(int j = 0; j < temp.length; j++) {
								if(temp[j][0] != -1) {
									jang[index][0] = temp[j][0];
									jang[index][1] = temp[j][1];
									index++;
								}
							}
							break;
						} else if(i == countArr.length - 1) {
							System.out.print("화면의 번호 중에서 입력하세요. ");
							item = scan.nextInt();
							i = -1;
						}
					}
				}
				
			} else if(sel == 0) {
				System.out.println("프로그램 종료");
				break;
			} else {
				System.out.println("0~4의 숫자를 입력해주세요.");
			}
			System.out.println("==============================");
		}
		
		scan.close();
	}
}
