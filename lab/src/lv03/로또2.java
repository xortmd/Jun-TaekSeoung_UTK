package lv03;

import java.util.Scanner;

public class 로또2 {
	/*
	 * # 즉석복권 1. 숫자 7이 연속으로 3번 등장하면, "당첨" 출력.
	 * 
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		//int[] lotto = { 0, 0, 7, 7, 7, 0, 0, 0 };
		//int[] lotto = {7, 0, 7, 7, 0, 7, 0, 7};
		//int[] lotto = { 7, 7, 7, 0, 7, 7, 7, 7 };
		
		int[] lotto = new int[8];
		for(int i = 0; i < 8; i++)
			lotto[i] = (int)(Math.random()*2)*7;
		
		int count = 0;
		int maxCount = 0; // 최대 연속 개수
		int count1 = 0; // 1등 당첨
		int count2 = 0; // 2등 당첨
		int count3 = 0; // 기본 당첨
		int count0 = 0; // 꽝
		int lottoCount = 1; // 이용한 복권 개수
		int payCount = 1; // 구매한 복권 개수
		
		boolean run = true;
		while(run) {
			System.out.println("1) 복권 결과확인");
			System.out.println("2) 복권 재구매");
			System.out.println("3) 복권 구매내역");
			System.out.println("4) 종료");
			System.out.print("메뉴 선택 : ");
			int sel = scan.nextInt();
			
			if(sel == 1) {
				System.out.print("{");
				for(int i = 0; i < 7; i++) {
					System.out.print(lotto[i] + ", ");
				}
				System.out.println(lotto[lotto.length - 1] + "}");
				
				for(int i = 0; i < 8; i++) {
					if(lotto[i] == 7) {
						count++;
						if(count >= 3 && maxCount < count)
							maxCount = count;
					} else if(lotto[i] == 0) {
						count = 0;
					}
				}
				
				if(maxCount == 3) {
					System.out.println("당첨되었습니다. 무료 복권이 다시 지급됩니다.");
					count3++;
				} else if(maxCount == 4) {
					System.out.println("2등 복권에 당첨되었습니다. 무료 복권이 다시 지급됩니다.");
					count2++;
				} else if(maxCount >= 5) {
					System.out.println("1등 복권에 당첨되었습니다. 무료 복권이 다시 지급됩니다.");
					count1++;
				} else {
					System.out.println("다음 기회에...");
					count0++;
				}
				
				if(maxCount >= 3) {
					for(int j = 0; j < 8; j++) {
						lotto[j] = (int)(Math.random()*2)*7;
					}
				}
				
				count = 0;
				maxCount = 0;
			} else if(sel == 2) {
				System.out.println("복권 1장이 추가로 지급되었습니다.");
				for(int i = 0; i < 8; i++) {
					lotto[i] = (int)(Math.random()*2)*7;
				}
				payCount++;
			} else if(sel == 3) {
				System.out.println("--------------------");
				System.out.println("복권 구매: " + lottoCount + "개");
				System.out.println("1등 당첨: " + count1 + "회");
				System.out.println("2등 당첨: " + count2 + "회");
				System.out.println("기본 당첨: " + count3 + "회");
				System.out.println("꽝: " + count0 + "회");
				System.out.println("누적 비용: " + payCount*5000 + "원");
				System.out.println("누적 당첨금: " + (count3*10000 + count2*30000 + count1*50000) + "원");
				System.out.println("--------------------");
			} else if(sel == 4) {
				System.out.println("이용해주셔서 감사합니다. 비용은 " + payCount*5000 + "이며 당첨금으로 "
						+ (count3*10000 + count2*30000 + count1*50000) + "원이 지급됩니다."); 
				run = false;
			} else {
				System.out.println("1 ~ 4의 번호를 입력해주세요.");
			}
			System.out.println("====================");
		}
		
		scan.close();
	}

}
// 복권 비용 5000원
// 복권 당첨금
// 1등(7 5번 이상 연속) 50000원
// 2등(7 4번 연속) 30000원
// 기본(7 3번 연속) 10000원