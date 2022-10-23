package lv03;

import java.util.Scanner;

public class 틱택토 {
	/* 
	 * # 틱택토
	 * === 틱택토 ===
	 * [X][X][O]
	 * [ ][O][ ]
	 * [ ][ ][ ]
	 * [p1]인덱스 입력 : 6
	 * === 틱택토 ===
	 * [X][X][O]
	 * [ ][O][ ]
	 * [O][ ][ ]
	 * [p1]승리
	 * 가로
	 * 세로
	 * 대각선 오왼
	 * 대각선 왼오
	 */

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int[] game = new int[9];
		int player = 0;
		int index;
		
		// 게임 실행
		boolean run = true;
		while(run) {
			// 화면 출력
			System.out.println("== 틱택토 ==");
			for(int i = 0; i < game.length; i++) {
				System.out.print("[" + (char)game[i] + "]");
				if(i%3 == 2)
					System.out.println();
			}
			
			// 입력
			System.out.print("[p" + (player%2 + 1) + "]인덱스 입력: ");
			index = scan.nextInt();
			
			// 중복입력 예외처리
			if(game[index] != 0) {
				System.out.println("이미 입력된 인덱스입니다. 다시 입력하세요.");
				player--;
			} else {
				// 인덱스 마크
				if(player%2 == 0)
					game[index] = 'O';
				else
					game[index] = 'X';
			}
			
			for(int i = 0; i < 3; i++) {
				// 가로빙고
				if(game[i*3] != 0)
					if(game[i*3] == game[i*3 + 1] && game[i*3 + 1] == game[i*3 + 2])
						run = false;
				// 세로빙고
				if(game[i] != 0)
					if(game[i] == game[i + 3] && game[i + 3] == game[i + 6])
						run = false;
			}
			
			// 대각선빙고
			if(game[4] != 0)
				if(game[0] == game[4] && game[4] == game[8])
					run = false;
				else if(game[2] == game[4] && game[4] == game[6])
					run = false;
			
			// 게임 종료
			if(run == false) {
				// 화면 재출력
				System.out.println("== 틱택토 ==");
				for(int i = 0; i < game.length; i++) {
					System.out.print("[" + (char)game[i] + "]");
					if(i%3 == 2)
						System.out.println();
				}
				// 승리 플레이어
				System.out.print("[p" + (player%2 + 1) + "]승리");
			}
			player++;
		}
		scan.close();
	}
}