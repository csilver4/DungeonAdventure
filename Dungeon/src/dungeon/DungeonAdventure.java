package dungeon;
import java.util.Scanner;
import java.awt.event.KeyEvent;

public class DungeonAdventure {

	static boolean pillarE;
	static boolean pillarA;
	static boolean pillarI;
	static boolean pillarP;
	static int dungeonXLoc;
	static int dungeonYLoc;
	static int roomXLoc;
	static int roomYLoc;
	static Dungeon dungeon = new Dungeon();
	static Hero mainHero;
	static boolean endGame;
	
	public static void main(String[] args) {
		
		Scanner kb = new Scanner(System.in);
		System.out.println("Dungeon Adventure!");
		System.out.println("Explore the dungeon, find the pillars of OO, and fight off monsters");
		System.out.println("press enter to continue, press s to load a saved game\n");
		if(kb.nextLine().equalsIgnoreCase("s")) {
			loadGame();
		}else {
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
			mainHero = Hero.factory(input);
			outerloop:
			for(int x = 0; x < dungeon.xDimension; x++) {
				for(int y = 0; y < dungeon.yDimension; y++) {
					if(dungeon.dungeonRoom[x][y].entrance) {
						dungeonXLoc = x;
						dungeonYLoc = y;
						break outerloop;
					}
				}
			}
			outerloop2:
			for(int x = 0; x < dungeon.dungeonRoom[dungeonXLoc][dungeonYLoc].xDimension; x++) {
				for(int y = 0; y < dungeon.dungeonRoom[dungeonXLoc][dungeonYLoc].yDimension; y++) {
					if(dungeon.dungeonRoom[dungeonXLoc][dungeonYLoc].floorTile[x][y].entrance) {
						roomXLoc = x;
						roomYLoc = y;
						break outerloop2;
					}
				}
			}
			displayRoom(dungeonXLoc, dungeonYLoc);
		}
		while(endGame) {
			
		}
		String temp;
		do {
			System.out.println("do you want to play again?\n Y for yes\n anything else for no");
			temp = kb.nextLine();
		}while(temp.equals(""));
		if(temp.equalsIgnoreCase("y")) {
			//play again
		}
		kb.close();
		//displays all rooms
		for(int y = dungeon.yDimension-1; y >= 0; y--) {
			for(int x = 0; x < dungeon.xDimension; x++)
				displayRoom(x,y);
		}
	}
	
