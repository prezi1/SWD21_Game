package at.campus02.swd.game.observer;

import at.campus02.swd.game.gameobjects.GameObject;

public class GameScore implements GameObjectObserver {

    private int gamescore;

    @Override
    public void update(GameObject gameObject) {
        System.out.println("Enemy killed! "+ gamescore +" "+ gameObject.toString());
        gamescore +=1;
    }

    public int getGamescore() {
        return gamescore;
    }
}
