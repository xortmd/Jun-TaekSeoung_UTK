package lv05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class 벡터구현저장 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int[] vector = null;
		int[] temp = null;
		int count = 0;
		
		String fileName = "vector.txt";
//		String path = System.getProperty("user.home");
//		path += "\\desktop";
//		File file = new File(path, fileName);
		File file = new File(fileName);
		FileWriter fw = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		while(true) {
			System.out.println(Arrays.toString(vector));
			
			System.out.println("[벡터 컨트롤러]");
			System.out.println("[1]추가하기");
			System.out.println("[2]삭제하기");
			System.out.println("[3]저장하기");
			System.out.println("[4]로드하기");
			System.out.println("[5]종료하기");
			
			System.out.print("메뉴 선택: ");
			int sel = scan.nextInt();
			
			if(sel == 1) {
				System.out.print("추가할 값 입력: ");
				int num = scan.nextInt();
					
				count++;
				vector = new int[count];
			
				for(int i = 0; i < vector.length - 1; i++)
					vector[i] = temp[i];
					
				vector[count - 1] = num;
				
			} else if(sel == 2) {
//				System.out.print("삭제할 인덱스 입력: ");
//				int idx = scan.nextInt();
//				
//				while(idx < 0 || count - 1 < idx) {
//					System.out.println("범위를 벗어난 인덱스, 다시 입력: ");
//					idx = scan.nextInt();
//				}
//				
//				count--;
//				vector = new int[count];
//				
//				int tempIdx = 0;
//				for(int i = 0; i < vector.length; i++) {
//					if(i == idx)
//						tempIdx++;
//					vector[i] = temp[tempIdx];
//					tempIdx++;
//				}
				
				System.out.println("삭제할 값 입력: ");
				int delNum = scan.nextInt();
				
				int delCnt = 0;
				for(int i = 0; i < count; i++)
					if(delNum == vector[i])
						delCnt++;
						
				count -= delCnt;
				vector = new int[count];
				
				int idx = 0;
				for(int i = 0; i < temp.length; i++) {
					if(temp[i] != delNum) {
						vector[idx] = temp[i];
						idx++;
					}
				}
			
				
			} else if(sel == 3) {
				try {
					fw = new FileWriter(file);
					
					String data = "";
					
					for(int i = 0; i < vector.length; i++) {
						data += vector[i];
						if(i < vector.length - 1)
							data += ", ";
					}
					
					fw.write(data);
					
					fw.close();
					
					System.out.println("저장이 완료되었습니다.");
					
				} catch(Exception e) {
					e.printStackTrace();
					System.err.println("저장 실패");
				}
				
			} else if(sel == 4) {
				
//				count = 0;
//				vector = null;
				
				if(file.exists()) {
					try {
						fr = new FileReader(file);
						br = new BufferedReader(fr);
						
						String[] data = br.readLine().split(", ");
						
						count = data.length;
						vector = new int[count];
						
						for(int i = 0; i < data.length; i++)
							vector[i] = Integer.parseInt(data[i]);
						
						fr.close();
						br.close();
						
					} catch(Exception e) {
						e.printStackTrace();
						System.err.println("로드 실패");
					}	
				} else {
					System.out.println("파일이 없습니다.");
				}
				
			} else if(sel == 5) {
				System.out.println("벡터 컨트롤러를 종료합니다.");
				break;
			} else {
				System.out.println("1~5의 번호를 입력하세요.");
			}
			
			temp = vector;
			System.out.println("====================");
		}
		scan.close();
	}
}
