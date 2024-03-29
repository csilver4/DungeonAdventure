package dungeon;

public class Tile {

	private boolean tileContainsHPotion;
	private boolean tileContainsVPotion;
	private boolean tileContainsPit;
	private boolean tileContainsEntrance;
	private boolean tileContainsExit;
	private boolean tileContainsPillarE;
	private boolean tileContainsPillarA;
	private boolean tileContainsPillarI;
	private boolean tileContainsPillarP;
	private boolean tileContainsObject;
	
	public Tile() {
		
		this.tileContainsHPotion = false;
		this.tileContainsVPotion = false;
		this.tileContainsPit = false;
		this.tileContainsEntrance = false;
		this.tileContainsExit = false;
		this.tileContainsPillarE = false;
		this.tileContainsPillarA = false;
		this.tileContainsPillarI = false;
		this.tileContainsPillarP = false;
		this.tileContainsObject = false;
	}
	
	public String displayTile() {
		
		if(this.tileContainsObject) {
			if(this.tileContainsHPotion)
				return " H";
			if(this.tileContainsVPotion)
				return " V";
			if(this.tileContainsPit)
				return " O";
			if(this.tileContainsPillarA)
				return " A";
			if(this.tileContainsPillarE)
				return " E";
			if(this.tileContainsPillarI)
				return " I";
			if(this.tileContainsPillarP)
				return " P";
			if(this.tileContainsEntrance)
				return " B";
			if(this.tileContainsExit)
				return " Z";
		}
		return " #";
	}
	
	public void checkContains(Hero mainHero, Dungeon dungeon) throws Exception {
		
		if(mainHero == null)throw new Exception("Hero object passed into checkContains is null");
		if(dungeon == null)throw new Exception("Dungeon object passed into checkContains is null");
		
		if(this.tileContainsObject) {
			if(this.tileContainsHPotion) {
				pickUpHPotion(mainHero);
				return;
			}
			if(this.tileContainsPit) {
				fellIntoPit(mainHero);
				return;
			}
			if(this.tileContainsVPotion) {
				dungeon.displayRoom("vision");
				return;
			}
			if(this.tileContainsEntrance) {
				System.out.println("you cannot exit you must finish your quest");
				return;
			}
			if(this.tileContainsExit) {
				useExit(mainHero);
				return;
			}
			if(this.tileContainsPillarA) {
				pickUpPillar(mainHero, " picked up the pillar of Abstraction");
				return;
			}
			if(this.tileContainsPillarE) {
				pickUpPillar(mainHero, " picked up the pillar of Encapsulation");
				return;
			}
			if(this.tileContainsPillarI) {
				pickUpPillar(mainHero, " picked up the pillar of Inheritance");
				return;
			}
			if(this.tileContainsPillarP) {
				pickUpPillar(mainHero, " picked up the pillar of Polymorphism");
				return;
			}
		}
	}

	private void pickUpHPotion(Hero mainHero)throws Exception {
	
		if(mainHero == null)throw new Exception("Hero object passed into pickUpHPotion is null");
		
		int healAmount = (int) (Math.random()*((15-5)+1))+5;
		mainHero.addHitPoints(healAmount);
		System.out.println(mainHero.name + " was healed " + healAmount + " health point");
		setHPotion(false);
		return;
	}

	private void fellIntoPit(Hero mainHero)throws Exception {
	
		if(mainHero == null)throw new Exception("Hero object passed into fellIntoPit is null");
		
		System.out.println(mainHero.name + " fell down a pit and hurt themself!");
		int fallDamage = (int) (Math.random()*((15-5)+1))+5;
		mainHero.subtractHitPoints(fallDamage, true);
		if(!mainHero.isAlive()) {
			System.out.println(mainHero.name + " died from the fall! Game Over");
			return;
		}
	}

	private void useExit(Hero mainHero)throws Exception {
	
		if(mainHero == null)throw new Exception("Hero object passed into useExit is null");
		
		if(mainHero.doesHeroHavePillarE() || mainHero.doesHeroHavePillarA() || mainHero.doesHeroHavePillarI() || mainHero.doesHeroHavePillarP()) {
			System.out.println(mainHero.getName()+" found all the pillars of OO and made it to the exit! Victory!");
			mainHero.subtractHitPoints(mainHero.getHitPoints());
			return;
		}
		System.out.println("you are missing a pillar of OO! \nyou must find them all before you leave the dungeon");
	}

	private void pickUpPillar(Hero mainHero, String string)throws Exception, Exception {
		
		if(mainHero == null)throw new Exception("Hero object passed into pickUpPillar is null");
		if(string == null || (!string.contains("Abstraction") && !string.contains("Encapsulation") && !string.contains("Inheritance") && !string.contains("Polymorphism")))throw new Exception("String object passed into pickUpPillar is null or does not contain key word in it");
	
		if(string.contains("Abstraction")) {
			mainHero.setHeroHavePillarA(true);
			this.tileContainsPillarA = false;
		}else if(string.contains("Encapsulation")) {
			mainHero.setHeroHavePillarE(true);
			this.tileContainsPillarE = false;
		}else if(string.contains("Inheritance")) {
			mainHero.setHeroHavePillarI(true);
			this.tileContainsPillarI = false;
		}else if(string.contains("Polymorphism")) {
			mainHero.setHeroHavePillarP(true);
			this.tileContainsPillarP = false;
		}
		System.out.println(mainHero.name + string);
}

	public boolean doesTileContainPillarP() {
		
		return this.tileContainsPillarP;
	}

	public boolean doesTileContainPillarI() {
		
		return this.tileContainsPillarI;
	}

	public boolean doesTileContainPillarA() {
		
		return this.tileContainsPillarA;
	}

	public boolean doesTileContainPillarE() {
		
		return this.tileContainsPillarE;
	}

	public boolean doesTileContainExit() {
		
		return this.tileContainsExit;
	}

	public boolean doesTileContainEntrance() {
		
		return this.tileContainsEntrance;
	}

	public boolean doesTileContainVPotion() {
		
		return this.tileContainsVPotion;
	}

	public boolean doesTileContainPit() {
		
		return this.tileContainsPit;
	}

	public boolean doesTileContainHPotion() {
		
		return this.tileContainsHPotion;
	}
	
	public boolean doesTileContainObject() {
		
		return this.tileContainsObject;
	}

	public void setHPotion(boolean containsHPotion) {
		
		this.tileContainsHPotion = containsHPotion;
	}

	public void setEntrance(boolean entrance) {
		
		this.tileContainsEntrance = entrance;
	}

	public void setExit(boolean exit) {
		
		this.tileContainsExit = exit;
	}

	public void setPit(boolean pit) {
		
		this.tileContainsPit = pit;
	}

	public void setVPotion(boolean vpotion) {
		
		this.tileContainsVPotion = vpotion;
	}

	public void setPillarE(boolean pillarE) {
		
		this.tileContainsPillarE = pillarE;
	}

	public void setPillarA(boolean pillarA) {
		
		this.tileContainsPillarA = pillarA;
	}

	public void setPillarI(boolean pillarI) {
		
		this.tileContainsPillarI = pillarI;
	}

	public void setPillarP(boolean pillarP) {
		
		this.tileContainsPillarP = pillarP;
	}

	public void setTileContainsObject(boolean tileContainsObject) {
		
		this.tileContainsObject = tileContainsObject;
	}
}
