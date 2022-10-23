package rpg;

import java.util.ArrayList;

public class Guild {
	final int PARTY_SIZE = 4;
	private ArrayList<Hero> guildList = new ArrayList<>();
	private Hero[] partyList;

	public Hero getGuildUnit(int num) {
		return guildList.get(num);
	}

	public void printAllUnitStaus() {
		System.out.println("======================================");
		System.out.println("[골드 : " + Player.getMoney() + "]");
		System.out.println("============= [길드원] =================");
		if(guildList.size() == 0) {
			System.out.println("(길드원 없음)");
		} else {
			for (int i = 0; i < guildList.size(); i++) {
				System.out.print("[" + (i + 1) + "번] ");
				guildList.get(i).printStatusHero();
				System.out.println();
			}			
		}
		System.out.println("=================================");
	}

	public void printUnitStaus(int num) {
		guildList.get(num).printStatusHero();
	}

	public void printUnitItem(int num) {
		guildList.get(num).printEquitedItem();
	}

	public void buyUnit() {
		if (Player.getMoney() < 5000) {
			System.out.println("골드가 부족합니다.");
			return;
		}
		System.out.print("길드원 이름 입력: ");
		String name = MainGame.scan.next();
		
		for(Unit list : guildList) {
			if(list.getName().equals(name)) {
				System.out.println("사용중인 이름입니다.");
				return;
			}
		}
		
		System.out.print("길드원 직업 입력[딜러(1), 힐러(2), 탱커(3)]: ");
		int job = MainGame.scan.nextInt();
		
		while(job < 1 || 3 < job) {
			System.out.println("화면의 번호 중에 입력하세요.");
			System.out.print("다시 입력: ");
			job = MainGame.scan.nextInt();
		}
		
		Hero temp = null;
		if(guildList.size() < 4) {
			if(job == Hero.DEALER)
				temp = new Hero(name, 1, 50, 15, 1, 0, true, job);
			else if(job == Hero.HEALER)
				temp = new Hero(name, 1, 50, 10, 1, 0, true, job);
			else if(job == Hero.TANKER)
				temp = new Hero(name, 1, 80, 10, 3, 0, true, job);
		} else {
			if(job == Hero.DEALER)
				temp = new Hero(name, 1, 50, 15, 1, 0, job);
			else if(job == Hero.HEALER)
				temp = new Hero(name, 1, 50, 10, 1, 0, job);
			else if(job == Hero.TANKER)
				temp = new Hero(name, 1, 80, 10, 3, 0, job);
		}

		System.out.println("=====================================");
		temp.printStatusHero();
		System.out.println("길드원을 추가합니다.");
		System.out.println("=====================================");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		guildList.add(temp);
		Player.setMoney(Player.getMoney() - 5000);
		for(int i = 0; i < this.partyList.length; i++) {
			if(partyList[i] == null) {
				partyList[i] = temp;
				break;
			}
		}
	}

