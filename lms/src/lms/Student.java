package lms;

import java.util.ArrayList;

public class Student {
	
	// 모든 멤버변수는 private처리
	private int number;
	private String name;
	private ArrayList<Subject> subList;
	
	public Student(int number, String name) {
		this.number = number;
		this.name = name;
		this.subList = new ArrayList<Subject>();
	}
	
	// 모든 Getter & Setter는 public 처리
	public int getNumber() { // 조회만 가능
		return this.number;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Subject> getSubList() {
		return this.subList;
	}
	
	public void setSubList(ArrayList<Subject> subList) {
		this.subList = subList;
	}
	
	// 그 외 기능 메소드는 용도에 맞게 public 또는 private 처리
}
