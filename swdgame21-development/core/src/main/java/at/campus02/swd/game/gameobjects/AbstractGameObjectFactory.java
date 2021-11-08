package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.Weapon.IWeapon;

public interface AbstractGameObjectFactory {

    GameObject createGameObject(GameObjectType type, GameObjectDirection direction, float damage);
    CreatureGameObject createCreatureGameObject(GameObjectType type);
    CreatureGameObject createCreatureGameObject(GameObjectType type, IWeapon IWeapon);
}