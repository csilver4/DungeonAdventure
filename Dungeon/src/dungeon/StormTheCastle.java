package dungeon;

public class StormTheCastle implements Attack{
  
@Override
	public void attack(DungeonCharacter player, DungeonCharacter opponent) throws Exception {
	
		if(player == null)throw new Exception("DungoneCharacter player object passed into attack is null");
		if(opponent == null)throw new Exception("DungeonCharacter opponent object passed into attack is null");
	
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
}
