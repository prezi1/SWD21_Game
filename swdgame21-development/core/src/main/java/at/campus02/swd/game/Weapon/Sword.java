package at.campus02.swd.game.Weapon;

import at.campus02.swd.game.gameobjects.EnemyManager;
import at.campus02.swd.game.gameobjects.GameObject;

public class Sword implements Weapon{

    private final float damage = 50;
    private final float range = 75;

    @Override
    public void execute(EnemyManager enemyManager, GameObject player) {

        for (GameObject gameObject : enemyManager.getEnemiesinRange(player,range)){
            gameObject.damage(damage);
        }

    }
}
