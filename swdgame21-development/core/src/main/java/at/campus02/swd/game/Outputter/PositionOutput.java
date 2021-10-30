package at.campus02.swd.game.Outputter;

public class PositionOutput {

    private final CSVOutputter csvOutputter = new CSVOutputter();
    private final TextOuputter textOuputter = new TextOuputter();


    /*
    public void print(Output output, float x, float y, String prefix) {
        output.print(x, y, prefix);
    }

    public void print(Output output, float x, float y) {
        output.print(x, y);
    }

     */

    public void print(float x, float y, String Enemy) {
        if (Enemy.equals("Zombie")) {
            csvOutputter.print(x, y, "C02");
        }

        if (Enemy.equals("Robot")) {
            if (x > 300) {
                csvOutputter.print(x, y);
            } else {
                textOuputter.print(x, y);
            }
        }

    }


}
