package dungeon;
<<<<<<< HEAD
public class SpeedVelocity implements Attack{
=======

public class SpeedVelocity implements Attack{
	
>>>>>>> 10af70a99741577e7056207ecabcbb1fa1e4bbc8
    @Override
	public void attack(DungeonCharacter player, DungeonCharacter opponent) {
    	
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
