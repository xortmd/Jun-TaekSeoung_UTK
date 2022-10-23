package lv05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class 쇼핑몰저장 {
	
//	* 실행 후 → items 파일이 존재하면 → 해당 파일에서 아이템 가져오고,
//	* 실행 후 → items 파일이 없으면 → 관리자 모드 상태로 → N회 items 입력 받기
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int count = 0;
		int log = -1;

		String[] ids = null;
		String[] pws = null;
		String[] items = null;
		
		String[] tempIds = null;
		String[] tempPws = null;
		String[] tempItems = null;

		int itemsSize = 0;
		int size = 0;
		int[][] jang = null; // new int[size][2]
		int[][] tempJang = null;

		String fileName = "jang.txt";
		File file = new File(fileName);
		String fileName2 = "items.txt";
		File file2 = new File(fileName2);
		FileWriter fw = null;
		FileReader fr = null;
		BufferedReader br = null;

		if(file2.exists()) {
			while(true) {
				System.out.println("ids = " + Arrays.toString(ids));
				System.out.println("pws = " + Arrays.toString(pws));

				System.out.println("[SHOP]");
				System.out.println("[1] 회원가입");
				System.out.println("[2] 회원탈퇴");
				System.out.println("[3] 로그인");
				System.out.println("[4] 로그아웃");
				System.out.println("[5] 쇼핑");
				System.out.println("[6] 장바구니");
				System.out.println("[7] 저장");
				System.out.println("[8] 로드");
				System.out.println("[0] 종료");

				System.out.print("메뉴 선택 : ");
				int sel = scan.nextInt();

				if(1 <= sel && sel <= 3 && log != -1) {
					System.out.println("로그아웃 후 이용해주세요.");
					
				} else if(sel == 1) {
					System.out.print("회원가입할 아이디 입력: ");
					String id = scan.next();
					
					System.out.print("비밀번호 입력: ");
					String pw = scan.next();
					
					count++;
					ids = new String[count];
					pws = new String[count];
					
					for(int i = 0; i < ids.length - 1; i++) {
						ids[i] = tempIds[i];
						pws[i] = tempPws[i];
					}
					
					ids[count - 1] = id;
					pws[count - 1] = pw;
					
					System.out.println(id + "님이 정상적으로 회원가입되었습니다.");
					
				} else if(sel == 2) {	
					System.out.print("탈퇴할 아이디 입력: ");
					String id = scan.next();
					
					boolean check = true;
					int myIdx = -1;
					for(int i = 0; i < count; i++) {
						if(ids[i].equals(id)) {
							myIdx = i;
							break;
						}
						if(i == count - 1)
							check = false;
					}
					
					if(check) {
						System.out.print("비밀번호 입력: ");
						String pw = scan.next();
						
						if(!pws[myIdx].equals(pw)) {
							System.out.println("비밀번호가 일치하지 않습니다.");
						} else {
							count--;
							ids = new String[count];
							pws = new String[count];
							
							int tempIdx = 0;
							for(int i = 0; i < count + 1; i++) {
								if(i != myIdx) {
									ids[tempIdx] = tempIds[i];
									pws[tempIdx] = tempPws[i];
									tempIdx++;
								}
							}
							
							System.out.println(tempIds[myIdx] + "님이 정상적으로 탈퇴되었습니다.");
						}
					} else {
						System.out.println("유효하지 않은 아이디입니다..");
					}
					
				} else if(sel == 3) {
					System.out.print("아이디 입력: ");
					String id = scan.next();
					
					boolean check = true;
					int myIdx = -1;
					for(int i = 0; i < count; i++) {
						if(ids[i].equals(id)) {
							myIdx = i;
							break;
						}
						if(i == count - 1)
							check = false;
					}
					
					if(check) {
						System.out.print("비밀번호 입력: ");
						String pw = scan.next();
						
						if(!pws[myIdx].equals(pw)) {
							System.out.println("비밀번호가 일치하지 않습니다.");
						} else {
							log = myIdx;
							System.out.println(ids[myIdx] + "님 환영합니다.");
						}
					} else {
						System.out.println("유효하지 않은 아이디입니다.");
					}
					
				} else if(4 <= sel && sel <= 7 && log == -1) {
					System.out.println("로그인 후 이용해주세요.");
					
				} else if(sel == 4) {
					log = -1;
					System.out.println("로그아웃 되었습니다.");
					
				} else if (sel == 5) {
					try {
						fr = new FileReader(file2);
						br = new BufferedReader(fr);
						
						String[] itemsData = br.readLine().split(", ");
						
						items = new String[itemsData.length];
						
						for(int i = 0; i < items.length; i++) {
							items[i] = itemsData[i];
						}
						
						for(int i = 0; i < items.length; i++)
							System.out.println(i + 1 + ") " + items[i]);
						System.out.print("품목 선택: ");
						sel = scan.nextInt();
						
						while(sel < 1 || items.length < sel) {
							System.out.print("화면의 번호 중에서 입력해주세요. 다시 입력: ");
							sel = scan.nextInt();
						}
						
						size++;
						jang = new int[size][2];
						
						for(int i = 0; i < size - 1; i++) {
							jang[i][0] = tempJang[i][0];
							jang[i][1] = tempJang[i][1];
						}
						
						jang[size - 1][0] = log;
						jang[size - 1][1] = sel;
						
						fr.close();
						br.close();
						
					} catch(Exception e) {
						e.printStackTrace();
						System.err.println("파일 불러오기 실패");
					}
					
				} else if (sel == 6) {
					int[] countArr = new int[items.length];
					for(int i = 0; i < ids.length; i++) {
						if(log == i) {
							System.out.println("[" + ids[i] + "님의 장바구니]");
							for(int j = 0; j < size; j++)
								if(i == jang[j][0])
									countArr[jang[j][1] - 1]++;
						}
					}
					boolean check = true;
					for(int i = 0; i < items.length; i++)
						if(countArr[i] != 0) {
							System.out.println("- " + items[i] + " " + countArr[i] + "개");
							check = false;
						}
					
					if(check)
						System.out.println("장바구니에 품목이 없습니다.");
					
				} else if (sel == 7) {
					try {
						fw = new FileWriter(file);
						
						String jangData = "";
						
						for(int i = 0; i < size; i++) {
							jangData += jang[i][0] + ", " + jang[i][1] + "\n";
						}
						
						fw.write(jangData);
						fw.close();
						
						System.out.println("저장이 완료되었습니다.");
					} catch(Exception e) {
						e.printStackTrace();
						System.err.println("저장 실패");
					}
					
				} else if (sel == 8) {
					try {
						fr = new FileReader(file);
						br = new BufferedReader(fr);
						
						size = 0;
						
						while(true) {
							String tempData = br.readLine();
							if(tempData == null)
								break;
							size++;
						}
						
						FileReader fr2 = new FileReader(file);
						BufferedReader br2 = new BufferedReader(fr2);

						jang = new int[size][2];
						for(int i = 0; i < size; i++) {
							String[] tempData = br2.readLine().split(", ");
							jang[i][0] = Integer.parseInt(tempData[0]);
							jang[i][1] = Integer.parseInt(tempData[1]);
						}
						
						fr2.close();
						br2.close();
						
					} catch(Exception e) {
						e.printStackTrace();
						System.err.println("로드 실패");
					}
					
				} else if (sel == 0) {
					System.out.println("프로그램 종료");
					break;
				} else {
					System.out.println("0~8의 번호를 입력하세요.");
				}
				
				tempIds = ids;
				tempPws = pws;
				tempJang = jang;
			}
			
		} else {
			System.out.println("실행할 파일이 없습니다. 관리자 모드로 전환합니다.");
			while(true) {
				System.out.println(Arrays.toString(items));
				System.out.println("[관리자 모드]");
				System.out.println("[1]아이템 추가");
				System.out.println("[2]저장");
				System.out.println("[3]종료");
				System.out.print("메뉴 선택: ");
				int sel = scan.nextInt();
				
				if(sel == 1) {
					System.out.print("아이템 입력: ");
					String item = scan.next();
					
					itemsSize++;
					items = new String[itemsSize];
					
					for(int i = 0; i < itemsSize - 1; i++)
						items[i] = tempItems[i];
					
					items[itemsSize - 1] = item;
				} else if(sel == 2) {
					try {
						fw = new FileWriter(file2);
						
						String itemsData = "";
						
						for(int i = 0; i < items.length; i++) {
							itemsData += items[i];
							if(i < items.length - 1)
								itemsData += ", ";
						}
						
						fw.write(itemsData);
						fw.close();
						System.out.println("저장이 완료되었습니다.");
						
					} catch(Exception e) {
						e.printStackTrace();
						System.err.println("저장 실패");
					}
					
				} else if(sel == 3) {
					System.out.println("프로그램 종료");
					break;
				} else {
					System.out.println("1~2의 번호를 입력하세요.");
				}
				
				tempItems = items;
			}			
		}
		scan.close();
	}
}