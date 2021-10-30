package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.Weapon.Weapon;

public class RobotGameObjectFactory implements AbstractGameObjectFactory {
    public GameObject createGameObject(GameObjectType type) {
        switch (type) {
            case ENEMY: return new Robot();
            case PLAYER: return new Ninja(Ninja.Type.MALE,null);
        }
        return null;
    }

    public GameObject createGameObject(GameObjectType type, Weapon weapon) {
        switch (type) {
            case ENEMY: return new Robot();
            case PLAYER: return new Ninja(Ninja.Type.MALE,weapon);
        }
        return null;
    }
}
