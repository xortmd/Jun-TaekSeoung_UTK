package lv02;

import java.util.Scanner;

public class ATM2 {
	/*
	 * # ATM[종합]
	 * 1. 로그인
	 * . 로그인 후 재 로그인 불가
	 * . 로그아웃 상태에서만 이용 가능
	 * 2. 로그아웃
	 * . 로그인 후 이용가능
	 * 3. 입금
	 * . 로그인 후 이용가능
	 * 4. 출금
	 * . 로그인 후 이용가능
	 * 5. 이체
	 * . 로그인 후 이용가능
	 * 6. 조회
	 * . 로그인 후 이용가능
	 * 7. 종료
	 */
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		int dbAcc1 = 1111;
		int dbPw1 = 1234;
		int dbMoney1 = 50000;
		
		int dbAcc2 = 2222;
		int dbPw2 = 2345;
		int dbMoney2 = 70000;	
		
		int log = -1; // -1(로그아웃), 1(dbAcc1로그인), 2(dbAcc2로그인)
		
		int acc, pw, myAcc, otherAcc;
		int count = 3;
		int money = 0;
		int myMoney = 0;
		
		boolean run = true;
		while(run) {
			System.out.println("1.로그인");
			System.out.println("2.로그아웃");
			System.out.println("3.입금");
			System.out.println("4.출금");
			System.out.println("5.이체");
			System.out.println("6.조회");
			System.out.println("0.종료");
			System.out.print("메뉴 선택 : ");
			int sel = scan.nextInt();
			
			if(sel == 1) { // 1번 메뉴
				if(log == -1) {
					while(true) {
						System.out.print("계좌명을 입력하세요.");
						acc = scan.nextInt();
						if(acc == dbAcc1) {
							log = 1;
							break;
						} else if(acc == dbAcc2) {
							log = 2;
							break;
						} else
							System.out.println("유효하지 않은 계좌명입니다. 다시 입력해주세요.");
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
					myAcc = (log == 1) ? dbAcc1 : dbAcc2;
					System.out.println("이미 로그인 상태입니다. 당신의 계좌명은 " + myAcc + "입니다.");
				}
			} else if(sel >= 2 && sel <= 6 && log == -1) {
				System.out.println("로그인 후 이용해주세요. 로그인은 1번입니다.");
			} else if(sel == 2) { // 2번 메뉴
				log = -1;
				System.out.println("로그아웃 되었습니다.");
			} else if(sel == 3) { // 3번 메뉴
				System.out.print("입금하실 금액을 입력해주세요.");
				money = scan.nextInt();
				while(money <= 0) {
					System.out.print("입금 금액은 1원 이상이어야합니다. 다시 입력해주세요.");
					money = scan.nextInt();
				}
				myMoney = (log == 1) ? (dbMoney1 += money) : (dbMoney2 += money);
				System.out.println(money + "원이 입금되었습니다.");
				System.out.println("귀하의 계좌의 잔액은 " + myMoney + "원입니다.");
			} else if(sel == 4) { // 4번 메뉴
				System.out.print("출금하실 금액을 입력해주세요.");
				money = scan.nextInt();
				myMoney = (log == 1) ? dbMoney1 : dbMoney2;
				while(money <= 0) {
					System.out.print("출금 금액은 1원 이상이어야합니다. 다시 입력해주세요.");
					money = scan.nextInt();
				}
				while(myMoney < money) {
					System.out.print("귀하의 최대 출금액은 " + myMoney + "원입니다. 다시 입력해주세요.");
					money = scan.nextInt();
				}
				myMoney = (log == 1) ? (dbMoney1 -= money) : (dbMoney2 -= money);
				System.out.println(money + "원이 출금되었습니다.");
				System.out.println("귀하의 계좌의 잔액은 " + myMoney + "원입니다.");				
			} else if(sel == 5) { // 5번 메뉴
				System.out.print("이체하실 금액을 입력해주세요.");
				money = scan.nextInt();
				myMoney = (log == 1) ? dbMoney1 : dbMoney2;
				while(money <= 0) {
					System.out.print("이체 금액은 1원 이상이어야합니다. 다시 입력해주세요.");
					money = scan.nextInt();
				}
				while(myMoney < money) {
					System.out.print("귀하의 최대 이체금액은 " + dbMoney1 + "원입니다. 다시 입력해주세요.");
					money = scan.nextInt();
				}
				System.out.print("이체하실 계좌를 입력해주세요.");
				otherAcc = scan.nextInt();
				while(!(otherAcc == dbAcc1 && log != 1) && !(otherAcc == dbAcc2 && log != 2)) {
					System.out.print("유효하지 않은 계좌명입니다. 다시 입력해주세요.");
					otherAcc = scan.nextInt();
				}
				if(log == 1) {
					myMoney = dbMoney1 -= money;
					dbMoney2 += money;
				} else {
					myMoney = dbMoney2 -= money;
					dbMoney1 += money;
				}
				System.out.println(money + "원이 이체되었습니다.");
				System.out.println("귀하의 계좌의 잔액은 " + myMoney + "원입니다.");
			} else if(sel == 6) { // 6번 메뉴
				myMoney = (log == 1) ? dbMoney1 : dbMoney2;
				System.out.println("귀하의 계좌에 있는 금액은 " + myMoney + "원입니다.");
			} else if(sel == 0) { // 0번 메뉴
				run = false;
				System.out.println("프로그램 종료");
			} else {
				System.out.println("0 ~ 6의 번호를 입력해주세요.");
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