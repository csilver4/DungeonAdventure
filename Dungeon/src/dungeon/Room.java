package dungeon;
import java.util.Random;

public class Room {
	
	int xDimension = 5;
	int yDimension = 5;
	boolean hPotion;
	boolean vPotion;
	boolean pit;
	boolean m;
	boolean entrance;
	boolean exit;
	boolean pillarE;
	boolean pillarA;
	boolean pillarI;
	boolean pillarP;
	Tile[][] floorTile = new Tile[5][5];
	
	public boolean placeObject(Tile floorTile) {
		
			if(!floorTile.contains) {
				floorTile.pit = true;
				floorTile.contains = true;
				return true;
			}
			return false;
	}
	
	public void initializeTiles() {
		
		Random rand = new Random();
		int xRand = rand.nextInt(xDimension);
		int yRand = rand.nextInt(yDimension);
		if(!entrance && !exit) {
			int randTemp = rand.nextInt(10);
			if(randTemp == 0)
				hPotion = true;
			randTemp = rand.nextInt(20);
			if(randTemp == 0)
				vPotion = true;
			randTemp = rand.nextInt(5);
			if(randTemp == 0)
				pit = true;
			randTemp = rand.nextInt(10);
			if(randTemp == 0)
				m = true;
		}
		for(int x = 0; x < xDimension; x++) {
			for(int y = 0; y < yDimension; y++)
				floorTile[x][y] = new Tile();
		}
		if(entrance) {
			floorTile[xRand][yRand].entrance = true;
			floorTile[xRand][yRand].contains = true;
		}else if(exit) {
			xRand = rand.nextInt(xDimension);
			yRand = rand.nextInt(yDimension);
			floorTile[xRand][yRand].exit = true;
			floorTile[xRand][yRand].contains = true;
		}else if(hPotion || vPotion || pit || pillarE || pillarA || pillarI || pillarP) {
			if(pit) {
				for(int x = 0; x < 3; x++) {
					do {
						xRand = rand.nextInt(xDimension);
						yRand = rand.nextInt(yDimension);
						if(!floorTile[xRand][yRand].contains) {
							floorTile[xRand][yRand].pit = true;
							floorTile[xRand][yRand].contains = true;
							break;
						}
					}while(floorTile[xRand][yRand].contains);
				}
			}
			if(hPotion) {
				do {
					xRand = rand.nextInt(xDimension);
					yRand = rand.nextInt(yDimension);
					if(!floorTile[xRand][yRand].contains) {
						floorTile[xRand][yRand].hPotion = true;
						floorTile[xRand][yRand].contains = true;
						break;
					}
				}while(floorTile[xRand][yRand].contains);
			}
			if(vPotion) {
				do {
					xRand = rand.nextInt(xDimension);
					yRand = rand.nextInt(yDimension);
					if(!floorTile[xRand][yRand].contains) {
						floorTile[xRand][yRand].vPotion = true;
						floorTile[xRand][yRand].contains = true;
						break;
					}
				}while(floorTile[xRand][yRand].contains);
			}
			if(pillarE) {
				do {
					xRand = rand.nextInt(xDimension);
					yRand = rand.nextInt(yDimension);
					if(!floorTile[xRand][yRand].contains) {
						floorTile[xRand][yRand].pillarE = true;
						floorTile[xRand][yRand].contains = true;
						break;
					}
				}while(floorTile[xRand][yRand].contains);
			}
			if(pillarA) {
				do {
					xRand = rand.nextInt(xDimension);
					yRand = rand.nextInt(yDimension);
					if(!floorTile[xRand][yRand].contains) {
						floorTile[xRand][yRand].pillarA = true;
						floorTile[xRand][yRand].contains = true;
						break;
					}
				}while(floorTile[xRand][yRand].contains);
			}
			if(pillarI) {
				do {
					xRand = rand.nextInt(xDimension);
					yRand = rand.nextInt(yDimension);
					if(!floorTile[xRand][yRand].contains) {
						floorTile[xRand][yRand].pillarI = true;
						floorTile[xRand][yRand].contains = true;
						break;
					}
				}while(floorTile[xRand][yRand].contains);
			}
			if(pillarP) {
				do {
					xRand = rand.nextInt(xDimension);
					yRand = rand.nextInt(yDimension);
					if(!floorTile[xRand][yRand].contains) {
						floorTile[xRand][yRand].pillarP = true;
						floorTile[xRand][yRand].contains = true;
						break;
					}
				}while(floorTile[xRand][yRand].contains);
			}
		}
	}

	public boolean checkBattle(Hero mainHero) {
		
		if(m == true)
		{
			Monster monster = Monster.monsterFactory();
			return mainHero.battle(monster);
		}
		return true;
	}
}
/*
 Contains default constructor and all methods you deem necessary -- modular design is CRUCIAL
 Contains the following items/behaviors
 (Possibly a) Healing Potion - heals 5-15 hit points (this amount will be randomly generated -- you can modify the range)
 (Possibly a) Pit - damage a pit can cause is from 1-20 hit points (this amount will be randomly generated - you can modify the range)
 (Possibly an) Entrance - only one room will have an entrance and the room that contains the entrance will contain NOTHING else
 (Possibly an) Exit - only one room will have an exit and the room that contains the exit will contain NOTHING else
 (Possibly a) Pillar of OO - four pieces in game and they will never be in the same room
 (Possibly a) Monster Doors - N, S, E, W
 10% possibility (this is a constant that you can modify) room will contain ahealing potion, vision potion, and pit (each of these are 
 independent of one another)
 (If working as a team) Vision Potion - can be used to allow user to see eightrooms surrounding current room as well as current room 
 (location in maze may cause less than 8 to be displayed). The Vision Potion allows you to see the rooms that are immediately around you 
 (this is up to eight rooms depending on your location in the dungeon). This potion only lasts for a single turn.
 Example:
 The hero is currently in room 1,1. If the hero drinks the Vision Potion, then the following rooms are visible for a single turn.
 Room 0,0 Room 0,1 Room 0,2
 Room 1,0 Room 1,1 Room 1,2
 Room 2,0 Room 2,1 Room 2,2
 Must contain a toString method that builds a 2D Graphical representation of the room
 (NOTE: you may use any graphical components in Java that you wish). The (command line) representation is as follows:
 * - * will represent a north/south door (the - represents the door). If the room is on a boundary of the maze (upper or lower), then that
 will be represented with ***
 East/west doors will be represented in a similar fashion with the door being the | character as opposed to a -.
 In the center of the room you will display a letter that represents what the room
 contains. Here are the letters to use and what they represent:
- M - Multiple Items
- P - Pit
- I - Entrance (In)
- O - Exit (Out)
- V - Vision Potion
- H - Healing Potion
- E - Empty Room
- X - Monster
Example: Room 1,1 might look like
* - *
| P |
* - *
Room 0,0 might look like
* * *
* E |
* - *
*/