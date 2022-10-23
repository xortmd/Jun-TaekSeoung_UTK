package lv04;

import java.util.Scanner;

public class 아파트관리비 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int[][] apt = {
				{101, 102, 103},
				{201, 202, 203},
				{301, 302, 303}
		};
		
		int[][] pay = {
				{1000, 2100, 1300},
				{4100, 2000, 1000,},
				{3000, 1600, 800}
		};
		
		// 문제1)
		int sum = 0;
		for(int i = 0; i < pay.length; i++) {
			for(int j = 0; j < pay[i].length; j++)
				sum += pay[i][j];
			System.out.print(sum + " ");
			sum = 0;
		}
		System.out.println();
		
		// 문제2)
		System.out.print("호 입력: ");
		int ho = scan.nextInt();
		int idx1 = -1;
		int idx2 = -1;
		
		for(int i = 0; i < apt.length; i++)
			for(int j = 0; j < apt[i].length; j++)
				if(ho == apt[i][j]) {
					idx1 = i;
					idx2 = j;
				}
		
		if(idx1 != -1) {
			System.out.print("관리비 출력: ");
			System.out.println(pay[idx1][idx2]);
		} else {
			System.out.println("해당 호는 아파트에 없습니다.");
		}
					
		
		// 문제3)
		int maxPay = pay[0][0];
		int minPay = pay[0][0];
		int maxIdx1 = 0;
		int maxIdx2 = 0;
		int minIdx1 = 0;
		int minIdx2 = 0;
		for(int i = 0; i < pay.length; i++) {
			for(int j = 0; j < pay[i].length; j++) {
				if(maxPay < pay[i][j]) {
					maxPay = pay[i][j];
					maxIdx1 = i;
					maxIdx2 = j;
				}
				if(minPay > pay[i][j]) {
					minPay = pay[i][j];
					minIdx1 = i;
					minIdx2 = j;
				}
			}
		}
		System.out.println("관리비가 가장 많이 나온 집: " + apt[maxIdx1][maxIdx2]);
		System.out.println("관리비가 가장 적게 나온 집: " + apt[minIdx1][minIdx2]);
		
		// 문제4)
		System.out.print("호1 입력: ");
		int ho1 = scan.nextInt();
		System.out.print("호2 입력: ");
		int ho2 = scan.nextInt();
		
		if(ho1 == ho2) {
			System.out.println("같은 호는 입력할 수 없습니다.");
		} else {
			idx1 = -1;
			idx2 = -1;
			int idx3 = -1;
			int idx4 = -1;
			for(int i = 0; i < apt.length; i++) {
				for(int j = 0; j < apt[i].length; j++) {
					if(ho1 == apt[i][j]) {
						idx1 = i;
						idx2 = j;
					}
					if(ho2 == apt[i][j]) {
						idx3 = i;
						idx4 = j;
					}
				}
			}
			
			if(idx1 != -1 && idx3 != -1) {
				int temp = pay[idx1][idx2];
				pay[idx1][idx2] = pay[idx3][idx4];
				pay[idx3][idx4] = temp;
			} else {
				System.out.println("해당 호는 아파트에 없습니다.");
			}
			
			for(int i = 0; i < pay.length; i++) {
				for(int j = 0; j < pay[i].length; j++)
					System.out.print(pay[i][j] + " ");
				System.out.println();
			}
		}
		scan.close();
	}
}
