package lv03;

import java.util.Scanner;

public class 틱택토2 {
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
		int countO = 0;
		int countX = 0;
		int run = 1;
		
		// 게임 실행
		while(true) {
			// 화면 출력
			System.out.println("== 틱택토 ==");
			for(int i = 0; i < game.length; i++) {
				System.out.print("[" + (char)game[i] + "]");
				if(i%3 == 2)
					System.out.println();
			}
			
			// 게임 종료
			if(run == 0) {
				// 플레이어 승리
				System.out.println("[p" + ((player - 1)%2 + 1) + "]승리!");
				break;
			} else if(player == 9) {
				// 무승부
				System.out.println("무승부입니다.");
				break;
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
			
			// 가로빙고
			for(int i = 0; i <= 6; i += 3) {
				for(int j = 0; j < 3; j++) {
					if(game[i + j] == 'O')
						countO++;
					else if(game[i + j] == 'X')
						countX++;
				}
				if(countO == 3 || countX == 3)
					run = 0;
				countO = 0;
				countX = 0;
			}
			
			// 세로빙고
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j <= 6; j += 3) {
					if(game[i + j] == 'O')
						countO++;
					else if(game[i + j] == 'X')
						countX++;
				}
				if(countO == 3 || countX == 3)
					run = 0;
				countO = 0;
				countX = 0;
			}
			
			// 대각선빙고
			for(int i = 0; i <= 8; i += 4) {
				if(game[i] == 'O')
					countO++;
				else if(game[i] == 'X')
					countX++;
				if(countO == 3 || countX == 3)
					run = 0;
			}
			countO = 0;
			countX = 0;
			for(int i = 2; i <= 6; i += 2) {
				if(game[i] == 'O')
					countO++;
				else if(game[i] == 'X')
					countX++;
				if(countO == 3 || countX == 3)
					run = 0;
			}
			countO = 0;
			countX = 0;
			
			player++;
			// 삼항연산자를 활용해 turn = turn == 1 ? 2 : 1;로 처리해도됨(턴이 바뀌는거)
			// turn을 마킹하고(1, 2) 출력할때 O,X 처리해도됨
		}
		scan.close();
	}
}