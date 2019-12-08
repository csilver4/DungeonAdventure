package dugeon;
public class MonsterFactory extends Monster{
    public static Monster monsterFactory(int res) {
		if(res == 0)
			return new Monster("The Black Knight", 200, 2, .6, .1, 30, 50, SpecialAttackFactory.getAttack("Basic Attack"), 30, 50);
		if(res == 1)
			return new Monster("Tom The Echanter", 70, 5, .8, .4, 15, 30, SpecialAttackFactory.getAttack("Basic Attack"), 20, 40);
		if(res == 2)
			return new Monster("The French Taunters", 100, 3, .8, .3, 30, 50, SpecialAttackFactory.getAttack("Basic Attack"), 30, 50);
		if(res == 3)
			return new Monster("The Knights Who Say Ni", 200, 10, .8, .3, 30, 50, SpecialAttackFactory.getAttack("Basic Attack"), 30, 50);
		if(res == 4)
			return new Monster("White Rabbit", 100, 3, .8, .3, 30, 50, SpecialAttackFactory.getAttack("Basic Attack"), 30, 50);
		else
			return new Monster("The Black Knight", 200, 2, .6, .1, 30, 50,SpecialAttackFactory.getAttack("Basic Attack"), 30, 50);
	}
}