package lv05;

public class 데이터분류 {
	public static void main(String[] args) {
		
		String nameData = "";
		nameData += "100001/이만수"; nameData += "\n";
		nameData += "100002/이영희"; nameData += "\n";
		nameData += "100003/유재석"; nameData += "\n";
		nameData += "100004/박명수"; nameData += "\n";
		nameData += "100005/김지연"; nameData += "\n";
		nameData += "100006/박연미";
		
		String moneyData = "";
		moneyData += "100001/600"; moneyData += "\n";
		moneyData += "100003/7000"; moneyData += "\n";
		moneyData += "100001/100"; moneyData += "\n";
		moneyData += "100002/400"; moneyData += "\n";
		moneyData += "100001/600"; moneyData += "\n";
		moneyData += "100004/900"; moneyData += "\n";
		moneyData += "100001/130"; moneyData += "\n";
		moneyData += "100003/200"; moneyData += "\n";
		moneyData += "100002/700"; moneyData += "\n";
		moneyData += "100002/900"; moneyData += "\n";
		moneyData += "100004/800";
		
		String userData = "";
		
		String[] nameArr = nameData.split("\n");
		String[] moneyArr = moneyData.split("\n");
		
		for(int i = 0; i < moneyArr.length; i++) {
			String[] tempMoneyArr = moneyArr[i].split("/");
			for(int j = 0; j < nameArr.length; j++) {
				String[] tempNameArr = nameArr[j].split("/");
				if(tempNameArr[0].equals(tempMoneyArr[0])) {
					userData += tempMoneyArr[0] + "/" + tempNameArr[1] + "/" + tempMoneyArr[1];
					if(i < moneyArr.length - 1)
						userData += "\n";
				}
			}
		}
		
		System.out.println(userData);
	}
}
