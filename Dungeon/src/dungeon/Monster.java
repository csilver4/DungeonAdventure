package dungeon;


public class Monster extends DungeonCharacter{
	
	protected double chanceToHeal;
	protected int minHeal, maxHeal;

	public Monster(String name, int hitPoints, int attackSpeed, double chanceToHit, double chanceToHeal,
					 int damageMin, int damageMax, int minHeal, int maxHeal){
		
		super(name, hitPoints, attackSpeed, chanceToHit, damageMin, damageMax);
		this.chanceToHeal = chanceToHeal;
		this.maxHeal = maxHeal;
		this.minHeal = minHeal;
	}

	public void heal(){
		
		boolean canHeal;
		int healPoints;
		canHeal = (Math.random() <= chanceToHeal) && (hitPoints > 0);
		if (canHeal){
			healPoints = (int)(Math.random() * (maxHeal - minHeal + 1)) + minHeal;
			addHitPoints(healPoints);
			System.out.println(name + " healed itself for " + healPoints + " points.\n"
						+ "Total hit points remaining are: " + hitPoints);
			System.out.println();
		}
	}

	public void subtractHitPoints(int hitPoints){
		
		super.subtractHitPoints(hitPoints);
		heal();
	}
	
	public void attack(DungeonCharacter opponent)
	{
		System.out.println(name + " attacks " +
							opponent.getName() + ":");
		super.attack(opponent);
	}

	public static Monster monsterFactory() {
	
		int res = (int) (Math.random()*5);
		if(res == 0)
			return new Monster("The Black Knight", 200, 2, .6, .1, 30, 50, 30, 50);
		if(res == 1)
			return new Monster("Tom The Echanter", 70, 5, .8, .4, 15, 30, 20, 40);
		if(res == 2)
			return new Monster("The French Taunters", 100, 3, .8, .3, 30, 50, 30, 50);
		if(res == 3)
			return new Monster("The French Taunters", 100, 3, .8, .3, 30, 50, 30, 50);
		if(res == 4)
			return new Monster("The French Taunters", 100, 3, .8, .3, 30, 50, 30, 50);
		else
			return new Monster("The Black Knight", 200, 2, .6, .1, 30, 50, 30, 50);
	}
}

