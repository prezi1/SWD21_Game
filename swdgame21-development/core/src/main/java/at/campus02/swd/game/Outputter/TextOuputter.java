package at.campus02.swd.game.Outputter;

public class TextOuputter implements Output{

    @Override
    public void print(float x, float y) {
        System.out.println("Position: " + x + "/" + y);
    }

    @Override
    public void print(float x, float y, String prefix) {
        System.out.println(prefix + x + "/" + y);
    }
}
