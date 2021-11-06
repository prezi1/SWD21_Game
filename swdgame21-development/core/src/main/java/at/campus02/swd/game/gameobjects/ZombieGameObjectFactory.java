package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.Weapon.Weapon;

public class ZombieGameObjectFactory implements AbstractGameObjectFactory {


    @Override
    public GameObject createGameObject(GameObjectType type, GameObjectDirection direction) {
        switch (type) {
            case BULLET: return new ProjectileGameObject(direction);
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
    public CreatureGameObject createCreatureGameObject(GameObjectType type, Weapon weapon) {
        switch (type) {
            case ENEMY: return new Zombie(weapon);
            case PLAYER: return new Ninja(Ninja.Type.FEMALE,weapon);
        }
        return null;
    }
}
