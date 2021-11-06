package at.campus02.swd.game;

import at.campus02.swd.game.Outputter.PositionOutput;
import at.campus02.swd.game.commands.MoveDownCommand;
import at.campus02.swd.game.commands.MoveUpCommand;
import at.campus02.swd.game.gameobjects.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
	private SpriteBatch batch;
	private final Array<CreatureGameObject> enemyGameObjects = new Array<>();
	private final float updatesPerSecond = 60;
	private final float logicFrameTime = 1 / updatesPerSecond;
	private float deltaAccumulator = 0;
	private AbstractGameObjectFactory gameObjectFactory;

	private CreatureGameObject player;
	private MoveDownCommand moveDownCommand;
	private MoveUpCommand moveUpCommand;

	@Override
	public void create() {
		AssetLoaderSingleton.getInstance().loadAssets();
		gameObjectFactory = new RobotGameObjectFactory();
		//gameObjectFactory = new ZombieGameObjectFactory();
		batch = new SpriteBatch();
		player = gameObjectFactory.createCreatureGameObject(GameObjectType.PLAYER);
		moveUpCommand = new MoveUpCommand(player, 10);
		moveDownCommand = new MoveDownCommand(player, 10);

		for(int i = 0; i < 10; i++) {
			CreatureGameObject creatureGameObject = gameObjectFactory.createCreatureGameObject(GameObjectType.ENEMY);
			creatureGameObject.setPosition(MathUtils.random(800), MathUtils.random(600-120));
			enemyGameObjects.add(creatureGameObject);
		}
	}

	private void act(float delta) {
		handleInputs(delta);
		Array<CreatureGameObject> toRemove = new Array<>();
		for(CreatureGameObject creatureGameObject : enemyGameObjects) {
			creatureGameObject.act(delta);
			if(creatureGameObject.getX() < 0) {
				toRemove.add(creatureGameObject);
			}
		}
		enemyGameObjects.removeAll(toRemove, true);
		for(int i = 0; i < toRemove.size; i++) {
			CreatureGameObject enemy = gameObjectFactory.createCreatureGameObject(GameObjectType.ENEMY);
			enemy.setPosition(800, MathUtils.random(0, 600-120));
			enemy.printPosition(new PositionOutput());
			enemyGameObjects.add(enemy);
		}
		toRemove.clear();
	}

	private void draw() {
		batch.begin();
		for(CreatureGameObject creatureGameObject : enemyGameObjects) {
			creatureGameObject.draw(batch);
		}
		player.draw(batch);
		batch.end();
	}

	private void handleInputs(float delta) {
		if(Gdx.input.isKeyPressed(Input.Keys.S)) {
			moveDownCommand.execute();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
			moveUpCommand.execute();
		}

		if(Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT)){
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
		while(deltaAccumulator > logicFrameTime) {
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