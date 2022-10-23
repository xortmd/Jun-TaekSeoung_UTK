package rpg;

import java.util.ArrayList;

public class Dungeon {
	
	private ArrayList<Monster> monsters = new ArrayList<>();;
	private String dungeonName;
	private int dungeonLv;
	private int prize;
	private int round;
	private String bossName;
	private boolean clear;
	
	public Dungeon(String dungeonName, int dungeonLv, int prize, int round) {
		this.monsters = getDungeonMonsters(dungeonLv);
		this.dungeonName = dungeonName;
		this.dungeonLv = dungeonLv;
		this.prize = prize;
		this.round = round;
		this.bossName = monsters.get(monsters.size() - 1).getName();
		this.clear = false;
	}

	public ArrayList<Monster> getDungeonMonsters(int dl) {
		ArrayList<Monster> result = new ArrayList<>();
		Monster temp = null;
		if(dl == 1) {
			temp = new Monster("닭", 1, 50, 10, 1, false, 10);
			result.add(temp);
			temp = new Monster("토끼", 2, 75, 20, 3, false, 15);
			result.add(temp);
			temp = new Monster("돼지", 3, 100, 40, 5, false, 25);
			result.add(temp);
			temp = new Monster("황소", 6, 200, 80, 10, true, 50);
			result.add(temp);
		} else if(dl == 2) {
			temp = new Monster("앵무새", 5, 60, 20, 4, false, 25);
			result.add(temp);
			temp = new Monster("아나콘다", 7, 120, 50, 8, false, 50);
			result.add(temp);
			temp = new Monster("악어", 9, 180, 90, 10, false, 100);
			result.add(temp);
			temp = new Monster("고릴라", 12, 500, 150, 20, true, 200);
			result.add(temp);
		} else if(dl == 3) {
			temp = new Monster("늑대", 9, 100, 60, 7, false, 50);
			result.add(temp);
			temp = new Monster("독수리", 10, 200, 100, 10, false, 100);
			result.add(temp);
			temp = new Monster("호랑이", 16, 500, 250, 15, false, 250);
			result.add(temp);
			temp = new Monster("불곰", 18, 1200, 300, 30, true, 400);
			result.add(temp);
		} else if(dl == 4) {
			temp = new Monster("얼룩말", 15, 400, 70, 10, false, 100);
			result.add(temp);
			temp = new Monster("기린", 18, 1000, 100, 20, false, 200);
			result.add(temp);
			temp = new Monster("사자", 20, 800, 300, 15, false, 350);
			result.add(temp);
			temp = new Monster("코끼리", 30, 3000, 800, 50, true, 650);
			result.add(temp);
		}
		return result;
	}

	public ArrayList<Monster> getMonsters() {
		return monsters;
	}

	public void setMonsters(ArrayList<Monster> monsters) {
		this.monsters = monsters;
	}

	public String getDungeonName() {
		return dungeonName;
	}

	public void setDungeonName(String dungeonName) {
		this.dungeonName = dungeonName;
	}

	public int getDungeonLv() {
		return dungeonLv;
	}

	public void setDungeonLv(int dungeonLv) {
		this.dungeonLv = dungeonLv;
	}

	public int getPrize() {
		return prize;
	}

	public void setPrize(int prize) {
		this.prize = prize;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public String getBossName() {
		return bossName;
	}

	public void setBossName(String bossName) {
		this.bossName = bossName;
	}
	
	public boolean isClear() {
		return clear;
	}

	public void setClear(boolean clear) {
		this.clear = clear;
	}
}
