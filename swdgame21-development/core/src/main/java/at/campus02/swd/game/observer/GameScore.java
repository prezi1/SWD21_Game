package at.campus02.swd.game.observer;

public class GameScore implements Observer{
    @Override
    public void EnemyEscaped() {
        System.out.println("Enemy escaped");
    }
}
