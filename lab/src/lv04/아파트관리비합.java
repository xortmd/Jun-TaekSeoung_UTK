package lv04;

import java.util.Scanner;

public class 아파트관리비합 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int[][] arr = {
				{101, 102, 103, 104},
				{201, 202, 203, 204},
				{301, 302, 303, 304}
		};
		
		int[] garo = new int[3];
		int[] sero = new int[4];
		
		for(int i = 0; i < garo.length; i++) {
			for(int j = 0; j < sero.length; j++) {
				garo[i] += arr[i][j];
				sero[j] += arr[i][j];
			}
		}
		System.out.print("가로 합: ");
		for(int i = 0; i < garo.length; i++)
			System.out.print(garo[i] + " ");
		System.out.println();
		System.out.print("세로 합: ");
		for(int i = 0; i < sero.length; i++)
			System.out.print(sero[i] + " ");
		
//		// 문제1)
//		System.out.print("가로 합: ");
//		for(int i = 0; i < arr.length; i++) {
//			int garoSum = 0;
//			for(int j = 0; j < arr[i].length; j++)
//				garoSum += arr[i][j];
//			System.out.print(garoSum + " ");
//		}
//		System.out.println();
//		
//		// 문제2)
//		System.out.print("세로 합: ");
//		for(int i = 0; i < arr[0].length; i++) {
//			int seroSum = 0;
//			for(int j = 0; j < arr.length; j++)
//				seroSum += arr[j][i];
//			System.out.print(seroSum + " ");
//		}
		
		scan.close();
	}
}
