package at.campus02.swd.game.Weapon;

import at.campus02.swd.game.gameobjects.EnemyManager;
import at.campus02.swd.game.gameobjects.GameObject;

public class Knife implements Weapon{

    private final float damage = 35;
    private final float range = 50;

    @Override
    public void execute(EnemyManager enemyManager, GameObject player) {

        for (GameObject gameObject : enemyManager.getEnemiesinRange(player,range)){
            gameObject.damage(damage);
        }

    }
}
