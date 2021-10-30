package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.AssetLoaderSingleton;
import at.campus02.swd.game.Outputter.PositionOutput;
import at.campus02.swd.game.Weapon.Weapon;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ninja implements GameObject{
    private Sprite sprite;
    private Weapon weapon;



    public enum Type {
        MALE,
        FEMALE
    }

    public Ninja(Type type,Weapon weapon) {
        Texture texture = type == Type.MALE
                ? AssetLoaderSingleton.getInstance().getMaleNinjaTexture()
                : AssetLoaderSingleton.getInstance().getFemaleNinjaTexture();
        sprite = new Sprite(texture);
        sprite.setSize(120f, 120f);
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public float getHealth() {
        return 100;
    }

    @Override
    public void act(float delta) {
    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x,y);
    }

    @Override
    public float getX() {
        return sprite.getX();
    }

    @Override
    public float getY() {
        return sprite.getY();
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public void increaseSpeed(float factor) {

    }

    @Override
    public void damage(float amount) {

    }

    @Override
    public void printPosition(PositionOutput positionOutput) {

    }

    @Override
    public float getSpeed() {
        return 0;
    }


}
