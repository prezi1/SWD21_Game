package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.AssetLoaderSingleton;
import at.campus02.swd.game.Outputter.CSVOutputter;
import at.campus02.swd.game.Outputter.Output;
import at.campus02.swd.game.Outputter.PositionOutput;
import at.campus02.swd.game.Outputter.TextOuputter;
import at.campus02.swd.game.Weapon.Weapon;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Robot implements GameObject {
    private Sprite sprite;
    private float speed;
    private float health;

    public Robot() {
        Texture texture = AssetLoaderSingleton.getInstance().getRobotTexture();
        sprite = new Sprite(texture);
        sprite.setSize(120f, 120f);
        sprite.flip(true, false);
        speed = MathUtils.random(30f, 60f);
        health = 100; //percentage
    }

    @Override
    public void act(float delta) {
        sprite.setPosition(sprite.getX() - speed * delta, sprite.getY());
    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x,y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public void printPosition(PositionOutput positionOutput) {
        /*
        if (getX() > 300) {
            positionOutput.print(new CSVOutputter(), getX(),getY());
        }else{
            positionOutput.print(new TextOuputter(), getX(), getY());
        }
        */
        positionOutput.print(getX(),getY(),this.getClass().getSimpleName());
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public void increaseSpeed(float factor) {
        this.speed *= factor;
    }

    @Override
    public void damage(float amount){
        if ((this.health - amount) < 0){
            this.health = 0;
        }else{
            this.health -= amount;
        }
    }

    @Override
    public Weapon getWeapon() {
        return null;
    }

    @Override
    public float getHealth() {
        return this.health;
    }

    @Override
    public float getX() {
        return sprite.getX();
    }

    @Override
    public float getY() {
        return sprite.getY();
    }
}
