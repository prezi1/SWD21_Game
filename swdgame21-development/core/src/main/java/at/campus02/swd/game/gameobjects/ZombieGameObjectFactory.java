package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.Weapon.Weapon;

public class ZombieGameObjectFactory implements AbstractGameObjectFactory {
    public GameObject createGameObject(GameObjectType type) {
        switch (type) {
            case ENEMY: return new Zombie();
            case PLAYER: return new Ninja(Ninja.Type.FEMALE,null);
        }
        return null;
    }
    public GameObject createGameObject(GameObjectType type, Weapon weapon) {
        switch (type) {
            case ENEMY: return new Zombie();
            case PLAYER: return new Ninja(Ninja.Type.FEMALE,weapon);
        }
        return null;
    }
}
