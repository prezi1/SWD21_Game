package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.AssetLoaderSingleton;
import at.campus02.swd.game.Outputter.PositionOutput;
import at.campus02.swd.game.Weapon.Weapon;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class CreatureGameObject extends GameObject implements ICreatureGameObject {

    protected Weapon weapon;
    protected float health;

    public enum Type {
        MALE,
        FEMALE
    }


    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void printPosition(PositionOutput positionOutput) {
        /*
        if (getX() > 300) {
            positionOutput.print(new CSVOutputter(), getX(),getY());
        }else{
            positionOutput.print(new TextOuputter(), getX(), getY());
        }
        */
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

    public Weapon getWeapon() {
        return weapon;
    }

    public float getHealth() {
        return this.health;
    }

}


