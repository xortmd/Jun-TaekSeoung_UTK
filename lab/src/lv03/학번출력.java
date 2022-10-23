package lv03;

public class 학번출력 {
	public static void main(String[] args) {
		
		int[] hakbuns = {1001, 1002, 1003, 1004, 1005};
		int[] scores  = {  87,   11,   45,   98,   23};
		
		int topScoreHakbun = hakbuns[0];
		int topScore = scores[0];
		
		for(int i = 1; i < hakbuns.length; i++) {
			if(topScore < scores[i]) {
				topScore = scores[i];
				topScoreHakbun = hakbuns[i];
			}
		}
		System.out.println(topScoreHakbun + "번(" + topScore + "점)");
	}
}
// 문제) 1등학생의 학번과 성적 출력
// 정답) 1004번(98점)