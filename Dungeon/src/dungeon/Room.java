package dungeon;
import java.lang.reflect.Method;

public class Room {
	
	private int roomXLength;
	private int roomYLength;
	private boolean roomContainsHPotion;//regularObject
	private boolean roomContainsVPotion;//regularObject
	private boolean roomContainsPits;   //regularObject
	private boolean roomContainsEntrance;//keyObject
	private boolean roomContainsExit;    //keyObject
	private boolean roomContainsPillarE; //keyObject
	private boolean roomContainsPillarA; //keyObject
	private boolean roomContainsPillarI; //keyObject
	private boolean roomContainsPillarP; //keyObject
	private boolean roomContainsMonster;
	private boolean roomContainsObject;
	private boolean roomContainsKeyObject;
	private Tile[][] floorTile;
	
	public Room(int roomXLength, int roomYLength) throws Exception {
		
		if((roomXLength < 3 && roomYLength < 2) || (roomXLength < 2 && roomYLength < 3))throw new Exception("Ints passed into room constructor would create a room that is too small to hold all possible objects");
		
		this.roomXLength = roomXLength;
		this.roomYLength = roomYLength;
		this.roomContainsHPotion = false;
		this.roomContainsVPotion = false;
		this.roomContainsPits = false;
		this.roomContainsEntrance = false;
		this.roomContainsExit = false;
		this.roomContainsPillarE = false;
		this.roomContainsPillarA = false;
		this.roomContainsPillarI = false;
		this.roomContainsPillarP = false;
		this.roomContainsMonster = false;
		this.roomContainsObject = false;
		this.roomContainsKeyObject = false;
		this.floorTile = new Tile[this.roomXLength][this.roomYLength];
		for(int x = 0; x < this.roomXLength; x++) {
			for(int y = 0; y < this.roomYLength; y++)
				this.floorTile[x][y] = new Tile();
		}
	}
	
	public void configureRoomTiles() throws Exception {
	
		randomizeRoomContents();
		if(this.roomContainsEntrance) 
			placeDungeonPortal("setEntrance");
		else if(this.roomContainsExit) 
			placeDungeonPortal("setExit");
		else if(this.roomContainsObject) {
			if(this.roomContainsPits) {
				for(int x = 0; x < ((Math.random() * 4) + 1); x++) 
					placeRoomObject("setPit");
			}
			if(this.roomContainsHPotion) 
				placeRoomObject("setHPotion");
			if(this.roomContainsVPotion) 
				placeRoomObject("setVPotion");
			if(this.roomContainsPillarA) 
				placeRoomObject("setPillarA");
			if(this.roomContainsPillarE) 
				placeRoomObject("setPillarE");
			if(this.roomContainsPillarI) 
				placeRoomObject("setPillarI");
			if(this.roomContainsPillarP) 
				placeRoomObject("setPillarP");
		}
	}
	
	private void placeDungeonPortal(String setMethod) throws Exception {
		
		if(setMethod == null)throw new Exception("String object passed into placeRoomPortal is null");
		
		int xRand = (int)(Math.random() * this.roomXLength);
		int yRand = (int)(Math.random() * this.roomYLength);
		Method method = this.floorTile[xRand][yRand].getClass().getMethod(setMethod, boolean.class);
        method.invoke(this.floorTile[xRand][yRand], true);
		this.floorTile[xRand][yRand].setTileContainsObject(true);
	}

	private void placeRoomObject(String setMethod) throws Exception {
		
		if(setMethod == null)throw new Exception("String object passed into placeRoomObject is null");
		
		int xRand = (int)(Math.random() * this.roomXLength);
		int yRand = (int)(Math.random() * this.roomYLength);
		do {
			if(!this.floorTile[xRand][yRand].doesTileContainObject()) {
		        Method method = this.floorTile[xRand][yRand].getClass().getMethod(setMethod, boolean.class);
		        method.invoke(this.floorTile[xRand][yRand], true);
				this.floorTile[xRand][yRand].setTileContainsObject(true);
				break;
			}
			xRand = (int)(Math.random() * this.roomXLength);
			yRand = (int)(Math.random() * this.roomYLength);
		}while(this.floorTile[xRand][yRand].doesTileContainObject());
	}