	private static void loadGame() {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
        Integer key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            if(roomXLoc == 0 && roomYLoc == 2 && dungeonXLoc > 0) {
            	roomXLoc = 4;
            	dungeonXLoc--;
            	dungeon.dungeonRoom[dungeonXLoc][dungeonYLoc].floorTile[roomXLoc][roomYLoc].checkContains(mainHero);
            	if(!dungeon.dungeonRoom[dungeonXLoc][dungeonYLoc].checkBattle(mainHero)) {
            		gameOver("Loss");
            	}
            	displayRoom(dungeonXLoc, dungeonYLoc);
            }
        	if(roomXLoc > 0) {
            	roomXLoc--;
            	dungeon.dungeonRoom[dungeonXLoc][dungeonYLoc].floorTile[roomXLoc][roomYLoc].checkContains(mainHero);
            	displayRoom(dungeonXLoc, dungeonYLoc);
            }
        }
        if (key == KeyEvent.VK_RIGHT) {
        	if(roomXLoc == 4 && roomYLoc == 2 && dungeonXLoc < 4) {
        		roomXLoc = 0;
        		dungeonXLoc++;
        		dungeon.dungeonRoom[dungeonXLoc][dungeonYLoc].floorTile[roomXLoc][roomYLoc].checkContains(mainHero);
        		if(!dungeon.dungeonRoom[dungeonXLoc][dungeonYLoc].checkBattle(mainHero)) {
            		gameOver("Loss");
            	}
        		displayRoom(dungeonXLoc, dungeonYLoc);
        	}
        	if(roomXLoc < 4) {
            	roomXLoc++;
            	dungeon.dungeonRoom[dungeonXLoc][dungeonYLoc].floorTile[roomXLoc][roomYLoc].checkContains(mainHero);
            	displayRoom(dungeonXLoc, dungeonYLoc);
        	}
        }
        if (key == KeyEvent.VK_UP) {
        	if(roomXLoc == 2 && roomYLoc == 4 && dungeonYLoc < 4) {
        		roomYLoc = 0;
        		dungeonYLoc++;
        		dungeon.dungeonRoom[dungeonXLoc][dungeonYLoc].floorTile[roomXLoc][roomYLoc].checkContains(mainHero);
        		if(!dungeon.dungeonRoom[dungeonXLoc][dungeonYLoc].checkBattle(mainHero)) {
            		gameOver("Loss");
            	}
        		displayRoom(dungeonXLoc, dungeonYLoc);
        	}
        	if(roomYLoc < 4) {
            	roomYLoc++;
            	dungeon.dungeonRoom[dungeonXLoc][dungeonYLoc].floorTile[roomXLoc][roomYLoc].checkContains(mainHero);
            	displayRoom(dungeonXLoc, dungeonYLoc);
        	}
        }
        if (key == KeyEvent.VK_DOWN) {
        	if(roomXLoc == 2 && roomYLoc == 0 && dungeonYLoc > 0) {
        		roomYLoc = 4;
        		dungeonYLoc--;
        		dungeon.dungeonRoom[dungeonXLoc][dungeonYLoc].floorTile[roomXLoc][roomYLoc].checkContains(mainHero);
        		if(!dungeon.dungeonRoom[dungeonXLoc][dungeonYLoc].checkBattle(mainHero)) {
            		gameOver("Loss");
            	}
        		displayRoom(dungeonXLoc, dungeonYLoc);
        	}
        	if(roomYLoc > 0) {
            	roomYLoc--;
            	dungeon.dungeonRoom[dungeonXLoc][dungeonYLoc].floorTile[roomXLoc][roomYLoc].checkContains(mainHero);
            	displayRoom(dungeonXLoc, dungeonYLoc);
        	}
        }
        if(key == KeyEvent.VK_S) {
        	//save game
        }
    }
	
	public static void displayRoom(int x, int y) {
		
		if(y < 4)
			System.out.println("- - - D - - -");
		else
			System.out.println("- - - - - - -");
		for(int ry = dungeon.dungeonRoom[x][y].yDimension-1; ry >= 0; ry--) {
			if(ry == 2 && x != 0)
				System.out.print("D");
			else
				System.out.print("|");
			for(int rx = 0; rx < dungeon.dungeonRoom[x][y].xDimension; rx++) {
				if(rx == roomXLoc && ry == roomYLoc && dungeonXLoc == x && dungeonYLoc == y)
					System.out.print(" C");
				else if(dungeon.dungeonRoom[x][y].floorTile[rx][ry].contains) {
					if(dungeon.dungeonRoom[x][y].floorTile[rx][ry].hPotion)
						System.out.print(" H");
					if(dungeon.dungeonRoom[x][y].floorTile[rx][ry].vPotion)
						System.out.print(" V");
					if(dungeon.dungeonRoom[x][y].floorTile[rx][ry].pit)
						System.out.print(" O");
					if(dungeon.dungeonRoom[x][y].floorTile[rx][ry].pillarE)
						System.out.print(" E");
					if(dungeon.dungeonRoom[x][y].floorTile[rx][ry].pillarA)
						System.out.print(" A");
					if(dungeon.dungeonRoom[x][y].floorTile[rx][ry].pillarI)
						System.out.print(" I");
					if(dungeon.dungeonRoom[x][y].floorTile[rx][ry].pillarP)
						System.out.print(" P");
					if(dungeon.dungeonRoom[x][y].floorTile[rx][ry].entrance)
						System.out.print(" B");
					if(dungeon.dungeonRoom[x][y].floorTile[rx][ry].exit)
						System.out.print(" Z");
				}else 
					System.out.print(" #");
			}
			if(ry == 2 && x != 4)
				System.out.println(" D");
			else
				System.out.println(" |");
		}
		if(y > 0)
			System.out.println("- - - D - - -");
		else
			System.out.println("- - - - - - -");
	}

	public static void gameOver(String res) {
		if(res.equals("win")) {
			System.out.println("You Won!");
			endGame = true; 
			return;
		}
		System.out.println("You Lost!");
		endGame = false;
	}

	public static void setPillarP(boolean b) {
		
		pillarP = b;
	}

	public static void setPillarI(boolean b) {
		
		pillarI = b;
	}

	public static void setPillarA(boolean b) {
		
		pillarA = b;
	}

	public static void setPillarE(boolean b) {
		
		pillarE = b;
	}

	public static boolean getPillarE() {
		
		return pillarE;
	}
	
	public static boolean getPillarA() {
		
		return pillarA;
	}
	
	public static boolean getPillarI() {
		
		return pillarI;
	}
	
	public static boolean getPillarP() {
		
		return pillarP;
	}

	public static void visionPotionDisplay() {
		
		
	}
}
/* 
 Contains the main method
 Provides an introduction to the game describing what the game is about and how to play
 Creates a Dungeon Object and a Hero Object (based on user choice)
 Does the following repetitively:
 Prints the current room (this is based on the Hero's current location)
 Determines the Hero's options (Move, Use a Potion)
 Continues this process until the Hero wins or dies
 NOTE: Include a hidden menu option for testing that prints out the entire Dungeon
  -- specify what the menu option is in your documentation for the DungeonAdventure class
 At the end of the game, display the entire Dungeon
 */