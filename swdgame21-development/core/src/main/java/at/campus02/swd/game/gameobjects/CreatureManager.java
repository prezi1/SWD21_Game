package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.AssetLoaderSingleton;
import at.campus02.swd.game.Outputter.PositionOutput;
import at.campus02.swd.game.Strategies.GameTypeStrategy;
import at.campus02.swd.game.Weapon.Gun;
import at.campus02.swd.game.observer.GameObjectObserver;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import java.util.function.DoubleBinaryOperator;

public class CreatureManager {

    private Array<CreatureGameObject> creatureGameObjects;
    private final AbstractGameObjectFactory abstractGameObjectFactory;
    private Array<GameObjectObserver> gameObjectObservers;
    private float speedfactor;
    private GameTypeStrategy gameTypeStrategy;


    public CreatureManager(AbstractGameObjectFactory abstractGameObjectFactory, GameTypeStrategy gameTypeStrategy) {
        this.abstractGameObjectFactory = abstractGameObjectFactory;
        this.gameObjectObservers = new Array<>();
        this.creatureGameObjects = new Array<>();
        this.speedfactor = 1;
        this.gameTypeStrategy = gameTypeStrategy;
        gameTypeStrategy.init(this);
    }


    public void createEnemies(float x1, float x2, float y1, float y2, int count) {
        for (int i = 0; i < count; i++) {
            CreatureGameObject creatureGameObject = abstractGameObjectFactory.createCreatureGameObject(GameObjectType.ENEMY, new Gun(ProjectileManager.getInstance(), GameObjectDirection.LEFT, 20));
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

    public void removeCreatureObject(CreatureGameObject creatureGameObject) {
        creatureGameObjects.removeValue(creatureGameObject, true);
    }

    public void removeCreatureObjects(Array<CreatureGameObject> creatureGameObjects) {
        if (creatureGameObjects.notEmpty()) {
            for (CreatureGameObject creatureGameObject : creatureGameObjects) {
                this.removeCreatureObject(creatureGameObject);
            }
        }
    }

    public Array<CreatureGameObject> getGameObjects() {
        return creatureGameObjects;
    }

    public AbstractGameObjectFactory getAbstractGameObjectFactory() {
        return abstractGameObjectFactory;
    }

    public Array<CreatureGameObject> getCreaturesinRange(GameObject gameObject, float range, GameObjectType type) {
        Array<CreatureGameObject> creaturesInRange = new Array<>();
        for (CreatureGameObject creatureGameObject : this.creatureGameObjects) {

            //##enemy Range Kontur 120f y-range
            if (type.equals(GameObjectType.PLAYER) && (creatureGameObject.getGameObjectType().equals(GameObjectType.ENEMY))) {
                if ((creatureGameObject.getX() <= (gameObject.getX() + range) && creatureGameObject.getX() > 0 && creatureGameObject.getY()-20 <= (gameObject.getY())
                        && creatureGameObject.getY()+100 >= (gameObject.getY()))) {
                    creaturesInRange.add(creatureGameObject);
                }
            }
            //##player range - Kontur 120f y-range
            if (type.equals(GameObjectType.ENEMY) && (creatureGameObject.getGameObjectType().equals(GameObjectType.PLAYER))) {
                if ((creatureGameObject.getX() >= (gameObject.getX() + range) && creatureGameObject.getY()+20 <= (gameObject.getY())
                        && creatureGameObject.getY()+100 >= (gameObject.getY()))) {
                    creaturesInRange.add(creatureGameObject);
                }
            }
        }
        return creaturesInRange;
    }

    public void damageCreature(CreatureGameObject creatureGameObject, float damage) {
        creatureGameObject.damage(damage);
        notifyObservers(creatureGameObject);
    }

    public void addObserver(GameObjectObserver gameObjectObserver) {
        gameObjectObservers.add(gameObjectObserver);
    }

    public void removeObserver(GameObjectObserver gameObjectObserver) {
        gameObjectObservers.removeValue(gameObjectObserver, true);
    }

    private void notifyObservers(CreatureGameObject creatureGameObject) {
        for (GameObjectObserver gameObjectObserver : gameObjectObservers) {
            gameObjectObserver.update(creatureGameObject);
        }
    }

    public void act(float delta) {
        gameTypeStrategy.act(this, delta);
        ProjectileManager.getInstance().act(delta, this);
    }


    public void increaseSpeed() {
        this.speedfactor *= 1.3;
    }


}
