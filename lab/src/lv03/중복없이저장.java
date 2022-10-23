package lv03;

import java.util.Scanner;

public class 중복없이저장 {
/*
 * # 중복숫자 금지[1단계]
 * 1. 0~4 사이의 숫자를 arr배열에 저장한다.
 * 2. 단, 중복되는 숫자는 없어야 한다.
 * 힌트) 랜덤 숫자를 check배열의 인덱스로 활용한다.
 * 
 * 예)
 * 랜덤숫자 : 1
 * check = {0, 1, 0, 0, 0}
 * arr   = {1, 0, 0, 0, 0}
 * 랜덤숫자 : 3
 * check = {0, 1, 0, 1, 0}
 * arr   = {1, 3, 0, 0, 0}
 * 랜덤숫자 : 2
 * check = {0, 1, 1, 1, 0}
 * arr   = {1, 3, 2, 0, 0}
 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int[] check = new int[5];
		int[] arr = new int[5];
		int num;
		
		for(int i = 0; i < 5; i++) {
			num = (int)(Math.random()*5);
			if(check[num] == 0)
				arr[i] = num;
			else
				i--;
			check[num] = 1;
		}
		
		System.out.print("check = {");
		for(int i = 0; i < 5 - 1; i++) {
			System.out.print(check[i] + ", ");
		}
		System.out.println(check[4] + "}");
		
		System.out.print("arr = {");
		for(int i = 0; i < 5 - 1; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.println(arr[4] + "}");
		

		scan.close();
	}

}