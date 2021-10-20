package at.campus02.swd.game.commands;

import at.campus02.swd.game.gameobjects.GameObject;

public class MoveUpCommand extends Booster implements PlayerCommand {
    private final GameObject gameObject;
    private final float distance;

    public MoveUpCommand(GameObject gameObject, float distance) {
        this.gameObject = gameObject;
        this.distance = distance;
    }

    @Override
    public void execute() {
        float newY = Math.min(600, gameObject.getY() + (distance+getBoosterStrength()));
        this.gameObject.setPosition(gameObject.getX(), newY);
    }

    @Override
    public int getBoosterStrength() {
        return super.getBoosterStrength();
    }

    @Override
    public void setBoosterStrength(int boosterStrength) {
        super.setBoosterStrength(boosterStrength);
    }
}
