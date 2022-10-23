package lv03;

import java.util.Scanner;

public class 숫자이동 {
	/*
	 * # 숫자이동[1단계]
	 * 1. 숫자2는 캐릭터이다.
	 * 2. 숫자1을 입력하면, 캐릭터가 왼쪽으로
	 * 	    숫자2를 입력하면, 캐릭터가 오른쪽으로 이동한다.
	 * 3. 단, 좌우 끝에 도달했을 때, 예외처리를 해야한다.
	 * 4. {0, 0, 2, 0, 0, 0, 0};  ==> 왼쪽 ==> {0, 2, 0, 0, 0, 0, 0};
	 * 
	 */

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int[] game = {0, 0, 2, 0, 0, 0, 0};
		int player = -1;
		
		for(int i = 0; i < 7; i++) {
			if(game[i] == 2) {
				player = i;
			}
		}
		
		while(true) {
			System.out.print("1.왼쪽  2.오른쪽 3.이동 종료");
			int sel = scan.nextInt();
			
			int x = player;
				
			if(sel == 1) {
				//if(player == 0) {
				//	System.out.println("왼쪽으로 이동할 수 없습니다.");
				//	player++;
				//}
				//player--;
				x--;
			} else if(sel == 2) {
				//if(player == 6) {
				//	System.out.println("오른쪽으로 이동할 수 없습니다.");
				//	player--;
				//}
				//player++;
				x++;
			} else if(sel == 3) {
				System.out.println("이동을 종료합니다.");
				break;
			} else {
				System.out.println("1~3의 숫자를 입력하세요.");
				continue;
			}
			
			//for(int i = 0; i < 7; i++) {
			//	game[i] = 0;
			//	if(i == player)
			//		game[i] = 2;
			//}
			
			game[player] = 0;
			
			if(x >= 0 && x < game.length)
				player = x;
			
			game[player] = 2;
				
			System.out.print("{");
			for(int i = 0; i < 7; i++) {
				if(game[i] == 2)
					System.out.print("옷");
				else
					System.out.print("__");
			}
			System.out.println("}");
			System.out.println("====================");
		}
		scan.close();
	}
}