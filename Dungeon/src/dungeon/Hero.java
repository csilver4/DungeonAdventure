package dungeon;
import java.util.Scanner;

public abstract class Hero extends DungeonCharacter{
	
	private boolean heroHavePillarE;
	private boolean heroHavePillarA;
	private boolean heroHavePillarI;
	private boolean heroHavePillarP;
	private Scanner kb = new Scanner(System.in);
	protected double chanceToBlock;
	protected int numTurns;
	private SpecialAttack spAttack;
	
	public Hero(String name, int hitPoints, int attackSpeed, double chanceToHit,
			    int damageMin, int damageMax, double chanceToBlock, SpecialAttack spAttack){
		
		super(name, hitPoints, attackSpeed, chanceToHit, damageMin, damageMax);
		this.heroHavePillarA = false;
		this.heroHavePillarE = false;
		this.heroHavePillarI = false;
		this.heroHavePillarP = false;
		this.chanceToBlock = chanceToBlock;
		this.spAttack = spAttack;
	}

	public boolean defend(){
		
		return Math.random() <= chanceToBlock;
	}
	
	public void subtractHitPoints(int hitPoints, boolean fall){
		
		if (defend() || !fall)
			System.out.println(name + " BLOCKED the attack!");
		else
			super.subtractHitPoints(hitPoints);
	}

	public void battleChoices(DungeonCharacter opponent){
		
	    numTurns = attackSpeed/opponent.getAttackSpeed();
		if (numTurns == 0)
			numTurns++;
		System.out.println("Number of turns this round is: " + numTurns);
		int choice;
		do{
		    System.out.println("1. Attack Opponent");
		    System.out.println("2. Special Attack");
		    System.out.print("Choose an option: ");
		    choice = kb.nextInt();
		    kb.nextLine();
		    switch (choice){
			    case 1: attack(opponent);
			        break;
			    case 2: spAttack.attack(this, opponent);
			        break;
			    default:
			        System.out.println("invalid choice!");
		    }
			numTurns--;
			if (numTurns > 0)
			    System.out.println("Number of turns remaining is: " + numTurns);
		} while(numTurns > 0);

	}

	public static Hero factory(String input) {
		
		if(input == null)
			return null;	
	    if(input.equals("1"))
	        return new KingArthur();
	    else if(input.equals("2"))
	         return new BraveSirRobin(); 
	    else if(input.equals("3"))
	         return new Zoot();
	    return null;
	}

	public boolean battle(Monster monster) {
		
		char pause = 'p';
		System.out.println(this.getName() + " battles " +monster.getName());
		System.out.println("---------------------------------------------");
		while (this.isAlive() && monster.isAlive() && pause != 'q'){
			this.battleChoices(monster);
			if (monster.isAlive())
			    monster.attack(this);
			System.out.print("\n-->q to quit, anything else to continue: ");
			pause = kb.next().charAt(0);
			kb.nextLine();
		}
		if (!monster.isAlive()) {
		    System.out.println(this.getName() + " was victorious!");
		    return true;
		}else if (!this.isAlive()) {
			System.out.println(this.getName() + " was defeated :-(");
			return false;
		}else {
			System.out.println("Quitters never win ;-)");
			return false;
		}
	}
	
	public void gameOver(String string) {
		
		
	}
	
	public boolean doesHeroHavePillarE() {
		
		return this.heroHavePillarE;
	}
	
	public boolean doesHeroHavePillarA() {
		
		return this.heroHavePillarA;
	}
	
	public boolean doesHeroHavePillarI() {
		
		return this.heroHavePillarI;
	}
	
	public boolean doesHeroHavePillarP() {
		
		return this.heroHavePillarP;
	}

	public void setHeroHavePillarE(boolean heroHavePillarE) {
		
		this.heroHavePillarE = heroHavePillarE;
	}
	
	public void setHeroHavePillarA(boolean heroHavePillarA) {
		
		this.heroHavePillarA = heroHavePillarA;
	}

	public void setHeroHavePillarI(boolean heroHavePillarI) {
	
		this.heroHavePillarI = heroHavePillarI;
	}	

	public void setHeroHavePillarP(boolean heroHavePillarP) {
	
		this.heroHavePillarP = heroHavePillarP;
	}
}