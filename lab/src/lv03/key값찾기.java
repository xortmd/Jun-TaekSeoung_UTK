package lv03;

import java.util.Scanner;

public class key값찾기 {
	/*
	 * [반복문 심화예제]
	 * 
	 * 1. -1이 나올 때까지 일련의 수를 입력받는다.
	 * 2. 위 수 중 key 값이 몇 번째에 포함되어 있는가를 출력하는 프로그램을 작성하시오.
	 * 3. 단, key가 여러개 포함되어 있을 경우 앞에 나타난 것의 위치를 출력하시오.
	 * 4. key가 일련의 수 안에 없는 경우 "not found"를 출력하시오.
	 * 
	 * 예) 
	 * result 값 입력 : 99
	 * 
	 * 입력 : 10
	 * 입력 : 99
	 * 입력 : 20
	 * 입력 : 99
	 * 입력 : 30
	 * 입력 : 99
	 * 입력 : 10
	 * 입력 : -1
	 * 
	 * 결과 : key값 99가 첫 번째 나타난 것은 2번째 이다.
	 */

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
        
		System.out.print("result 값 입력: ");
		int result = scan.nextInt();
        
		int key = 0;
		int seq = 0; // 입력 순서
		int count = 0; // key값 개수
		int firstSeq = 0; // 처음 key값 인덱스
		
		while(key != -1) {
			System.out.print("key값 입력 : ");
			key = scan.nextInt();
			
			seq++;
			
			if(key == result) {
				count++;
				if(count == 1) {
					firstSeq = seq;
				}
			}
		}

		if(count == 0) {
			System.out.println("key는 입력받은 적이 없습니다.");		
		} else {
			System.out.println("key값은 " + count + "번 입력되었고 첫 번째에 나타난 것은 " + firstSeq + "번째입니다.");
		}
		scan.close();
	}

}