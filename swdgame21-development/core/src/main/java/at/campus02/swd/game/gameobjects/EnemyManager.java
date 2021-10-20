package at.campus02.swd.game.gameobjects;


import at.campus02.swd.game.Outputter.PositionOutput;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class EnemyManager implements EnemyObject {

    private Array<GameObject> gameObjects;
    private final AbstractGameObjectFactory abstractGameObjectFactory;
    private int maxGameObjects;
    private boolean gameover;


    public EnemyManager(AbstractGameObjectFactory abstractGameObjectFactory, int maxGameObjects) {
        this.abstractGameObjectFactory = abstractGameObjectFactory;
        this.gameObjects = new Array<>();
        this.maxGameObjects = maxGameObjects;
        this.gameover = false;
        for (int i = 0; i < maxGameObjects; i++) {
            GameObject gameObject = abstractGameObjectFactory.createGameObject(GameObjectType.ENEMY);
            gameObject.setPosition(MathUtils.random(200,800), MathUtils.random(600 - 120));
            gameObject.printPosition(new PositionOutput());
            gameObjects.add(gameObject);
        }

    }

    @Override
    public void createEnemies(float x, float y) {
        for (int i = gameObjects.size; i <= this.maxGameObjects; i++) {
            GameObject gameObject = abstractGameObjectFactory.createGameObject(GameObjectType.ENEMY);
            gameObject.setPosition(x, y);
            gameObject.printPosition(new PositionOutput());
            gameObjects.add(gameObject);
        }
    }

    @Override
    public void deleteEnemies(float posplayerX, float posplayerY, float dist) {
        Array<GameObject> toRemove = new Array<>();
        for (GameObject gameObject : gameObjects) {
            if ((gameObject.getX() <= (posplayerX + dist) && gameObject.getX() > 0 &&
                    gameObject.getY() <= (posplayerY + dist) && gameObject.getY() >= (posplayerY - dist))) {
                toRemove.add(gameObject);
            } else if (gameObject.getX() < -50) {
                toRemove.addAll(gameObjects);
                this.gameover = true;
            }
            gameObjects.removeAll(toRemove, true);
            toRemove.clear();

        }
    }

    @Override
    public void setMaxEnemies(int maxGameObjects) {
        this.maxGameObjects = maxGameObjects;
    }


    @Override
    public void moveEnemy(float delta) {
        for (GameObject gameObject : this.gameObjects) {
            gameObject.act(delta);
        }

    }

    @Override
    public void drawEnemy(SpriteBatch batch) {
        for (GameObject gameObject : this.gameObjects) {
            gameObject.draw(batch);
        }
    }

    @Override
    public boolean isGameover() {
        return this.gameover;
    }

}
