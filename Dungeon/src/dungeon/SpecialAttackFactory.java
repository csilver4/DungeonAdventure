package dungeon;
import java.util.HashMap;
import java.util.Map;
public class SpecialAttackFactory{
    
    private static SpecialAttack attack;
    private static HashMap<String, SpecialAttack> specialAttacks = new HashMap<String, SpecialAttack>();
    
    public static SpecialAttack getAttack(String name){
        if(specialAttacks.containsKey(name)){
            attack = specialAttacks.get(name);
        }
        else{
            if(name.equals("Crushing Blow")){
                attack = specialAttacks.put(name, new CrushingBlow());
            }
            else if(name.equals("Self Heal")){
                attack = specialAttacks.put(name, new SelfHeal());
            }
            else if(name.equals("Sneak Attack")){
                attack = specialAttacks.put(name, new SneakAttack());
            }
            else if(name.equals("Storm the Castle")){
                attack = specialAttacks.put(name, new StormTheCastle());
            }
            else if(name.equals("Speed Velocity")){
                attack = specialAttacks.put(name, new SpeedVelocity());
            }
        }
        return attack;
    }
    public int getTotalAttacks(){
        return specialAttacks.size();
    }
}