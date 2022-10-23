package lv01;

public class 삼육구 {
	public static void main(String[] args) {
		
		int ran = (int)(Math.random()*50) + 1;
		
		int num10 = ran/10;
		int num1 = ran%10;
		
		if(num10%3 == 0 && num10 != 0 && num1%3 == 0 && num1 != 0) {
			System.out.println(ran + ": 짝짝");
		} else if(num10%3 == 0 && num10 != 0 || num1%3 == 0 && num1 != 0) {
			System.out.println(ran + ": 짝");
		} else {
			System.out.println(ran);
		}
		
	}
}