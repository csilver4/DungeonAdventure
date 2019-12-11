package dungeon;
public class BasicAttack implements Attack{

    @Override
    public void attack(DungeonCharacter player, DungeonCharacter opponent) throws Exception{
    	
    	if(player == null)throw new Exception("DungoneCharacter player object passed into attack is null");
		if(opponent == null)throw new Exception("DungeonCharacter opponent object passed into attack is null");
		
		boolean canAttack;
		int damage;
	    canAttack = Math.random() <= player.chanceToHit;
		if (canAttack){
			damage = (int)(Math.random() * (player.damageMax - player.damageMin + 1))+ player.damageMin ;
			opponent.subtractHitPoints(damage);
			System.out.println();
		}else{
			System.out.println(player.getName() + "'s attack on " + opponent.getName() +" failed!");
			System.out.println();
		}

	}
}