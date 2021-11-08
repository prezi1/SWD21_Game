package at.campus02.swd.game.Weapon;

import at.campus02.swd.game.gameobjects.CreatureManager;
import at.campus02.swd.game.gameobjects.CreatureGameObject;
import at.campus02.swd.game.gameobjects.GameObjectDirection;
import at.campus02.swd.game.gameobjects.ProjectileManager;

public class Gun implements IWeapon {

    private float damage;
    private ProjectileManager projectileManager;
    private GameObjectDirection direction;

    public Gun(ProjectileManager projectileManager,GameObjectDirection direction, float damage) {
        this.projectileManager = projectileManager;
        this.direction = direction;
        this.damage = damage;

    }

    @Override
    public void execute(CreatureManager creatureManager, CreatureGameObject gameObject) {

        //create new projectile
        this.projectileManager.addProjectile(gameObject.getX(), gameObject.getY(), 1,direction, damage);

    }

    public float getDamage() {
        return damage;
    }
}
