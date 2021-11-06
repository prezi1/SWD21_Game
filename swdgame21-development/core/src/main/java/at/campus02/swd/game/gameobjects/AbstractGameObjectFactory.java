package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.Weapon.Weapon;

public interface AbstractGameObjectFactory {

    GameObject createGameObject(GameObjectType type, GameObjectDirection direction);
    CreatureGameObject createCreatureGameObject(GameObjectType type);
    CreatureGameObject createCreatureGameObject(GameObjectType type, Weapon weapon);
}