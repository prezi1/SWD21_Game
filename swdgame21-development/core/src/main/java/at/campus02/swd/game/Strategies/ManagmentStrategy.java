package at.campus02.swd.game.Strategies;

import at.campus02.swd.game.gameobjects.CreatureManager;

public interface ManagmentStrategy {


    void act(CreatureManager creatureManager, float delta);
    void init(CreatureManager creatureManager);
    boolean GameOver();

}
