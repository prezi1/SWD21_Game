package at.campus02.swd.game.observer;

public class CatchLogger implements Observer{
    private Integer counter;

    public CatchLogger() {
        this.counter = 0;
    }

    @Override
    public void EnemyCatched() {
        this.counter += 1;
        System.out.println("Number of enemys catched: " + this.counter);
    }

    public Integer getCounter() {
        return counter;
    }
}
