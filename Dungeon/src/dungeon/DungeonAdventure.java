package dungeon;
import java.util.Scanner;
import java.awt.event.KeyEvent;

public class DungeonAdventure {
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("Dungeon Adventure!");
		System.out.println("Explore the dungeon, find the pillars of OO, and fight off monsters");
		System.out.println("press enter to continue, press L to load a saved game\n");
		Hero mainHero = null;
		Dungeon dungeon = null;
		Scanner kb = new Scanner(System.in);
		if(kb.nextLine().equalsIgnoreCase("l")) {
			loadGame(mainHero, dungeon);
		}else{
			mainHero = Hero.factory(gameIntroduction());
			dungeon = new Dungeon(5,5);
		}
		kb.close();
		dungeon.displayRoom("current");
		runGame(mainHero, dungeon);
		System.out.println("do you want to play again?\n Y for yes\n anything else for no");
		if(kb.nextLine().equalsIgnoreCase("y")) {
			//play again
		}
		
		
	}

	private static void runGame(Hero mainHero, Dungeon dungeon) throws Exception {
		
		do {
			//check for button input
			
		}while(mainHero.isAlive());
	}

	private static String gameIntroduction() {
		
		Scanner kb = new Scanner(System.in);
		System.out.println("LEGEND:");
		System.out.println("Entrance: B");
		System.out.println("Exit: Z");
		System.out.println("Health Potion: H");
		System.out.println("Vision Potion: V");
		System.out.println("Pit: O");
		System.out.println("Abstraction Pillar: A");
		System.out.println("Encapsulation Pillar: E");
		System.out.println("Inheritance Pillar: I");
		System.out.println("Polymorphism Pillar P");
		System.out.println("Door: D");
		System.out.println("Regular Floor: #");
		System.out.println("Character: C");
		System.out.println("Press enter to continue...");
		kb.nextLine();
		System.out.println("please select a new Character by entering the number");
		System.out.println(" 1) King Arther");
		System.out.println(" 2) Brave Sir Robin");
		System.out.println(" 3) Zoot");
		System.out.println(" 4) Sir Lancelot");
		System.out.println(" 5) a Swollow");
		String input = kb.nextLine();
		kb.close();
		return input;
	}

	private static void loadGame(Hero mainHero, Dungeon dungeon) {
		// TODO Auto-generated method stub
		do
	}

	public void keyPressed(KeyEvent e, Dungeon dungeon, Hero mainHero) throws Exception {
        Integer key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            if(dungeon.getHeroRoomXLoc() == 0 && dungeon.getHeroRoomYLoc() == 2 && dungeon.getHeroDungeonXLoc() > 0) {
            	dungeon.setHeroRoomXLoc(4);
            	dungeon.setHeroDungeonXLoc(dungeon.getHeroDungeonXLoc()-1);
            	dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroDungeonYLoc()].checkContains(mainHero, dungeon);
            	if(!dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).checkBattle(mainHero)) {
            		//gameOver("Loss");
            	}
            	dungeon.displayRoom("current");
            }
        	if(dungeon.getHeroRoomXLoc() > 0) {
            	dungeon.setHeroRoomXLoc(dungeon.getHeroRoomXLoc()-1);
            	dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroRoomYLoc()].checkContains(mainHero, dungeon);
            	dungeon.displayRoom("current");
            }
        }
        if (key == KeyEvent.VK_RIGHT) {
        	if(dungeon.getHeroRoomXLoc() == 4 && dungeon.getHeroRoomYLoc() == 2 && dungeon.getHeroDungeonXLoc() < 4) {
        		dungeon.setHeroRoomXLoc(0);
        		dungeon.setHeroDungeonXLoc(dungeon.getHeroDungeonXLoc()+1);
        		dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroRoomYLoc()].checkContains(mainHero, dungeon);
        		if(!dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).checkBattle(mainHero)) {
            		//gameOver("Loss");
            	}
        		dungeon.displayRoom("current");
        	}
        	if(dungeon.getHeroRoomXLoc() < 4) {
            	dungeon.setHeroRoomXLoc(dungeon.getHeroRoomXLoc()+1);
            	dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroDungeonYLoc()].checkContains(mainHero, dungeon);
            	dungeon.displayRoom("current");
        	}
        }
        if (key == KeyEvent.VK_UP) {
        	if(dungeon.getHeroRoomXLoc() == 2 && dungeon.getHeroRoomYLoc() == 4 && dungeon.getHeroDungeonYLoc() < 4) {
        		dungeon.setHeroRoomYLoc(0);
        		dungeon.setHeroDungeonYLoc(dungeon.getHeroDungeonYLoc()+1);
        		dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroDungeonYLoc()].checkContains(mainHero, dungeon);
        		if(!dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).checkBattle(mainHero)) {
            		//gameOver("Loss");
            	}
        		dungeon.displayRoom("current");
        	}
        	if(dungeon.getHeroRoomYLoc() < 4) {
            	dungeon.setHeroRoomYLoc(dungeon.getHeroRoomYLoc());
            	dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroDungeonYLoc()].checkContains(mainHero, dungeon);
            	dungeon.displayRoom("current");
        	}
        }
        if (key == KeyEvent.VK_DOWN) {
        	if(dungeon.getHeroRoomXLoc() == 2 && dungeon.getHeroRoomYLoc() == 0 && dungeon.getHeroDungeonYLoc() > 0) {
        		dungeon.setHeroRoomYLoc(4);
        		dungeon.setHeroDungeonYLoc(dungeon.getDungeonYLength()-1);
        		dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroDungeonYLoc()].checkContains(mainHero, dungeon);
        		if(!dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).checkBattle(mainHero)) {
            		//gameOver("Loss");
            	}
        		dungeon.displayRoom("current");
        	}
        	if(dungeon.getHeroRoomYLoc() > 0) {
            	dungeon.setHeroRoomYLoc(dungeon.getHeroRoomYLoc()-1);
            	dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroDungeonYLoc()].checkContains(mainHero, dungeon);
            	dungeon.displayRoom("current");
        	}
        }
        if(key == KeyEvent.VK_S) {
        	//save game
        }
    }
}
/* 
 NOTE: Include a hidden menu option for testing that prints out the entire Dungeon
  -- specify what the menu option is in your documentation for the DungeonAdventure class
 At the end of the game, display the entire Dungeon
 */