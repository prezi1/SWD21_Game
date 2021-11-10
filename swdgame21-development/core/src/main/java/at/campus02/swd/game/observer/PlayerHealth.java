package at.campus02.swd.game.observer;

import at.campus02.swd.game.gameobjects.CreatureGameObject;
import at.campus02.swd.game.gameobjects.CreatureManager;
import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.GameObjectType;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerHealth implements GameObjectObserver{

    private float health;
    private SpriteBatch batch;
    private BitmapFont font;
    private CreatureManager creatureManager;

    public PlayerHealth(CreatureManager creatureManager, SpriteBatch batch, float initPlayerHealth) {
        this.creatureManager = creatureManager;
        creatureManager.addObserver(this);
        this.batch = batch;
        this.health = initPlayerHealth;
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(1, 1);
    }

    @Override
    public void update(CreatureGameObject creatureGameObject) {
        if (creatureGameObject.getGameObjectType().equals(GameObjectType.PLAYER)) {
            this.health = creatureGameObject.getHealth();
            System.out.println("Attention Player was injured! Current Health Status: "+health);
        }
    }

    public void display(){
        font.draw(batch, "Health: " + (health) +"%", 15, 590);
    }

}
