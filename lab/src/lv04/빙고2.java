package lv04;

import java.util.Random;
import java.util.Scanner;

public class 빙고2 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Random ran = new Random();

		int maxNum = 5;

		int[][] bingo1 = new int[maxNum][maxNum];
		int[][] bingo2 = new int[maxNum][maxNum];

		int[] temp1 = new int[maxNum*maxNum];
		int[] temp2 = new int[maxNum*maxNum];
		int win1 = 0;
		int win2 = 0;
		int index1 = 0;
		int index2 = 0;
		int player = 1;
		boolean check = true; // 숫자 저장할 때 사용

		// 중복없이 숫자 저장(p1)
		for(int i = 0; i < bingo1.length; i++) {
			for(int j = 0; j < bingo1[i].length; j++) {
				check = true;
				temp1[index1] = ran.nextInt(50) + 1;
				for(int k = 0; k < index1; k++) {
					if(temp1[k] == temp1[index1]) {
						j--;
						check = false;
					}
				}
				if(check) {
					bingo1[i][j] = temp1[index1];
					index1++;
				}
			}
		}
		// 중복없이 숫자 저장(p2)
		for(int i = 0; i < bingo2.length; i++) {
			for(int j = 0; j < bingo2[i].length; j++) {
				check = true;
				temp2[index2] = ran.nextInt(50) + 1;
				for(int k = 0; k < index2; k++) {
					if(temp2[k] == temp2[index2]) {
						j--;
						check = false;
					}
				}
				if(check) {
					bingo2[i][j] = temp2[index2];
					index2++;
				}
			}
		}

		while(true) {
			// 화면 출력(p1)
			System.out.println("┌────────[p1]────────┐ ┌────────[p2]────────┐");
			for(int i = 0; i < maxNum; i++) {
				System.out.print("│");
				for(int j = 0; j < maxNum; j++) {
					if(bingo1[i][j] == 0)
						System.out.print(" ■■ ");
					else
						System.out.printf(" %2d ", bingo1[i][j]);
				}
				System.out.print("│ │");
				for(int j = 0; j < maxNum; j++) {
					if(bingo2[i][j] == 0)
						System.out.print(" ■■ ");
					else
						System.out.printf(" %2d ", bingo2[i][j]);
				}
				System.out.print("│");
				System.out.println();
			}
			System.out.println("└────────────────────┘ └────────────────────┘");
			
			// 게임 종료(3빙고)
			if(win1 >= 3 && win2 >= 3) {
				System.out.println("[p1] " + win1 + "BINGO!");
				System.out.println("[p2] " + win2 + "BINGO!");
				break;
			} else if(win1 >= 3) {
				System.out.println("[p1] " + win1 + "BINGO!");
				break;
			} else if(win2 >= 3) {
				System.out.println("[p2] " + win2 + "BINGO!");
				break;
			}

			// 숫자 입력
			System.out.print("[p" + player + "] 숫자 입력: ");
			int num = scan.nextInt();

			// 이미 지운 숫자 확인(temp에 있는데 bingo에 없으면)
			boolean check1 = false;
			boolean check2 = false;
			for(int i = 0; i < maxNum*maxNum; i++) {
				if(num == temp1[i])
					check1 = true;
				if(num == temp2[i])
					check2 = true;
			}
			boolean bingo1Check = false;
			boolean bingo2Check = false;
			for(int i = 0; i < maxNum; i++) {
				for(int j = 0; j < maxNum; j++) {
					if(num == bingo1[i][j])
						bingo1Check = true;
					if(num == bingo2[i][j])
						bingo2Check = true;
				}
			}
			if((check1 || check2) && (!bingo1Check && !bingo2Check)) {
				System.out.println("이미 호명한 숫자입니다.");
				continue;
			}
			
			// 숫자 색칠
			for(int i = 0; i < maxNum; i++) {
				for(int j = 0; j < maxNum; j++) {
					if(bingo1[i][j] == num)
						bingo1[i][j] = 0;
					if(bingo2[i][j] == num)
						bingo2[i][j] = 0;
				}
			}
			
			// 빙고 확인
			int count1 = 0;
			int count2 = 0;
			win1 = 0;
			win2 = 0;
			
			// 가로 빙고
			for(int i = 0; i < maxNum; i++) {
				count1 = 0;
				count2 = 0;
				for(int j = 0; j < maxNum; j++) {
					if(bingo1[i][j] == 0)
						count1++;
					if(bingo2[i][j] == 0)
						count2++;
				}
				if(count1 == 5)
					win1++;
				if(count2 == 5)
					win2++;
			}

			// 세로 빙고
			for(int i = 0; i < maxNum; i++) {
				count1 = 0;
				count2 = 0;
				for(int j = 0; j < maxNum; j++) {
					if(bingo1[j][i] == 0)
						count1++;
					if(bingo2[j][i] == 0)
						count2++;
				}
				if(count1 == 5)
					win1++;
				if(count2 == 5)
					win2++;
			}

			// 대각선 빙고
			count1 = 0;
			count2 = 0;
			for(int i = 0,j = 4; i <= 4 && j >= 0; i++,j--) {
				if(bingo1[i][j] == 0) // = bingo[i][maxNum - 1 - i]
					count1++;
				if(bingo2[i][j] == 0)
					count2++;
			}
			if(count1 == 5)
				win1++;
			if(count2 == 5)
				win2++;
			count1 = 0;
			count2 = 0;
			for(int i = 0,j = 0; i <= 4 && j <= 4; i++,j++) {
				if(bingo1[i][j] == 0) // = bingo[i][i]
					count1++;
				if(bingo2[i][j] == 0)
					count2++;
			}
			if(count1 == 5)
				win1++;
			if(count2 == 5)
				win2++;

			System.out.println("=============================================");
			player = player == 1 ? 2 : 1;
		}
		scan.close();
	}
}
