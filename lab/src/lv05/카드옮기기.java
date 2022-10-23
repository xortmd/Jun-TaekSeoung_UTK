package lv05;

import java.util.Scanner;

public class 카드옮기기 {
	//문제1) 0 이 플레이어이다 
	// 1) left 2)right 3)up 4)down 5)되감기
	//번호를 입력받고 해당위치로 이동 ==> 이동할때 값을 서로 교환한다. 
	// 예) 1 ==> 
	/*
		    {1,2,3,4},
			{5,6,7,8},
			{9,10,11,12},
			{13,14,0,15}		
	 */
	
	//문제2) 심화  ==> 한칸한칸이동할때마다 yx 에 배열에 이동한경로를 저장했다가 
	// 5번입력시 ==> 왔던길로 되돌아가기 한다. 
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int game[][] = {
				{1,2,3,4},
				{5,6,7,8},
				{9,10,11,12},
				{13,14,15,0}
				};
		
		int moveCnt = 1;
		int[][] yx = {{3,3}};
		int[][] tempYX = yx;
		
		int pY = 3;
		int pX = 3;
		
		while(true) {
			System.out.println("┌────┬────┬────┬────┐");
			for(int i = 0; i < game.length; i++) {
				System.out.print("│");
				for(int j = 0; j < game[i].length; j++) {
					System.out.printf(" %2d ", game[i][j]);
					if(j < game[i].length - 1)
						System.out.print("│");
				}
				System.out.println("│");
				if(i < game.length - 1)
					System.out.println("├────┼────┼────┼────┤");
			}
			System.out.println("└────┴────┴────┴────┘");
			
			System.out.println("a(←) w(↑) d(→) s(↓) b(뒤로가기) m(종료)");
			System.out.print("입력: ");
			String move = scan.next();
			
			int y = -1;
			int x = -1;
			
			if(move.equals("a")) {
				y = pY;
				x = pX - 1;
				
			} else if(move.equals("w")) {
				y = pY - 1;
				x = pX;
				
			} else if(move.equals("d")) {
				y = pY;
				x = pX + 1;
				
			} else if(move.equals("s")) {
				y = pY + 1;
				x = pX;
				
			} else if(move.equals("b")) {
				if(moveCnt == 1) {
					System.out.println("더이상 뒤로갈 수 없습니다.");
					continue;
				} else {
					y = yx[moveCnt - 2][0];
					x = yx[moveCnt - 2][1];
					
					moveCnt--;
					yx = new int[moveCnt][2];
					
					for(int i = 0; i < yx.length; i++) {
						yx[i][0] = tempYX[i][0];
						yx[i][1] = tempYX[i][1];
					}
					
					tempYX = yx;
				}
				
			} else if(move.equals("m")) {
				System.out.println("게임을 종료합니다.");
				break;
				
			} else {
				System.out.println("화면의 키중에 입력하세요.");
				continue;
			}
			
			if(y < 0 || x < 0 || y > game.length - 1 || x > game.length - 1) {
				System.out.println("이동할 수 없습니다.");
			} else {
				int temp = game[pY][pX];
				game[pY][pX] = game[y][x];
				game[y][x] = temp;
				
				pY = y;
				pX = x;
				
				if(!move.equals("b")) {
					moveCnt++;
					yx = new int[moveCnt][2];
					
					for(int i = 0; i < tempYX.length; i++) {
						yx[i][0] = tempYX[i][0];
						yx[i][1] = tempYX[i][1];
					}
					
					yx[moveCnt - 1][0] = pY;
					yx[moveCnt - 1][1] = pX;
					
					tempYX = yx;
				}
			}
		}		
		scan.close();
	}
}
