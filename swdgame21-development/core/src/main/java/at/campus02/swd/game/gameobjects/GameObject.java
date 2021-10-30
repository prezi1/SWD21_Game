package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.AssetLoaderSingleton;
import at.campus02.swd.game.Outputter.PositionOutput;
import at.campus02.swd.game.Weapon.Weapon;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class GameObject {

    protected Sprite sprite;
    protected Weapon weapon;
    protected float speed;
    protected float health;

    public enum Type {
        MALE,
        FEMALE
    }

    public GameObject() {
        Texture texture = AssetLoaderSingleton.getInstance().getRobotTexture();
        sprite = new Sprite(texture);
        sprite.setSize(120f, 120f);
        sprite.flip(true, false);
        speed = MathUtils.random(30f, 60f);
        health = 100; //percentage
    }


    public void act(float delta) {
        sprite.setPosition(sprite.getX() - speed * delta, sprite.getY());
    }


    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
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
        return null;
    }

    public float getHealth() {
        return this.health;
    }


    public float getX() {
        return sprite.getX();
    }


    public float getY() {
        return sprite.getY();
    }
}


