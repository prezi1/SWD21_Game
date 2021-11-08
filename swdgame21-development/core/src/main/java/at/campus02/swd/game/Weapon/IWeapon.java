package at.campus02.swd.game.Weapon;

import at.campus02.swd.game.gameobjects.CreatureManager;
import at.campus02.swd.game.gameobjects.CreatureGameObject;
import at.campus02.swd.game.gameobjects.ProjectileManager;

public interface IWeapon {

    void execute(CreatureManager creatureManager, CreatureGameObject player);
}
