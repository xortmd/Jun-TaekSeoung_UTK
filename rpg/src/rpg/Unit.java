package rpg;

public class Unit {
	static final int DEALER = 1;
	static final int HEALER = 2;
	static final int TANKER = 3;
	
	private String name;
	private int level;
	private int hp;
	private int maxHp;
	private int att;
	private int def;
	private boolean live;
	
	public Unit(String name, int level, int hp, int att, int def) {
		this.name = name;
		this.level = level;
		this.maxHp = hp;
		this.hp = maxHp;
		this.att = att;
		this.def = def;
		this.live = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
}