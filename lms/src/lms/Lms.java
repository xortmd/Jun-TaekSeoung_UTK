package lms;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Lms {
	Scanner scan;
	
	private String brand;
	
	private String[] subTitles = {"국어", "수학", "영어"};
	
	public Lms(String brand) {
		this.scan = new Scanner(System.in);
		this.brand = brand;
	}
	
	private ArrayList<Student> list = new ArrayList<>();
	
	public int printMenu() {
		System.out.println("[" + this.brand + " 학생 컨트롤러]");
		System.out.println("[1] 추가");
		System.out.println("[2] 삭제");
		System.out.println("[3] 정렬");
		System.out.println("[4] 출력");
		System.out.println("[5] 저장");
		System.out.println("[6] 로드");
		System.out.println("[7] 종료");
		System.out.print("번호 입력: ");
		int sel = this.scan.nextInt();
		return sel;
	}
	
	public int printSubMenu() {
		System.out.println("1) 학생 등록: ");
		System.out.println("2) 수강 신청: ");
		System.out.println("3) 성적 입력: ");
		System.out.print("번호 입력: ");
		int sel = this.scan.nextInt();
		return sel;
	}
	
	public int randomGenerator() {
		Random ran = new Random();
		
		int rNum = 0;
		
		boolean check = true;
		while(check) {
			check = false;
			rNum = ran.nextInt(9000) + 1000;
			for(Student s : list)
				if(s.getNumber() == rNum)
					check = true;
		}
		return rNum;
	}
	
	public void printSubjectTitle() {
		for(int i = 0; i < this.subTitles.length; i++)
			System.out.println((i + 1) + ") " + subTitles[i]);
	}
	
	int printDeleteSubMenu() {
		System.out.println("1) 학생 탈퇴: ");
		System.out.println("2) 수강 취소: ");
		System.out.print("번호 입력");
		int sel = this.scan.nextInt();
		return sel;
	}
	
	public void addStudent() {
		System.out.print("등록할 학생명 입력: ");
		String name = scan.next();
		
		int num = randomGenerator();
		
		list.add(new Student(num, name));
		
		System.out.println(name + " 학생(" + num + "번) 등록완료");
	}
	
	public void addSubject() {
		System.out.print("학번 입력: ");
		int number = scan.nextInt();
		
		Student student = null;
		ArrayList<Subject> subjects = null;
		
		int idx = -1;
		for(Student s : list) {
			if(number == s.getNumber()) {
				idx = list.indexOf(s);
				student = new Student(s.getNumber(), s.getName());
				subjects = s.getSubList();
			}
		}
		
		if(idx != -1) {
			printSubjectTitle();
			System.out.print("신청할 과목 번호 입력: ");
			int subIdx = scan.nextInt() - 1;
			
			if(subIdx < 0 || subTitles.length - 1 < subIdx) {
				System.out.println("화면의 번호 중에 입력하세요.");
			} else {
				boolean check = true;
				for(Subject s : subjects)
					if(subTitles[subIdx].equals(s.getTitle()))
						check = false;
				
				if(check) {
					subjects.add(new Subject(subTitles[subIdx]));
					student.setSubList(subjects);
					
					list.set(idx, student);
					
					System.out.println("수강신청 완료");
				} else {
					System.out.println("이미 신청한 과목입니다.");
				}
			}
			
		} else {
			System.out.println("유효하지 않은 학번입니다.");
		}
	}
	
	public void updateScore() {
		System.out.print("학번 입력: ");
		int number = scan.nextInt();
		
		Student student = null;
		ArrayList<Subject> subjects = null;
		
		int idx = -1;
		for(Student s : list) {
			if(number == s.getNumber()) {
				idx = list.indexOf(s);
				student = new Student(s.getNumber(), s.getName());
				subjects = s.getSubList();
			}
		}
		
		if(idx != -1) {
			for(int i = 0; i < subjects.size(); i++)
				System.out.println((i + 1) + ") " + subjects.get(i).getTitle());
			
			System.out.print("수정할 과목 번호 입력: ");
			int subIdx = scan.nextInt() - 1;
			
			if(subIdx < 0 || subjects.size() - 1 < subIdx)
				System.out.println("화면의 번호 중에 입력하세요.");
			else {
				System.out.print("수정할 성적 입력: ");
				int score = scan.nextInt();
				
				if(0 <= score && score <= 100) {
					Subject subject;
					subject = subjects.get(subIdx);
					subject.setScore(score);
					
					subjects.set(subIdx, subject);
					student.setSubList(subjects);
					list.set(idx, student);
					
					System.out.println("성적이 반영되었습니다.");
				} else
					System.out.println("유효하지 않은 성적 값입니다.");
			}
			
			
		} else {
			System.out.println("유효하지 않은 학번입니다.");
		}		
	}
	
	void removeStudent() {
		System.out.print("학번 입력: ");
		int number = scan.nextInt();
		
		int idx = -1;
		for(Student s : list)
			if(number == s.getNumber())
				idx = list.indexOf(s);
		
		if(idx != -1) {
			list.remove(idx);
			System.out.println("학생 탈퇴 완료");			
		} else {
			System.out.println("유효하지 않은 학번입니다.");
		}
	}
	
	void removeSubject() {
		System.out.print("학번 입력: ");
		int number = scan.nextInt();
		
		Student student = null;
		ArrayList<Subject> subjects = null;
		
		int idx = -1;
		for(Student s : list) {
			if(number == s.getNumber()) {
				idx = list.indexOf(s);
				student = new Student(s.getNumber(), s.getName());
				subjects = s.getSubList();
			}
		}
		
		if(idx != -1) {
			for(int i = 0; i < subjects.size(); i++)
				System.out.println((i + 1) + ") " + subjects.get(i).getTitle());
			
			System.out.print("취소할 과목 번호 입력: ");
			int subIdx = scan.nextInt() - 1;
			
			if(subIdx < 0 || subjects.size() - 1 < subIdx) {
				System.out.println("화면의 번호 중에 입력하세요.");
			} else {
				subjects.remove(subIdx);
				
				student.setSubList(subjects);
				
				list.set(idx, student);
				
				System.out.println("수강신청 취소");
			}
			
		} else {
			System.out.println("유효하지 않은 학번입니다.");
		}
	}
	
	public int printSortSubMenu() {
		System.out.println("1) 이름 정렬");
		System.out.println("2) 학번 정렬");
		System.out.print("번호 입력: ");
		int sel = this.scan.nextInt();
		return sel;
	}
	
	public void sortStudentName() {
		for(int i = 0; i < list.size(); i++) {
			for(int j = i; j < list.size(); j++) {
				if(list.get(i).getName().compareTo(list.get(j).getName()) > 0) {
					Student temp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
		}
		System.out.println("정렬이 완료되었습니다.");
	}
	
	public void sortStudentNumber() {
		for(int i = 0; i < list.size(); i++) {
			for(int j = i; j < list.size(); j++) {
				if(list.get(i).getNumber() > list.get(j).getNumber()) {
					Student temp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
		}
		System.out.println("정렬이 완료되었습니다.");
	}
	
	public void printStudentAll() {
		for(int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i).getName() + " 학생(" + list.get(i).getNumber() + "번): ");
			for(int j = 0; j < list.get(i).getSubList().size(); j++) {
				System.out.print(list.get(i).getSubList().get(j).getTitle() + "(" + list.get(i).getSubList().get(j).getScore() + ")");
				if(j < list.get(i).getSubList().size() - 1)
					System.out.print(", ");
			}
			System.out.println();
		}
	}
	
	public void saveData() {
		// 학번/학생명: 수강과목1/성적1, 수강과목2/성적2, ...
		String data = "";
		
		for(int i = 0; i < list.size(); i++) {
			data += list.get(i).getNumber() + "/";
			data += list.get(i).getName() + ": ";
			
			for(int j = 0; j < list.get(i).getSubList().size(); j++) {
				data += list.get(i).getSubList().get(j).getTitle() + "/";
				data += list.get(i).getSubList().get(j).getScore();
				if(j < list.get(i).getSubList().size() - 1)
					data += ", ";
			}
			
			if(i < list.size() - 1)
				data += "\n";
		}
		
		FileManager fm = new FileManager();
		
		fm.fw(data);
	}
	
	public void loadData() {
		
		FileManager fm = new FileManager();
		
		this.list = fm.fr();		
	}	
	
	public void run() {
		while(true) {
			int sel = printMenu();
			
			if(sel == 1) {
				sel = printSubMenu();
				
				if(sel == 1)
					addStudent();
					
				else if(sel == 2)
					addSubject();
					
				else if(sel == 3)
					updateScore();
					
				else
					System.out.println("화면의 번호 중에 입력하세요.");
				
			} else if(sel == 2) {
				sel = printDeleteSubMenu();
				
				if(sel == 1)
					removeStudent();
					
				else if(sel == 2)
					removeSubject();
				
				else
					System.out.println("화면의 번호 중에 입력하세요.");				
				
			} else if(sel == 3) {
				sel = printSortSubMenu();
				
				if(sel == 1)
					sortStudentName();
				
				else if(sel == 2)
					sortStudentNumber();
				
				else
					System.out.println("화면의 번호 중에 입력하세요.");	
				
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
			
			System.out.println("===============================");
		}
	}
	
}
