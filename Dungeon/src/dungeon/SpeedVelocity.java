package dungeon;

public class SpeedVelocity implements Attack{
	
    @Override
	public void attack(DungeonCharacter player, DungeonCharacter opponent) throws Exception {
    	
    	if(player == null)throw new Exception("DungoneCharacter player object passed into attack is null");
		if(opponent == null)throw new Exception("DungeonCharacter opponent object passed into attack is null");
    	
		if (player.name.equals("African")){
			player.damageMax = 11; //meters per second
			player.attack(opponent);
		}else if (player.name.equals("European")){
			player.damageMax = 9; // meters per second
            player.attack(opponent);
		}else
            System.out.println("Oh no!");
	}
}
