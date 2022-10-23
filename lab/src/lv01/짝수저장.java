package lv01;

public class 짝수저장 {
	public static void main(String[] args) {
		
		int arr[] = {10,20,30,40,50};	
		int b[] = new int[5];
		int count = b.length - 1;
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i]%4 == 0) {
				b[count] = arr[i];
				count--;
			}
		}
		
		System.out.print("b = {");
		for(int i = 0; i < b.length - 1; i++) {
			System.out.print(b[i] + ", ");
		}
		System.out.print(b[b.length - 1] + "}");
	}

}

