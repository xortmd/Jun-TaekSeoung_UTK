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
import java.util.Random;
import java.util.Scanner;

class Student2 {
	int number; // PK
	String name; // NOT NULL
	
	int subCnt;
	Subject2[] subjects;
	
	Student2(int number, String name) {
		this.number = number;
		this.name = name;
	}
	
	void printSubject() {
		for(int i = 0; i < this.subCnt; i++) {
			Subject2 subject = this.subjects[i];
			System.out.printf("ㄴ %d) %s (%d점)\n", i + 1,subject.title,subject.score);
		}
	}
}

class Subject2 {
	String title;
	int score;
	
	Subject2(String title) {
		this.title = title;
	}
}

class Lms2 { // Learning Management System
	Scanner scan;
	
	File file;
	FileWriter fw;
	FileReader fr;
	BufferedReader br;
	
	String brand;
	int cnt;
	Student2[] students;
	
	String[] subTitles = {"국어", "수학", "영어"};
	
	Lms2(String brand) {
		this.scan = new Scanner(System.in); // 생성단계에서 -> 멤버 초기화
		this.file = new File("lms.txt");
		this.brand = brand;
	}
	
	int printMenu() {
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
		return sel;
	}
	
	int printSubMenu() {
		System.out.println("1) 학생 등록: ");
		System.out.println("2) 수강 신청: ");
		System.out.println("3) 성적 입력: ");
		System.out.print("선택: ");
		int sel = this.scan.nextInt();
		return sel;
	}
	
	int printDeleteSubMenu() {
		System.out.println("1) 학생 탈퇴: ");
		System.out.println("2) 수강 취소: ");
		System.out.print("선택: ");
		int sel = this.scan.nextInt();
		return sel;
	}
	
	void addStudent() {
		System.out.println("등록할 학생명: ");
		String name = this.scan.next();
		
		int num = randomGenerator();
		
		Student2[] temp = this.students;
		this.students = new Student2[this.cnt + 1];
		for(int i = 0; i < this.cnt; i++)
			this.students[i] = temp[i];
		
		Student2 student = new Student2(num, name);
		this.students[cnt] = student;
		this.cnt++;
		
		System.out.printf("학번: %d, 학생명: %s 등록완료\n", num,name);
	}
	
	int randomGenerator() {
		Random ran = new Random();
		
		int num = 0;
		while(true) {
			num = ran.nextInt(8999) + 1000;
			
			boolean dupl = false;
			for(int i = 0; i < this.cnt; i++) {
				if(this.students[i].number == num)
					dupl = true;
			}
			
			if(!dupl)
				break;
		}
		return num;
	}
	
	void printStudentAll() {
		for(int i = 0; i < this.cnt; i++) {
			Student2 student = this.students[i];
			System.out.printf("%d) %s\n", student.number,student.name);
			student.printSubject();
		}
	}
	
	void printSubjectTitles() {
		for(int i = 0; i < this.subTitles.length; i++)
			System.out.printf("%d) %s\n", i + 1, this.subTitles[i]);
	}
	
	Student2 getStudent() {
		System.out.print("학번 입력: ");
		int num = this.scan.nextInt();
		
		for(int i = 0; i < this.cnt; i++) {
			Student2 student = this.students[i];
			if(student.number == num)
				return student; // return 키워드를 만나면 -> 메소드가 즉시 소멸
		}
		return null;
	}
	
	void addSubject() {
		Student2 student = getStudent();
		
		if(student != null) {
			printSubjectTitles();
			System.out.print("과목 선택: ");
			int idx = this.scan.nextInt() - 1;
			
			if(idx >= 0 && idx < this.subTitles.length) {
				Subject2[] temp = student.subjects;
				
				boolean dupl = false;
				for(int i = 0; i < student.subCnt; i++) {
					Subject2 subject = temp[i];
					if(subject.title.equals(this.subTitles[idx]))
						dupl = true;
				}
				
				if(!dupl) {
					student.subjects = new Subject2[student.subCnt + 1];
					for(int i = 0; i < student.subCnt; i++)
						student.subjects[i] = temp[i];
					
					student.subjects[student.subCnt] = new Subject2(this.subTitles[idx]);
					student.subCnt++;
					
					System.out.println("수강신청 완료");
				} else
					System.out.println("이미 신청한 과목입니다.");
			}			
		}
	}
	
	void updateScore() {
		Student2 student = getStudent();
		
		if(student != null) {
			student.printSubject();
			System.out.print("수정할 과목: ");
			int idx = this.scan.nextInt() - 1;
			
			if(idx >= 0 && idx < student.subCnt) {
				Subject2 subject = student.subjects[idx];
				
				System.out.printf("%s 성적: ", subject.title);
				int score = scan.nextInt();
				
				if(score >= 0 && score <= 100) {
					subject.score = score;
					System.out.println("성적이 반영되었습니다.");
				} else
					System.out.println("유효하지 않은 성적 값입니다.");
			}
		}
	}
	
