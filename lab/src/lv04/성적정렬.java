package lv04;

public class 성적정렬 {
	public static void main(String[] args) {
		
		String[] name = {"홍길동", "김영", "자바킹", "민병철", "메가맨"};
		int[] score = {87, 42, 100, 11, 98};
		
		for(int i = 0; i < score.length; i++) {
			int maxScoreIndex = i;
			for(int j = i; j < score.length; j++) {
				if(score[maxScoreIndex] < score[j]) {
					maxScoreIndex = j;
				}
			}
			int tempScore = score[i];
			score[i] = score[maxScoreIndex];
			score[maxScoreIndex] = tempScore;
			
			String tempName = name[i];
			name[i] = name[maxScoreIndex];
			name[maxScoreIndex] = tempName;			
		}
		
		for(int i = 0; i < name.length; i++) {
			System.out.println((i + 1) + "등: " + name[i] + "(" + score[i] + "점)");
		}
	}
}
