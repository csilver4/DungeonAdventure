package dungeon;
public class BasicAttack implements Attack{

    @Override
    public void attack(DungeonCharacter player, DungeonCharacter opponent){
		
		boolean canAttack;
		int damage;
<<<<<<< HEAD
	    canAttack = Math.random() <= player.getChanceToHit;
		if (canAttack){
			damage = (int)(Math.random() * (play.getDamageMax - player.getDamageMin + 1))+ player.getDamageMin ;
=======
	    canAttack = Math.random() <= player.chanceToHit;
		if (canAttack){
			damage = (int)(Math.random() * (player.damageMax - player.damageMin + 1))+ player.damageMin ;
>>>>>>> 10af70a99741577e7056207ecabcbb1fa1e4bbc8
			opponent.subtractHitPoints(damage);
			System.out.println();
		}else{
			System.out.println(player.getName() + "'s attack on " + opponent.getName() +" failed!");
			System.out.println();
		}

	}
}