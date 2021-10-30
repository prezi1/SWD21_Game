package at.campus02.swd.game.gameobjects;


import at.campus02.swd.game.Outputter.PositionOutput;
import at.campus02.swd.game.Strategies.GameTypeStrategy;
import at.campus02.swd.game.observer.Observable;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class EnemyManager {

    private Array<GameObject> gameObjects;
    private final AbstractGameObjectFactory abstractGameObjectFactory;
    private int maxGameObjects;
    private Observable observable;
    private float speedfactor;
    private GameTypeStrategy gameTypeStrategy;


    public EnemyManager(AbstractGameObjectFactory abstractGameObjectFactory, GameTypeStrategy gameTypeStrategy, Observable observable) {
        this.abstractGameObjectFactory = abstractGameObjectFactory;
        this.gameObjects = new Array<>();
        this.maxGameObjects = maxGameObjects;
        this.speedfactor = 1;
        this.observable = observable;
        this.gameTypeStrategy = gameTypeStrategy;
        gameTypeStrategy.init(this);

    }

    public void createEnemies(float x1, float x2, float y1, float y2, int count) {
        for (int i = 0; i < count; i++) {
            GameObject gameObject = abstractGameObjectFactory.createGameObject(GameObjectType.ENEMY);
            gameObject.increaseSpeed(this.speedfactor);
            gameObject.setPosition(MathUtils.random(x1, x2), MathUtils.random(y1, y2));
            gameObject.printPosition(new PositionOutput());
            gameObjects.add(gameObject);
        }
    }


    public void removeAllEnemies() {
        gameObjects.clear();
    }

    public void reset(GameObject enemy){
        enemy.setPosition(0,enemy.getY());
    }

    public void removeEnemy(GameObject enemy) {
        gameObjects.removeValue(enemy, true);
        this.observable.notifyObservers();
    }

    public void removeEnemy(Array<GameObject> enemies) {
        gameObjects.removeAll(enemies,true);
        for (int i=0; i < enemies.size; i++){
            this.observable.notifyObservers();
        }
    }

    public Array<GameObject> getGameObjects() {
        return gameObjects;
    }

    public Array<GameObject> getEnemiesinRange(GameObject player, float range){
        Array<GameObject> EnemiesinRange = new Array<>();
        for (GameObject gameObject : this.gameObjects) {
            if ((gameObject.getX() <= (player.getX() + range) && gameObject.getX() > 0 && gameObject.getY() <= (player.getY() + range)
                    && gameObject.getY() >= (player.getY() - range))) {
                EnemiesinRange.add(gameObject);
            }
        }
        return EnemiesinRange;
    }


    /*
    public Array<GameObject> destroyEnemies(GameObject player, float range) {
        for (GameObject gameObject : this.gameObjects) {
            if ((gameObject.getX() <= (player.getX() + range) && gameObject.getX() > 0 && gameObject.getY() <= (player.getY() + range)
                    && gameObject.getY() >= (player.getY() - range))) {

            }
        }
    }
    *
     */

    public void act(float delta) {
        gameTypeStrategy.act(this, delta);
    }

    public void MngStr() {

    }



    public void increaseSpeed() {
        this.speedfactor *= 1.3;
    }


}
