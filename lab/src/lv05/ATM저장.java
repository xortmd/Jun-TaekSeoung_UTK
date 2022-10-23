package lv05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class ATM저장 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int cnt = 0;
		int log = -1;
		
		String[] accs = null;
		String[] pws = null;
		int[] moneys = null;
		
		String[] tempAccs = null;
		String[] tempPws = null;
		int[] tempMoneys = null;
		
		String fileName = "atm.txt";
		File file = new File(fileName);
		FileWriter fw = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		while(true) {
			System.out.println("accs = " + Arrays.toString(accs));
			System.out.println("pws = " + Arrays.toString(pws));
			System.out.println("moneys = " + Arrays.toString(moneys));
			System.out.println("cnt = " + cnt);
			System.out.println("log = " + log);
			System.out.println("--------------------");
			
			System.out.println("[ATM]");
			System.out.println("[1] 회원가입");
			System.out.println("[2] 회원탈퇴");
			System.out.println("[3] 로그인");
			System.out.println("[4] 로그아웃");
			System.out.println("[5] 입금");
			System.out.println("[6] 출금");
			System.out.println("[7] 이체");
			System.out.println("[8] 잔액조회");
			System.out.println("[9] 저장");
			System.out.println("[10] 로드");
			System.out.println("[0] 종료");
			System.out.print("메뉴 입력: ");
			int sel = scan.nextInt();
			
			if(1 <= sel && sel <= 3 && log != -1) {
				System.out.println("로그아웃 후 이용해주세요.");
				
			} else if(sel == 1) {
				System.out.print("회원가입할 계좌 입력: ");
				String acc = scan.next();
				
				System.out.print("비밀번호 입력: ");
				String pw = scan.next();
				
				cnt++;
				accs = new String[cnt];
				pws = new String[cnt];
				moneys = new int[cnt];
				
				for(int i = 0; i < accs.length - 1; i++) {
					accs[i] = tempAccs[i];
					pws[i] = tempPws[i];
					moneys[i] = tempMoneys[i];
				}
				
				accs[cnt - 1] = acc;
				pws[cnt - 1] = pw;
				
				System.out.println(acc + "님이 정상적으로 회원가입되었습니다.");
				
			} else if(sel == 2) {	
				System.out.print("탈퇴할 계좌 입력: ");
				String acc = scan.next();
				
				boolean check = true;
				int myIdx = -1;
				for(int i = 0; i < cnt; i++) {
					if(accs[i].equals(acc)) {
						myIdx = i;
						break;
					}
					if(i == cnt - 1)
						check = false;
				}
				
				if(check) {
					System.out.print("비밀번호 입력: ");
					String pw = scan.next();
					
					if(!pws[myIdx].equals(pw)) {
						System.out.println("비밀번호가 일치하지 않습니다.");
					} else {
						cnt--;
						accs = new String[cnt];
						pws = new String[cnt];
						moneys = new int[cnt];
						
						int tempIdx = 0;
						for(int i = 0; i < cnt + 1; i++) {
							if(i != myIdx) {
								accs[tempIdx] = tempAccs[i];
								pws[tempIdx] = tempPws[i];
								moneys[tempIdx] = tempMoneys[i];
								tempIdx++;
							}
						}
						
						System.out.println(tempAccs[myIdx] + "님이 정상적으로 탈퇴되었습니다.");
					}
				} else {
					System.out.println("유효하지 않은 계좌입니다.");
				}
				
			} else if(sel == 3) {
				System.out.print("계좌 입력: ");
				String acc = scan.next();
				
				boolean check = true;
				int myIdx = -1;
				for(int i = 0; i < cnt; i++) {
					if(accs[i].equals(acc)) {
						myIdx = i;
						break;
					}
					if(i == cnt - 1)
						check = false;
				}
				
				if(check) {
					System.out.print("비밀번호 입력: ");
					String pw = scan.next();
					
					if(!pws[myIdx].equals(pw)) {
						System.out.println("비밀번호가 일치하지 않습니다.");
					} else {
						log = myIdx;
						System.out.println(accs[myIdx] + "님 환영합니다.");
					}
				} else {
					System.out.println("유효하지 않은 계좌입니다.");
				}
				
			} else if(4 <= sel && sel <= 9 && log == -1) {
				System.out.println("로그인 후 이용해주세요.");
				
			} else if(sel == 4) {
				log = -1;
				System.out.println("로그아웃 되었습니다.");
				
			} else if(sel == 5) {
				System.out.print("입금할 금액 입력: ");
				int money = scan.nextInt();
				
				while(money <= 0) {
					System.out.println("0보다 큰 금액을 입력해주세요.");
					System.out.print("다시 입력: ");
					money = scan.nextInt();
				}
				
				moneys[log] += money;
				
				System.out.println(money + "원이 정상적으로 입금되었습니다.");
				
			} else if(sel == 6) {
				if(moneys[log] == 0) {
					System.out.println("잔액이 모자랍니다.");
					
				} else {
					System.out.print("출금할 금액 입력: ");
					int money = scan.nextInt();
					
					while(money <= 0 || moneys[log] < money) {
						System.out.println("0 이상 " + moneys[log] + " 이하의 금액을 입력해주세요.");
						System.out.print("다시 입력: ");
						money = scan.nextInt();
					}
					
					moneys[log] -= money;
					
					System.out.println(money + "원이 정상적으로 출금되었습니다.");
				}
				
			} else if(sel == 7) {
				if(moneys[log] == 0) {
					System.out.println("잔액이 모자랍니다.");
				} else {
					System.out.print("이체할 금액 입력: ");
					int money = scan.nextInt();
					
					while(money <= 0 || moneys[log] < money) {
						System.out.println("0 이상 " + moneys[log] + " 이하의 금액을 입력해주세요.");
						System.out.print("다시 입력: ");
						money = scan.nextInt();
					}
					
					System.out.print("이체할 계좌 입력: ");
					String acc = scan.next();
					
					boolean check = true;
					int yourIdx = -1;
					for(int i = 0; i < cnt; i++) {
						if(accs[i].equals(acc)) {
							yourIdx = i;
							break;
						}
						if(i == cnt - 1)
							check = false;
					}
					
					if(check) {
						moneys[log] -= money;
						moneys[yourIdx] += money;
						System.out.println(money + "원이 정상적으로 이체되었습니다.");
						
					} else {
						System.out.println("유효하지 않은 계좌입니다.");
					}
				}
				
			} else if(sel == 8) {
				System.out.println(accs[log] + "님의 계좌에는 현재 " + moneys[log] + "원이 있습니다.");
				
			} else if(sel == 9) {
				try {
					fw = new FileWriter(file);
					
					String accData = "";
					String pwData = "";
					String moneyData = "";
					
					for(int i = 0; i < cnt; i++) {
						accData += accs[i];
						pwData += pws[i];
						moneyData += moneys[i];
						if(i < cnt - 1) {
							accData += ", ";
							pwData += ", ";
							moneyData += ", ";
						}
					}
					
					fw.write(accData + "\n");
					fw.write(pwData + "\n");
					fw.write(moneyData);
					
					fw.close();
					
					System.out.println("저장이 완료되었습니다.");
					
				} catch(Exception e) {
					e.printStackTrace();
					System.err.println("저장 실패");
				}
				
			} else if(sel == 10) {
				if(file.exists()) {
					try {
						fr = new FileReader(file);
						br = new BufferedReader(fr);
						
						String[] accArr = br.readLine().split(", ");
						String[] pwArr = br.readLine().split(", ");
						String[] moneyArr = br.readLine().split(", ");
						
						cnt = accArr.length;
						accs = new String[cnt];
						pws = new String[cnt];
						moneys = new int[cnt];
						
						for(int i = 0; i < cnt; i++) {
							accs[i] = accArr[i];
							pws[i] = pwArr[i];
							moneys[i] = Integer.parseInt(moneyArr[i]);
						}
						
						fr.close();
						br.close();
						
						System.out.println("저장된 파일을 로드합니다.");
						
					} catch(Exception e) {
						e.printStackTrace();
						System.out.println("로드 실패");
					}
					
				} else {
					System.out.println("불러올 파일이 없습니다.");
				}
				
			} else if(sel == 0){
				System.out.println("ATM 이용을 종료합니다.");
				break;
			} else {
				System.out.println("0~10의 번호를 입력하세요.");
			}
			
			tempAccs = accs;
			tempPws = pws;
			tempMoneys = moneys;
			System.out.println("====================");
		}
		
		scan.close();
	}
}
