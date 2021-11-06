package at.campus02.swd.game.commands;

import at.campus02.swd.game.gameobjects.CreatureGameObject;

public class MoveDownCommand extends Booster implements PlayerCommand {
    private final CreatureGameObject creatureGameObject;
    private final float distance;


    public MoveDownCommand(CreatureGameObject creatureGameObject, float distance) {
        this.creatureGameObject = creatureGameObject;
        this.distance = distance;
    }

    @Override
    public void execute() {
        float newY = Math.max(0, creatureGameObject.getY() - (distance+getBoosterStrength()));
        this.creatureGameObject.setPosition(creatureGameObject.getX(), newY);
    }

    @Override
    public int getBoosterStrength() {
        return super.getBoosterStrength();
    }

}
