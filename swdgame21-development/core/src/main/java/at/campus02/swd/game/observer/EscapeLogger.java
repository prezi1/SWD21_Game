package at.campus02.swd.game.observer;

public class EscapeLogger implements Observer{
    private Integer counter;

    public EscapeLogger() {
        this.counter = 0;
    }

    @Override
    public void EnemyEscaped() {
        this.counter += 1;
        System.out.println("Number of enemys escaped: " + this.counter);
    }
}
