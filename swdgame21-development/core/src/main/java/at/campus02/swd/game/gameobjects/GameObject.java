package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.Outputter.Output;
import at.campus02.swd.game.Outputter.PositionOutput;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface GameObject {
    void act(float delta);
    void setPosition(float x, float y);
    float getX();
    float getY();
    void draw(SpriteBatch batch);
    void printPosition(PositionOutput positionOutput);

}
