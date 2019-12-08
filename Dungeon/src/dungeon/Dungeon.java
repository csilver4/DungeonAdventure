package dungeon;
import java.lang.reflect.Method;

public class Dungeon {

	private int heroDungeonXLoc;
	private int heroDungeonYLoc;
	private int heroRoomXLoc;
	private int heroRoomYLoc;
	private int dungeonXLength;
	private int dungeonYLength;
	private Room[][] dungeonRooms;
	
	public Dungeon(int dungeonXLength, int dungeonYLength) throws Exception {
		
		if((dungeonXLength < 3 && dungeonYLength < 2) || (dungeonXLength < 2 && dungeonYLength < 3))throw new Exception("Ints passed into dungeon constructor would create a dungeon that is too small to hold all possible key objects");
		
		this.dungeonXLength = dungeonXLength;
		this.dungeonYLength = dungeonYLength;
		this.dungeonRooms = new Room[this.dungeonXLength][this.dungeonYLength];
		for(int x = 0; x < this.dungeonXLength; x++) {
			for(int y = 0; y < this.dungeonYLength; y++)
				this.dungeonRooms[x][y] = new Room(5,5);
		}
		placeKeyObject("setRoomContainsEntrance", (int)(Math.random() * this.dungeonXLength), (int)(Math.random() * this.dungeonYLength));
		placeKeyObject("setRoomContainsExit", (int)(Math.random() * this.dungeonXLength), (int)(Math.random() * this.dungeonYLength));
		placeKeyObject("setRoomContainsPillarA", (int)(Math.random() * this.dungeonXLength), (int)(Math.random() * this.dungeonYLength));
		placeKeyObject("setRoomContainsPillarE", (int)(Math.random() * this.dungeonXLength), (int)(Math.random() * this.dungeonYLength));
		placeKeyObject("setRoomContainsPillarI", (int)(Math.random() * this.dungeonXLength), (int)(Math.random() * this.dungeonYLength));
		placeKeyObject("setRoomContainsPillarP", (int)(Math.random() * this.dungeonXLength), (int)(Math.random() * this.dungeonYLength));
		for(int x = 0; x < this.dungeonXLength; x++) {
			for(int y = 0; y < this.dungeonYLength; y++)
				this.dungeonRooms[x][y].configureRoomTiles();
		}
		setHeroAtEntrance();
	}

	private void placeKeyObject(String setMethod, int xRand, int yRand) throws Exception {
		
		if(setMethod == null)throw new Exception("String object passed into placeKeyObject is null");
		if( xRand < 0 || xRand > this.dungeonXLength || yRand < 0 || yRand > this.dungeonYLength) throw new Exception("xRand or yRand passed into placeKeyObject are less then 0 or larger than the dungeons dimensions");
		
		while(!this.dungeonRooms[xRand][yRand].doesRoomContainsKeyObject()) {
			if(!this.dungeonRooms[xRand][yRand].doesRoomContainsKeyObject()) {
				Method method = this.dungeonRooms[xRand][yRand].getClass().getMethod(setMethod, boolean.class);
				method.invoke(this.dungeonRooms[xRand][yRand], true);
				this.dungeonRooms[xRand][yRand].setRoomContainsObject(true);
				this.dungeonRooms[xRand][yRand].setRoomContainsKeyObject(true);
				return;
			}
			xRand = (int)(Math.random() * this.dungeonXLength);
			yRand = (int)(Math.random() * this.dungeonYLength);
		}
	}

	private void setHeroAtEntrance() {
		
		outerloop:
		for(int x = 0; x < this.dungeonXLength; x++) {
			for(int y = 0; y < this.dungeonYLength; y++) {
				if(this.dungeonRooms[x][y].doesRoomContainEntrance()) {
					this.heroDungeonXLoc = x;
					this.heroDungeonYLoc = y;
					break outerloop;
				}
			}
		}
		outerloop2:
		for(int x = 0; x < this.dungeonRooms[this.heroDungeonXLoc][this.heroDungeonYLoc].getRoomXLength(); x++) {
			for(int y = 0; y <this.dungeonRooms[this.heroDungeonXLoc][this.heroDungeonYLoc].getRoomYLength(); y++) {
				if(this.dungeonRooms[this.heroDungeonXLoc][this.heroDungeonYLoc].getFloorTiles()[x][y].doesTileContainEntrance()) {
					this.heroRoomXLoc = x;
					this.heroRoomYLoc = y;
					break outerloop2;
				}
			}
		}
	}
	


	public void displayRoom(String display)throws Exception {
		
		if(display == null)throw new Exception("String objectpassed into displayRoom is null");
		
		if(display.equalsIgnoreCase("current"))
			displayCurrentRoom();
		if(display.equalsIgnoreCase("vision"))
			visionPotionDisplay();
		if(display.equalsIgnoreCase("all"))
			displayAllRooms();
	}

