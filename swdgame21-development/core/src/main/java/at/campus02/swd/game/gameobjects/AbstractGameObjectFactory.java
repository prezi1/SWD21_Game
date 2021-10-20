package at.campus02.swd.game.gameobjects;

public interface AbstractGameObjectFactory {
    GameObject createGameObject(GameObjectType type);
}