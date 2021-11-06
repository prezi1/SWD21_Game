package at.campus02.swd.game.Weapon;

import at.campus02.swd.game.gameobjects.CreatureManager;
import at.campus02.swd.game.gameobjects.CreatureGameObject;

public class Knife implements Weapon{

    private final float damage = 35;
    private final float range = 50;

    @Override
    public void execute(CreatureManager creatureManager, CreatureGameObject player) {

        for (CreatureGameObject creatureGameObject : creatureManager.getCreaturesinRange(player,range)){
            creatureGameObject.damage(damage);
        }

    }
}
