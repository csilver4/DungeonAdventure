package dungeon;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class DungeonAdventure {
	
	public Scanner kb;
	
	public static void main(String[] args) throws Exception{
		
		DungeonAdventure instance = new DungeonAdventure();
		instance.kb = new Scanner(System.in);
		System.out.println("Dungeon Adventure!");
		System.out.println("Explore the dungeon, find the pillars of OO, and fight off monsters");
		System.out.println("press enter to continue, press L to load a saved game\n");
		Hero mainHero = null;
		Dungeon dungeon = null;
		String temp = instance.kb.nextLine();
		if(temp.equalsIgnoreCase("l")) {
			try {
				instance.loadGame(new File("save.ser"), dungeon, mainHero);
			}catch (Exception ex) {
				System.out.println("Error opening file - " + ex.getMessage()); 
				mainHero = HeroFactory.factory(instance.gameIntroduction(instance));
				dungeon = new Dungeon(5,5);
			}
		}else if(temp.equalsIgnoreCase("d")) {
			mainHero = HeroFactory.factory(instance.gameIntroduction(instance));
			dungeon = new Dungeon(5, 5);
			dungeon.displayRoom("vision");
		}else{
			mainHero = HeroFactory.factory(instance.gameIntroduction(instance));
			dungeon = new Dungeon(5,5);
		}
		String res;
		do {
			dungeon.displayRoom("current");
			instance.runGame(mainHero, dungeon, instance);
			dungeon.displayRoom("all");
			System.out.println("do you want to play again?\n Y for yes\n anything else for no");
			res = instance.kb.nextLine();
			if(res.equalsIgnoreCase("y")) {
				mainHero = HeroFactory.factory(instance.gameIntroduction(instance));
				dungeon = new Dungeon(5,5);
			}
		}while(!res.equalsIgnoreCase("y"));
	}

	private void runGame(Hero mainHero, Dungeon dungeon, DungeonAdventure instance) throws Exception {
		
		if(mainHero == null)throw new Exception("Hero object passed into runGame is null");
		if(dungeon == null)throw new Exception("Dungeon object passed into runGame is null");
		if(instance == null)throw new Exception("DungeonAdventure object passed into instance is null");
		
		do {
			System.out.print("Direction: ");
			String directionInput = instance.kb.nextLine().trim().toLowerCase();
			switch(directionInput) {
			case "l":
				moveLeft(dungeon, mainHero);
				break;
			case "r":
				moveRight(dungeon, mainHero);
				break;
			case "u":
				moveUp(dungeon, mainHero);
				break;
			case "d":
				moveDown(dungeon, mainHero);
				break;
			case "s":
				saveGame(new File("save.ser"), dungeon, mainHero);
				break;
			default:
				System.out.println("Invalid Direction");
			}
		}while(mainHero.isAlive());
	}

	private String gameIntroduction(DungeonAdventure instance) throws Exception {
		
		if(instance == null)throw new Exception("DungeonAdventure object passed into gameIntroduction is null");
		
		System.out.print("LEGEND:");
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
		instance.kb.nextLine();
		System.out.println("please select a new Character by entering the number");
		System.out.println(" 1) King Arther");
		System.out.println(" 2) Brave Sir Robin");
		System.out.println(" 3) Zoot");
		System.out.println(" 4) Sir Lancelot");
		System.out.println(" 5) a Swallow");
		String input = instance.kb.nextLine();
		return input;
	}
	
	private void saveGame(File f, Dungeon dungeon, Hero mainHero) throws Exception {
		
		if(f == null)throw new Exception("File object passed into saveGame is null");
		if(dungeon == null)throw new Exception("Dungeon object passed into saveGame is null");
		if(mainHero == null)throw new Exception("Hero object passed into saveGame is null");
		
		try {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(dungeon);
			oos.writeObject(mainHero);
			oos.close();
			fos.close();
		} catch (Exception ex) {
			System.out.println("Error saving file - " + ex.getMessage());
		}
	}
	
	private void loadGame(File f, Dungeon dungeon, Hero mainHero) throws Exception {
		
		if(f == null)throw new Exception("File object passed into loadGame is null");
		
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
		dungeon = (Dungeon) ois.readObject();
		mainHero = (Hero) ois.readObject();
		ois.close();
		fis.close();
	}
	
	private void moveLeft(Dungeon dungeon, Hero mainHero) throws Exception {
		
		if(dungeon == null)throw new Exception("Dungeon object passed into moveLeft is null");
		if(mainHero == null)throw new Exception("Hero object passed into moveLeft is null");
		
		if(dungeon.getHeroRoomXLoc() == 0 && dungeon.getHeroRoomYLoc() == 2 && dungeon.getHeroDungeonXLoc() > 0) {
        	dungeon.setHeroRoomXLoc(4);
        	dungeon.setHeroDungeonXLoc(dungeon.getHeroDungeonXLoc()-1);
        	dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroDungeonYLoc()].checkContains(mainHero, dungeon);
        	if(!dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).checkBattle(mainHero)) 
        		return;
        }else if(dungeon.getHeroRoomXLoc() > 0) {
        	dungeon.setHeroRoomXLoc(dungeon.getHeroRoomXLoc()-1);
        	dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroRoomYLoc()].checkContains(mainHero, dungeon);
        }
		dungeon.displayRoom("current");
	}

	private void moveRight(Dungeon dungeon, Hero mainHero) throws Exception {
		
		if(dungeon == null)throw new Exception("Dungeon object passed into moveRight is null");
		if(mainHero == null)throw new Exception("Hero object passed into moveRight is null");
		
		if(dungeon.getHeroRoomXLoc() == 4 && dungeon.getHeroRoomYLoc() == 2 && dungeon.getHeroDungeonXLoc() < 4) {
    		dungeon.setHeroRoomXLoc(0);
    		dungeon.setHeroDungeonXLoc(dungeon.getHeroDungeonXLoc()+1);
    		dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroRoomYLoc()].checkContains(mainHero, dungeon);
    		if(!dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).checkBattle(mainHero)) 
        		return;
    	}else if(dungeon.getHeroRoomXLoc() < 4) {
        	dungeon.setHeroRoomXLoc(dungeon.getHeroRoomXLoc()+1);
        	dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroDungeonYLoc()].checkContains(mainHero, dungeon);
        }
    	dungeon.displayRoom("current");
	}

	private void moveUp(Dungeon dungeon, Hero mainHero) throws Exception {
		
		if(dungeon == null)throw new Exception("Dungeon object passed into moveUp is null");
		if(mainHero == null)throw new Exception("Hero object passed into moveUp is null");
		
		if(dungeon.getHeroRoomXLoc() == 2 && dungeon.getHeroRoomYLoc() == 4 && dungeon.getHeroDungeonYLoc() < 4) {
    		dungeon.setHeroRoomYLoc(0);
    		dungeon.setHeroDungeonYLoc(dungeon.getHeroDungeonYLoc()+1);
    		dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroDungeonYLoc()].checkContains(mainHero, dungeon);
    		if(!dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).checkBattle(mainHero)) 
        		return;
    	}else if(dungeon.getHeroRoomYLoc() < 4) {
        	dungeon.setHeroRoomYLoc(dungeon.getHeroRoomYLoc()+1);
        	dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroDungeonYLoc()].checkContains(mainHero, dungeon);
    	}
		dungeon.displayRoom("current");
	}

	private void moveDown(Dungeon dungeon, Hero mainHero) throws Exception {
		
		if(dungeon == null)throw new Exception("Dungeon object passed into moveDown is null");
		if(mainHero == null)throw new Exception("Hero object passed into moveDown is null");
		
		if(dungeon.getHeroRoomXLoc() == 2 && dungeon.getHeroRoomYLoc() == 0 && dungeon.getHeroDungeonYLoc() > 0) {
    		dungeon.setHeroRoomYLoc(4);
    		dungeon.setHeroDungeonYLoc(dungeon.getHeroDungeonYLoc()-1);
    		dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroDungeonYLoc()].checkContains(mainHero, dungeon);
    		if(!dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).checkBattle(mainHero)) 
        		return;
    	}else if(dungeon.getHeroRoomYLoc() > 0) {
        	dungeon.setHeroRoomYLoc(dungeon.getHeroRoomYLoc()-1);
        	dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroDungeonYLoc()].checkContains(mainHero, dungeon);
        }
		dungeon.displayRoom("current");
	}
}