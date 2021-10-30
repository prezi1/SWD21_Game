package at.campus02.swd.game.Strategies;

import at.campus02.swd.game.gameobjects.EnemyManager;
import at.campus02.swd.game.gameobjects.GameObject;

public class GameTypeStrategy implements ManagmentStrategy {

    private GameObject player;
    private int maxGameObjects;
    private boolean gameover;

    public GameTypeStrategy(GameObject player, int maxGameObjects) {
        this.player = player;
        this.maxGameObjects = maxGameObjects;
        this.gameover = false;
    }

    @Override
    public void act(EnemyManager enemyManager, float delta) {

        if (!this.gameover) {
            //Get enemy objects and move them
            for (GameObject gameObject : enemyManager.getGameObjects()) {

                if (gameObject.getHealth() <= 0) {
                    enemyManager.removeEnemy(gameObject);
                    continue;

                }
                //GAME OVER
                if (gameObject.getX() < -50) {
                    enemyManager.removeAllEnemies();
                    this.gameover = true;
                    break;
                }

                gameObject.act(delta);
            }

            //enemyManager.removeEnemy(enemyManager.getEnemiesinRange(player, 75));

            //Add Enemys
            if (enemyManager.getGameObjects().size < maxGameObjects) {
                int add = maxGameObjects - enemyManager.getGameObjects().size;
                enemyManager.createEnemies(800, 800, 0, 380, add);
            }
        }

    }

    @Override
    public void init(EnemyManager enemyManager) {
        enemyManager.createEnemies(600, 800, 0, 380, maxGameObjects);

    }

    @Override
    public boolean GameOver() {
        return this.gameover;
    }
}

