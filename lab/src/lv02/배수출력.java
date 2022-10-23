package lv02;

public class 배수출력 {
	public static void main(String[] args) {
		
		// 문제1
		for(int i = 0; true; i += 9) {
			if(i%10 == 6) {
				System.out.println("답: " + i);
				break;
			}
		}
		
		// 문제2
		for(int i = 0; true; i += 9) {
			if(i/10 == 6) {
				System.out.println("답: " + i);
				break;
			}
		}
		
		// 문제3
		for(int i = 0; true; i += 8) {
			if(i >= 150) {
				System.out.println("답: " + (i - 8));
				break;
			}
		}
	}
}
//for를 사용해서 풀어보세요 
//문제1) 9의 배수중 일의 자리가 6인 첫번째 배수 출력 ==> 답 : 36
//문제2) 9의 배수중 십의 자리가 6인 첫번째 배수 출력 ==> 답 : 63
//문제3) 8의 배수중  150보다 작고 150 에 가장 가까운수를 출력 ==> 답 : 144