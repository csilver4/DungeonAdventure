package dungeon;

public class CrushingBlow implements Attack{
	
	@Override
	public void attack(DungeonCharacter player, DungeonCharacter opponent) throws Exception {
		
		if(player == null)throw new Exception("DungoneCharacter player object passed into attack is null");
		if(opponent == null)throw new Exception("DungeonCharacter opponent object passed into attack is null");
		
		if (Math.random() <= .4){
			int blowPoints = (int)(Math.random() * 76) + 100;
			System.out.println(player.name + " lands a CRUSHING BLOW for " + blowPoints+ " damage!");
			opponent.subtractHitPoints(blowPoints);
		}else{
			System.out.println(player.name + " failed to land a crushing blow");
			System.out.println();
		}
	}
}