	private void randomizeRoomContents() {
		
		if(!this.roomContainsEntrance && !this.roomContainsExit) {
			int randTemp = (int)(Math.random() * 10);
			if(randTemp == 0) {
				this.roomContainsHPotion = true;
				this.roomContainsObject = true;
			}
			randTemp = (int)(Math.random() * 20);
			if(randTemp == 0) {
				this.roomContainsVPotion = true;
				this.roomContainsObject = true;
			}
			randTemp = (int)(Math.random() * 10);
			if(randTemp == 0) {
				this.roomContainsPits = true;
				this.roomContainsObject = true;
			}
			randTemp = (int)(Math.random() * 10);
			if(randTemp == 0) {
				this.roomContainsMonster = true;
				this.roomContainsObject = true;
			}
		}
	}
	public boolean checkBattle(Hero mainHero)throws Exception {
		
		if(mainHero == null)throw new Exception("Hero object passed into checkBattle is null");
		
		if(roomContainsMonster == true)
		{
			Monster monster = Monster.monsterFactory();
			return mainHero.battle(monster);
		}
		return true;
	}
	
	public boolean doesRoomContainEntrance() {
		
		return this.roomContainsEntrance;
	}
	
	public boolean doesRoomContainExit() {
		
		return this.roomContainsExit;
	}
	
	public boolean doesRoomContainPillarA() {
		
		return this.roomContainsPillarA;
	}
	
	public boolean doesRoomContainPillarE() {
		
		return this.roomContainsPillarE;
	}
	public boolean doesRoomContainPillarI() {
		
		return this.roomContainsPillarI;
	}
	
	public boolean doesRoomContainPillarP() {
		
		return this.roomContainsPillarP;
	}
	
	public boolean doesRoomContainObject() {
		return this.roomContainsObject;
	}

	public boolean doesRoomContainsKeyObject() {
		return this.roomContainsKeyObject;
	}
	
	public int getRoomXLength() {
		return this.roomXLength;
	}
	
	public int getRoomYLength() {
		
		
		return this.roomYLength;
	}
	
	public Tile[][] getFloorTiles(){
		
		return this.floorTile;
	}
	
	public void setRoomXLength(int roomXLength) {
		
		this.roomXLength = roomXLength;
	}
	
	public void setRoomYLength(int roomYLength) {
		
		this.roomYLength = roomYLength;
	}
	
	public void setRoomContainsEntrance(boolean roomContainsEntrance) {
		
		this.roomContainsEntrance = roomContainsEntrance;
	}
	
	public void setRoomContainsExit(boolean roomContainsExit) {
		
		this.roomContainsExit = roomContainsExit;
	}
	
	public void setRoomContainsPillarA(boolean roomContainsPillarA) {
		
		this.roomContainsPillarA = roomContainsPillarA;
	}
	
	public void setRoomContainsPillarE(boolean roomContainsPillarE) {
		
		this.roomContainsPillarE = roomContainsPillarE;
	}
	
	public void setRoomContainsPillarI(boolean roomContainsPillarI) {
		
		this.roomContainsPillarI = roomContainsPillarI;
	}
	
	public void setRoomContainsPillarP(boolean roomContainsPillarP) {
		
		this.roomContainsPillarP = roomContainsPillarP;
	}

	public void setRoomContainsObject(boolean roomContainsObject) {
		this.roomContainsObject = roomContainsObject;
	}

	public void setRoomContainsKeyObject(boolean roomContainsKeyObject) {
		this.roomContainsKeyObject = roomContainsKeyObject;
	}
}
