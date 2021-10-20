package at.campus02.swd.game.Outputter;

public class CSVOutputter implements Output {

    public void print(float x, float y) {
        System.out.println(x + ";" + y);
    }

    public void print(float x, float y, String prefix) {
        System.out.println(prefix+ ";" + x + ";" + y);
    }

}
