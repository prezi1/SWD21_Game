package at.campus02.swd.game.observer;

import at.campus02.swd.game.gameobjects.CreatureGameObject;
import at.campus02.swd.game.gameobjects.CreatureManager;
import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.GameObjectType;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScore implements GameObjectObserver {

    private int gamescore;
    private SpriteBatch batch;
    private BitmapFont font;
    private CreatureManager creatureManager;

    public GameScore(CreatureManager creatureManager, SpriteBatch batch) {
        this.creatureManager = creatureManager;
        creatureManager.addObserver(this);
        this.batch = batch;
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(1, 1);
    }

    @Override
    public void update(CreatureGameObject creatureGameObject) {
        if (creatureGameObject.getGameObjectType().equals(GameObjectType.ENEMY)) {
            if (creatureGameObject.getHealth() <= 0) {
                gamescore += 1;
                System.out.println("Enemy has been killed! Number: " +gamescore);
            }
            //display();
        }
    }

    public void display() {
        font.draw(batch, "Score: " + (gamescore), 700, 590);
    }
}
