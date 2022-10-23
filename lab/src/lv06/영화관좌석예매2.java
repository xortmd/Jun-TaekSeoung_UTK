package lv06;

import java.util.Scanner;

class Movie {
	String brand;
	int total;
	
	// 클래스 배열
	Seat[] seats = new Seat[7]; // null 초기화 -> new Seat()
}

class Seat {
	int price = 12000;
	int num;
	boolean isBooked;
}

public class 영화관좌석예매2 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Movie mov = new Movie();	
		for(int i = 0; i < mov.seats.length; i++)
			mov.seats[i] = new Seat();
		
		while(true) {
			System.out.println("[영화관 좌석예매]");
			System.out.println("1. 좌석예매");
			System.out.println("2. 좌석취소");
			System.out.println("3. 종료");
		
			System.out.print("[ ");
			for(int i = 0; i < mov.seats.length; i++) {
				if(mov.seats[i].isBooked == true)
					System.out.print("1 ");
				else
					System.out.print("0 ");
			}
			System.out.println("]");
			
			System.out.print("메뉴 선택: ");
			int sel = scan.nextInt();
			
			if(sel == 1) {
				System.out.print("좌석 인덱스 입력: ");
				int seatIdx = scan.nextInt();
				
				if(seatIdx < 0 || mov.seats.length - 1 < seatIdx) {
					System.out.println("범위를 벗어난 인덱스입니다.");
				} else {
					if(mov.seats[seatIdx].isBooked) {
						System.out.println("이미 예매된 좌석입니다.");
					} else {
						mov.seats[seatIdx].isBooked = true;
						mov.total += mov.seats[seatIdx].price;						
					}
				}
				
			} else if(sel == 2) {
				System.out.print("좌석 인덱스 입력: ");
				int seatIdx = scan.nextInt();
				
				if(seatIdx < 0 || mov.seats.length - 1 < seatIdx) {
					System.out.println("범위를 벗어난 인덱스입니다.");
				} else {
					if(!mov.seats[seatIdx].isBooked) {
						System.out.println("예매되지 않은 좌석입니다.");
					} else {
						mov.seats[seatIdx].isBooked = false;
						mov.total -= mov.seats[seatIdx].price;						
					}
				}
				
			} else if(sel == 3) {
				System.out.println("프로그램을 종료합니다.");
				 break;
			} else {
				System.out.println("화면의 번호중에 입력하세요.");
			}
			System.out.println("====================");
		}
		
		System.out.println(mov.total + "원을 지불해주세요.");
		
		scan.close();
	}
}
