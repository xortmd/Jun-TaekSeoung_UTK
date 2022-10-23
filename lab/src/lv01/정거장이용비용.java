package lv01;

import java.util.Scanner;

public class 정거장이용비용 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("이용할 정거장의 수: ");
		int sta = sc.nextInt();
		int mon;
		
		if(1 <= sta && sta <= 5) {
			mon = 500;
		} else if(6 <= sta && sta <= 10) {
			mon = 600;
		} else {
			mon = 375 + 25*sta;
			if(sta%2 == 0) {
				mon -= 25;
			}
		}
		
		System.out.println(mon + "원입니다.");
		
		sc.close();
	}
}
