package lv04;

import java.util.Scanner;

public class 벡터구현 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int[] score = null;
		int[] temp = null;
		int count = 0; // score배열의 크기
		int index = -1;
		int num;
		boolean check = true;
		
		while(true) {
			System.out.println("1. 추가");
			System.out.println("2. 삭제(인덱스)");
			System.out.println("3. 삭제(값)");
			System.out.println("4. 삽입(인덱스, 값)");
			System.out.println("5. 종료");
			System.out.print("메뉴 선택: ");
			int sel = scan.nextInt();
			
			if(sel == 1) {
				// 새 집 생성
				count++;
				score = new int[count];
				
				// 추가 값 입력
				System.out.print("추가할 값 입력: ");
				score[count - 1] = scan.nextInt();
				
				// 새 집에 저장
				for(int i = 0; i < score.length - 1; i++)
					score[i] = temp[i];
			} else if(sel == 2) {
				// 삭제 인덱스 입력
				System.out.print("삭제할 인덱스 입력: ");
				index = scan.nextInt();
				
				// 범위를 벗어난 인덱스
				if(index < 0 || score.length <= index) {
					System.out.println("인덱스의 범위를 벗어났습니다.");
				} else {
					// 새 집 생성
					count--;
					score = new int[count];
					
					// 새 집에 저장(인덱스 빼고)
					check = true;
					for(int i = 0; i < score.length; i++) {
						if(i == index)
							check = false;
						if(check)
							score[i] = temp[i];
						else
							score[i] = temp[i + 1];
					}	
				}
			} else if(sel == 3) {
				// 삭제 값 입력
				System.out.print("삭제할 값 입력: ");
				num = scan.nextInt();
				
				// 유효한 값인지 확인
				check = true;
				for(int i = 0; i < score.length; i++) {
					if(num == score[i]) {
						index = i;
						break;
					}
					if(i == score.length - 1)
						check = false;
				}
				
				if(check) {
					// 새 집 생성
					count--;
					score = new int[count];
					
					// 새 집에 저장(입력 값 빼고)
					check = true;
					for(int i = 0; i < score.length; i++) {
						if(i == index)
							check = false;
						if(check)
							score[i] = temp[i];
						else
							score[i] = temp[i + 1];
					}
				} else {
					// 유효한 값이 아닐 때
					System.out.println("삭제할 값이 없습니다.");
				}
			} else if(sel == 4) {
				// 삽입 인덱스 입력
				System.out.print("삽입할 인덱스 입력: ");
				index = scan.nextInt();
				
				// 범위를 벗어난 인덱스
				if(index < 0 || score.length < index) {
					System.out.println("삽입할 수 없는 인덱스입니다.");
				} else {
					// 삽입 값 입력;
					System.out.print("삽입할 값 입력: ");
					num = scan.nextInt();
					
					// 새 집 생성
					count++;
					score = new int[count];
					
					// 새 집에 저장
					score[index] = num;
					check = true;
					for(int i = 0; i < score.length; i++) {
						if(i == index) {
							check = false;
							i++;
						}
						if(check)
							score[i] = temp[i];
						else
							score[i] = temp[i - 1];
					}
				}
			} else if(sel == 5){
				System.out.println("프로그램을 종료합니다.");
				break;
			} else {
				System.out.println("1~5를 입력하세요.");
			}
			
			// 헌 집 다오
			temp = score;
			
			// score 출력
			System.out.print("score = ");
			for(int i = 0; i < score.length; i++) {
				System.out.print(score[i] + " ");
			}
			System.out.println();
			System.out.println("--------------------");
		}

		scan.close();
	}
}