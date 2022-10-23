package lv04;

public class 오름차순정렬 {
	public static void main(String[] args) {
		
		int[] score = {10, 50, 30, 40, 80, 7};
		
//		for(int i = 0; i < score.length; i++) {
//			for(int j = i; j < score.length; j++) {
//				if(score[i] < score[j]) {
//					int temp = score[i];
//					score[i] = score[j];
//					score[j] = temp;
//				}
//			}
//		}
		
		for(int i = 0; i < score.length; i++) {
			int maxIndex = i;
			for(int j = i; j < score.length; j++) {
				if(score[maxIndex] < score[j]) {
					maxIndex = j;
				}
			}
			int temp = score[i];
			score[i] = score[maxIndex];
			score[maxIndex] = temp;
		}
		
		for(int i = 0; i < score.length; i++) {
			System.out.print(score[i] + " ");
		}
		
	}
}
