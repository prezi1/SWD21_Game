package at.campus02.swd.game.observer;

import java.util.ArrayList;

public class Observable {

    private ArrayList<Observer> observer;

    public Observable() {
        this.observer = new ArrayList<>();
    }

    public void registerObserver(Observer observer) {
        this.observer.add(observer);
    }

    public void unregisterObserver(Observer observer) {
        this.observer.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : this.observer) {
            observer.EnemyCatched();
        }
    }


}
