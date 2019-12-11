package dungeon;
import java.util.HashMap;
public class AttackFactory{
    
    private static Attack attack;
    private static HashMap<String, Attack> attacks = new HashMap<String, Attack>();
    
    public static Attack getAttack(String name) throws Exception{
    	
    	if(name == null || name.contentEquals(""))throw new Exception("String object passed into getAttack is null");
    	
        if(attacks.containsKey(name)){
            attack = attacks.get(name);
        }
        else{
            if(name.equals("Crushing Blow")){
                attack = attacks.put(name, new CrushingBlow());
            }
            else if(name.equals("Self Heal")){
                attack = attacks.put(name, new SelfHeal());
            }
            else if(name.equals("Sneak Attack")){
                attack = attacks.put(name, new SneakAttack());
            }
            else if(name.equals("Storm the Castle")){
                attack = attacks.put(name, new StormTheCastle());
            }
            else if(name.equals("Speed Velocity")){
                attack = attacks.put(name, new SpeedVelocity());
            }
            else if(name.equals("Basic Attack")){
                attack = attacks.put(name, new BasicAttack());
            }
        }
        return attack;
    }
    
    public int getTotalAttacks(){
    	
        return attacks.size();
    }
}