package lv06;

import java.util.Random;
import java.util.Scanner;

class Card {
	int shape;
	int num;
	boolean check;
}

class Player {
	int score;
	int numSum;
	int myCard1Shape;
	int myCard1Num;
	int myCard2Shape;
	int myCard2Num;
}

class CardPlay {
	Scanner scan = new Scanner(System.in);

	final int SIZE = 40;
	int playerCnt;
	int cardCnt;
	Card[] cards = new Card[SIZE];
	Player[] players = null;
	
	void cardReset() {
		for(int i = 0; i < SIZE; i++) {
			cards[i] = new Card();
			cards[i].num = i;
		}
	}
	
	void cardShuffle() {
		Random ran = new Random();
		
		for(int i = 0; i < 1000; i++) {
			int rNum = ran.nextInt(SIZE);
			int temp = cards[0].num;
			cards[0].num = cards[rNum].num;
			cards[rNum].num = temp;
		}
	}
	
	void cardSetting() {
		for(int i = 0; i < SIZE; i++) {
			cards[i].shape = cards[i].num/10 + 1; // 1 ~ 4 (♠, ◆, ♥, ♣)
			cards[i].num = cards[i].num%10 + 1; // 1 ~ 10
		}
	}
	
	void playerSetting() {
		System.out.print("플레이어 수 입력: ");
		this.playerCnt = this.scan.nextInt();
		
		while(this.playerCnt <= 0) {
			System.out.println("0 보다 큰 값을 입력하세요.");
			System.out.print("다시 입력: ");
			this.playerCnt = scan.nextInt();
		}
		
		this.players = new Player[this.playerCnt];
		for(int i = 0; i < this.playerCnt; i++)
			this.players[i] = new Player();
	}
	
	void cardDraw() {
		for(int i = 0; i < this.playerCnt; i++) {
			this.players[i].myCard1Shape = this.cards[this.cardCnt].shape;
			this.players[i].myCard1Num = this.cards[this.cardCnt].num;
			this.cardCnt++;
			this.players[i].myCard2Shape = this.cards[this.cardCnt].shape;
			this.players[i].myCard2Num = this.cards[this.cardCnt].num;
			this.cardCnt++;
			
			this.players[i].numSum = this.players[i].myCard1Num + this.players[i].myCard2Num;
		}
	}
	
	void shapePrint(int a) {
		if(a == 1)
			System.out.print("♠");
		else if(a == 2)
			System.out.print("◆");
		else if(a == 3)
			System.out.print("♥");
		else if(a == 4)
			System.out.print("♣");
	}
	
	void cardPrint() {
		for(int i = 0; i < this.playerCnt; i++) {
			System.out.printf("p%d: %2d(", i + 1,this.players[i].myCard1Num);
			shapePrint(this.players[i].myCard1Shape);
			System.out.printf("), %2d(", this.players[i].myCard2Num);
			shapePrint(this.players[i].myCard2Shape);
			System.out.println(")");
		}
	}
	
	void scoreCheck() {
		int maxSum = this.players[0].numSum;
		for(int i = 1; i < this.playerCnt; i++)
			if(maxSum < this.players[i].numSum)
				maxSum = this.players[i].numSum;
		
		for(int i = 0; i < this.playerCnt; i++)
			if(this.players[i].numSum == maxSum)
				this.players[i].score++;
	}
	
	void scoreNow() {
		for(int i = 0; i < this.playerCnt; i++) {
			System.out.print("p" + (i + 1) + ": " + this.players[i].score + "점");
			if(i < this.playerCnt - 1)
				System.out.print(" / ");
		}
		System.out.println();
	}
	
	void rankCheck() {
		int maxScore = this.players[0].score;
		for(int i = 1; i < this.playerCnt; i++) {
			if(maxScore < this.players[i].score)
				maxScore = this.players[i].score;
		}
		System.out.println("===[최종 점수]===");
		
		for(int i = 0; i < this.playerCnt; i++) {
			System.out.print((i + 1) + "번 플레이어: " + this.players[i].score + "점");
			if(this.players[i].score == maxScore)
				System.out.println(" ==> 우승!");
			else
				System.out.println();
		}
	}
	
	void run() {		
		cardReset();
		
		cardShuffle();
		
		cardSetting();
		
		playerSetting();
		
		while(SIZE - this.cardCnt >= this.playerCnt*2) {
			cardDraw();
			
			cardPrint();
			
			scoreCheck();
			
			scoreNow();
			
			System.out.print("계속 진행(아무키 입력): ");
			this.scan.next();
			
			System.out.println("--------------------------------");
		}
		
		System.out.println("카드가 모자랍니다. 게임을 종료합니다.");
		
		rankCheck();
		
		this.scan.close();
	}
	
}

public class 포커 {
	public static void main(String[] args) {
		
		CardPlay game = new CardPlay();
		game.run();
		
	}
}
