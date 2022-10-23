package lv03;

import java.util.Scanner;

public class 소수찾기 {
	/*
	 * # 소수찾기[2단계]
	 * 정수 한 개를 입력받아, 2부터 해당 숫자까지의 모든 소수 출력
	 * 예)
	 * 입력 : 20
	 * 2, 3, 5, 7, 11, 13, 17, 19
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("정수 입력: ");
		int num = scan.nextInt();
		
		int count = 0;
		
		for(int i = 2; i <= num; i++) {
			for(int j = 1; j <= i; j++)
				if(i%j == 0)
					count++;
			if(count == 2)
				System.out.print(i + " ");
			count = 0;
		}
		
		scan.close();
	}
		
}