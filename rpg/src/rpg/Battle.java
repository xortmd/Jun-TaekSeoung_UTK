package rpg;

import java.util.ArrayList;

public class Battle {
	
	private ArrayList<Dungeon> dungeonList = new ArrayList<>();
	
	public void setDungeonList() {
		Dungeon temp = null;
		temp = new Dungeon("농장", 1, 5000, 4);
		this.dungeonList.add(temp);
		temp = new Dungeon("아마존", 2, 10000, 4);
		this.dungeonList.add(temp);
		temp = new Dungeon("시베리아", 3, 20000, 4);
		this.dungeonList.add(temp);
		temp = new Dungeon("사바나", 4, 50000, 4);
		this.dungeonList.add(temp);
	}
	
	public void dungeonMenu() {
		if(Player.getGuildList().size() < 4) {
			System.out.println("4인의 파티를 구성해주세요.");
			return;
		}
		System.out.println("=============== [던전] ================");
		for(int i = 0; i < dungeonList.size(); i++) {
			Dungeon temp = dungeonList.get(i);
			System.out.printf("[%d번] [%-4s(보스: %s, 난이도: %s)]\n",
					(i + 1),temp.getDungeonName(),temp.getBossName(),rankDungeanString(i + 1));
		}
		int sel = MainGame.scan.nextInt();
		if(0 < sel && sel <= dungeonList.size())
			playDungeon(sel);
	}
	
	private String rankDungeanString(int dungeonLv) {
		if(dungeonLv == 1)
			return "쉬움";
		else if(dungeonLv == 2)
			return "보통";
		else if(dungeonLv == 3)
			return "어려움";
		else if(dungeonLv == 4)
			return "매우 어려움";
		else
			return "UNKNOWN";
	}
	
	public void playDungeon(int dl) {
		Dungeon dungeon = null;
		for(int i = 0; i < dungeonList.size(); i++) {
			if(dl == dungeonList.get(i).getDungeonLv()) {
				dungeon = dungeonList.get(i);
			}
		}
		System.out.println("=============== [" + dungeon.getDungeonName() + "] ================");
		System.out.println(dungeon.getDungeonName() + "에 입장했습니다.");
		
		Hero[] party = Player.getGuild().getPartyList();
		int idx = 0; // 파티원 순서 & 파티원 사망 체크
		
		for(int i = 0; i < dungeon.getRound(); i++) {
			Monster monster = dungeon.getMonsters().get(i);
			System.out.println("몬스터 등장!");
			if(i == dungeon.getRound() - 1)
				System.out.println("보스가 등장했습니다. 조심하세요!");
			System.out.print("[몬스터 정보]");
			System.out.println(" [이름: " + monster.getName() + "]");
			System.out.print("=[레벨: " + monster.getLevel() + "]");
			System.out.println(" [체력: " + monster.getHp() + "]");
			System.out.print("=[공격력: " + monster.getAtt() + "]");
			System.out.println(" [방어력: " + monster.getDef() + "]");
			
			System.out.println("[1. 전투]");
			System.out.println("[2. 도망]");
			int sel = MainGame.scan.nextInt();
			
			if(sel == 1) {
				while(monster.isLive()) {
					// 몬스터는 가장 앞에 있는 파티원부터 공격
					monsterAttHero(idx, monster, party);
					
					if(party[idx].getNewHp() <= 0) {
						sleep(500);
						System.out.println(party[idx].getName() + " 사망!");
						party[idx].setLive(false);
						idx++;
					}
					
					if(idx == party.length) {
						sleep(500);
						System.out.println("파티원이 전원 사망했습니다. 마을로 돌아갑니다.");
						return;
					}
					
					sleep(500);
					
					// 파티원은 총 공격력의 합으로 몬스터 공격
					heroAttMonster(monster, party);
					
					int healCnt = 0;
					for(int j = 0; j < party.length; j++) {
						if(party[j].getJob() == Hero.HEALER && party[j].isLive())
							healCnt += party[j].getLevel();
					}
					
					if(healCnt > 0) {
						System.out.println("힐러 능력 발동!");
						System.out.println("모든 파티원 체력이 " + 10*healCnt + " 회복됩니다.");
						for(int j = idx; j < party.length; j++) {
							if(party[j].isLive()) {
								if(party[j].getRing() != null) {
									if(party[j].getJob() == Hero.TANKER) {
										party[j].setNewHp(party[j].getNewHp() + 10*healCnt);
										if(party[j].getNewHp() > party[j].getHp() + party[j].getRing().getPower()*2)
											party[j].setNewHp(party[j].getHp() + party[j].getRing().getPower()*2);
									} else {
										party[j].setNewHp(party[j].getNewHp() + 10*healCnt);
										if(party[j].getNewHp() > party[j].getHp() + party[j].getRing().getPower())
											party[j].setNewHp(party[j].getHp() + party[j].getRing().getPower());
									}
								} else {
									party[j].setNewHp(party[j].getNewHp());						
									if(party[j].getNewHp() > party[j].getHp())
										party[j].setNewHp(party[j].getHp());
								}								
							}
						}
					}
					
					System.out.println("---------------------------------------");
					
					if(monster.getHp() <= 0) {
						if(i == dungeon.getRound() - 1) {
							System.out.println("축하합니다! " + dungeon.getDungeonName() + "을 클리어했습니다.");
							System.out.println("보상으로 " + dungeon.getPrize() + "골드가 지급됩니다.");
							Player.setMoney(Player.getMoney() + dungeon.getPrize());
							monster.setLive(false);
							levelUp(monster, party);
						} else {
							System.out.println(monster.getName() + "을(를) 처치했습니다. 다음 라운드로 이동합니다.");
							monster.setLive(false);
							levelUp(monster, party);
							Player.getGuild().printParty();
						}
					}
					
					sleep(500);
				}
			} else if(sel == 2) {
				System.out.println("마을로 돌아갑니다.");
				break;
			} else {
				System.out.println("화면의 번호중에 입력하세요.");
				i--;
			}
			
		}
	}
	
