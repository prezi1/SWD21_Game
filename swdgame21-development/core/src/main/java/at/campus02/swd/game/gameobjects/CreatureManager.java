package at.campus02.swd.game.gameobjects;


import at.campus02.swd.game.Outputter.PositionOutput;
import at.campus02.swd.game.Strategies.GameTypeStrategy;
import at.campus02.swd.game.Weapon.Gun;
import at.campus02.swd.game.Weapon.Knife;
import at.campus02.swd.game.observer.Observable;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class CreatureManager {

    private Array<CreatureGameObject> creatureGameObjects;
    private final AbstractGameObjectFactory abstractGameObjectFactory;
    private int maxGameObjects;
    private Observable observable;
    private float speedfactor;
    private GameTypeStrategy gameTypeStrategy;
    private ProjectileManager projectileManager;

    public ProjectileManager getProjectileManager() {
        return projectileManager;
    }

    public CreatureManager(AbstractGameObjectFactory abstractGameObjectFactory, GameTypeStrategy gameTypeStrategy, Observable observable, ProjectileManager projectileManager) {
        this.abstractGameObjectFactory = abstractGameObjectFactory;
        this.creatureGameObjects = new Array<>();
        this.maxGameObjects = maxGameObjects;
        this.speedfactor = 1;
        this.observable = observable;
        this.gameTypeStrategy = gameTypeStrategy;
        this.projectileManager = projectileManager;
        gameTypeStrategy.init(this);
    }

    public void createEnemies(float x1, float x2, float y1, float y2, int count) {
        for (int i = 0; i < count; i++) {
            CreatureGameObject creatureGameObject = abstractGameObjectFactory.createCreatureGameObject(GameObjectType.ENEMY,new Gun(this.projectileManager,GameObjectDirection.LEFT));
            creatureGameObject.increaseSpeed(this.speedfactor);
            creatureGameObject.setPosition(MathUtils.random(x1, x2), MathUtils.random(y1, y2));
            creatureGameObject.printPosition(new PositionOutput());
            creatureGameObjects.add(creatureGameObject);
        }
    }

    public void addPlayer(CreatureGameObject player){

    }

    public void removeAllEnemies() {
        creatureGameObjects.clear();
    }

    public void reset(CreatureGameObject enemy){
        enemy.setPosition(0,enemy.getY());
    }

    public void removeEnemy(CreatureGameObject enemy) {
        creatureGameObjects.removeValue(enemy, true);
        this.observable.notifyObservers();
    }

    public void removeEnemies(Array<CreatureGameObject> enemies) {
        creatureGameObjects.removeAll(enemies,true);
        for (int i=0; i < enemies.size; i++){
            this.observable.notifyObservers();
        }
    }

    public Array<CreatureGameObject> getGameObjects() {
        return creatureGameObjects;
    }

    public Array<CreatureGameObject> getCreaturesinRange(GameObject gameObject, float range){
        Array<CreatureGameObject> creaturesInRange = new Array<>();
        for (CreatureGameObject creatureGameObject : this.creatureGameObjects) {
            if ((creatureGameObject.getX() <= (gameObject.getX() + range) && creatureGameObject.getX() > 0 && creatureGameObject.getY() <= (gameObject.getY() + range)
                    && creatureGameObject.getY() >= (gameObject.getY() - range))) {
                creaturesInRange.add(creatureGameObject);
            }
        }
        return creaturesInRange;
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
        projectileManager.act(delta,this);
    }

    public void MngStr() {

    }

    public void increaseSpeed() {
        this.speedfactor *= 1.3;
    }


}
