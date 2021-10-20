package at.campus02.swd.game.gameobjects;

public class RobotGameObjectFactory implements AbstractGameObjectFactory {
    public GameObject createGameObject(GameObjectType type) {
        switch (type) {
            case ENEMY: return new Robot();
            case PLAYER: return new Ninja(Ninja.Type.MALE);
        }
        return null;
    }
}
