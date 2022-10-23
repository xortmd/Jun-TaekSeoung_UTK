package lv05;

import java.util.Arrays;

public class 구분자2 {
	public static void main(String[] args) {
		
		// 문제) 이름은 name배열에 성적은 score배열에 각각 저장 및 출력
		
		String str = "김철수/87,이만수/42,이영희/95";
	
		String[] name = new String[3];
		int[] score = new int[3];
		
		String[] nameScore1 = str.split(",");
		
		for(int i = 0; i < nameScore1.length; i++) {
			String[] nameScore2 = nameScore1[i].split("/");
			name[i] = nameScore2[0];
			score[i] = Integer.parseInt(nameScore2[1]);
		}
		
		System.out.println(Arrays.toString(name));
		System.out.println(Arrays.toString(score));
	}
}
