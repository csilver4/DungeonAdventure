package dungeon;

public class KingArthur extends Hero{
	
    public KingArthur(){

		super("King Aurther", 125, 4, .8, 35, 60, .2, AttackFactory.getAttack("Basic Attack"), AttackFactory.getAttack("Crushing Blow"));
    }

	public void attack(DungeonCharacter opponent){
		
		System.out.println(name + " swings a mighty sword at " + opponent.getName() + ":");
		this.attack.attack(this, opponent);//this wont be super.attack
	}
}
