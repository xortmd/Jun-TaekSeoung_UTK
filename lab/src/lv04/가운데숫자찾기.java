package lv04;

import java.util.Scanner;

public class 가운데숫자찾기 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("숫자 입력(1 ~ 1,000,000): ");
		int num = scan.nextInt();
		
		int temp1 = num;
		int temp2 = num;
		int size = 0;
		
		if(1 <= num && num <= 1000000) {
			while(temp1 > 0) {
				temp1 /= 10;
				size++;
			}
			if(size%2 == 1) {
				int[] arr = new int[size];
				for(int i = 0; i < size; i++) {
					arr[i] = temp2%10;
					temp2 /= 10;
				}
				System.out.println(num + "의 가운데 숫자는 " + arr[size/2] + "입니다.");
				
			} else
				System.out.println(num + "은(는) 짝수의 자리입니다.");
			
		} else
			System.out.println("범위 내에서 입력해주세요.");

		scan.close();
	}
}
