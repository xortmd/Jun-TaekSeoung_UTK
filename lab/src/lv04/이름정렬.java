package lv04;

public class 이름정렬 {
	public static void main(String[] args) {
		
		String[] students = {"정택승", "신재유", "김성태", "안형기", "송기영", "황태양"};
		
		for(int i = 0; i < students.length; i++) {
			for(int j = i; j < students.length; j++) {
				if(students[i].compareTo(students[j]) > 0) {
					String temp = students[i];
					students[i] = students[j];
					students[j] = temp;
				}
			}
		}
		
		for(int i = 0; i < students.length; i++)
			System.out.print(students[i] + " ");
		
	}
}