package lv01;

import java.util.Scanner;

public class 점수입력 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("국어점수 입력: ");
		int kor = sc.nextInt();
		System.out.print("영어점수 입력: ");
		int eng = sc.nextInt();
		System.out.print("수학점수 입력: ");
		int mat = sc.nextInt();
		double ave = (double)(kor + eng + mat)/3;
		char grade;
		char pluse;
		
		switch((int)ave/10) {
		case 10: case 9:
			grade = 'A';
			break;
		case 8:
			grade = 'B';
			break;
		case 7:
			grade = 'C';
			break;
		default:
			grade = '\0';
			break;
		}
		
		if((int)ave%10 >= 7) {
			pluse = '+';
		} else if(ave == 100) {
			pluse = '+';
		} else {
			pluse = '\0';
		}
		
		if(ave <= 69) {
			System.out.println("재시험입니다.");
		} else {
			System.out.printf("평균: %.2f, 학점: %c%c", ave,grade,pluse);
		}
	
		sc.close();
	}

}
