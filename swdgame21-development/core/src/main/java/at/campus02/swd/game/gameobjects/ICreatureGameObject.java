package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.Outputter.PositionOutput;
import at.campus02.swd.game.Weapon.IWeapon;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface ICreatureGameObject {

    public void draw(SpriteBatch batch);
    public void printPosition(PositionOutput positionOutput);
    public void increaseSpeed(float factor);
    public void damage(float amount);
    public IWeapon getWeapon();
    public float getHealth();
}

