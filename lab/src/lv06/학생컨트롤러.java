package lv06;
/*
 * [학생 컨트롤러]
 * ㄴ 클래스(생성자) & 메서드
 * 
 * 생성자
 * ㄴ 객체 생성을 위한 것 (멤버변수를 초기화하기 위한 용도)
 * ㄴ 메소드와의 차이점: 리턴값이 존재하지 않음
 * ㄴ 클래스명(파라미터) { 실행문; }
 * 
 * 1. 추가
 * 1-1. 학생 등록
 * 1-2. 과목 (등록한 학생의 수강과목 추가)
 * 1-3. 성적 (수강 과목의 성적을 수정)
 * 2. 삭제
 * 2-1. 학생 탈퇴
 * 2-2. 과목 삭제
 * 3. 정렬 (학번, 이름, 성적 등)
 * 4. 출력
 * 5. 저장
 * 6. 로드
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

class Student {
	String name;
	int subCnt;
	Subject[] subjects;
	Subject[] tempSub;
	
	Student() { }
	
	Student(String name, int subCnt) {
		this.name = name;
		this.subCnt = subCnt;
		this.subjects = new Subject[subCnt];
		for(int i = 0; i < subCnt; i++)
			subjects[i] = new Subject();
	}
}

class Subject {
	String subName;
	int score;
	
	Subject() { }
	
	Subject(String subName, int score) {
		this.subName = subName;
		this.score = score;
	}
}

class Lms { // Learning Management System
	Scanner scan = new Scanner(System.in);
	
	String fileName = "student.txt";
	File file = new File(fileName);
	
	String brand;
	Student[] students;
	Student[] tempStu;
	int stuCnt;
	String tempName;
	
	Lms(String brand) {
		this.brand = brand;
	}
	
	void addNewStudent(int a) {
		for(int i = 0; i < a; i++) {
			students[i] = new Student(tempStu[i].name, tempStu[i].subCnt);
			for(int j = 0; j < tempStu[i].subCnt; j++) {
				students[i].subjects[j] = tempStu[i].subjects[j];
			}
		}
		students[a] = new Student();
		
		System.out.print("학생 이름 입력: ");
		students[a] = new Student(scan.next(), 1);
		
		System.out.print("과목 입력: ");
		students[a].subjects[0].subName = scan.next();
		
		System.out.print("성적 입력: ");
		students[a].subjects[0].score = scan.nextInt();
	}
	
	void addSubject(int a) {
		while(true) {
			students[a].tempSub = students[a].subjects;
			System.out.println("[1] 과목 추가");
			System.out.println("[2] 뒤로가기");
			System.out.print("번호 입력: ");
			int sel = scan.nextInt();
			
			if(sel == 1) {
				students[a].subCnt++;
				students[a].subjects = new Subject[students[a].subCnt];
				
				for(int i = 0; i < students[a].subCnt - 1; i++) {
					students[a].subjects[i] = new Subject(students[a].tempSub[i].subName,
							students[a].tempSub[i].score);
				}   
				students[a].subjects[students[a].subCnt - 1] = new Subject();
				
				System.out.print("과목 입력: ");
				String subjectName = scan.next();
				
				boolean check = true;
				while(check) {
					check = false;
					for(int i = 0; i < students[a].subCnt - 1; i++) {
						if(students[a].subjects[i].subName.equals(subjectName)) {
							System.out.println("이미 있는 과목명입니다. 다시 입력하세요.");
							System.out.print("과목 입력: ");
							subjectName = scan.next();
							check = true;
						}
					}
				}
				
				students[a].subjects[students[a].subCnt - 1].subName = subjectName;
				
				System.out.print("성적 입력: ");
				students[a].subjects[students[a].subCnt - 1].score = scan.nextInt();
				
			} else if(sel == 2) {
				System.out.println("홈 화면으로 돌아갑니다.");
				break;
			} else {
				System.out.println("화면의 번호 중에 입력하세요.");
			}
		}
	}
	
	void run() {
		while(true) {
			System.out.println("[학생 컨트롤러]");
			System.out.println("[1] 추가");
			System.out.println("[2] 삭제");
			System.out.println("[3] 정렬");
			System.out.println("[4] 출력");
			System.out.println("[5] 저장");
			System.out.println("[6] 로드");
			System.out.println("[7] 종료");
			System.out.print("번호 입력: ");
			int sel = scan.nextInt();
			
			if(sel == 1) {
				if(this.stuCnt == 0) {
					this.stuCnt++;
					this.students = new Student[this.stuCnt];
					
					addNewStudent(0);
					
					addSubject(0);
					
				} else {
					for(int i = 0; i < this.stuCnt; i++)
						System.out.println((i + 1) + ") " + students[i].name);
					System.out.println(this.stuCnt + 1 + ") 새 학생 추가");
					System.out.print("번호 입력: ");
					sel = this.scan.nextInt();
					
					while(sel < 1 || this.stuCnt + 1 < sel) {
						System.out.println("화면의 번호 중에 입력하세요.");
						System.out.print("다시 입력: ");
						sel = this.scan.nextInt();
					}
					
					if(sel == this.stuCnt + 1) {
						tempStu = students;
						this.stuCnt++;
						this.students = new Student[this.stuCnt];
						
						addNewStudent(this.stuCnt - 1);
						
						addSubject(this.stuCnt - 1);
						
					} else {
						int idx = sel - 1;
						
						addSubject(idx);
					}
				}
				
			} else if(sel == 2) {
				if(this.stuCnt == 0) {
					System.out.println("삭제할 학생 정보가 없습니다.");
				} else {
					for(int i = 0; i < this.stuCnt; i++)
						System.out.println((i + 1) + ") " + students[i].name);
					System.out.print("삭제할 학생 번호 입력: ");
					int idx = this.scan.nextInt() - 1;
					
					while(idx < 0 || this.stuCnt - 1 < idx) {
						System.out.println("화면의 번호 중에 입력하세요.");
						System.out.print("다시 입력: ");
						idx = this.scan.nextInt() - 1;
					}
					
					System.out.println("1) 학생 탈퇴");
					System.out.println("2) 과목 삭제");
					System.out.print("번호 입력: ");
					sel = this.scan.nextInt();
					
					while(sel < 1 || 2 < sel) {
						System.out.println("화면의 번호 중에 입력하세요.");
						System.out.print("다시 입력: ");
						sel = this.scan.nextInt();
					}
					
					if(students[idx].subCnt == 1)
						sel = 1;
					
					if(sel == 1) {
						System.out.println(students[idx].name + " 학생이 정상적으로 탈퇴되었습니다.");
						
						tempStu = students;
						this.stuCnt--;
						this.students = new Student[this.stuCnt];
						
						int tempIdx = 0;
						for(int i = 0; i < this.stuCnt; i++) {
							if(i == idx)
								tempIdx++;
							students[i] = new Student(tempStu[tempIdx].name, tempStu[tempIdx].subCnt);
							for(int j = 0; j < tempStu[i].subCnt; j++) {
								students[i].subjects[j] = tempStu[tempIdx].subjects[j];
							}
							tempIdx++;
						}
						
					} else if(sel == 2) {
						for(int i = 0; i < students[idx].subCnt; i++)
							System.out.println((i + 1) + ") " + students[idx].subjects[i].subName);
						
						System.out.print("삭제할 과목 번호 입력: ");
						int subIdx = scan.nextInt() - 1;
						
						while(idx < 0 || students[idx].subCnt - 1 < idx) {
							System.out.println("화면의 번호 중에 입력하세요.");
							System.out.print("다시 입력: ");
							subIdx = this.scan.nextInt() - 1;
						}
						
						students[idx].tempSub = students[idx].subjects;
						students[idx].subCnt--;
						students[idx].subjects = new Subject[students[idx].subCnt];						
						
						int tempIdx = 0;
						for(int i = 0; i < students[idx].subCnt; i++) {
							if(i == subIdx)
								tempIdx++;
							students[idx].subjects[i] =
									new Subject(students[idx].tempSub[tempIdx].subName,
									students[idx].tempSub[tempIdx].score);
							tempIdx++;
						}						
					}
				}
				
			} else if(sel == 3) {
				if(this.stuCnt == 0) {
					System.out.println("정렬할 학생 정보가 없습니다.");
				} else {
					System.out.println("1) 이름 정렬");
					System.out.println("2) 성적 정렬");
					System.out.print("번호 입력: ");
					sel = scan.nextInt();
					
					while(sel < 1 || 2 < sel) {
						System.out.println("화면의 번호 중에 입력하세요.");
						System.out.print("다시 입력: ");
						sel = this.scan.nextInt();
					}
					
					if(sel == 1) {
						for(int i = 0; i < students.length; i++) {
							for(int j = i; j < students.length; j++) {
								if(students[i].name.compareTo(students[j].name) > 0) {
									Student tempStudent = students[i];
									students[i] = students[j];
									students[j] = tempStudent;
								}
							}
						}
						
					} else if(sel == 2) {
						System.out.print("정렬할 과목 입력: ");
						String sub = scan.next();
						
						boolean check = false;
						int[] scoreArr = new int[students.length];
						for(int i = 0; i < students.length; i++) {
							for(int j = 0; j < students[i].subjects.length; j++) {
								if(students[i].subjects[j].subName.equals(sub)) {
									scoreArr[i] = students[i].subjects[j].score;
									check = true;
								}
							}
						}
						
						if(check) {
							for(int i = 0; i < students.length; i++) {
								for(int j = i; j < students.length; j++) {
									if(scoreArr[i] < scoreArr[j]) {
										Student tempStudent = students[i];
										students[i] = students[j];
										students[j] = tempStudent;
										int temp = scoreArr[i];
										scoreArr[i] = scoreArr[j];
										scoreArr[j] = temp;
									}
								}
							}
							
						} else {
							System.out.println("유효하지 않은 과목명입니다.");
						}
					}				
				}
				
			} else if(sel == 4) {
				for(int i = 0; i < this.students.length; i++) {
					System.out.print((i + 1) + ") " + students[i].name + " 학생: ");
					for(int j = 0; j < students[i].subjects.length; j++) {
						System.out.print(students[i].subjects[j].subName + "(" + students[i].subjects[j].score + "점)");
						if(j < students[i].subjects.length - 1)
							System.out.print(", ");
					}
					System.out.println();
				}
				
			} else if(sel == 5) {
				try {
					FileWriter fw = new FileWriter(this.file);
					
					String data = "";
					
					for(int i = 0; i < this.stuCnt; i++) {
						data += students[i].name + ": ";
						for(int j = 0; j < students[i].subCnt; j++) {
							data += students[i].subjects[j].subName + "/" + students[i].subjects[j].score;
							if(j < students[i].subCnt - 1)
								data += ", ";
						}
						if(i < this.stuCnt - 1)
							data += "\n";
					}
					
					fw.write(data);
					fw.close();
					
					System.out.println("저장되었습니다.");
					
				} catch(Exception e) {
					e.printStackTrace();
					System.err.println("파일 저장 실패!");
				}
				
			} else if(sel == 6) {
				if(file.exists()) {
					try {
						FileReader fr = new FileReader(this.file);
						BufferedReader br = new BufferedReader(fr);
						
						this.stuCnt = 0;
						
						while(br.ready()) {
							String[] str1 = br.readLine().split(": ");
							
							this.stuCnt++;
							this.students = new Student[this.stuCnt];
							
							for(int i = 0; i < this.stuCnt - 1; i++) {
								students[i] = new Student(tempStu[i].name, tempStu[i].subCnt);
								for(int j = 0; j < tempStu[i].subCnt; j++) {
									students[i].subjects[j] = tempStu[i].subjects[j];
								}
							}
							
							students[this.stuCnt - 1] = new Student();
							students[this.stuCnt - 1].name = str1[0];
							
							String[] str2 = str1[1].split(", ");
							students[this.stuCnt - 1].subCnt = str2.length;
							students[this.stuCnt - 1].subjects = new Subject[students[this.stuCnt - 1].subCnt];
							
							for(int i = 0; i < students[this.stuCnt - 1].subCnt; i++) {
								String[] str3 = str2[i].split("/");
								students[this.stuCnt - 1].subjects[i] = new Subject(str3[0], Integer.parseInt(str3[1]));
							}
							
							this.tempStu = this.students;
						}
						
						fr.close();
						br.close();
						
						System.out.println("저장된 파일을 불러옵니다.");
						
					} catch(Exception e) {
						e.printStackTrace();
						System.out.println("파일 로드 실패!");
					}
				} else {
					System.out.println("불러올 파일이 없습니다.");
				}
				
			} else if(sel == 7) {
				System.out.println("학생 컨트롤러를 종료합니다.");
				break;
			} else {
				System.out.println("화면의 번호 중에 입력하세요.");
			}
			
			for(int i = 0; i < this.students.length; i++) {
				System.out.print((i + 1) + ") " + students[i].name + " 학생: ");
				for(int j = 0; j < students[i].subjects.length; j++) {
					System.out.print(students[i].subjects[j].subName + "(" + students[i].subjects[j].score + "점)");
					if(j < students[i].subjects.length - 1)
						System.out.print(", ");
				}
				System.out.println();
			}
			System.out.println("======================================");
		}
	}
}

public class 학생컨트롤러 {
	public static void main(String[] args) {
		
		Lms lms = new Lms("EZEN");
		lms.run();
		
	}
}
