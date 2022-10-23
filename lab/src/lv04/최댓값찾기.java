package lv04;

import java.util.Scanner;

public class 최댓값찾기 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int[] arr = {11, 87, 42, 100, 24};
		
		int count = 0;
		
		while(count == 0) {
			int max = arr[0];
			int maxIndex = -1;
			
			for(int i = 0; i < arr.length; i++) {
				// 배열 출력
				System.out.print(arr[i] + " ");
				// max값 결정
				if(max <= arr[i]) {
					max = arr[i];
					maxIndex = i;
				}
				// 0 카운트
				if(arr[i] == 0) {
					count++;
				}
			}
			System.out.println();
			
			// 입력
			System.out.print("입력: ");
			int num = scan.nextInt();
			
			// 최댓값 0 저장
			if(num == max) {
				arr[maxIndex] = 0;
			} else {
				System.out.println("땡!");
			}
		}
		
		System.out.println("clear!");
		
		scan.close();
	}
}
