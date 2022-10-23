package lv05;

public class 데이터분류2 {
	public static void main(String[] args) {
		
		String data = "10001/김철수/600\n";
		data += "10002/이영희/800\n";
		data += "10001/김철수/1400\n";
		data += "10003/유재석/780\n";
		data += "10002/이영희/950\n";
		data += "10004/박명수/330\n";
		data += "10001/김철수/670\n";
		data += "10003/유재석/3300\n";
		data += "10002/이영희/200\n";
		data += "10004/박명수/6800\n";

        // 문제1) 위 데이터는 각각의 회원이 물건을 구입했을때마다 기록한 내용이다.
		//		 데이터를 아래와 같이 출력 하시요 (각 회원별 구입 총합)
		/*
		  =====================
			10001 김철수 2670
			10002 이영희 1950
			10003 유재석 4080
			10004 박명수 7130
		   =====================		  
		 */
		
		String[] dataArr = data.split("\n");
		String[] LengthCheck = dataArr[0].split("/");
		String[][] dataArr2 = new String[dataArr.length][LengthCheck.length];
		
		for(int i = 0; i < dataArr.length; i++) {
			String[] tempData = dataArr[i].split("/");
			int idx = -1;
			
			for(int j = 0; j < dataArr2.length; j++)
				if(dataArr2[j][0] != null && tempData[0].equals(dataArr2[j][0]))
					idx = j;
			
			if(idx == -1) {
				for(int j = 0; j < tempData.length; j++)
					dataArr2[i][j] = tempData[j];
			} else {
				int tempInt = Integer.parseInt(dataArr2[idx][2]);
				tempInt += Integer.parseInt(tempData[2]);
				dataArr2[idx][2] = String.valueOf(tempInt);
			}
		}
		
		System.out.println("====================");
		for(int i = 0; i < dataArr2.length; i++) {
			for(int j = 0; j < dataArr2[i].length; j++)
				if(dataArr2[i][j] != null)
					System.out.print(dataArr2[i][j] + " ");
			if(dataArr2[i][0] != null)
				System.out.println();
		}
		System.out.println("====================");
		
	}
}
