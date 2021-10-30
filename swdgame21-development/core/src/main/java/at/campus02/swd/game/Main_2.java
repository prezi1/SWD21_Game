package at.campus02.swd.game;

import at.campus02.swd.game.Strategies.GameTypeStrategy;
import at.campus02.swd.game.Weapon.Gun;
import at.campus02.swd.game.Weapon.Weapon;
import at.campus02.swd.game.commands.MoveDownCommand;
import at.campus02.swd.game.commands.MoveUpCommand;
import at.campus02.swd.game.gameobjects.*;
import at.campus02.swd.game.observer.CatchLogger;
import at.campus02.swd.game.observer.GameScore;
import at.campus02.swd.game.observer.Observable;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private float timeincreasespeed = 0;
    private AbstractGameObjectFactory gameObjectFactory;

    private GameObject player;
    private MoveDownCommand moveDownCommand;
    private MoveUpCommand moveUpCommand;
    private EnemyManager enemyManager;

    private Observable observable;
    private GameScore gameScore;
    private CatchLogger catchLogger;
    private BitmapFont font12;
    private BitmapFont font32;
    private BitmapFont font;
    private GameTypeStrategy gameTypeStrategy;

    @Override
    public void create() {
        AssetLoaderSingleton.getInstance().loadAssets();
        observable = new Observable();
        gameScore = new GameScore();
        catchLogger = new CatchLogger();
        observable.registerObserver(gameScore);
        observable.registerObserver(catchLogger);
        gameObjectFactory = new RobotGameObjectFactory();
        Weapon gun = new Gun();

        //gameObjectFactory = new ZombieGameObjectFactory();

        batch = new SpriteBatch();
        player = gameObjectFactory.createGameObject(GameObjectType.PLAYER, gun);
        moveUpCommand = new MoveUpCommand(player, 10);
        moveDownCommand = new MoveDownCommand(player, 10);
        gameTypeStrategy = new GameTypeStrategy(player, 10);
        enemyManager = new EnemyManager(gameObjectFactory, gameTypeStrategy, observable);
        font = new BitmapFont();

    }

    private void act(float delta) {
        handleInputs(delta);
        enemyManager.act(delta);
        /*
        enemyManager.moveEnemy(delta);
        enemyManager.deleteEnemies(player.getX(), player.getY(), 75);
        if (!enemyManager.isGameover()) {
            enemyManager.createEnemies(800, 800, 0, 380);
        }

         */
    }

    private void draw() {
        batch.begin();
        font.setColor(Color.BLACK);
        font.getData().setScale(2, 2);
        font.draw(batch, Integer.toString(catchLogger.getCounter()), 750, 590);

        for (GameObject gameObject : enemyManager.getGameObjects()) {
            gameObject.draw(batch);
        }

        player.draw(batch);


        if (gameTypeStrategy.GameOver()){
            font.setColor(Color.BLACK);
            font.draw(batch, "GAME OVER!"+"\n"+"Press the Key R for a new Game!", 220, 350);
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

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            player.getWeapon().execute(enemyManager,player);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.R)){
            create();
        }

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float delta = Gdx.graphics.getDeltaTime();
        deltaAccumulator += delta;
        timeincreasespeed += delta;


        if (timeincreasespeed > 5) {
            enemyManager.increaseSpeed();
            timeincreasespeed = 0;
        }

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