	void removeStudent() {
		Student2 student = getStudent();
		if(student != null) {
			Student2[] temp = this.students;
			this.students = new Student2[this.cnt - 1];
			
			int idx = 0;
			for(int i = 0; i < this.cnt; i++) {
				if(student.number != temp[i].number) {
					this.students[idx] = temp[i];
					idx++;
				}
			}
			this.cnt--;
			System.out.println("학생 탈퇴 완료");
		}
	}
	
	void removeSubject() {
		Student2 student = getStudent();
		student.printSubject();
		System.out.print("삭제할 과목: ");
		int delIdx = this.scan.nextInt();
		
		if(delIdx >= 0 && delIdx < student.subCnt) {
			Subject2[] temp = student.subjects;
			student.subjects = new Subject2[student.subCnt - 1];
			
			int idx = 0;
			for(int i = 0; i < student.subCnt; i++) {
				if(delIdx != i) {
					student.subjects[idx] = temp[i];
					idx++;
				}
			}
			student.subCnt--;
			System.out.println("수강취소 완료");
		}
	}
	
	void sortStudent() {
		for(int i = 0; i < this.cnt; i++) {
			Student2 student = this.students[i];
			String first = this.students[i].name;
			
			for(int j = i; j < this.cnt; j++) {
				Student2 student2 = this.students[j];
				
				if(first.compareTo(student2.name) > 0) {
					this.students[i] = student2;
					this.students[j] = student;
				}
			}
		}
	}
	
	void saveData() {
		// 학번/학생명,수강과목1/성적1,수강과목2/성적2...
		String data = "";
		
		for(int i = 0; i < this.cnt; i++) {
			Student2 student = this.students[i];
			data += student.number + "/";
			data += student.name + "/";
			
			for(int j = 0; j < student.subCnt; j++) {
				Subject2 subject = student.subjects[j];
				
				data += ",";
				data += subject.title + "/";
				data += subject.score;
			}
			
			if(i < this.cnt - 1)
				data += "\n";
		}
		
		try {
			fw = new FileWriter(this.file);
			fw.write(data);
			fw.close();
			
			System.out.println("파일 저장 완료");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("파일 저장 실패");
		}
	}
	
	void loadData() {
//		saveData(); // 자동저장 처리 후, 로드
		
		this.cnt = 0;
		this.students = null;
		
		try {
			this.fr = new FileReader(this.file);
			this.br = new BufferedReader(this.fr);
			
			while(this.br.ready()) {
				String[] data = this.br.readLine().split(",");
				String[] info = data[0].split("/");
				int number = Integer.parseInt(info[0]);
				String name = info[1];
				
				Student2 student = new Student2(number, name);
				
				if(data.length > 1) {
					student.subCnt = data.length - 1;
					student.subjects = new Subject2[student.subCnt];
					
					for(int i = 1; i < data.length; i++) {
						info = data[i].split("/");
						
						Subject2 subject = new Subject2(info[0]);
						subject.score = Integer.parseInt(info[1]);
						
						student.subjects[i - 1] = subject;
					}
				}
				
				Student2[] temp = this.students;
				this.students = new Student2[this.cnt + 1];
				for(int i = 0; i < this.cnt; i++)
					this.students[i] = temp[i];
				this.students[this.cnt] = student;
				this.cnt++;
			}
			
			this.fr.close();
			this.br.close();
			
			System.out.println("파일 로드 성공");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("파일 로드 실패");
		}
	}
	
	void run() {
		while(true) {
			int sel = printMenu();
			
			if(sel == 1) {
				sel = printSubMenu();
				if(sel == 1) {
					addStudent();
				} else if(sel == 2) {
					addSubject();
				} else if(sel == 3) {
					updateScore();
				}
				
			} else if(sel == 2) {
				sel = printDeleteSubMenu();
				if(sel == 1) {
					removeStudent();
				} else if(sel == 2) {
					removeSubject();
				}
				
			} else if(sel == 3) {
				sortStudent();
				
			} else if(sel == 4) {
				printStudentAll();
				
			} else if(sel == 5) {
				saveData();
				
			} else if(sel == 6) {
				loadData();
				
			} else if(sel == 7) {
				System.out.println("학생 컨트롤러를 종료합니다.");
				break;
			} else {
				System.out.println("화면의 번호 중에 입력하세요.");
			}
			
		}
	}
}

public class 학생컨트롤러2 {
	public static void main(String[] args) {
		
		Lms2 lms = new Lms2("EZEN");
		lms.run();
		
	}
}
