package lv05;

import java.util.Scanner;

public class 식권자판기 {
	/*
	 * 식권 자판기 프로그램
	 * 1) 관리자, 2) 사용자
	 * 관리자 ==> 1) 식권충전, 2) 잔돈충전, 3) 뒤로가기
	 * 사용자 ==> 1) 구입 ==> 입금 금액 입력 ==> 구매 매수 입력 ==> 잔돈 출력, 2) 뒤로가기
	 * 조건) 예) 잔돈이 7600원이다. 5000원 권이 없으면 1000원짜리 7장 출력
	 * 화면)
	 * 식권 가격: 3200원
	 * 식권: ??매 (매진 가능)
	 * 50000원: ??매, 10000원 ??매, 5000원: ??매,
	 * 1000원: ??매, 500원: ??개, 100원: ??개
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int[] money = {50000, 10000, 5000, 1000, 500, 100};
		int[] charges = {1, 1, 1, 1, 5, 10};
		
		int tickets = 5; // 식권 개수
		int price = 3200; // 식권 가격
		
		while(true) {
			// 화면 출력
			System.out.println("식권 가격: " + price);
			System.out.println("식권: " + tickets + "매");
			for(int i = 0; i < money.length; i++)
				System.out.printf("%5d원: %2d매\n", money[i],charges[i]);
			System.out.println("--------------------");
			
			System.out.println("[1] 관리자");
			System.out.println("[2] 사용자");
			System.out.println("[3] 종료");
			System.out.print("번호 선택: ");
			int sel = scan.nextInt();
			
			if(sel == 1) {
				while(true) {
					System.out.println("[관리자]");
					System.out.println("[1] 식권충전");
					System.out.println("[2] 잔돈충전");
					System.out.println("[3] 뒤로가기");
					System.out.print("번호 선택: ");
					sel = scan.nextInt();
					
					if(sel == 1) {
						System.out.print("충전할 식권 매수 입력: ");
						int cntTicket = scan.nextInt();
						
						while(cntTicket < 1) {
							System.out.println("0보다 큰 값을 입력해주세요.");
							System.out.print("다시 입력: ");
							cntTicket = scan.nextInt();
						}
						
						tickets += cntTicket;
						System.out.println("충전이 완료되었습니다. 현재 식권은 " + tickets + "매입니다.");
						
					} else if(sel == 2) {
						System.out.println("[충전 화폐 선택]");
						System.out.println("[1] 50000원");
						System.out.println("[2] 10000원");
						System.out.println("[3] 5000원");
						System.out.println("[4] 1000원");
						System.out.println("[5] 500원");
						System.out.println("[6] 100원");
						System.out.print("번호 선택: ");
						int selMoney = scan.nextInt();
						
						while(selMoney < 1 || 6 < selMoney) {
							System.out.println("화면의 번호중에 입력하세요.");
							System.out.print("다시 입력: ");
							selMoney = scan.nextInt();
						}
						
						System.out.print("화폐 매수 입력: ");
						int cntMoney = scan.nextInt();
						
						while(cntMoney < 1) {
							System.out.println("0보다 큰 값을 입력해주세요.");
							System.out.print("다시 입력: ");
							cntMoney = scan.nextInt();
						}
						
						charges[selMoney - 1] += cntMoney;
						
						System.out.println("잔돈 충전이 완료되었습니다.");
						
					} else if(sel == 3) {
						System.out.println("홈 화면으로 돌아갑니다.");
						break;
						
					} else {
						System.out.println("화면의 번호중에 입력하세요.");
					}
				}
				
			} else if(sel == 2) {
				while(true) {
					System.out.println("[사용자]");
					System.out.println("[1] 구입");
					System.out.println("[2] 뒤로가기");
					System.out.print("메뉴 선택: ");
					sel = scan.nextInt();
					
					if(sel == 1) {
						if(tickets == 0) {
							System.out.println("식권이 모두 매진되었습니다.");
							
						} else {
							int myMoney = 0; // 지불한 금액
							int[] myCntMoney = new int[charges.length]; // 지불한 화폐 매수
							while(true) {
								System.out.println("[입금 화폐 선택]");
								System.out.println("[1] 50000원");
								System.out.println("[2] 10000원");
								System.out.println("[3] 5000원");
								System.out.println("[4] 1000원");
								System.out.println("[5] 500원");
								System.out.println("[6] 100원");
								System.out.println("[결제하기]");
								System.out.println("[7] 결제");
								System.out.print("번호 선택: ");
								int selMoney = scan.nextInt();
								
								while(selMoney < 1 || 7 < selMoney) {
									System.out.println("화면의 번호중에 입력하세요.");
									System.out.print("다시 입력: ");
									selMoney = scan.nextInt();
								}
								
								if(selMoney != 7) {
									System.out.print("화폐 매수 입력: ");
									int cntMoney = scan.nextInt();
									
									while(cntMoney < 1) {
										System.out.println("0보다 큰 값을 입력해주세요.");
										System.out.print("다시 입력: ");
										cntMoney = scan.nextInt();
									}
									
									for(int i = 0; i < myCntMoney.length; i++) {
										if(i == selMoney - 1) {
											myCntMoney[i] += cntMoney;
											charges[i] += cntMoney;
											myMoney += money[i]*cntMoney;
										}
									}
									
								} else {
									if(myMoney < price)
										System.out.println("금액이 모자랍니다. 돈을 더 넣어주세요.");
									else
										break;
								}
							}
							
							System.out.print("식권 매수 입력: ");
							int cntTicket = scan.nextInt();
							
							while(true) {
								if(cntTicket < 1) {
									System.out.println("0보다 큰 값을 입력해주세요.");
									System.out.print("다시 입력: ");
									cntTicket = scan.nextInt();
									
								} else if(tickets < cntTicket) {
									System.out.println("잔여 식권은 " + tickets + "매입니다.");
									System.out.print("다시 입력: ");
									cntTicket = scan.nextInt();
									
								} else if(myMoney < cntTicket*price) {
									int cntTicketPsble = 0; // 최대 구매 가능 식권
									while(myMoney > cntTicketPsble*price)
										cntTicketPsble++;
									
									System.out.println("최대 구매 가능 매수는 " + (cntTicketPsble - 1) + "매입니다.");
									System.out.print("다시 입력: ");
									cntTicket = scan.nextInt();

								} else
									break;
							}
							
							int changeMoney = myMoney - cntTicket*price; // 거스름돈
							int[] yourCharges = new int[charges.length]; // 줘야하는 화폐 매수
							for(int i = 0; i < money.length; i++) {
								if(money[i] <= changeMoney && charges[i] != 0) {
									charges[i]--;
									yourCharges[i]++;
									changeMoney -= money[i];
									i--;
								}
							}
							
							if(changeMoney > 0) {
								System.out.println("잔돈이 모자랍니다. 관리자에게 문의해주세요.");
								System.out.println("입금액 " + myMoney + "원을 반환합니다.");
								
								for(int i = 0; i < charges.length; i++) {
									charges[i] += yourCharges[i];
									charges[i] -= myCntMoney[i];
								}

							} else {
								System.out.println("식권 " + cntTicket + "매가 구매완료되었습니다.");
								System.out.print("잔돈 " + (myMoney - cntTicket*price) + "(");
								String changeResult = "";
								for(int i = 0; i < yourCharges.length; i++) {
									if(yourCharges[i] != 0)
										changeResult += money[i] + "원(" + yourCharges[i] + "개),";
								}
								System.out.println(changeResult.substring(0, changeResult.length() - 1) + ")원을 수령해주세요.");
								tickets -= cntTicket;
							}
						}
						
					} else if(sel == 2) {
						System.out.println("홈 화면으로 돌아갑니다.");
						break;
						
					} else {
						System.out.println("화면의 번호중에 입력하세요.");
					}
				}
				
			} else if(sel == 3) {
				System.out.println("프로그램을 종료합니다.");
				break;
				
			} else {
				System.out.println("화면의 번호중에 입력하세요.");
			}
			
			System.out.println("====================");
		}
		
		
		scan.close();
	}
}
