package dungeon;

public class SirLancealot extends Hero{
    public SirLancealot(){

		super("Sir Lancealot", 100, 3, .9, 32, 60, .1,SpecialAttackFactory.getAttack("Basic Attack"), SpecialAttackFactory.getAttack("Storm the Castle"));
    }

	public void attack(DungeonCharacter opponent){
		
		System.out.println(name + " runs at and stabs " + opponent.getName() + ":");
		super.attack(opponent);
	}
}