	private void levelUp(Monster monster, Hero[] party) {
		for(int j = 0; j < party.length; j++) {
			if(party[j].isLive()) {
				if(party[j].getLevel() < (party[j].getExp() + monster.getMonExp())/100 + 1) {
					party[j].setExp(party[j].getExp() + monster.getMonExp());
					while(party[j].getLevel() < (party[j].getExp())/100 + 1) {
						System.out.println(party[j].getName() + " LEVEL UP!");
						party[j].setExp(party[j].getExp() - party[j].getLevel()*100);
						party[j].setLevel(party[j].getLevel() + 1);
						
						if(party[j].getJob() == Hero.DEALER) {
							party[j].setAtt(party[j].getAtt() + 10);
							party[j].setDef(party[j].getDef() + 1);
							party[j].setHp(party[j].getHp() + 15);
						} else if(party[j].getJob() == Hero.HEALER) {
							party[j].setAtt(party[j].getAtt() + 5);
							party[j].setDef(party[j].getDef() + 1);
							party[j].setHp(party[j].getHp() + 15);
						} else if(party[j].getJob() == Hero.TANKER) {
							party[j].setAtt(party[j].getAtt() + 5);
							party[j].setDef(party[j].getDef() + 2);
							party[j].setHp(party[j].getHp() + 30);
						}
					}									
				} else {
					party[j].setExp(party[j].getExp() + monster.getMonExp());									
				}
			}
		}
	}
	
	private void monsterAttHero(int idx, Monster monster, Hero[] party) {
		System.out.println(party[idx].getName() + " <<< " + monster.getName() + " 공격!");
		
		sleep(500);
		
		int damage;
		if(party[idx].getArmor() != null) {
			if(party[idx].getJob() == Hero.TANKER)
				damage = monster.getAtt() - (party[idx].getDef() + party[idx].getArmor().getPower()*2);
			else
				damage = monster.getAtt() - (party[idx].getDef() + party[idx].getArmor().getPower());
		} else
			damage = monster.getAtt() - party[idx].getDef();
		
		if(damage < 0)
			damage = 0;
		
		party[idx].setNewHp(party[idx].getNewHp() - damage);
		
		if(party[idx].getNewHp() <= 0)
			party[idx].setNewHp(0);
		
		System.out.print(party[idx].getName() + " " + damage + " 피해! [체력: " + party[idx].getNewHp());
		
		if (party[idx].getRing() != null) {
			if(party[idx].getJob() == Hero.TANKER)
				System.out.println("/" + party[idx].getHp() + "(+" + party[idx].getRing().getPower()*2 + ")]");
			else
				System.out.println("/" + party[idx].getHp() + "(+" + party[idx].getRing().getPower() + ")]");
		} else {
			System.out.println("/" + party[idx].getHp() + "]");
		}
		System.out.println();
	}
	
	private void heroAttMonster(Monster monster, Hero[] party) {
		System.out.println("파티원 >>> " + monster.getName() + " 공격!");
		
		sleep(500);
		
		int attTotal = 0;
		for(int j = 0; j < party.length; j++) {
			if(party[j].isLive()) {
				attTotal += party[j].getAtt();
				if(party[j].getWeapon() != null) {
					if(party[j].getJob() == Hero.DEALER)
						attTotal += party[j].getWeapon().getPower()*2;
					else
						attTotal += party[j].getWeapon().getPower();
				}
			}
		}
		
		int damage = attTotal - monster.getDef();
		
		if(damage < 0)
			damage = 0;
		
		monster.setHp(monster.getHp() - damage);
		
		if(monster.getHp() <= 0)
			monster.setHp(0);
		
		System.out.println(monster.getName() + " " + damage + " 피해! [체력: " + monster.getHp() + "/" + monster.getMaxHp() + "]");
		System.out.println();
		
		sleep(500);
	}
	
	public void setMonsterHp() {
		for(int i = 0; i < dungeonList.size(); i++) {
			for(int j = 0; j < dungeonList.get(i).getMonsters().size(); j++) {
				dungeonList.get(i).getMonsters().get(j).setHp(dungeonList.get(i).getMonsters().get(j).getMaxHp());
				dungeonList.get(i).getMonsters().get(j).setLive(true);
			}
		}
	}
	
	public void sleep(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
