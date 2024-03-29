package dungeon;

public class SneakAttack implements Attack{

	@Override
	public void attack(DungeonCharacter player, DungeonCharacter opponent) throws Exception {
		
		if(player == null)throw new Exception("DungoneCharacter player object passed into attack is null");
		if(opponent == null)throw new Exception("DungeonCharacter opponent object passed into attack is null");
		
		double surprise = Math.random();
		if (surprise <= .4){
			System.out.println("Surprise attack was successful!\n" +player.name + " gets an additional turn.");
			((Hero)player).numTurns++;
			player.attack(opponent);
		}else if (surprise >= .9){
			System.out.println("Uh oh! " + opponent.getName() + " saw you and" +" blocked your attack!");
		}else
			player.attack(opponent);
	}
}
