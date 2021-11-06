package at.campus02.swd.game.Weapon;

import at.campus02.swd.game.gameobjects.CreatureManager;
import at.campus02.swd.game.gameobjects.CreatureGameObject;
import at.campus02.swd.game.gameobjects.GameObjectDirection;
import at.campus02.swd.game.gameobjects.ProjectileManager;

public class Gun implements Weapon{

    private final float damage = 100;
    private final float range = 250;
    private ProjectileManager projectileManager;
    private GameObjectDirection direction;

    public Gun(ProjectileManager projectileManager,GameObjectDirection direction) {
        this.projectileManager = projectileManager;
        this.direction = direction;

    }

    @Override
    public void execute(CreatureManager creatureManager, CreatureGameObject gameObject) {

        //create new projectile
        this.projectileManager.addProjectile(gameObject.getX(), gameObject.getY(), 1,direction);

        /*
        for (CreatureGameObject creatureGameObject : creatureManager.getCreaturesinRange(player,range)){
            creatureGameObject.damage(damage);
        }

         */

    }
}
