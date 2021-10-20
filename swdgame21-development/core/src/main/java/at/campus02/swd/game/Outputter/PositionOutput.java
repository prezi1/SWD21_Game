package at.campus02.swd.game.Outputter;

import at.campus02.swd.game.gameobjects.GameObject;

public class PositionOutput {


    public void print(Output output, float x, float y, String prefix) {
        output.print(x, y, prefix);
    }

    public void print(Output output, float x, float y) {
        output.print(x, y);
    }


}
