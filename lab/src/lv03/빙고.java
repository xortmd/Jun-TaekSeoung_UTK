package lv03;

public class 빙고 {
	public static void main(String[] args) {
		
		int arr[] = {
				0,0,0,
				0,0,0,
				3,3,3
		};
		
		for(int i = 0; i < arr.length; i++) {
			if(i%3 == 0 && i != 0)
				System.out.println();
			if(i == arr.length - 1)
				System.out.print(arr[i]);
			else
				System.out.print(arr[i] + ",");
			if(i%3 == 2 && arr[i] == 3 && arr[i - 1] == 3 && arr[i - 2] == 3)
				System.out.print(" --> 빙고!");
		}
		
		System.out.println();
		
		// count 활용
		for(int i = 0; i < arr.length; i += 3) {
			int count = 0;
			for(int j = 0; j < 3; j++) {
				if(arr[i + j] == 3) {
					count++;
				}
			}
			if(count == 3) {
				System.out.println("빙고!");
			}
		}
		
		
	}
}
//문제1) 배열을 위와 같이3줄씩 출력