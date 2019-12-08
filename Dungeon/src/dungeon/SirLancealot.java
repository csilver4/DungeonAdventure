package dungeon;

public class SirLancealot extends Hero{
    public SirLancealot(){

		super("Sir Lancealot", 100, 3, .9, 32, 60, .1, new StormTheCastle());
    }

	public void attack(DungeonCharacter opponent){
		
		System.out.println(name + " runs at and stabs " + opponent.getName() + ":");
		super.attack(opponent);
	}
}
