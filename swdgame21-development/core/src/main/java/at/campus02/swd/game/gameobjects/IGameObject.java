package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.Outputter.PositionOutput;
import at.campus02.swd.game.Weapon.Weapon;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IGameObject {

    public void act(float delta);
    public void setPosition(float x, float y);
    public void draw(SpriteBatch batch);
    public float getSpeed();
    public void increaseSpeed(float factor);
    public float getX();
    public float getY();
}

