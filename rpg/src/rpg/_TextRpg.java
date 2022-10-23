package rpg;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class MainGame {
	static Scanner scan = new Scanner(System.in);
	static Random ran = new Random();

	public MainGame() {
		Player player = new Player();
		Shop shop = new Shop();
		FileData fileData = new FileData();
		System.out.println("tsRPG에 오신 것을 환영합니다.");
		System.out.println("길드를 모집하여 최종목표에 도전하세요.");
		while (true) {
			Player.getGuild().setHeroHp();
			Player.getBattle().setMonsterHp();
			System.out.println("=============== [메인메뉴] ================");
			System.out.println("[1.길드관리] [2.상점] [3.인벤토리] [4.던전입장]");
			System.out.println("[5.저장] [6.로드] [7.게임설명] [8.제작자]");
			System.out.println("[0.종료]");
			int sel = scan.nextInt();
			
			if (sel == 1) {
				player.guildMenu();
			} else if (sel == 2) {
				shop.shopMng();
			} else if (sel == 3) {
				player.inventoryMenu();
			} else if (sel == 4) {
				player.dungeonMenu();
			} else if (sel == 5) {
				try {
					fileData.save();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (sel == 6) {
				try {
					fileData.loadData();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (sel == 7) {
				System.out.println("=============== [게임설명] ================");
				System.out.println("길드원을 영입하고 파티를 구성하여 던전을 클리어하는 게임입니다.");
				System.out.println("던전을 클리어하면 일정량의 골드를 받을 수 있습니다.");
				System.out.println("골드는 아이템을 사는데 이용됩니다.");
				System.out.println("--------------- [전투설명] ----------------");
				System.out.println("던전은 총 4라운드로 이루어지며 각 라운드 마다 몬스터가 등장합니다.");
				System.out.println("몬스터는 가장 선봉에 있는 파티원부터 공격합니다.");
				System.out.println("파티원들은 힘을 합쳐 몬스터를 공격합니다.");
				System.out.println("파티원 전원 사망시 마을로 돌아갑니다.");
				System.out.println("마지막 몬스터는 보스이며 보스를 처치해야만 던전을 클리어합니다.");
				System.out.println("---------------- [직업] ------------------");
				System.out.println("직업마다 기본 능력치와 성장 능력치가 다르며 각각 특수효과가 부여됩니다.");
				System.out.println("딜러: 무기 아이템의 효과를 두배로 받음");
				System.out.println("힐러: 전투 라운드 종료시 생존한 모든 파티원의 체력 10*Lv씩 회복");
				System.out.println("탱커: 방어, 체력 아이템의 효과를 두배로 받음");
			} else if (sel == 8) {
				System.out.println("┌──────────────────────────┐");
				System.out.println("│Developer: Jung TeakSeoung│");
				System.out.println("└──────────────────────────┘");
			} else {
				System.out.println("게임을 종료 합니다.");
				break;
			}
		}
		MainGame.scan.close();
	}
}

public class _TextRpg {
	public static void main(String[] args) {
		new MainGame();
	}
}