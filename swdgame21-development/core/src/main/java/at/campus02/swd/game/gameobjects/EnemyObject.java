package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public interface EnemyObject {

    void createEnemies(float x1,float x2, float y1, float y2);
    void deleteEnemies(float posplayerX,float posplayerY, float dist);
    void setMaxEnemies(int maxGameObjects);
    void moveEnemy(float delta);
    void drawEnemy (SpriteBatch batch);
    boolean isGameover();
    void increaseSpeed();
}