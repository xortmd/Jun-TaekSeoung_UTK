package lv05;

public class 경마2 {
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
				Thread.sleep(500);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			int[] ranNumArr = new int[5];
			for(int i = 0; i < total.length; i++) {
				ranNumArr[i] = (int)(Math.random()*4); // 0~3 칸 이동
				
				total[i] += ranNumArr[i];
				
				if(i == total.length - 1) {
					int count = 0;
					for(int j = 0; j < total.length; j++) {
						if(total[j] >= max - 1 && rank[j] == 0)
							count++;
					}
					if(count >= 2) {
						for(int j = 0; j < total.length; j++)
							total[j] -= ranNumArr[j];
						i = -1;
					}
				}
			}
			
			for(int i = 0; i < total.length; i++)
				if(total[i] >= max)
					total[i] = max - 1;
			
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
			
			// 순위 집계
			for(int i = 0; i < total.length; i++) {
				if(total[i] == max - 1 && rank[i] == 0) {
					rank[i] = horseRank;
					horseRank++;
				}
			}
			
			// 게임 종료
			if(horseRank > 5) {
				for(int i = 0; i < rank.length; i++)
					System.out.println("말" + (i + 1) + ": " + rank[i] + "등!");
				break;
			}			
		}
	}
}
