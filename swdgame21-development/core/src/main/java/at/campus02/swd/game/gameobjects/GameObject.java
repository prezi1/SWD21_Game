package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.Outputter.Output;
import at.campus02.swd.game.Outputter.PositionOutput;
import at.campus02.swd.game.Weapon.Weapon;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface GameObject {
    void act(float delta);
    void setPosition(float x, float y);
    float getX();
    float getY();
    void draw(SpriteBatch batch);
    void printPosition(PositionOutput positionOutput);
    float getSpeed();
    void increaseSpeed(float factor);
    void damage(float amount);
    Weapon getWeapon();
    float getHealth();
}
