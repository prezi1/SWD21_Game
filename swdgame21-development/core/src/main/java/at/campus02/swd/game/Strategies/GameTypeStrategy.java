package at.campus02.swd.game.Strategies;

import at.campus02.swd.game.gameobjects.CreatureManager;
import at.campus02.swd.game.gameobjects.CreatureGameObject;

public class GameTypeStrategy implements ManagmentStrategy {

    private CreatureGameObject player;
    private int maxGameObjects;
    private boolean gameover;

    public GameTypeStrategy(CreatureGameObject player, int maxGameObjects) {
        this.player = player;
        this.maxGameObjects = maxGameObjects;
        this.gameover = false;
    }

    @Override
    public void act(CreatureManager creatureManager, float delta) {

        if (!this.gameover) {
            //Get enemy objects and move them
            for (CreatureGameObject creatureGameObject : creatureManager.getGameObjects()) {

                if (creatureGameObject.getHealth() <= 0) {
                    creatureManager.removeEnemy(creatureGameObject);
                    continue;

                }
                //GAME OVER
                if (creatureGameObject.getX() < -50) {
                    creatureManager.removeAllEnemies();
                    this.gameover = true;
                    break;
                }


                if ((Math.floor(Math.random()*500))==1)
                {
                    creatureGameObject.getWeapon().execute(creatureManager, creatureGameObject);
                }

                creatureGameObject.act(delta);
            }

            //enemyManager.removeEnemy(enemyManager.getEnemiesinRange(player, 75));

            //Add Enemys
            if (creatureManager.getGameObjects().size < maxGameObjects) {
                int add = maxGameObjects - creatureManager.getGameObjects().size;
                creatureManager.createEnemies(800, 800, 0, 380, add);
            }
        }

    }

    @Override
    public void init(CreatureManager creatureManager) {
        creatureManager.createEnemies(600, 800, 0, 380, maxGameObjects);

    }

    @Override
    public boolean GameOver() {
        return this.gameover;
    }
}

