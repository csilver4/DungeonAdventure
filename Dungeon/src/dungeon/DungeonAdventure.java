package dungeon;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class DungeonAdventure {
	
	private Scanner kb;
	private Dungeon dungeon;
	private Hero mainHero;
	
	public static void main(String[] args) throws Exception{
		
		DungeonAdventure game = new DungeonAdventure();
		game.kb = new Scanner(System.in);
		System.out.println("Dungeon Adventure!");
		System.out.println("Explore the dungeon, find the pillars of OO, and fight off monsters");
		System.out.println("press enter to continue, press L to load a saved game\n");
		game.mainHero = null;
		game.dungeon = null;
		String temp = game.kb.nextLine();
		if(temp.equalsIgnoreCase("l")) {
			try {
				game.loadGame(new File("save.ser"));
			}catch (Exception ex) {
				System.out.println("Error opening file - " + ex.getMessage()); 
				game.mainHero = HeroFactory.factory(game.gameIntroduction());
				game.dungeon = new Dungeon(5,5);
			}
		}else if(temp.equalsIgnoreCase("d")) {
			game.mainHero = HeroFactory.factory(game.gameIntroduction());
			game.dungeon = new Dungeon(5, 5);
			game.dungeon.displayRoom("all");
		}else{
			game.mainHero = HeroFactory.factory(game.gameIntroduction());
			game.dungeon = new Dungeon(5,5);
		}
		String res;
		do {
			game.dungeon.displayRoom("current");
			game.runGame();
			game.dungeon.displayRoom("all");
			System.out.println("do you want to play again?\n Y for yes\n anything else for no");
			res = game.kb.nextLine();
			if(res.equalsIgnoreCase("y")) {
				game.mainHero = HeroFactory.factory(game.gameIntroduction());
				game.dungeon = new Dungeon(5,5);
			}
		}while(res.equalsIgnoreCase("y"));
	}

	private void runGame() throws Exception {
		
		do {
			System.out.print("Direction: ");
			String directionInput = this.kb.nextLine().trim().toLowerCase();
			switch(directionInput) {
			case "l":
				this.moveLeft();
				break;
			case "r":
				this.moveRight();
				break;
			case "u":
				this.moveUp();
				break;
			case "d":
				this.moveDown();
				break;
			case "s":
				this.saveGame(new File("save.ser"));
				break;
			default:
				System.out.println("Invalid Direction");
			}
		}while(mainHero.isAlive());
	}

	private String gameIntroduction() throws Exception {
		
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
		this.kb.nextLine();
		System.out.println("please select a new Character by entering the number");
		System.out.println(" 1) King Arther");
		System.out.println(" 2) Brave Sir Robin");
		System.out.println(" 3) Zoot");
		System.out.println(" 4) Sir Lancelot");
		System.out.println(" 5) a Swallow");
		String input = this.kb.nextLine();
		return input;
	}
	
	private void saveGame(File f) throws Exception {
		
		if(f == null)throw new Exception("File object passed into saveGame is null");
		
		try {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.dungeon);
			System.out.println("dungeon was serialized");
			oos.writeObject(this.mainHero);
			System.out.println("hero was serialized");
			oos.close();
			fos.close();
			System.out.println("Game has been saved to save.ser");
		} catch (Exception ex) {
			System.out.println("Error saving file - " + ex.getMessage());
		}
		
	}
	
	private void loadGame(File f) throws Exception {
		
		if(f == null)throw new Exception("File object passed into loadGame is null");
		
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
		this.dungeon = (Dungeon) ois.readObject();
		this.mainHero = (Hero) ois.readObject();
		ois.close();
		fis.close();
	}
	
	private void moveLeft() throws Exception {
		
		if(dungeon.getHeroRoomXLoc() == 0 && dungeon.getHeroRoomYLoc() == 2 && dungeon.getHeroDungeonXLoc() > 0) {
        	dungeon.setHeroRoomXLoc(4);
        	dungeon.setHeroDungeonXLoc(dungeon.getHeroDungeonXLoc()-1);
        	dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroRoomYLoc()].checkContains(mainHero, dungeon);
        	if(!dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).checkBattle(mainHero)) 
        		return;
        }else if(dungeon.getHeroRoomXLoc() > 0) {
        	dungeon.setHeroRoomXLoc(dungeon.getHeroRoomXLoc()-1);
        	dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroRoomYLoc()].checkContains(mainHero, dungeon);
        }
		dungeon.displayRoom("current");
	}

	private void moveRight() throws Exception {
		
		if(dungeon.getHeroRoomXLoc() == 4 && dungeon.getHeroRoomYLoc() == 2 && dungeon.getHeroDungeonXLoc() < 4) {
    		dungeon.setHeroRoomXLoc(0);
    		dungeon.setHeroDungeonXLoc(dungeon.getHeroDungeonXLoc()+1);
    		dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroRoomYLoc()].checkContains(mainHero, dungeon);
    		if(!dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).checkBattle(mainHero)) 
        		return;
    	}else if(dungeon.getHeroRoomXLoc() < 4) {
        	dungeon.setHeroRoomXLoc(dungeon.getHeroRoomXLoc()+1);
        	dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroRoomYLoc()].checkContains(mainHero, dungeon);
        }
    	dungeon.displayRoom("current");
	}

	private void moveUp() throws Exception {
		
		if(dungeon.getHeroRoomXLoc() == 2 && dungeon.getHeroRoomYLoc() == 4 && dungeon.getHeroDungeonYLoc() < 4) {
    		dungeon.setHeroRoomYLoc(0);
    		dungeon.setHeroDungeonYLoc(dungeon.getHeroDungeonYLoc()+1);
    		dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroRoomYLoc()].checkContains(mainHero, dungeon);
    		if(!dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).checkBattle(mainHero)) 
        		return;
    	}else if(dungeon.getHeroRoomYLoc() < 4) {
        	dungeon.setHeroRoomYLoc(dungeon.getHeroRoomYLoc()+1);
        	dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroRoomYLoc()].checkContains(mainHero, dungeon);
    	}
		dungeon.displayRoom("current");
	}

	private void moveDown() throws Exception {
		
		if(dungeon.getHeroRoomXLoc() == 2 && dungeon.getHeroRoomYLoc() == 0 && dungeon.getHeroDungeonYLoc() > 0) {
    		dungeon.setHeroRoomYLoc(4);
    		dungeon.setHeroDungeonYLoc(dungeon.getHeroDungeonYLoc()-1);
    		dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroRoomYLoc()].checkContains(mainHero, dungeon);
    		if(!dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).checkBattle(mainHero)) 
        		return;
    	}else if(dungeon.getHeroRoomYLoc() > 0) {
        	dungeon.setHeroRoomYLoc(dungeon.getHeroRoomYLoc()-1);
        	dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroRoomYLoc()].checkContains(mainHero, dungeon);
        }
		dungeon.displayRoom("current");
	}
}