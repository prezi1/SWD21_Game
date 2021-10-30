package at.campus02.swd.game.Weapon;

import at.campus02.swd.game.gameobjects.EnemyManager;
import at.campus02.swd.game.gameobjects.GameObject;

public class Gun implements Weapon{

    private final float damage = 100;
    private final float range = 250;

    @Override
    public void execute(EnemyManager enemyManager, GameObject player) {

        for (GameObject gameObject : enemyManager.getEnemiesinRange(player,range)){
            gameObject.damage(damage);
        }

    }
}
