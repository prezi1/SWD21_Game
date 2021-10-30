package at.campus02.swd.game.Strategies;

import at.campus02.swd.game.gameobjects.EnemyManager;
import at.campus02.swd.game.gameobjects.GameObject;

public interface ManagmentStrategy {


    void act(EnemyManager enemyManager, float delta);
    void init(EnemyManager enemyManager);
    boolean GameOver();

}
