package dungeon;

public class BraveSirRobin extends Hero{
	
    public BraveSirRobin() throws Exception{
    	
    	super("Brave Sir Robin", 75, 6, .8, 20, 40, .5, AttackFactory.getAttack("Basic Attack"), AttackFactory.getAttack("Sneak Attack"));
    }
}

