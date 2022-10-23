package rpg;

public class Monster extends Unit {
	
	private boolean boss; // 보스 처치시 던전 클리어
	private int monExp;
	
	public Monster(String name, int level, int hp, int att, int def, boolean boss, int monExp) {
		super(name, level, hp, att, def);
		this.boss = boss;
		this.monExp = monExp;
	}

	public boolean isBoss() {
		return boss;
	}

	public int getMonExp() {
		return monExp;
	}
}
