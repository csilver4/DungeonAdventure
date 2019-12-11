package dungeon;

public class Zoot extends Hero{
	
	public final int MIN_ADD = 25;
	public final int MAX_ADD = 50;

	public Zoot() throws Exception{
		
		super("Zoot", 75, 5, .7, 25, 50, .3, AttackFactory.getAttack("Basic Attack"), AttackFactory.getAttack("Self Heal"));
	}
	public void attack(DungeonCharacter opponent) throws Exception{
		
		if(opponent == null)throw new Exception("DungeonCharacter opponent object passed into attack is null");
		
		System.out.println(name + " casts a spell of fireball at " +opponent.getName() + ":");
		super.attack(opponent);
	}
}

