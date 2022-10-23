package lv04;

import java.util.Scanner;

public class 쇼핑몰3 {
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
				if(count == MAX_SIZE) {
					System.out.println("데이터 베이스 오류, 관리자 문의 요망.");
					continue;
				}
				
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
				int[] countArr = new int[items.length];
				for(int i = 0; i < ids.length; i++) {
					if(log == i) {
						System.out.println("[" + ids[i] + "님의 장바구니]");
						for(int j = 0; j < count; j++)
							if(i == jang[j][0])
								countArr[jang[j][1] - 1]++;
					}
				}
				boolean check = true;
				for(int i = 0; i < items.length; i++)
					if(countArr[i] != 0) {
						System.out.println("- " + items[i] + " " + countArr[i] + "개");
						check = false;
					}
				
				if(check)
					System.out.println("장바구니에 품목이 없습니다.");
				
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