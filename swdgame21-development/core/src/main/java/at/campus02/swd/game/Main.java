package at.campus02.swd.game;

import at.campus02.swd.game.Strategies.GameTypeStrategy;
import at.campus02.swd.game.Weapon.Gun;
import at.campus02.swd.game.Weapon.IWeapon;
import at.campus02.swd.game.Weapon.Sword;
import at.campus02.swd.game.commands.MoveDownCommand;
import at.campus02.swd.game.commands.MoveUpCommand;
import at.campus02.swd.game.gameobjects.*;
import at.campus02.swd.game.observer.GameScore;
import at.campus02.swd.game.observer.PlayerHealth;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private final float updatesPerSecond = 60;
    private final float logicFrameTime = 1 / updatesPerSecond;
    private float deltaAccumulator = 0;
    private float timeincreasespeed = 0;
    private AbstractGameObjectFactory gameObjectFactory;

    private CreatureGameObject player;
    private MoveDownCommand moveDownCommand;
    private MoveUpCommand moveUpCommand;
    private CreatureManager creatureManager;
    private GameScore gameScore;
    private PlayerHealth playerHealth;
    private BitmapFont font;
    private GameTypeStrategy gameTypeStrategy;


    private boolean startGame = false;

    @Override
    public void create() {
        AssetLoaderSingleton.getInstance().loadAssets();
        gameObjectFactory = new RobotGameObjectFactory();
        //gameObjectFactory = new ZombieGameObjectFactory();
        IWeapon gun = new Gun(ProjectileManager.getInstance(), GameObjectDirection.RIGHT,100);
        batch = new SpriteBatch();
        player = gameObjectFactory.createCreatureGameObject(GameObjectType.PLAYER, gun);
        moveUpCommand = new MoveUpCommand(player, 10);
        moveDownCommand = new MoveDownCommand(player, 10);
        gameTypeStrategy = new GameTypeStrategy(player, 10);
        creatureManager = new CreatureManager(gameObjectFactory, gameTypeStrategy);
        gameScore = new GameScore(creatureManager,batch);
        playerHealth = new PlayerHealth(creatureManager,batch, player.getHealth());
        font = new BitmapFont();

    }

    private void act(float delta) {
        handleInputs(delta);
        if (startGame) {
            creatureManager.act(delta);
        }
    }

    private void draw() {
        batch.begin();

        if (!startGame) {
            font.setColor(Color.WHITE);
            Sprite sprite = new Sprite(new Texture("StartGame.jpg"));
            sprite.setPosition(0,0);
            sprite.setSize(800f,600f);
            sprite.draw(batch);
            font.draw(batch, "Control KEY Commands: " + "\n" +
                    "Up -> W" + "\n" + "Down -> S" + "\n" + "Shot -> Spacebar", 120, 300);
            font.draw(batch,"Press S to start the Game! Have Fun :)",120,175);
        }
        if (startGame) {
            playerHealth.display();
            gameScore.display();

            for (CreatureGameObject creatureGameObject : creatureManager.getGameObjects()) {
                creatureGameObject.draw(batch);
            }

            for (GameObject projectile : ProjectileManager.getInstance().getProjectile()) {
                projectile.draw(batch);
            }

            if (gameTypeStrategy.GameOver()) {
                font.setColor(Color.BLACK);
                font.draw(batch, "GAME OVER!" + "\n" + "Press - KEY R - for a new Game!", 300, 350);
            }
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

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            player.getWeapon().execute(creatureManager, player);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ALT_LEFT)) {
            player.getWeapon().execute(creatureManager, player);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.S) && !startGame) {
            startGame = true;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.R) && gameTypeStrategy.GameOver()) {
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
            //creatureManager.increaseSpeed();
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