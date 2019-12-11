package dungeon;

public class HeroFactory{
	
   public static Hero factory(String input) throws Exception {
	   
	   if(input == null || input.equals(""))throw new Exception("String object passed into factory is null or empty");
	   
		Hero hero = null;	
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
}
