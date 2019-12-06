package dungeon;
public class BasicAttack implements Attack{

    @Override
    public void attack(DungeonCharacter player, DungeonCharacter opponent){
		
		boolean canAttack;
		int damage;
	    canAttack = Math.random() <= player.getChanceToHit;
		if (canAttack){
			damage = (int)(Math.random() * (play.getDamageMax - player.getDamageMin + 1))+ player.getDamageMin ;
			opponent.subtractHitPoints(damage);
			System.out.println();
		}else{
			System.out.println(player.getName() + "'s attack on " + opponent.getName() +" failed!");
			System.out.println();
		}

	}
}