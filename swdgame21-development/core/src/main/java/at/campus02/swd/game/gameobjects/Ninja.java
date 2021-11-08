package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.AssetLoaderSingleton;
import at.campus02.swd.game.Weapon.IWeapon;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Ninja extends CreatureGameObject {

    public enum Type {
        MALE,
        FEMALE
    }

    public Ninja(Type type, IWeapon IWeapon) {
        gameObjectType = GameObjectType.PLAYER;
        Texture texture = type == Type.MALE
                ? AssetLoaderSingleton.getInstance().getMaleNinjaTexture()
                : AssetLoaderSingleton.getInstance().getFemaleNinjaTexture();
        sprite = new Sprite(texture);
        sprite.setSize(120f, 120f);
        this.IWeapon = IWeapon;
        speed = 0;
        health = 100; //100%
    }

    public IWeapon getWeapon() {
        return IWeapon;
    }

    @Override
    public float getSpeed() {
        return 0;
    }


}
