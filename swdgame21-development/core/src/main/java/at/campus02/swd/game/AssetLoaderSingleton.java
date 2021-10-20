package at.campus02.swd.game;

import com.badlogic.gdx.graphics.Texture;

public class AssetLoaderSingleton {
    private static AssetLoaderSingleton instance;

    private Texture zombieTexture;
    private Texture robotTexture;
    private Texture maleNinjaTexture;
    private Texture femaleNinjaTexture;

    private AssetLoaderSingleton() {};

    public static AssetLoaderSingleton getInstance() {
        if(instance == null) {
            instance = new AssetLoaderSingleton();
        }
        return instance;
    }

    public void loadAssets() {
        zombieTexture = new Texture("zombie1.png");
        robotTexture = new Texture("robot.png");
        maleNinjaTexture = new Texture("male-ninja.png");
        femaleNinjaTexture = new Texture("female-ninja.png");
    }

    public Texture getZombieTexture() {
        return zombieTexture;
    }

    public Texture getMaleNinjaTexture() {
        return maleNinjaTexture;
    }

    public Texture getFemaleNinjaTexture() {
        return femaleNinjaTexture;
    }

    public Texture getRobotTexture() {
        return robotTexture;
    }

    public void dispose() {
        zombieTexture.dispose();
    }

}
