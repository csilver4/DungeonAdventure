package dungeon;

public class SirLancealot extends Hero{
    public SirLancealot(){

<<<<<<< HEAD
		super("Sir Lancealot", 100, 3, .9, 32, 60, .1, AttackFactory.getAttack("Basic Attack"), AttackFactory.getAttack("Storm the Castle"));
=======
    	super("Sir Lancealot", 100, 3, .9, 32, 60, .1, AttackFactory.getAttack("Basic Attack"), AttackFactory.getAttack("Storm the Castle"));
>>>>>>> 10af70a99741577e7056207ecabcbb1fa1e4bbc8
    }

	public void attack(DungeonCharacter opponent){
		
		System.out.println(name + " runs at and stabs " + opponent.getName() + ":");
		super.attack(opponent);
	}
}
