package dungeon;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

public class Dungeon implements Serializable {

	int xDimension = 5;
	int yDimension = 5;
	Room[][] dungeonRoom = new Room[5][5];
	
	public Dungeon() {
		
		boolean exit = false;
		boolean pillarE = false;
		boolean pillarA = false;
		boolean pillarI = false;
		boolean pillarP = false;
		
		for(int x = 0; x < xDimension; x++) {
			for(int y = 0; y < yDimension; y++)
				dungeonRoom[x][y] = new Room();
		}
		//crate rooms
		Random rand = new Random();
		int xRand = rand.nextInt(xDimension);
		int yRand = rand.nextInt(yDimension);
		dungeonRoom[xRand][yRand].entrance = true;
		while(exit == false) {
			xRand = rand.nextInt(xDimension);
			yRand = rand.nextInt(yDimension);
			if(!dungeonRoom[xRand][yRand].entrance) {
				dungeonRoom[xRand][yRand].exit = true;
				exit = true;
			}
		}
		while(pillarE == false) {
			xRand = rand.nextInt(xDimension);
			yRand = rand.nextInt(yDimension);
			if(!dungeonRoom[xRand][yRand].entrance && !dungeonRoom[xRand][yRand].exit) {
				dungeonRoom[xRand][yRand].pillarE = true;
				pillarE = true;
			}
		}
		while(pillarA == false) {
			xRand = rand.nextInt(xDimension);
			yRand = rand.nextInt(yDimension);
			if(!dungeonRoom[xRand][yRand].pillarE && !dungeonRoom[xRand][yRand].entrance  && !dungeonRoom[xRand][yRand].exit) {
				dungeonRoom[xRand][yRand].pillarA = true;
				pillarA = true;
			}
		}
		while(pillarI == false) {
			xRand = rand.nextInt(xDimension);
			yRand = rand.nextInt(yDimension);
			if(!dungeonRoom[xRand][yRand].pillarE && !dungeonRoom[xRand][yRand].pillarA && !dungeonRoom[xRand][yRand].entrance && !dungeonRoom[xRand][yRand].exit) {
				dungeonRoom[xRand][yRand].pillarI = true;
				pillarI = true;
			}
		}
		while(pillarP == false) {
			xRand = rand.nextInt(xDimension);
			yRand = rand.nextInt(yDimension);
			if(!dungeonRoom[xRand][yRand].pillarE && !dungeonRoom[xRand][yRand].pillarA && !dungeonRoom[xRand][yRand].pillarI && !dungeonRoom[xRand][yRand].entrance && !dungeonRoom[xRand][yRand].exit) {
				dungeonRoom[xRand][yRand].pillarP = true;
				pillarP = true;
			}
		}
		for(int x = 0; x < xDimension; x++) {
			for(int y = 0; y < yDimension; y++) 
				dungeonRoom[x][y].initializeTiles();
		}
	}
	

	
	
}
/*
 Creates/contains a 5 X 5 2D Array of Rooms (you can make this larger if you wish)
 Places the Entrance, the Exit, and the Pillars of OO Pieces. NOTES: the entrance and exit
 are empty rooms. The Pillar pieces cannot be at the entrance or the exit. Pillar pieces must not occur in the same room.
 Maintains location of the Hero in the Dungeon
 Contains a toString method that builds a String containing information about the entire dungeon.
*/