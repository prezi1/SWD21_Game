package at.campus02.swd.game;

import at.campus02.swd.game.Outputter.PositionOutput;
import at.campus02.swd.game.commands.MoveDownCommand;
import at.campus02.swd.game.commands.MoveUpCommand;
import at.campus02.swd.game.gameobjects.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main_2 extends ApplicationAdapter {
    private SpriteBatch batch;
    private final Array<GameObject> enemyGameObjects = new Array<>();
    private final float updatesPerSecond = 60;
    private final float logicFrameTime = 1 / updatesPerSecond;
    private float deltaAccumulator = 0;
    private AbstractGameObjectFactory gameObjectFactory;

    private GameObject player;
    private MoveDownCommand moveDownCommand;
    private MoveUpCommand moveUpCommand;
    private EnemyObject enemyManager;

    @Override
    public void create() {
        AssetLoaderSingleton.getInstance().loadAssets();

        gameObjectFactory = new RobotGameObjectFactory();
        //gameObjectFactory = new ZombieGameObjectFactory();
        enemyManager = new EnemyManager(gameObjectFactory, 10);

        batch = new SpriteBatch();
        player = gameObjectFactory.createGameObject(GameObjectType.PLAYER);
        moveUpCommand = new MoveUpCommand(player, 10);
        moveDownCommand = new MoveDownCommand(player, 10);
        enemyManager.createEnemies(800, MathUtils.random(600 - 120));

    }

    private void act(float delta) {
        handleInputs(delta);
        enemyManager.moveEnemy(delta);
        enemyManager.deleteEnemies(player.getX(), player.getY(), 50);
        if (!enemyManager.isGameover()) {
            enemyManager.createEnemies(800, MathUtils.random(600 - 120));
        }
    }

    private void draw() {
        batch.begin();
        if (!enemyManager.isGameover()) {
            enemyManager.drawEnemy(batch);
            player.draw(batch);
        }else{
            BitmapFont font = new BitmapFont();
            font.setColor(Color.BLACK);
            font.draw(batch,"GAME OVER!",350,350);
        }
        batch.end();
    }

    private void handleInputs(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            moveDownCommand.execute();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            moveUpCommand.execute();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT)) {
            moveDownCommand.setBoosterStrength(10);
            moveUpCommand.setBoosterStrength(10);
        }

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float delta = Gdx.graphics.getDeltaTime();
        deltaAccumulator += delta;
        while (deltaAccumulator > logicFrameTime) {
            deltaAccumulator -= logicFrameTime;
            act(logicFrameTime);
        }
        draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        AssetLoaderSingleton.getInstance().dispose();
    }
}