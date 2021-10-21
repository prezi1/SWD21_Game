package at.campus02.swd.game.observer;

public class GameScore implements Observer{
    @Override
    public void EnemyCatched() {
        System.out.println("Enemy catched");
    }
}
