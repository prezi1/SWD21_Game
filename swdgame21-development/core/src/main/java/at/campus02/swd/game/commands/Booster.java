package at.campus02.swd.game.commands;

public class Booster {

    private int boosterStrength;
    private int boosterDuration;

    public Booster() {
        this.boosterStrength = 0;
        this.boosterDuration = 0;
    }

    public int getBoosterStrength() {
        if (this.boosterDuration >= 0) {
            this.boosterDuration -= 1;
        }else{
            this.boosterStrength = 0;
        }
        return boosterStrength;
    }

    public void setBoosterStrength(int boosterStrength) {
        {
            this.boosterStrength = boosterStrength;
            this.boosterDuration = 100;
        }
    }
}
