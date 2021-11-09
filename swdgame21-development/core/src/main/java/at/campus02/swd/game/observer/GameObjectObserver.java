package at.campus02.swd.game.observer;

import at.campus02.swd.game.gameobjects.CreatureGameObject;
import at.campus02.swd.game.gameobjects.GameObject;

public interface GameObjectObserver {

    public void update(CreatureGameObject creatureGameObject);

}
