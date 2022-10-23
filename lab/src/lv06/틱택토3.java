package lv06;

import java.util.Scanner;

class Tictactoe {
	Box[] boxs = new Box[9];
	int player = 1;
	int gameOver;
	int turn;
}

class Box {
	int check;
}

public class 틱택토3 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Tictactoe game = new Tictactoe();
		
		for(int i = 0; i < game.boxs.length; i++) {
			game.boxs[i] = new Box();
			game.boxs[i].check = 0;
		}
		
		while(true) {
			for(int i = 0; i < game.boxs.length; i++) {
				if(game.boxs[i].check == 0)
					System.out.print("[ ]");
				else if(game.boxs[i].check == 1)
					System.out.print("[O]");
				else
					System.out.print("[X]");
				if(i%3 == 2)
					System.out.println();
			}
			
			if(game.gameOver != 0) {
				System.out.println("p" + game.gameOver + " WIN!");
				break;
			} else if(game.turn >= game.boxs.length) {
				System.out.println("DRAW!");
				break;
			}
			
			System.out.print("p" + game.player + " 입력: ");
			int idx = scan.nextInt();
			
			while(idx < 0 || game.boxs.length - 1 < idx || game.boxs[idx].check != 0) {
				System.out.println("유효하지 않은 인덱스입니다.");
				System.out.print("p" + game.player + " 다시 입력: ");
				idx = scan.nextInt();
			}
			
			game.turn++;
			game.boxs[idx].check = game.player;
			
			// 빙고 확인
			for(int i = 0; i < 3; i++) {
				int cnt = 0;
				for(int j = 0; j <= 6; j += 3) {
					if(game.boxs[i + j].check == game.player)
						cnt++;
					if(cnt == 3)
						game.gameOver = game.player;
				}
			}
			
			for(int i = 0; i <= 6; i += 3) {
				int cnt = 0;
				for(int j = 0; j < 3; j++) {
					if(game.boxs[i + j].check == game.player)
						cnt++;
					if(cnt == 3)
						game.gameOver = game.player;
				}
			}
			
			int cnt = 0;
			for(int i = 0; i < game.boxs.length; i += 4) {
				if(game.boxs[i].check == game.player)
					cnt++;
				if(cnt == 3)
					game.gameOver = game.player;
			}
			
			cnt = 0;
			for(int i = 2; i <= 6; i += 2) {
				if(game.boxs[i].check == game.player)
					cnt++;
				if(cnt == 3)
					game.gameOver = game.player;
			}
			
			game.player = game.player == 1 ? 2 : 1;
		}
		
		scan.close();
	}
}
