package lv03;

import java.util.Random;
import java.util.Scanner;

public class 기억력게임 {
	/*
	 * # 기억력 게임
	 * 1. 같은 숫자의 위치를 2개 입력해 정답을 맞추는 게임이다.
	 * 2. 정답을 맞추면 back에 해당 숫자를 저장해,
	 *    back에 모든 수가 채워지면 게임은 종료된다.
	 * 예)
	 * front = [1, 1, 2, 2, 3, 3, 4, 4, 5, 5]
	 * back  = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
	 * 입력1 : 0
	 * 입력2 : 1
	 * 
	 * front = [1, 1, 2, 2, 3, 3, 4, 4, 5, 5]
	 * back  = [1, 1, 0, 0, 0, 0, 0, 0, 0, 0]
	 */

	public static void main(String[] args) {
		Random ran = new Random();
		Scanner scan = new Scanner(System.in);
		
		int[] front = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5}; // 셔플 처리 후 게임 진행 : 카드 섞기
		int[] back = new int[10];
		
		int count = 0;
		int num1, num2;
		boolean flag = true;
		
		for(int i = 0; i < 1000; i++) {
			int num = ran.nextInt(10); // 0~9 범위의 난수(정수) 추출
			int t = front[0];
			front[0] = front[num];
			front[num] = t;
		}
		
		// 게임 시작
		while(true) {
			// 카드 출력
			// front
			System.out.print("front = [");
			for(int i = 0; i < front.length - 1; i++) {
				System.out.print(front[i] + ", ");
			}
			System.out.println(front[front.length - 1] + "]");
			// back
			System.out.print("back  = [");
			for(int i = 0; i < back.length - 1; i++) {
				System.out.print(back[i] + ", ");
			}
			System.out.println(back[back.length - 1] + "]");
			
			// 게임 종료
			if(back[9] != 0) {
				System.out.println("게임 종료");
				break;
			}
			
			// 입력
			System.out.print("입력1: ");
			num1 = scan.nextInt();
			System.out.print("입력2: ");
			num2 = scan.nextInt();
			
			// 같은 카드 인덱스를 선택하는 경우
			if(num1 == num2) {
				System.out.println("서로 다른 값을 입력해주세요.");
				continue;
			// 배열의 범위를 벗어난 인덱스
			} else if((num1 < 0 || num1 > 9) || (num2 < 0 || num2 > 9)) {
				System.out.println("0~9 사이의 값을 입력해주세요.");
				continue;
			}
			
			for(int i = 0; i < back.length; i++)
				if(back[i] == front[num1] && back[i] == front[num2])
					flag = false;
			
			if(!flag)
				System.out.println("이미 입력된 숫자입니다.");
			else {
				if(front[num1] == front[num2]) {
					back[count++] = front[num1];
					back[count++] = front[num1];
				} else
					System.out.println("숫자가 같지 않습니다.");
			}
			
			flag = true;
		}
		scan.close();
	}
}