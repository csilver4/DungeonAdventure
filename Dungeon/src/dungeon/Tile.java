package dungeon;
import java.util.Scanner;

public class Tile {

	boolean hPotion;
	boolean vPotion;
	boolean pit;
	boolean entrance;
	boolean exit;
	boolean pillarE;
	boolean pillarA;
	boolean pillarI;
	boolean pillarP;
	boolean contains;
	
	public void checkContains(Hero mainHero) {
		if(contains) {
			Scanner kb = new Scanner(System.in);
			if(hPotion) {
				System.out.println("do you want to pick up the health potion? \n y for yes \n anything else for no\n");
				String input = kb.nextLine();
				if(input.equalsIgnoreCase("y")) {
					int healAmount = (int) (Math.random()*((15-5)+1))+5;
					mainHero.hitPoints = mainHero.hitPoints + healAmount;
					System.out.println(mainHero.name + " was healed " + healAmount + " health point");
					hPotion = false;
					kb.close();
					return;
				}
				kb.close();
				return;
			}
			if(pit) {
				int fallDamage = (int) (Math.random()*((15-5)+1))+5;
				mainHero.hitPoints = mainHero.hitPoints + fallDamage;
				System.out.println(mainHero.name + " fell down a pit and hurt themself!");
				if(!mainHero.isAlive()) {
					System.out.println(mainHero.name + " died from the fall!");
					System.out.println("Press enter to continue...");
					kb.nextLine();
					kb.close();
					DungeonAdventure.gameOver("loss");
					return;
				}
				System.out.println("Press enter to continue...");
				kb.nextLine();
				kb.close();
				return;
			}
			if(vPotion) {
				DungeonAdventure.visionPotionDisplay();
				kb.close();
				return;
			}
			if(entrance) {
				System.out.println("you cannot exit you must finish your quest");
				kb.close();
				return;
			}
			if(exit) {
				if(!DungeonAdventure.getPillarE() || !DungeonAdventure.getPillarA() || !DungeonAdventure.getPillarI() || !DungeonAdventure.getPillarP())
					System.out.println("you are missing a pillar of OO! \nyou must find them all before you leave the dungeon");
				else {
					System.out.println("do you want to leave the dungeon? \n y for yes \n anything else for no\n");
					String input = kb.nextLine();
					if(input.equalsIgnoreCase("y")) {
						kb.close();
						DungeonAdventure.setPillarE(false);
						DungeonAdventure.setPillarA(false);
						DungeonAdventure.setPillarP(false);
						DungeonAdventure.setPillarI(false);
						DungeonAdventure.gameOver("win");
						return;
					}
					kb.close();
					return;
				}
			}
			if(pillarE) {
				DungeonAdventure.setPillarE(true);
				System.out.println(mainHero.name + " picked up the pillar of Encapsulation");
				kb.close();
				return;
			}
			if(pillarA) {
				DungeonAdventure.setPillarA(true);
				System.out.println(mainHero.name + " picked up the pillar of Abstraction");
				kb.close();
				return;
			}
			if(pillarI) {
				DungeonAdventure.setPillarI(true);
				System.out.println(mainHero.name + " picked up the pillar of Inheritance");
				kb.close();
				return;
			}
			if(pillarP) {
				DungeonAdventure.setPillarP(true);
				System.out.println(mainHero.name + " picked up the pillar of Polymorphism");
				kb.close();
				return;
			}
			kb.close();
			return;
		}
	}
}
