package lv01;

import java.util.Scanner;

public class 정수합 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num;
		int sum = 0;
		
		for(int i = 0; i < 5; i++) {
			System.out.print("정수 입력: ");
			num = sc.nextInt();
			sum += num;
			System.out.println("합: " + sum);
			if(sum >= 100) {
				break;
			}
		}
		
		System.out.println("종료");
		
		sc.close();
	}

}
