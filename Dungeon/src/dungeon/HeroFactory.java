package dungeon;
<<<<<<< HEAD
public class HeroFactory{
   public static Hero factory(String input) {
		Hero hero;
=======

public class HeroFactory{
	
   public static Hero factory(String input) {
	   
		Hero hero = null;
>>>>>>> 10af70a99741577e7056207ecabcbb1fa1e4bbc8
		if(input == null || input.equals(""))
			return null;	
	    if(input.equals("1"))
	        hero = new KingArthur();
	    else if(input.equals("2"))
	        hero = new BraveSirRobin(); 
	    else if(input.equals("3"))
	        hero = new Zoot();
		else if(input.equals("4"))
			hero = new SirLancealot();
		else if(input.equals("5"))
			hero = new UnladenSwallow();
	    return hero;
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> 10af70a99741577e7056207ecabcbb1fa1e4bbc8
