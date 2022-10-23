package lv05;

import java.util.Scanner;

public class 단어맞추기 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int score = 100;
		String word = "performance";
		String meaning = "공연";
		int size = word.length();
		int[] check = new int[size];
		
		while(true) {
			System.out.println("뜻: " + meaning);
			System.out.print("문제: ");
			for(int i = 0; i < size; i++) {
				if(check[i] == 0)
					System.out.print("*");
				else
					System.out.print(word.charAt(i));
			}
			System.out.println();
			System.out.println("영어단어를 입력하세요 >>> ");
			String me = scan.next();
			
			if(me.equals(word)) {
				System.out.println("맞췄습니다. 점수는 " + score + "점입니다.");
				break;
			} else {
				boolean allOne = false;
				for(int i = 0; i < size; i++) {
					if(check[i] == 0)
						break;
					if(i == size - 1)
						allOne = true;
				}
				
				if(allOne) {
					System.out.println("GAME OVER!");
					break;
				}
				
				int ranIdx = (int)(Math.random()*size);
				while(check[ranIdx] == 1)
					ranIdx = (int)(Math.random()*size);
				
				for(int i = 0; i < size; i++)
					if(word.charAt(i) == word.charAt(ranIdx))
						check[i] = 1;
								
				score -= 5;
			}
		}
		
		scan.close();
	}
}
