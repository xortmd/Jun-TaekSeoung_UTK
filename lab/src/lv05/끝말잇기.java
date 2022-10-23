package lv05;

import java.util.Scanner;

public class 끝말잇기 {
	/*
	 * # 끝말잇기 게임
	 * 제시어 : 자전거
	 * 입력 : 거미
	 * 제시어 : 거미
	 * 입력 : 미술
	 * ...
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String start = "자전거";
		System.out.println("제시어 : " + start);
		
		while(true) {
			System.out.print("입력: ");
			String word = scan.next();
			
			if(start.charAt(start.length() - 1) != word.charAt(0)) {
				System.out.println("땡!");
				break;
			}
			
			start = word;
		}
		
		scan.close();
	}
}
