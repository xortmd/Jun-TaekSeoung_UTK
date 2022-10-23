package lv03;

import java.util.Scanner;

public class 배열인덱스저장 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int arr[] = {10,20,30,40,50};	
		int b[] = new int[5];
		int num;

		for(int i = 0; i < arr.length; i++) {
			System.out.print("정수" + (i + 1) + " 입력: ");
			num = scan.nextInt();
			for(int j = 0; j < arr.length; j++) {
				if(arr[j] == num) {
					b[i] = j;
					j = arr.length; // break;
				} else {
					b[i] = -1;
				}
			}
		}
		
		// b 출력
		System.out.print("b = ");
		for(int i = 0; i < b.length; i++) {
			System.out.print(b[i] + " ");
		}

		scan.close();
	}

}
// 숫자를 5개 입력받고 arr 배열안에 입력한값이 있을때마다 
// b배열안에 해당 값의 인덱스를 차례대로 저장할려고 한다. 
// 조건) 만약에 입력한 숫자가 arr 에 없으면 인덱스 대신 -1 저장 

// 예) 10, 20, 10, 1, 50
//  b ={0,1,0,-1,4}

// 예)  30, 40, 1, 10, 2
// b = {2,3,-1,0,-1}