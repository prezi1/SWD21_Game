package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.Weapon.Weapon;

public interface AbstractGameObjectFactory {
    GameObject createGameObject(GameObjectType type);
    GameObject createGameObject(GameObjectType type, Weapon weapon);
}