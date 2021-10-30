package at.campus02.swd.game.Weapon;

import at.campus02.swd.game.gameobjects.EnemyManager;
import at.campus02.swd.game.gameobjects.GameObject;

public interface Weapon {

    void execute(EnemyManager enemyManager, GameObject player);

}
