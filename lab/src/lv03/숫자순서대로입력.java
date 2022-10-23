package lv03;

import java.util.Random;
import java.util.Scanner;

public class 숫자순서대로입력 {
	/*
	 * #  1 to 4
	 * 1. arr배열에 1~4 사이의 숫자를 중복없이 저장한다.
	 * 2. 사용자는 1부터 순서대로 해당 위치 값을 입력한다.
	 * 3. 정답을 맞추면 해당 값은 9로 변경되어 모든 값이 9가 되면 게임은 종료된다.
	 * 예)
	 * 4 2 3 1
	 * 입력 : 3
	 * 4 2 3 9
	 * 입력 : 1
	 * 4 9 3 9
	 * ...
	 */
	public static void main(String[] args) {
		Random ran = new Random();
		Scanner scan = new Scanner(System.in);
		
		int[] arr = new int[4];
		int[] check = new int[4];
		int cardCount = 1;
		
		// 1 ~ 4 중복없이 저장
		for(int i = 0; i < arr.length; i++) {
			int num = ran.nextInt(4) + 1;
			if(check[num - 1] != 1)
				arr[i] = num;
			else
				i--;
			check[num - 1] = 1;
		}
		
		// 게임 실행
		while(true) {
			// 카드 출력
			for(int i = 0; i < arr.length; i++)
				System.out.print(arr[i] + " ");
			
			// 게임 종료
			if(cardCount == 5) {
				System.out.println("\n게임 종료");
				break;
			}
						
			// 입력
			System.out.print("\n입력:");
			int sel = scan.nextInt();
			
			// 범위를 벗어난 인덱스 입력
			if(sel < 0 || sel > 3) {
				System.out.println("0 ~ 3 사이의 값을 입력하세요.");
				continue;
			}
			
			// 카드 맞추기
			if(arr[sel] == cardCount) {
				arr[sel] = 9;
				cardCount++;
			} else {
				System.out.println("틀렸습니다. 다시 입력하세요.");
			}
		}
		scan.close();
	}
}