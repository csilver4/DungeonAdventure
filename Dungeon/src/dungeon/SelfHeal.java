package dungeon;

public class SelfHeal implements Attack{
	
	@Override
	public void attack(DungeonCharacter player, DungeonCharacter opponent) throws Exception {
		
		if(player == null)throw new Exception("DungoneCharacter player object passed into attack is null");
		if(opponent == null)throw new Exception("DungeonCharacter opponent object passed into attack is null");
		
	    int hPoints;
		hPoints = (int)(Math.random() * (((Zoot)player).MAX_ADD - ((Zoot)player).MIN_ADD + 1)) + ((Zoot)player).MIN_ADD;
		player.addHitPoints(hPoints);
		System.out.println(player.name + " added [" + hPoints + "] points.\n"+ "Total hit points remaining are: "+ player.hitPoints);
		System.out.println();
    }
}
