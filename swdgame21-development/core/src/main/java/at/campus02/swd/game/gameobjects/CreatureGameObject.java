package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.Outputter.PositionOutput;
import at.campus02.swd.game.Weapon.IWeapon;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CreatureGameObject extends GameObject implements ICreatureGameObject {

    protected IWeapon IWeapon;
    protected float health;

    public enum Type {
        MALE,
        FEMALE
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void printPosition(PositionOutput positionOutput) {
        positionOutput.print(getX(), getY(), this.getClass().getSimpleName());
    }


    public float getSpeed() {
        return this.speed;
    }


    public void increaseSpeed(float factor) {
        this.speed *= factor;
    }


    public void damage(float amount) {
        if ((this.health - amount) < 0) {
            this.health = 0;
        } else {
            this.health -= amount;
        }
    }

    public IWeapon getWeapon() {
        return IWeapon;
    }

    public float getHealth() {
        return this.health;
    }

}


