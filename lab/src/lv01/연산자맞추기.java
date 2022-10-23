package lv01;

import java.util.Scanner;

public class 연산자맞추기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int ran1 = (int)(Math.random()*10) + 1;
		int ran2 = (int)(Math.random()*10) + 1;
		int opr = (int)(Math.random()*4) + 1;
		int result, myOpr;
		
		if(opr == 1) {
			result = ran1 + ran2;
		} else if(opr == 2) {
			result = ran1 - ran2;
		} else if(opr == 3) {
			result = ran1 * ran2;
		} else {
			result = ran1 % ran2;
		}
		
		System.out.println(ran1 + " ? " + ran2 + "= " + result);
		System.out.println("물음표에 들어갈 연산자는?");
		System.out.println("1) + 2) - 3) * 4) %");
		myOpr = sc.nextInt();
		
		if(opr == myOpr)
			System.out.println("정답입니다.");
		else
			System.out.println("오답입니다.");
		
		System.out.println("정답: " + opr + "번");
		
		sc.close();
	}
}
