package dungeon;

public class KingArthur extends Hero{
	
    public KingArthur() throws Exception{

    	super("King Aurther", 125, 4, .8, 35, 60, .2, AttackFactory.getAttack("Basic Attack"), AttackFactory.getAttack("Crushing Blow"));
    }

	public void attack(DungeonCharacter opponent) throws Exception{
		
		if(opponent == null)throw new Exception("dungeonCharacter object passed into attack is null");
		
		System.out.println(name + " swings a mighty sword at " + opponent.getName() + ":");
		super.attack(opponent);
	}
}
