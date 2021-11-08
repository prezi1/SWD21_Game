package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.AssetLoaderSingleton;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class ProjectileGameObject extends GameObject{

    private GameObjectType gunner;
    private float damage;

    public ProjectileGameObject(GameObjectDirection direction, float damage) {
        gameObjectType = GameObjectType.BULLET;
        texture  = AssetLoaderSingleton.getInstance().getBulletTexture();
        sprite = new Sprite(texture);
        sprite.setSize(20f, 20f);
        this.damage = damage;
        //sprite.flip(true, false);
        speed = -100;
        gunner = GameObjectType.PLAYER;
        if (direction.equals(GameObjectDirection.LEFT)) {
            speed *= -1;
            sprite.flip(true, false);
            gunner = GameObjectType.ENEMY;
        }

    }

    public float getDamage() {
        return damage;
    }

    public GameObjectType getGunner() {
        return gunner;
    }
}
