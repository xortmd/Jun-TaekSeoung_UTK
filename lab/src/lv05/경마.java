package lv05;

public class 경마 {
	/*
	 * # 경마 게임
	 * 5마리의 말이 랜덤으로 한번에 일정거리 이동 가능함
	 * 이동한 거리의 합이 20 이상이면 도착
	 * 등수 출력
	 * 조건) 단! 동시 도착 예외 처리
	 */
	public static void main(String[] args) {
		
		int[][] horse = new int[5][20];
		int max = 20;
		
		int[] rank = new int[5];
		int[] total = new int[5];
		
		int horseRank = 1;
		
		// 첫화면 출력
		for(int i = 0; i < horse.length; i++) {
			for(int j = 0; j < horse[i].length; j++) {
				if(j == total[i])
					System.out.print("말" + (i + 1));
				else
					System.out.print("__");
			}
			System.out.println();
		}
		System.out.println();
		
		while(true) {
			try {
				Thread.sleep(1000);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			for(int i = 0; i < horse.length; i++) {
				int ranNum = (int)(Math.random()*3) + 1; // 1~3 칸 이동
				total[i] += ranNum;
				
				if(total[i] >= max)
					total[i] = max - 1;
				
				for(int j = 0; j < horse[i].length; j++) {
					if(j == total[i])
						System.out.print("말" + (i + 1));
					else
						System.out.print("__");
				}
				System.out.println();
			}
			System.out.println();
			
			// 순위 집계
			int rankCount = 0;
			for(int i = 0; i < total.length; i++) {
				if(total[i] == max - 1 && rank[i] == 0) {
					rank[i] = horseRank;
					rankCount++;
				}
			}
			
			horseRank += rankCount;
			
			// 게임 종료
			if(horseRank > 5) {
				for(int i = 0; i < rank.length; i++) {
					int tempCnt = 0; // 공동 확인
					for(int j = 0; j < rank.length; j++) {
						if(rank[i] == rank[j])
							tempCnt++;
					}
					
					if(tempCnt == 1)
						System.out.println("말" + (i + 1) + ": " + rank[i] + "등!");
					else
						System.out.println("말" + (i + 1) + ": 공동 " + rank[i] + "등!");
				}
				break;
			}			
		}
	}
}
