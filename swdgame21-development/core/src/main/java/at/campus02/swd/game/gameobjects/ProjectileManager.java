package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.Outputter.PositionOutput;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class ProjectileManager {

    private Array<ProjectileGameObject> projectiles;
    private AbstractGameObjectFactory abstractGameObjectFactory;

    public ProjectileManager(AbstractGameObjectFactory abstractGameObjectFactory) {
        this.abstractGameObjectFactory = abstractGameObjectFactory;
        projectiles = new Array<>();
    }

    public void addProjectile(float x, float y, int count, GameObjectDirection direction) {
        ProjectileGameObject projectile = (ProjectileGameObject) abstractGameObjectFactory.createGameObject(GameObjectType.BULLET, direction);
        if (projectile.getGunner().equals(GameObjectType.PLAYER)) {
            projectile.increaseSpeed(5);
        }else{
            projectile.increaseSpeed(1);
        }
        projectile.setPosition(x+20,y+40);
        if (direction.equals(GameObjectDirection.RIGHT)) {
            projectile.setPosition(x + 60, y + 40);
        }
        projectiles.add(projectile);
    }

    public void remove(ProjectileGameObject projectile) {
        projectiles.removeValue(projectile, true);
    }

    public void act(float delta, CreatureManager creatureManager) {
        boolean shot = false;
        for (ProjectileGameObject projectile : projectiles) {

            if (projectile.getX() > 800 || projectile.getX() < -50) {
                projectiles.removeValue(projectile, true);
            }

            if (projectile.getGunner().equals(GameObjectType.PLAYER)){
                for (CreatureGameObject creatureGameObject : creatureManager.getCreaturesinRange(projectile, 0,projectile.getGunner())) {
                    creatureManager.removeEnemy(creatureGameObject);
                    shot = true;
                }
            }

            if (shot) {
                projectiles.removeValue(projectile, true);
                shot = false;
            }

            projectile.act(delta);
        }


    }

    public Array<ProjectileGameObject> getProjectile() {
        return projectiles;
    }
}
