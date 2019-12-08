package dungeon;

public abstract class DungeonCharacter{
	
	protected String name;
	protected int hitPoints;
	protected int attackSpeed;
	protected double chanceToHit;
	protected int damageMin, damageMax;
	private Attack theAttack;
	public DungeonCharacter(String name, int hitPoints, int attackSpeed,
				     double chanceToHit, int damageMin, int damageMax, Attack theAttack){
		
		this.name = name;
		this.hitPoints = hitPoints;
		this.attackSpeed = attackSpeed;
		this.chanceToHit = chanceToHit;
		this.damageMin = damageMin;
		this.damageMax = damageMax;
		this.attack = theAttack;

	}
	public Attack getAttack(){
		return theAttack;
	}

	public int getDamageMax(){
		return damageMax;
	}

	public int getDamageMin(){
		return damageMin;
	}

	public double getChanceToHit(){
		return chanceToHit;
	}
	
	public String getName(){
		return name;
	}

	public int getHitPoints(){
		return hitPoints;
	}
	
	public int getAttackSpeed(){
		return attackSpeed;
	}

	public void addHitPoints(int hitPoints){
		
		if (hitPoints <=0)
			System.out.println("Hitpoint amount must be positive.");
		else
			this.hitPoints += hitPoints;
	}

	public void subtractHitPoints(int hitPoints){
		
		if (hitPoints <0)
			System.out.println("Hitpoint amount must be positive.");
		else if (hitPoints >0){
			this.hitPoints -= hitPoints;
			if (this.hitPoints < 0)
				this.hitPoints = 0;
			System.out.println(getName() + " was hit for <" + hitPoints + "> points damage.");
			System.out.println(getName() + " now has " +getHitPoints() + " hit points remaining.");
			System.out.println();
		}
		if (this.hitPoints == 0)
			System.out.println(name + " has been killed :-(");
	}
	
	public boolean isAlive(){
		
	  return (hitPoints > 0);
	}

	public void attack(DungeonCharacter opponent){
		this.theAttack.attack(this, opponent);
	}
}

