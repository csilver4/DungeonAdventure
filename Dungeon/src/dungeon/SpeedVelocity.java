package dungeon;
public class SpeedVelocity implements SpecialAttack{
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
