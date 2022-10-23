package lv06;

import java.util.Scanner;

class Calculator {
	
	int a, b;
	
	int plus(int a, int b) {
		return a + b;
	}
	
	int minu() {
		return a - b;
	}
	
	void mult(int a, int b) {
		System.out.println(a + " * " + b + " = " + a*b);
	}
	
	void divi() {
		System.out.println(a + " / " + b + " = " + a/b);
	}
	
	void run() {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("숫자1 입력: ");
		int x = scan.nextInt();
		System.out.print("숫자2 입력: ");
		int y = scan.nextInt();
		
		// this.
		// 클래스 자기 자신의 멤버 변수를 지칭
		this.a = x;
		this.b = y;
		
		System.out.println("[1] 더하기");
		System.out.println("[2] 빼기");
		System.out.println("[3] 곱하기");
		System.out.println("[4] 나누기(몫)");
		System.out.print("번호 선택: ");
		int sel = scan.nextInt();
		
		if(sel == 1) {
			System.out.println(x + " + " + y + " = " + this.plus(x, y));	
			
		} else if(sel == 2) {
			System.out.println(x + " - " + y + " = " + this.minu());
			
		} else if(sel == 3) {
			this.mult(x, y);
			
		} else if(sel == 4) {
			this.divi();
		}
		
		scan.close();
	}
}

public class 계산기 {
	public static void main(String[] args) {
		
		Calculator calculator = new Calculator();
		
		calculator.run();
		
	}
}
