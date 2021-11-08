package at.campus02.swd.game.gameobjects;


import at.campus02.swd.game.Outputter.PositionOutput;
import at.campus02.swd.game.Strategies.GameTypeStrategy;
import at.campus02.swd.game.Weapon.Gun;
import at.campus02.swd.game.observer.GameObjectObserver;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class CreatureManager {

    private Array<CreatureGameObject> creatureGameObjects;
    private final AbstractGameObjectFactory abstractGameObjectFactory;
    private Array<GameObjectObserver> gameObjectObservers;
    private float speedfactor;
    private GameTypeStrategy gameTypeStrategy;
    private ProjectileManager projectileManager;

    public ProjectileManager getProjectileManager() {
        return projectileManager;
    }

    public CreatureManager(AbstractGameObjectFactory abstractGameObjectFactory, GameTypeStrategy gameTypeStrategy, ProjectileManager projectileManager) {
        this.abstractGameObjectFactory = abstractGameObjectFactory;
        this.gameObjectObservers = new Array<>();
        this.creatureGameObjects = new Array<>();
        this.speedfactor = 1;
        this.gameTypeStrategy = gameTypeStrategy;
        this.projectileManager = projectileManager;
        gameTypeStrategy.init(this);
    }

    public void createEnemies(float x1, float x2, float y1, float y2, int count) {
        for (int i = 0; i < count; i++) {
            CreatureGameObject creatureGameObject = abstractGameObjectFactory.createCreatureGameObject(GameObjectType.ENEMY, new Gun(this.projectileManager, GameObjectDirection.LEFT,20));
            creatureGameObject.increaseSpeed(this.speedfactor);
            creatureGameObject.setPosition(MathUtils.random(x1, x2), MathUtils.random(y1, y2));
            creatureGameObject.printPosition(new PositionOutput());
            creatureGameObjects.add(creatureGameObject);
        }
    }

    public void addPlayer(CreatureGameObject player) {
        creatureGameObjects.add(player);
    }

    public void removeAllEnemies() {
        creatureGameObjects.clear();
    }

    public void reset(CreatureGameObject enemy) {
        enemy.setPosition(0, enemy.getY());
    }

    public void removeEnemy(CreatureGameObject enemy) {
        creatureGameObjects.removeValue(enemy, true);
        if (enemy.getGameObjectType().equals(GameObjectType.ENEMY)) {
            notifyObservers(enemy);
        }
    }

    public void removeEnemies(Array<CreatureGameObject> creatureGameObjects) {
        for (CreatureGameObject creatureGameObject : creatureGameObjects){
            this.removeEnemy(creatureGameObject);
        }
    }

    public Array<CreatureGameObject> getGameObjects() {
        return creatureGameObjects;
    }

    public Array<CreatureGameObject> getCreaturesinRange(GameObject gameObject, float range, GameObjectType type) {
        Array<CreatureGameObject> creaturesInRange = new Array<>();


        for (CreatureGameObject creatureGameObject : this.creatureGameObjects) {
            //##enemy Range
            if (type.equals(GameObjectType.PLAYER) && (creatureGameObject.getGameObjectType().equals(GameObjectType.ENEMY))) {
                if ((creatureGameObject.getX() <= (gameObject.getX() + range) && creatureGameObject.getX() > 0 && creatureGameObject.getY() <= (gameObject.getY() + 20)
                        && creatureGameObject.getY() >= (gameObject.getY() -100))) {
                    creaturesInRange.add(creatureGameObject);
                }
            }
            //##player range
            if (type.equals(GameObjectType.ENEMY) && (creatureGameObject.getGameObjectType().equals(GameObjectType.PLAYER))) {
                if ((creatureGameObject.getX() >= (gameObject.getX() + range) && creatureGameObject.getY() <= (gameObject.getY() + 20)
                        && creatureGameObject.getY() >= (gameObject.getY() - 100))) {
                    creaturesInRange.add(creatureGameObject);
                }
            }
        }
        return creaturesInRange;
    }


    public void addObserver(GameObjectObserver gameObjectObserver) {
        gameObjectObservers.add(gameObjectObserver);
    }

    public void removeObserver(GameObjectObserver gameObjectObserver) {
        gameObjectObservers.removeValue(gameObjectObserver, true);
    }

    private void notifyObservers(GameObject gameObject) {
        for (GameObjectObserver gameObjectObserver : gameObjectObservers) {
            gameObjectObserver.update(gameObject);
        }
    }

    public void act(float delta) {
        gameTypeStrategy.act(this, delta);
        projectileManager.act(delta, this);
    }


    public void increaseSpeed() {
        this.speedfactor *= 1.3;
    }


}
