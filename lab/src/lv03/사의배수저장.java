package lv03;

public class 사의배수저장 {
	public static void main(String[] args) {
		
		int[] arr = {44, 11, 29, 24, 76};
		int[] temp = null;

		int count = 0;
		
		for(int i = 0; i < arr.length; i++)
			if(arr[i]%4 == 0)
				count++;
		
		temp = new int[count];
		count = 0;
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i]%4 == 0) {
				temp[count] = arr[i];
				count++;
			}
		}
		
		System.out.print("temp = ");
		for(int i = 0; i < temp.length; i++) {
			System.out.print(temp[i] + " ");
		}
		
	}
}
/*
 * # 4의 배수만 저장
 * - arr 배열에서 4의 배수만 골라 temp 배열에 저장
 * - 단! temp 의 길이를 4의 배수만큼만 설정한다. 
 */