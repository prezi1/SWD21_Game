package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.AssetLoaderSingleton;
import at.campus02.swd.game.Weapon.Weapon;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Ninja extends CreatureGameObject {

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
        speed = 0;

    }

    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public float getHealth() {
        return 100;
    }

    @Override
    public float getSpeed() {
        return 0;
    }


}
