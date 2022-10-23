package rpg;

public class Hero extends Unit {

	static final int DEALER = 1; // 아이템 공격력 2배
	static final int HEALER = 2; // 던전 전투마다 모든 파티원 체력 회복 +10*레벨
	static final int TANKER = 3; // 아이템 체력, 방어력 2배
	
	private int exp;
	private boolean party;
	private Item weapon;
	private Item armor;
	private Item ring;
	private int job;
	private int newHp;
	
	public Hero(String name, int level, int hp, int att, int def, int exp, int job) {
		super(name, level, hp, att, def);
		this.exp = exp;
		this.party = false;
		this.job = job;
		this.newHp = hp;
		this.weapon = null;
		this.armor = null;
		this.ring = null;
	}

	public Hero(String name, int level, int hp, int att, int def, int exp, boolean party, int job) {
		super(name, level, hp, att, def);
		this.exp = exp;
		this.party = party;
		this.job = job;
		this.newHp = hp;
		this.weapon = null;
		this.armor = null;
		this.ring = null;
	}
	
	public void setItem(Item weapon, Item armor, Item ring) {
		this.weapon = weapon;
		this.armor = armor;
		this.ring = ring;
	}
	
	public void printStatusHero() {
		System.out.println("[이름: " + getName() + "]");
		System.out.print("=[레벨: " + getLevel() + "]");
		System.out.print(" [체력: " + getNewHp());
		if (getRing() != null) {
			if(getJob() == Hero.TANKER)
				System.out.println("/" + getHp() + "(+" + getRing().getPower()*2 + ")]");
			else
				System.out.println("/" + getHp() + "(+" + getRing().getPower() + ")]");
		} else {
			System.out.println("/" + getHp() + "]");
		}
		if (getWeapon() != null) {
			if(getJob() == Hero.DEALER)
				System.out.print("=[공격력: " + getAtt() + "(+" + getWeapon().getPower()*2 + ")]");
			else
				System.out.print("=[공격력: " + getAtt() + "(+" + getWeapon().getPower() + ")]");
		} else {
			System.out.print("=[공격력: " + getAtt() + "]");
		}
		if (getArmor() != null) {
			if(getJob() == Hero.TANKER)
				System.out.println(" [방어력: " + getDef() + "(+ " + getArmor().getPower()*2 + ")]");
			else
				System.out.println(" [방어력: " + getDef() + "(+ " + getArmor().getPower() + ")]");
		} else {
			System.out.println(" [방어력: " + getDef() + "]");
		}
		System.out.print("=[파티중: " + isParty() + "]");
		System.out.println("[직업: " + getJobString(getJob()) + "]");
		System.out.println("=[경험치: " + getExp() + "/" + getLevel()*100 + "]");
	}
	
	public void printEquitedItem() {
		if (getWeapon() == null) {
			System.out.println("[무기: 없음]");
		} else {
			System.out.println("[무기: " + getWeapon().getName() + "]");
		}
		if (getArmor() == null) {
			System.out.println("[방어구: 없음]");
		} else {
			System.out.println("[방어구: " + getArmor().getName() + "]");
		}
		if (getRing() == null) {
			System.out.println("[반지: 없음]");
		} else {
			System.out.println("[반지: " + getRing().getName() + "]");
		}
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public boolean isParty() {
		return party;
	}

	public void setParty(boolean party) {
		this.party = party;
	}

	public Item getWeapon() {
		return weapon;
	}

	public void setWeapon(Item weapon) {
		this.weapon = weapon;
	}

	public Item getArmor() {
		return armor;
	}

	public void setArmor(Item armor) {
		this.armor = armor;
	}

	public Item getRing() {
		return ring;
	}

	public void setRing(Item ring) {
		this.ring = ring;
	}

	public int getJob() {
		return job;
	}
	
	public String getJobString(int job) {
		if(job == DEALER)
			return "딜러";
		else if(job == HEALER)
			return "힐러";
		else
			return "탱커";
	}

	public void setJob(int job) {
		this.job = job;
	}

	public int getNewHp() {
		return newHp;
	}

	public void setNewHp(int newHp) {
		this.newHp = newHp;
	}
}