	private void displayCurrentRoom()throws Exception {
		
		if(this.heroDungeonXLoc < 0 || this.heroDungeonXLoc > this.dungeonXLength ||this.heroDungeonYLoc < 0 || this.heroDungeonYLoc > this.dungeonYLength) throw new Exception("displayCurrentRoom cannot disply heros current room because it is out of bounds");
		
		if(this.heroDungeonYLoc < 4)
			System.out.println("- - - D - - -");
		else
			System.out.println("- - - - - - -");
		for(int ry = this.dungeonRooms[this.heroDungeonXLoc][this.heroDungeonYLoc].getRoomYLength()-1; ry >= 0; ry--) {
			if(ry == 2 && this.heroDungeonXLoc != 0)
				System.out.print("D");
			else
				System.out.print("|");
			for(int rx = 0; rx < this.dungeonRooms[this.heroDungeonXLoc][this.heroDungeonYLoc].getRoomXLength(); rx++) {
				if(rx == this.heroRoomXLoc && ry == this.heroRoomYLoc)
					System.out.print(" C");
				else 
					System.out.print(this.dungeonRooms[this.heroDungeonXLoc][this.heroDungeonYLoc].getFloorTiles()[rx][ry].displayTile());
			}
			if(ry == 2 && this.heroDungeonXLoc != 4)
				System.out.println(" D");
			else
				System.out.println(" |");
		}
		if(this.heroDungeonYLoc > 0)
			System.out.println("- - - D - - -");
		else
			System.out.println("- - - - - - -");
	}
	
	private void visionPotionDisplay() {
		// TODO Auto-generated method stub
		
	}
	
	private void displayAllRooms() {
		
		int yCounter = 0;
		int wallCounterX = 1;
		int wallCounterY = this.dungeonYLength+1;
		int tileCounterX = 0;
		int tileCounterY = this.dungeonRooms[0][0].getRoomYLength()-1;
		int dungeonGridXLength = (this.dungeonXLength * this.dungeonRooms[0][0].getRoomXLength())+(this.dungeonXLength+1);
		int dungeonGridYLength = (this.dungeonYLength * this.dungeonRooms[0][0].getRoomYLength())+(this.dungeonYLength+1);
		for(int gridY = dungeonGridYLength; gridY > 0; gridY--) {
			for(int gridX = 0; gridX < dungeonGridXLength; gridX++) {
				if(gridY == 0 || gridY == dungeonGridYLength || (gridY-wallCounterY)/(this.dungeonRooms[0][0].getRoomYLength() * wallCounterY) == 1) {
					if(gridX != 0 && gridX != dungeonGridXLength && gridY != 0 && gridY != dungeonGridYLength && gridX == ((this.dungeonRooms[0][0].getRoomXLength()+1)/2)*wallCounterX)
						System.out.print(" D");
					else
						System.out.print(" -");
					yCounter = 1;
				}else if(gridX == dungeonGridXLength || gridX == 0 || (gridX-wallCounterX)/(this.dungeonRooms[0][0].getRoomXLength() * wallCounterX) == 1) {
					if(gridX != 0 && gridX != dungeonGridXLength && gridY != 0 && gridY != dungeonGridYLength && gridY == ((this.dungeonRooms[0][0].getRoomYLength()+1)/2)*wallCounterX)
						System.out.print(" D");
					else 
						System.out.print(" |");
					tileCounterX = 0;
					wallCounterX++;
					
				}else {
					System.out.print(this.dungeonRooms[wallCounterX-1][wallCounterY-1].getFloorTiles()[tileCounterX][tileCounterY].displayTile());
					tileCounterX++;
				}
			}
			if(yCounter == 1) {
				tileCounterY = 4;
				wallCounterX = 0;
				wallCounterY--;
			}
			System.out.println("");
		}
	}

	public int getHeroDungeonXLoc() {
		
		return this.heroDungeonXLoc;
	}

	public int getHeroDungeonYLoc() {
		
		return this.heroDungeonYLoc;
	}
	
	public int getDungeonXLength() {
		
		return this.dungeonXLength;
	}

	public int getDungeonYLength() {
		
		return this.dungeonYLength;
	}

	public int getHeroRoomXLoc() {
		return heroRoomXLoc;
	}

	public int getHeroRoomYLoc() {
		return heroRoomYLoc;
	}

	public Room getDungeonRoom(int x, int y) {
		
		return dungeonRooms[x][y];
	}
	
	public void setHeroRoomXLoc(int heroRoomXLoc) {
		this.heroRoomXLoc = heroRoomXLoc;
	}

	public void setHeroRoomYLoc(int heroRoomYLoc) {
		this.heroRoomYLoc = heroRoomYLoc;
	}

	public void setHeroDungeonXLoc(int heroDungeonXLoc) {
		
		this.heroDungeonXLoc = heroDungeonXLoc;
	}
	
	public void setHeroDungeonYLoc(int heroDungeonYLoc) {
		
		this.heroDungeonYLoc = heroDungeonYLoc;
	}
}

