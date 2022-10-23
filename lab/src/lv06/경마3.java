package lv06;

class Horse {
	int rank;
	int total;
}

class Racing {	
	final int HORSECNT = 5;
	final int SIZE = 20;
	Horse[] horse = new Horse[HORSECNT];
	int horseRank = 1;
	
	void run() {
		for(int i = 0; i < HORSECNT; i++)
			horse[i] = new Horse();
		
		while(horseRank < 6) {
			for(int i = 0; i < HORSECNT; i++) {
				for(int j = 0; j < SIZE; j++) {
					if(horse[i].total == j)
						System.out.print((i + 1) + "번 말");
					else
						System.out.print("__");
				}
				System.out.println();
			}
			
			int[] temp = new int[HORSECNT]; // 말 이동전에 랜덤값 저장
			int sameTimeGoalIn = 0; // 동시 골인 카운트
			int idx = -1; // 골인 말 기억
			
			for(int i = 0; i < HORSECNT; i++) {
				if(horse[i].total < SIZE - 1) {
					temp[i] = (int)(Math.random()*4);
					// 골인 카운트
					if(horse[i].total + temp[i] >= SIZE - 1) {
						sameTimeGoalIn++;
						idx = i;
					}
				}
				if(i == HORSECNT - 1) {
					// 동시 골인 (다시 뽑기)
					if(sameTimeGoalIn > 1) {
						i = -1;
						sameTimeGoalIn = 0;
						idx = -1;
					} else if(sameTimeGoalIn == 1) {
						horse[idx].rank = horseRank;
						horseRank++;
					}
				}
			}
			
			// 말 이동
			for(int i = 0; i < HORSECNT; i++) {
				horse[i].total += temp[i];
				if(horse[i].total >= SIZE - 1)
					horse[i].total = SIZE - 1;
			}			
			
			try {
				Thread.sleep(200);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("===========================================");
		}
		
		// 도착 출력
		for(int i = 0; i < HORSECNT; i++) {
			for(int j = 0; j < SIZE; j++) {
				if(horse[i].total == j)
					System.out.print((i + 1) + "번 말");
				else
					System.out.print("__");
			}
			System.out.println();
		}
		
		System.out.println("===========================================");
		
		// 결과 출력
		for(int i = 0; i < HORSECNT; i++)
			System.out.println((i + 1) + "번 말: " + horse[i].rank + "등");
	}
	
}

public class 경마3 {
	public static void main(String[] args) {
		
		Racing game = new Racing();
		game.run();
		
	}
}
