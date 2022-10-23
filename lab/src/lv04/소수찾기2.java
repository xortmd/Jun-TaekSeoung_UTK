package lv04;

import java.util.Scanner;

public class 소수찾기2 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter Number ? ");
		int number = scan.nextInt();
		
		for(int i = number + 1; true; i++) {
			boolean check = true;
			for(int j = 2; j < i; j++)
				if(i%j == 0)
					check = false;
			if(check) {
				System.out.println(number + "보다 큰 첫 번째 소수는 " + i + "입니다.");
				break;
			}
		}
		
		scan.close();
	}
}
