package lms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileManager {

	private File file = new File("lms.txt");
	private FileWriter fw;
	private FileReader fr;
	private BufferedReader br;
	
	public File getFile() {
		return this.file;
	}
	
	public void fw(String data) {
		try {
			fw = new FileWriter(this.file);
			
			fw.write(data);
			fw.close();
			
			System.out.println("파일 저장 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파일 저장 실패");
		}
	}
	
	public ArrayList<Student> fr() {
		// 학번/학생명: 수강과목1/성적1, 수강과목2/성적2, ...
		try {
			this.fr = new FileReader(this.file);
			this.br = new BufferedReader(this.fr);
			
			ArrayList<Student> list = new ArrayList<>();
			
			while(this.br.ready()) {
				String[] data = this.br.readLine().split(": ");
				String[] info = data[0].split("/");
				
				Student student = new Student(Integer.parseInt(info[0]), info[1]);
				
				ArrayList<Subject> subList = new ArrayList<>();
				
				if(data.length > 1) {
					String[] info2 = data[1].split(", ");
					
					for(int i = 0; i < info2.length; i++) {
						String[] info3 = info2[i].split("/");
						
						Subject subject = new Subject(info3[0]);
						subject.setScore(Integer.parseInt(info3[1]));
						
						subList.add(subject);
					}
				}
				
				student.setSubList(subList);
				
				list.add(student);
			}
			
			this.fr.close();
			this.br.close();
			
			System.out.println("파일 로드 성공");
			
			return list;
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("파일 로드 실패");
			return null;
		}
	}
	
}
