package lv04;

public class 랜덤값저장 {
	public static void main(String[] args) {
		
		int[] arr = new int[5];
		
		// 값 저장
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()*10) + 1;
			for(int j = 0; j < i; j++) {
				if(arr[i] == arr[j])
					i--;
			}
		}
		
		// 출력
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		
	}
}
