package dungeon;
import java.util.Scanner;

public class UnladenSwallow extends Hero {
    
    public UnladenSwallow() {
        super(choice(), 25, 10, .8, 8, 20, .2, AttackFactory.getAttack("Basic Attack"), AttackFactory.getAttack("Speed Velocity"));
    }
    public static String choice(){
    
        Scanner kb = new Scanner(System.in);
        System.out.println("Choose a type of Unladen Swallow:");
        System.out.println("1. African\r\n"+"2. European\r\n");
        if(kb.nextInt() == 2){
        	kb.close();
            return "Eurepean";
        }
        else{
        	kb.close();
            return "African";
        }
    }
    
    public void attack(DungeonCharacter opponent){
		
		System.out.println(name + " runs at and stabs " + opponent.getName() + ":");
		super.attack(opponent);
	}

}