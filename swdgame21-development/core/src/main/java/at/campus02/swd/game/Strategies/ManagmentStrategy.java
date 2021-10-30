package at.campus02.swd.game.Strategies;

import at.campus02.swd.game.gameobjects.EnemyManager;

public interface ManagmentStrategy {


    void act(EnemyManager enemyManager, float delta);
    void init(EnemyManager enemyManager);
    boolean GameOver();

}