	public void removeUnit() {
		printAllUnitStaus();
		System.out.println("삭제할 번호를 입력하세요 ");
		int sel = MainGame.scan.nextInt();
		if (guildList.get(sel - 1).isParty()) {
			System.out.println("파티중인 멤버는 삭제할수 없습니다.");
		} else {
			System.out.println("=================================");
			System.out.print("[이름 : " + guildList.get(sel - 1).getName() + "]");
			System.out.println("길드원을 삭제합니다.");
			System.out.println("=================================");
			guildList.remove(sel - 1);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void printParty() {
		System.out.println("================ [파티원] ===============");
		for (int i = 0; i < PARTY_SIZE; i++) {
			System.out.print("[" + (i + 1) + "번]");
			System.out.println(" [이름: " + partyList[i].getName() + "]");
			System.out.print("=[레벨: " + partyList[i].getLevel() + "]");
			System.out.print(" [체력: " + partyList[i].getNewHp());
			if (partyList[i].getRing() != null) {
				if(partyList[i].getJob() == Hero.TANKER)
					System.out.println("/" + partyList[i].getHp() + "(+" + partyList[i].getRing().getPower()*2 + ")]");
				else
					System.out.println("/" + partyList[i].getHp() + "(+" + partyList[i].getRing().getPower() + ")]");
			} else {
				System.out.println("/" + partyList[i].getHp() + "]");
			}
			if (partyList[i].getWeapon() != null) {
				if(partyList[i].getJob() == Hero.DEALER)
					System.out.print("=[공격력: " + partyList[i].getAtt() + "(+" + partyList[i].getWeapon().getPower()*2 + ")]");
				else
					System.out.print("=[공격력: " + partyList[i].getAtt() + "(+" +partyList[i].getWeapon().getPower() + ")]");
			} else {
				System.out.print("=[공격력: " + partyList[i].getAtt() + "]");
			}
			if (partyList[i].getArmor() != null) {
				if(partyList[i].getJob() == Hero.TANKER)
					System.out.println(" [방어력: " + partyList[i].getDef() + "(+" + partyList[i].getArmor().getPower()*2 + ")]");
				else
					System.out.println(" [방어력: " + partyList[i].getDef() + "(+" + partyList[i].getArmor().getPower() + ")]");
			} else {
				System.out.println(" [방어력: " + partyList[i].getDef() + "]");
			}
			System.out.print("=[파티중: " + partyList[i].isParty() + "]");
			System.out.println(" [직업: " + partyList[i].getJobString(partyList[i].getJob()) + "]");
			System.out.println("=[경험치: " + partyList[i].getExp() + "/" + partyList[i].getLevel()*100 + "]");
			System.out.println();
		}
		System.out.println("=====================================");
	}

	public void partyChange() {

		printParty();
		System.out.println("교체할 번호를 입력하세요 ");
		int partyNum = MainGame.scan.nextInt();
		printAllUnitStaus();
		System.out.println("참가할 번호를 입력하세요 ");
		int guildNum = MainGame.scan.nextInt();
		
		for(int i = 0; i < guildList.size(); i++) {
			if(partyList[partyNum - 1].getName().equals(guildList.get(i).getName()))
				guildList.get(i).setParty(false);
		}
		guildList.get(guildNum - 1).setParty(true);

		System.out.println("====================================");
		System.out.print("[이름 : " + partyList[partyNum - 1].getName() + "]");
		System.out.print("에서 ");
		System.out.print("[이름 : " + guildList.get(guildNum - 1).getName() + "]");
		System.out.println("으로 교체 합니다. ");
		System.out.println("====================================");

		partyList[partyNum - 1] = guildList.get(guildNum - 1);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void guildMenu() {
		while (true) {
			System.out.println("=============== [길드관리] ================");
			System.out.println("[1.길드목록] [2.길드원추가] [3.길드원삭제]\n" + "[4.파티확인] [5.파티원교체] [6.정렬]\n" + "[0.뒤로가기]");
			int sel = MainGame.scan.nextInt();
			if (sel == 1) {
				printAllUnitStaus();
			} else if (sel == 2) {
				buyUnit();
			} else if (sel == 3) {
				removeUnit();
			} else if (sel == 4) {
				printParty();
			} else if (sel == 5) {
				partyChange();
			} else if (sel == 6) {
				guildSort();
			} else if (sel == 0) {
				break;
			}
		}
	}

	public ArrayList<Hero> getGuildList() {
		return guildList;
	}

	public void setGuildList(ArrayList<Hero> guildList) {
		this.guildList = guildList;
	}
	
	public Hero[] getPartyList() {
		return partyList;
	}
	
	public void setPartyList() {
		partyList = new Hero[PARTY_SIZE];
		int n = 0;
		for (int i = 0; i < guildList.size(); i++) {
			if (guildList.get(i).isParty()) {
				partyList[n] = guildList.get(i);
				n += 1;
			}
		}
	}
	
	private void guildSort() {
		System.out.println("[1.이름 정렬]");
		System.out.println("[2.레벨 정렬]");
		System.out.println("[3.공격력 정렬]");
		System.out.println("[4.방어력 정렬]");
		System.out.println("[5.체력 정렬]");
		int sel = MainGame.scan.nextInt();
		
		if(sel == 1) {
			for(int i = 0; i < guildList.size(); i++) {
				for(int j = i; j < guildList.size(); j++) {
					if(guildList.get(i).getName().compareTo(guildList.get(j).getName()) > 0) {
						Hero temp = guildList.get(i);
						guildList.set(i, guildList.get(j));
						guildList.set(j, temp);
					}
				}
			}
			System.out.println("이름 순으로 정렬되었습니다.");
		} else if(sel == 2) {
			for(int i = 0; i < guildList.size(); i++) {
				for(int j = i; j < guildList.size(); j++) {
					if(guildList.get(i).getLevel() < guildList.get(j).getLevel()) {
						Hero temp = guildList.get(i);
						guildList.set(i, guildList.get(j));
						guildList.set(j, temp);
					}
				}
			}
			System.out.println("레벨 순으로 정렬되었습니다.");
		} else if(sel == 3) {
			for(int i = 0; i < guildList.size(); i++) {
				int attI;
				if(guildList.get(i).getWeapon() != null) {
					if(guildList.get(i).getJob() == Hero.DEALER) {
						attI = guildList.get(i).getAtt() + guildList.get(i).getWeapon().getPower()*2;
					} else {
						attI = guildList.get(i).getAtt() + guildList.get(i).getWeapon().getPower();
					}						
				} else {
					attI = guildList.get(i).getAtt();
				}
				for(int j = i; j < guildList.size(); j++) {
					int attJ;
					if(guildList.get(j).getWeapon() != null) {
						if(guildList.get(j).getJob() == Hero.DEALER) {
							attJ = guildList.get(j).getAtt() + guildList.get(j).getWeapon().getPower()*2;
						} else {
							attJ = guildList.get(j).getAtt() + guildList.get(j).getWeapon().getPower();
						}						
					} else {
						attJ = guildList.get(j).getAtt();
					}
					if(attI < attJ) {
						Hero temp = guildList.get(i);
						guildList.set(i, guildList.get(j));
						guildList.set(j, temp);
						attI = attJ;
					}
				}
			}
			System.out.println("공격력 순으로 정렬되었습니다.");
		} else if(sel == 4) {
			for(int i = 0; i < guildList.size(); i++) {
				int defI;
				if(guildList.get(i).getArmor() != null) {
					if(guildList.get(i).getJob() == Hero.TANKER) {
						defI = guildList.get(i).getDef() + guildList.get(i).getArmor().getPower()*2;
					} else {
						defI = guildList.get(i).getDef() + guildList.get(i).getArmor().getPower();
					}						
				} else {
					defI = guildList.get(i).getDef();
				}
				for(int j = i; j < guildList.size(); j++) {
					int defJ;
					if(guildList.get(j).getArmor() != null) {
						if(guildList.get(j).getJob() == Hero.TANKER) {
							defJ = guildList.get(j).getDef() + guildList.get(j).getArmor().getPower()*2;
						} else {
							defJ = guildList.get(j).getDef() + guildList.get(j).getArmor().getPower();
						}						
					} else {
						defJ = guildList.get(j).getDef();
					}
					if(defI < defJ) {
						Hero temp = guildList.get(i);
						guildList.set(i, guildList.get(j));
						guildList.set(j, temp);
						defI = defJ;
					}
				}
			}
			System.out.println("방어력 순으로 정렬되었습니다.");
		} else if(sel == 5) {
			for(int i = 0; i < guildList.size(); i++) {
				int hpI;
				if(guildList.get(i).getRing() != null) {
					if(guildList.get(i).getJob() == Hero.DEALER) {
						hpI = guildList.get(i).getHp() + guildList.get(i).getRing().getPower()*2;
					} else {
						hpI = guildList.get(i).getHp() + guildList.get(i).getRing().getPower();
					}						
				} else {
					hpI = guildList.get(i).getHp();
				}
				for(int j = i; j < guildList.size(); j++) {
					int hpJ;
					if(guildList.get(j).getRing() != null) {
						if(guildList.get(j).getJob() == Hero.DEALER) {
							hpJ = guildList.get(j).getHp() + guildList.get(j).getRing().getPower()*2;
						} else {
							hpJ = guildList.get(j).getHp() + guildList.get(j).getRing().getPower();
						}						
					} else {
						hpJ = guildList.get(j).getHp();
					}
					if(hpI < hpJ) {
						Hero temp = guildList.get(i);
						guildList.set(i, guildList.get(j));
						guildList.set(j, temp);
						hpI = hpJ;
					}
				}
			}
			System.out.println("체력 순으로 정렬되었습니다.");
		}
	}
	
	public void setHeroHp() {
		for(int i = 0; i < guildList.size(); i++) {
			if(guildList.get(i).getRing() != null) {
				if(guildList.get(i).getJob() == Hero.TANKER)
					guildList.get(i).setNewHp(guildList.get(i).getHp() + guildList.get(i).getRing().getPower()*2);
				else
					guildList.get(i).setNewHp(guildList.get(i).getHp() + guildList.get(i).getRing().getPower());
			} else
				guildList.get(i).setNewHp(guildList.get(i).getHp());
			guildList.get(i).setLive(true);
		}
	}
}