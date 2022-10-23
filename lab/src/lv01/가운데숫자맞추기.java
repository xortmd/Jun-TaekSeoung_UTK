package lv01;

import java.util.Scanner;

public class 가운데숫자맞추기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int ran = (int)(Math.random()*101) + 150;
		int num = ran%100/10;
		
		System.out.println("랜덤 숫자: " + ran);
		
		System.out.print("가운데 숫자를 맞춰보세요.: ");
		int myNum = sc.nextInt();
		
		if(num == myNum) {
			System.out.println("정답입니다.");
		} else {
			System.out.println("오답입니다.");
		}
			
		sc.close();
	}
}