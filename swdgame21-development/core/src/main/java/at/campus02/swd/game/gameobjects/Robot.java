package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.AssetLoaderSingleton;
import at.campus02.swd.game.Weapon.Weapon;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class Robot extends CreatureGameObject {


    public Robot(Weapon _weapon) {
        weapon = _weapon;
        texture = AssetLoaderSingleton.getInstance().getRobotTexture();
        sprite = new Sprite(texture);
        sprite.setSize(120f, 120f);
        sprite.flip(true, false);
        speed = MathUtils.random(30f, 60f);
        health = 100; //percentage
    }
    public Robot() {
        texture = AssetLoaderSingleton.getInstance().getRobotTexture();
        sprite = new Sprite(texture);
        sprite.setSize(120f, 120f);
        sprite.flip(true, false);
        speed = MathUtils.random(30f, 60f);
        health = 100; //percentage
    }

}
