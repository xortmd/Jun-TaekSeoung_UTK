package lv03;

import java.util.Scanner;

public class 회원가입 {
	/*
	 * # 회원가입 
	 * 1. 가입
	 * . 아이디 와 비밀번호를 입력받아 가입
 	 * . 아이디 중복검사
 	 * 2. 탈퇴
 	 * . 아이디 를 입력받아 탈퇴
 	 */	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int[] ids = {0, 0, 0, 0, 0};
		int[] pws = {0, 0, 0, 0, 0};
		
		int cnt = 0;
		int id, pw;
		int idIdx = -1;
		
		boolean run = true;
		while(run) {
			System.out.println("1.가입");
			System.out.println("2.탈퇴");
			System.out.println("3.종료");
			System.out.print("메뉴 선택 : ");
			int sel = scan.nextInt();
			
			if(sel == 1) {
				// 아이디 입력
				System.out.print("가입할 아이디를 입력하세요.");
				id = scan.nextInt();
				
				while(id == 0) {
					System.out.print("아이디는 0일 수 없습니다. 다시 입력하세요.");
					id = scan.nextInt();
				}
				
				// 아이디 중복 확인
				for(int i = 0; i < ids.length; i++) { // i < cnt;가 더 효율적임
					if(ids[i] == id) {
						System.out.print("중복된 아이디입니다. 다른 아이디를 입력하세요.");
						id = scan.nextInt();
						i = -1;
					}
				}
				
				// 아이디 저장
				ids[cnt] = id;
				
				// 비밀번호 입력
				System.out.print("비밀번호를 입력하세요.");
				pw = scan.nextInt();
				
				// 비밀번호 저장
				pws[cnt] = pw;
				
				cnt++;
			} else if(sel == 2) {
				// 아이디 입력
				System.out.print("탈퇴할 아이디를 입력하세요.");
				id = scan.nextInt();
				
				// 아이디 유효 확인
				for(int i = 0; i < ids.length; i++) {
					if(ids[i] == id) {
						idIdx = i;
						break;
					}
					if(i == ids.length - 1) {
						System.out.print("유효하지 않은 아이디입니다. 다시 입력하세요.");
						id = scan.nextInt();
						i = -1;
					}
				}
				
				// 비밀번호 입력
				System.out.print("비밀번호를 입력하세요.");
				pw = scan.nextInt();
				
				// 비밀번호 확인
				while(pw != pws[idIdx]) {
					System.out.print("비밀번호가 맞지 않습니다. 다시 입력하세요.");
					pw = scan.nextInt();
				}
				
				// 탈퇴
				ids[idIdx] = 0;
				pws[idIdx] = 0;
				for(int i = 0; i < ids.length - 1; i++) {
					if(ids[i] == 0) {
						ids[i] = ids[i + 1];
						ids[i + 1] = 0;
					}
					if(pws[i] == 0) {
						pws[i] = pws[i + 1];
						pws[i + 1] = 0;
					}
				}
				
				System.out.println("회원님의 계정이 정상적으로 탈퇴되었습니다.");
				
				cnt--;
			} else if(sel == 3) {
				System.out.println("프로그램을 종료합니다.");
				run = false;
			} else {
				System.out.println("1~3의 숫자를 입력하세요.");
			}
			
			// 검사용
//			for(int i = 0; i < 5; i++)
//				System.out.print(ids[i] + " ");
//			System.out.println();
//			for(int i = 0; i < 5; i++)
//				System.out.print(pws[i] + " ");
//			System.out.println();
			
		}

		scan.close();
	}
}