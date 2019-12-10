package dungeon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class DungeonAdventure {
	
    private Hero      mainHero;
    private Dungeon   dungeon;
    public Scanner   kb;
	
	public static void main(String[] args) throws Exception{
		DungeonAdventure instance = new DungeonAdventure();
        instance.kb = new Scanner(System.in);
		System.out.println("Dungeon Adventure!");
		System.out.println("Explore the dungeon, find the pillars of OO, and fight off monsters");
		System.out.println("press enter to continue, press L to load a saved game\n");
		Hero mainHero = null;
		Dungeon dungeon = null;
		String userInputOnLoad = instance.kb.nextLine();
		if(userInputOnLoad.equalsIgnoreCase("l")) {
			try {
				instance.loadGame(new File("save.ser"), dungeon, mainHero);
			}catch (Exception ex) {
				System.out.println("Error opening file - " + ex.getMessage()); 
				mainHero = Hero.factory(instance.gameIntroduction());
				dungeon = new Dungeon(5,5);
			}
		}else{
			mainHero = Hero.factory(instance.gameIntroduction());
			dungeon = new Dungeon(5,5);
		}
		
		
		//kb.close();
		
		
		String res;
		do {
			dungeon.displayRoom("current");
			instance.runGame(mainHero, dungeon);
			System.out.println("do you want to play again?\n Y for yes\n anything else for no");
			res = instance.kb.nextLine();
			if(res.equalsIgnoreCase("y")) {
				mainHero = Hero.factory(instance.gameIntroduction());
				dungeon = new Dungeon(5,5);
			}
		}while(res.equalsIgnoreCase("y"));
	}
	
	public Hero getHero()
	{
		return this.mainHero;
	}
	
	public void setHero(Hero mainHero)
	{
		this.mainHero = mainHero;
	}
	


	private void runGame(Hero mainHero, Dungeon dungeon) throws Exception 
	{

		do {
			    System.out.print("Direction: ");
			    String inputtedDirection = kb.nextLine().trim().toLowerCase();
			    switch(inputtedDirection) {
			    case"l": 
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
			    System.out.println("Direction Moved");
			} while(mainHero.isAlive());			
	}

	private String gameIntroduction() {
		
		Scanner kb = new Scanner(System.in);
		System.out.print("LEGEND:\n");
		System.out.print("Entrance: B\n");
		System.out.print("Exit: Z\n");
		System.out.print("Health Potion: H\n");
		System.out.print("Vision Potion: V\n");
		System.out.print("Pit: O\n");
		System.out.print("Abstraction Pillar: A\n");
		System.out.print("Encapsulation Pillar: E\n");
		System.out.print("Inheritance Pillar: I\n");
		System.out.print("Polymorphism Pillar P\n");
		System.out.print("Door: D\n");
		System.out.print("Regular Floor: #\n");
		System.out.print("Character: C\n");
		System.out.print("Press enter to continue...\n");
		kb.nextLine();
		System.out.print("please select a new Character by entering the number\n");
		System.out.print(" 1) King Arther\n");
		System.out.print(" 2) Brave Sir Robin\n");
		System.out.print(" 3) Zoot\n");
		System.out.print(" 4) Sir Lancelot\n");
		System.out.print(" 5) a Swollow\n");
		String input = kb.nextLine();
		return input;
	}
	

	
	private void saveGame(File f, Dungeon dungeon, Hero mainHero) {
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
		
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
		dungeon = (Dungeon) ois.readObject();
		mainHero = (Hero) ois.readObject();
		ois.close();
		fis.close();
	}
	
	private void moveLeft(Dungeon dungeon, Hero mainHero) throws Exception {
		
		if(dungeon.getHeroRoomXLoc() == 0 && dungeon.getHeroRoomYLoc() == 2 && dungeon.getHeroDungeonXLoc() > 0) {
        	dungeon.setHeroRoomXLoc(4);
        	dungeon.setHeroDungeonXLoc(dungeon.getHeroDungeonXLoc()-1);
        	dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroDungeonYLoc()].checkContains(mainHero, dungeon);
        	if(!dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).checkBattle(mainHero)) {
        		//gameOver("Loss");
        	}
        	dungeon.displayRoom("current");
        }
		else if(dungeon.getHeroRoomXLoc() > 0) {
        	dungeon.setHeroRoomXLoc(dungeon.getHeroRoomXLoc()-1);
        	dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroRoomYLoc()].checkContains(mainHero, dungeon);
        	dungeon.displayRoom("current");
        }
	}

	private void moveRight(Dungeon dungeon, Hero mainHero) throws Exception {
		
		if(dungeon.getHeroRoomXLoc() == 4 && dungeon.getHeroRoomYLoc() == 2 && dungeon.getHeroDungeonXLoc() < 4) {
    		dungeon.setHeroRoomXLoc(0);
    		dungeon.setHeroDungeonXLoc(dungeon.getHeroDungeonXLoc()+1);
    		dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroRoomYLoc()].checkContains(mainHero, dungeon);
    		if(!dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).checkBattle(mainHero)) {
        		//gameOver("Loss");
        	}
    		dungeon.displayRoom("current");
    	}
		else if(dungeon.getHeroRoomXLoc() < 4) {
        	dungeon.setHeroRoomXLoc(dungeon.getHeroRoomXLoc()+1);
        	dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroDungeonYLoc()].checkContains(mainHero, dungeon);
        	dungeon.displayRoom("current");
    	}
	}

	private void moveUp(Dungeon dungeon, Hero mainHero) throws Exception {
		
		if(dungeon.getHeroRoomXLoc() == 2 && dungeon.getHeroRoomYLoc() == 4 && dungeon.getHeroDungeonYLoc() < 4) {
    		dungeon.setHeroRoomYLoc(0);
    		dungeon.setHeroDungeonYLoc(dungeon.getHeroDungeonYLoc()+1);
    		dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroDungeonYLoc()].checkContains(mainHero, dungeon);
    		if(!dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).checkBattle(mainHero)) {
        		//gameOver("Loss");
        	}
    		dungeon.displayRoom("current");
    	}
		else if(dungeon.getHeroRoomYLoc() < 4) {
        	dungeon.setHeroRoomYLoc(dungeon.getHeroRoomYLoc());
        	dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroDungeonYLoc()].checkContains(mainHero, dungeon);
        	dungeon.displayRoom("current");
    	}
	}

	private void moveDown(Dungeon dungeon, Hero mainHero) throws Exception {
		
		if(dungeon.getHeroRoomXLoc() == 2 && dungeon.getHeroRoomYLoc() == 0 && dungeon.getHeroDungeonYLoc() > 0) {
    		dungeon.setHeroRoomYLoc(4);
    		dungeon.setHeroDungeonYLoc(dungeon.getDungeonYLength()-1);
    		dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroDungeonYLoc()].checkContains(mainHero, dungeon);
    		if(!dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).checkBattle(mainHero)) {
        		//gameOver("Loss");
        	}
    		dungeon.displayRoom("current");
    	}
		else if(dungeon.getHeroRoomYLoc() > 0) {
        	dungeon.setHeroRoomYLoc(dungeon.getHeroRoomYLoc()-1);
        	dungeon.getDungeonRoom(dungeon.getHeroDungeonXLoc(), dungeon.getHeroDungeonYLoc()).getFloorTiles()[dungeon.getHeroRoomXLoc()][dungeon.getHeroDungeonYLoc()].checkContains(mainHero, dungeon);
        	dungeon.displayRoom("current");
    	}
	}
}
/* 
 NOTE: Include a hidden menu option for testing that prints out the entire Dungeon
  -- specify what the menu option is in your documentation for the DungeonAdventure class
 At the end of the game, display the entire Dungeon
 */