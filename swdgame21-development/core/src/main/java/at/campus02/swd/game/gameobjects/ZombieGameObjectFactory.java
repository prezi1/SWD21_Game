package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.Weapon.IWeapon;

public class ZombieGameObjectFactory implements AbstractGameObjectFactory {


    @Override
    public GameObject createGameObject(GameObjectType type, GameObjectDirection direction, float damage) {
        switch (type) {
            case BULLET: return new ProjectileGameObject(direction,damage);
        }
        return null;
    }

    public CreatureGameObject createCreatureGameObject(GameObjectType type) {
        switch (type) {
            case ENEMY: return new Zombie();
            case PLAYER: return new Ninja(Ninja.Type.FEMALE,null);
        }
        return null;
    }
    public CreatureGameObject createCreatureGameObject(GameObjectType type, IWeapon IWeapon) {
        switch (type) {
            case ENEMY: return new Zombie(IWeapon);
            case PLAYER: return new Ninja(Ninja.Type.FEMALE, IWeapon);
        }
        return null;
    }
}
