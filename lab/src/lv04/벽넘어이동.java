package lv04;

import java.util.Scanner;

public class 벽넘어이동 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int[] game = {0, 0, 1, 0, 2, 0, 0, 1, 0};
		
		int idx = 4;
		
		while(true) {
			// 화면 출력
			for(int i = 0; i < game.length; i++) {
				System.out.print(game[i] + " ");
			}
			System.out.println();
			
			// 이동 선택
			System.out.print("이동(1.(←), 2.(→), 3.벽격파, 4.종료) ");
			int move = scan.nextInt();
			
			// 왼쪽 이동
			if(move == 1) {
				// 왼쪽 끝 or 왼쪽벽
				if(idx == 0 || game[idx - 1] == 1) {
					System.out.println("왼쪽으로 이동할 수 없습니다.");
					continue;
				}
				// 이동값 저장
				game[idx] = 0;
				idx--;
				game[idx] = 2;
			// 오른쪽 이동
			} else if(move == 2) {
				// 오른쪽 끝 or 오른쪽벽
				if(idx == 8 || game[idx + 1] == 1) {
					System.out.println("오른쪽으로 이동할 수 없습니다.");
					continue;
				}
				// 이동값 저장
				game[idx] = 0;
				idx++;
				game[idx] = 2;
			// 벽격파
			} else if(move == 3) {
				if(idx > 0 && game[idx - 1] == 1) {
					game[idx - 1] = 0;
				} else if(idx < game.length - 1 && game[idx + 1] == 1) {
					game[idx + 1] = 0;
				} else {
					System.out.println("격파할 벽이 없습니다.");
				}
			// 이동 종료
			} else if(move == 4) {
				System.out.println("이동 종료");
				break;
			// 범위를 벗어난 선택 입력
			} else {
				System.out.println("1~4를 입력해주세요.");
			}
			
		}
		
		scan.close();
	}
}
