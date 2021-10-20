package at.campus02.swd.game.gameobjects;

public class ZombieGameObjectFactory implements AbstractGameObjectFactory {
    public GameObject createGameObject(GameObjectType type) {
        switch (type) {
            case ENEMY: return new Zombie();
            case PLAYER: return new Ninja(Ninja.Type.FEMALE);
        }
        return null;
    }
}
