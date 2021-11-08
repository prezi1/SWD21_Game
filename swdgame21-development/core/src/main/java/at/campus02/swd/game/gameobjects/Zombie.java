package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.AssetLoaderSingleton;
import at.campus02.swd.game.Weapon.IWeapon;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class Zombie extends CreatureGameObject {

    public Zombie(IWeapon _I_weapon) {
        gameObjectType = GameObjectType.ENEMY;
        IWeapon = _I_weapon;
        texture = AssetLoaderSingleton.getInstance().getZombieTexture();
        sprite = new Sprite(texture);
        sprite.setSize(120f, 120f);
        sprite.flip(true, false);
        speed = MathUtils.random(30f, 60f);
        health = 100; //percentage
    }
    public Zombie() {
        gameObjectType = GameObjectType.ENEMY;
        texture = AssetLoaderSingleton.getInstance().getZombieTexture();
        sprite = new Sprite(texture);
        sprite.setSize(120f, 120f);
        sprite.flip(true, false);
        speed = MathUtils.random(30f, 60f);
        health = 100; //percentage
    }

}

