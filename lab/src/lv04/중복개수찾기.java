package lv04;

public class 중복개수찾기 {
	public static void main(String[] args) {
		
//		int[] a = {1, 1, 3, 3, 3, 100, 2, 2, 3, 1, 3, 100};
//		int[] a = {1, 1, 3, 3, 3, 100, 2, 2, 3, 1, 3, 100, 1, 1};
//		int[] a = {1, 2, 3, 4, 5};
		int[] a = {1, 1, 3, 3, 3, 4, 4};
		
		int[] b = new int[a.length];
		int count = 0;
		
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a.length; j++)
				if(a[i] == a[j])
					count++;
			b[i] = count;
			count = 0;
		}
		
		int maxCount = b[0];
		int minCount = b[0];
		
		for(int i = 0; i < b.length; i++) {
			if(maxCount <= b[i])
				maxCount = b[i];
			if(minCount >= b[i])
				minCount = b[i];
		}
		
		if(maxCount == minCount) {
			System.out.print("가장 많은 개수 출력: ");
			for(int i = 0; i < a.length; i++)
				System.out.print(a[i] + " ");
			System.out.println("===> " + maxCount + "개");
			
			System.out.print("가장 적은 개수 출력: ");
			for(int i = 0; i < a.length; i++)
				System.out.print(a[i] + " ");
			System.out.println("===> " + maxCount + "개");
			
		} else {
			System.out.print("가장 많은 개수 출력: ");
			for(int i = 0; i < a.length; i++) {
				if(b[i] == maxCount) {
					System.out.print(a[i] + " ");
					for(int j = i; j < a.length; j++)
						if(a[j] == a[i])
							b[j] = 0;
				}
			}
			System.out.println("===> " + maxCount + "개");
			
			System.out.print("가장 적은 개수 출력: ");
			for(int i = 0; i < a.length; i++) {
				if(b[i] == minCount) {
					System.out.print(a[i] + " ");
					for(int j = i; j < a.length; j++)
						if(a[j] == a[i])
							b[j] = 0;
				}
			}
			System.out.println("===> " + minCount + "개");
		}		
	}
}
