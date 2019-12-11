package dungeon;

public class SirLancealot extends Hero{
    public SirLancealot() throws Exception{

    	super("Sir Lancealot", 100, 3, .9, 32, 60, .1, AttackFactory.getAttack("Basic Attack"), AttackFactory.getAttack("Storm the Castle"));
    }

	public void attack(DungeonCharacter opponent) throws Exception{
		
		if(opponent == null)throw new Exception("DungeonCharacter opponent object passed into attack is null");
		
		System.out.println(name + " runs at and stabs " + opponent.getName() + ":");
		super.attack(opponent);
	}
}
