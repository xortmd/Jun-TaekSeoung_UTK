package lv04;

import java.util.Random;
import java.util.Scanner;

public class 빙고 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Random ran = new Random();

		int maxNum = 5;

		int[][] bingo = new int[maxNum][maxNum];

		int[] temp = new int[maxNum * maxNum];
		int win = 0;
		int index = 0;

		// 중복없이 숫자 저장
		for(int i = 0; i < bingo.length; i++) {
			for(int j = 0; j < bingo[i].length; j++) {
				boolean check = true;
				temp[index] = ran.nextInt(50) + 1;
				for(int k = 0; k < index; k++) {
					if(temp[k] == temp[index]) {
						j--;
						check = false;
					}
				}
				if(check) {
					bingo[i][j] = temp[index];
					index++;
				}
			}
		}

		while(true) {
			// 화면 출력
			for(int i = 0; i < bingo.length; i++) {
				for(int j = 0; j < bingo[i].length; j++) {
					if(bingo[i][j] == 0)
						System.out.print("■■ ");
					else
						System.out.printf("%2d ", bingo[i][j]);
				}
				System.out.println();
			}
			
			// 1빙고
			if(win >= 1) {
				System.out.println("1빙고~");
				break;
			}

			// 숫자 입력
			System.out.print("숫자 입력: ");
			int num = scan.nextInt();

			// 없는 숫자 확인
			boolean check = true;
			for(int i = 0; i < temp.length; i++) {
				if(num == temp[i])
					break;
				if(i == temp.length - 1)
					check = false;
			}
			if(!check) {
				System.out.println("빙고판에 없는 숫자입니다.");
				continue;
			// 이미 지운 숫자 확인
			} else {
				for(int i = 0; i < bingo.length; i++)
					for(int j = 0; j < bingo[i].length; j++)
						if(num == bingo[i][j])
							check = false;
				if(check)
					System.out.println("이미 지워진 숫자입니다.");
			}

			// 숫자 색칠
			for(int i = 0; i < bingo.length; i++)
				for(int j = 0; j < bingo[i].length; j++)
					if(bingo[i][j] == num)
						bingo[i][j] = 0;

			// 가로 빙고
			for(int i = 0; i < bingo.length; i++) {
				for(int j = 0; j < bingo[i].length; j++) {
					if(bingo[i][j] != 0)
						break;
					if(j == bingo[i].length - 1)
						win++;
				}
			}

			// 세로 빙고
			for(int i = 0; i < bingo[0].length; i++) {
				for(int j = 0; j < bingo.length; j++) {
					if(bingo[j][i] != 0)
						break;
					if(j == bingo[i].length - 1)
						win++;
				}
			}

			// 대각선 빙고
			for(int i = 0,j = 4; i <= 4 && j >= 0; i++,j--) {
				if(bingo[i][j] != 0)
					break;
				if(i == 4 && j == 0)
					win++;
			}
			for(int i = 0,j = 0; i <= 4 && j <= 4; i++,j++) {
				if(bingo[i][j] != 0)
					break;
				if(i == 4 && j == 4)
					win++;
			}

			System.out.println("====================");

		}
		scan.close();
	}
}
