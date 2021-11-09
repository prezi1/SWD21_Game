package at.campus02.swd.game.Strategies;

import at.campus02.swd.game.gameobjects.CreatureManager;
import at.campus02.swd.game.gameobjects.CreatureGameObject;
import at.campus02.swd.game.gameobjects.GameObjectType;
import at.campus02.swd.game.gameobjects.ProjectileGameObject;
import com.badlogic.gdx.utils.Array;


public class GameTypeStrategy implements ManagmentStrategy {

    private CreatureGameObject player;
    private int maxGameObjects;
    private boolean gameover;
    private Array<CreatureGameObject> removeCreatures;

    public GameTypeStrategy(CreatureGameObject player, int maxGameObjects) {
        this.player = player;
        this.maxGameObjects = maxGameObjects;
        this.gameover = false;
        this.removeCreatures = new Array<>();
    }

    @Override
    public void act(CreatureManager creatureManager, float delta) {

        if (!this.gameover) {
            boolean player_available = false;

            //Get enemy objects and move them
            for (CreatureGameObject creatureGameObject : creatureManager.getGameObjects()) {
                if (creatureGameObject.getHealth() <= 0) {
                    removeCreatures.add(creatureGameObject);
                    continue;
                }

                //GAME OVER when Enemy escapes
                if (creatureGameObject.getX() < -50) {
                    removeCreatures.add(creatureGameObject);
                    creatureManager.removeAllEnemies();
                    this.gameover = true;
                    break;
                }

                //Check if Player exists
                if (creatureGameObject.getGameObjectType().equals(GameObjectType.PLAYER) && !player_available) {
                    player_available = true;
                }

                //Shots from enemies
                if ((Math.floor(Math.random() * 500)) == 1 && creatureGameObject.getGameObjectType().equals(GameObjectType.ENEMY)) {
                    creatureGameObject.getWeapon().execute(creatureManager, creatureGameObject);
                }
                creatureGameObject.act(delta);

            }

            //Remove Creature Objects
            creatureManager.removeCreatureObjects(removeCreatures);
            removeCreatures.clear();

            //GAME OVER when Player has been shot/removed
            if (!player_available) {
                creatureManager.removeAllEnemies();
                this.gameover = true;
            }

            //Create Creature Objects
            if (creatureManager.getGameObjects().size < maxGameObjects) {
                int add = maxGameObjects - creatureManager.getGameObjects().size;
                creatureManager.createEnemies(800, 800, 0, 380, add);
            }
        }

    }

    @Override
    public void init(CreatureManager creatureManager) {
        creatureManager.createEnemies(600, 800, 0, 380, maxGameObjects);
        creatureManager.addPlayer(player);
    }

    @Override
    public boolean GameOver() {
        return this.gameover;
    }
}

