package dungeon;

public class StormTheCastle implements SpecialAttack{
  
@Override
	public void attack(DungeonCharacter player, DungeonCharacter opponent) {
		double arrow = Math.random();
		if (arrow <= .60){
			int blowPoints = (int)(Math.random() * 50) + 15;
			System.out.println(player.name + " stabs for " + blowPoints+ " damage!");
			opponent.subtractHitPoints(blowPoints);
		}else{
			System.out.println(player.name + " failed to storm the castle...");
			System.out.println();
		}
}
