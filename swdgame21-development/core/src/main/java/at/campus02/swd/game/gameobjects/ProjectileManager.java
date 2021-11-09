package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.utils.Array;

public class ProjectileManager {

    private Array<ProjectileGameObject> projectiles;
    private AbstractGameObjectFactory abstractGameObjectFactory;

    public ProjectileManager(AbstractGameObjectFactory abstractGameObjectFactory) {
        this.abstractGameObjectFactory = abstractGameObjectFactory;
        projectiles = new Array<>();
    }

    public void addProjectile(float x, float y, int count, GameObjectDirection direction,float damage) {
        ProjectileGameObject projectile = (ProjectileGameObject) abstractGameObjectFactory.createGameObject(GameObjectType.BULLET, direction,damage);
        if (projectile.getGunner().equals(GameObjectType.PLAYER)) {
            projectile.increaseSpeed(5);
        } else {
            projectile.increaseSpeed(1);
        }
        projectile.setPosition(x + 20, y + 40);
        if (direction.equals(GameObjectDirection.RIGHT)) {
            projectile.setPosition(x + 60, y + 40);
        }
        projectiles.add(projectile);
    }

    public void remove(ProjectileGameObject projectile) {
        projectiles.removeValue(projectile, true);
    }

    public void act(float delta, CreatureManager creatureManager) {

        for (ProjectileGameObject projectile : projectiles) {

            if (projectile.getX() > 800 || projectile.getX() < -50) {
                remove(projectile);
            }

            for (CreatureGameObject creatureGameObject : creatureManager.getCreaturesinRange(projectile, -50, projectile.getGunner())) {
                //creatureManager.removeEnemy(creatureGameObject);
                //creatureGameObject.damage(projectile.getDamage());
                creatureManager.damageCreature(creatureGameObject,projectile.getDamage());
                remove(projectile);
            }

            projectile.act(delta);
        }

    }

    public Array<ProjectileGameObject> getProjectile() {
        return projectiles;
    }
}
