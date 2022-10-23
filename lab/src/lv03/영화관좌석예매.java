package lv03;

import java.util.Scanner;

public class 영화관좌석예매 {

/*
 * # 영화관 좌석예매
 * 1. 사용자로부터 좌석번호(index)를 입력받아 예매하는 시스템이다.
 * 2. 예매가 완료되면 해당 좌석 값을 1로 변경한다.
 * 3. 이미 예매가 완료된 좌석은 재구매할 수 없다.
 * 4. 한 좌석당 예매 가격은 12000원이다.
 * 5. 프로그램 종료 후, 해당 영화관의 총 매출액을 출력한다.
 * 예)
 * seat = 0 0 0 0 0 0 0
 * 
 * 좌석선택 : 1
 * seat = 0 1 0 0 0 0 0
 * 
 * 좌석선택 : 3
 * seat = 0 1 0 1 0 0 0
 * 
 * 좌석선택 : 3
 * seat = 0 1 0 1 0 0 0
 * 이미 예매가 완료된 자리입니다.
 * ----------------------
 * 매출액 : 24000원
 */

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);		
		int[] seat = new int[7];
		
		int mySeat = -1;
		int ticketCount1 = 0;
		int ticketCount2 = 0;
		int count = 3;
		int id, pw;
		
		int log = -1; // -1(로그아웃), 1(dbId1로그인), 2(dbId2로그인)
		
		int dbId1 = 1111;
		int dbPw1 = 1234;
		
		int dbId2 = 2222;
		int dbPw2 = 2345;
		
		boolean run = true;
		while(run) {
			System.out.println("=영화관=");
			System.out.println("1.로그인");
			System.out.println("2.로그아웃");
			System.out.println("3.좌석예매");
			System.out.println("4.예매취소");
			System.out.println("5.종료");
			
			System.out.print("메뉴 선택 : ");
			int sel = scan.nextInt();
			
			if(sel == 1) {
				if(log == -1) {
					while(true) {
						System.out.print("아이디를 입력하세요.");
						id = scan.nextInt();
						if(id == dbId1) {
							log = 1;
							break;
						} else if(id == dbId2) {
							log = 2;
							break;
						} else
							System.out.println("유효하지 않은 아이디입니다. 다시 입력해주세요.");
					}
					while(count > 0) {
						System.out.print("비밀번호를 입력하세요.(" + count + "회 오류시 프로그램 자동종료)");
						pw = scan.nextInt();
						if(log == 1) {
							if(pw == dbPw1)
								break;
						} else if (log == 2) {
							if(pw == dbPw2)
								break;
						}
						count--;
						System.out.println("비밀번호가 맞지 않습니다.");
					}
				} else {
					System.out.println("이미 로그인 상태입니다.");
				}
			} else if(sel >= 2 && sel <= 4 && log == -1) {
				System.out.println("로그인 후 이용해주세요. 로그인은 1번입니다.");
			} else if(sel == 2) { // 2번 메뉴
				log = -1;
				System.out.println("로그아웃 되었습니다.");
				for(int i = 0; i < seat.length; i++)
					seat[i] = 0;
			} else if(sel == 3) {
				System.out.print("seat = ");
				for(int i = 0; i < seat.length; i++) {
					System.out.print(seat[i] + " ");
				}
				System.out.print("\n좌석 선택: ");
				mySeat = scan.nextInt();
				while(seat[mySeat] == 1) {
					System.out.println("이미 예매가 완료된 자리입니다. 다른 좌석을 선택해주세요.");
					System.out.print("좌석 선택: ");
					mySeat = scan.nextInt();
				}
				seat[mySeat] = 1;
				if(log == 1)
					ticketCount1++;
				else if(log == 2)
					ticketCount2++;
				System.out.print("seat = ");
				for(int i = 0; i < seat.length; i++) {
					System.out.print(seat[i] + " ");
				}
				System.out.println();
			} else if(sel == 4) {
				System.out.println("취소할 좌석을 선택해주세요.");
				System.out.print("seat = ");
				for(int i = 0; i < seat.length; i++) {
					System.out.print(seat[i] + " ");
				}
				System.out.print("\n좌석 선택: ");
				mySeat = scan.nextInt();
				while(seat[mySeat] == 0) {
					System.out.println("예매가 되지 않은 좌석입니다. 다시 선택해주세요.");
					System.out.print("좌석 선택: ");
					mySeat = scan.nextInt();
				}
				seat[mySeat] = 0;
				System.out.println("좌석취소가 완료되었습니다.");
				if(log == 1)
					ticketCount1--;
				else if(log == 2)
					ticketCount2--;
			} else if(sel == 5) {
				int myTicketCount = (log == 1) ? ticketCount1 : ticketCount2;
				System.out.println("좌석예매를 종료합니다. " + 12000*myTicketCount + "를 지불해주세요."); 
				run = false;
			} else {
				System.out.println("0 ~ 4의 번호를 입력해주세요.");
			}
			System.out.println("====================");
			if(count == 0) {
				run = false;
				System.out.println("프로그램 종료");
			}
		}
		scan.close();
	}

}

