package main.gfx;

import java.util.Random;

public class Bitmap2D extends Bitmap{

    private Bitmap uiMap;

    Random random = new Random();

    public Bitmap2D(int width, int height) {
        super(width, height);

        uiMap = new Bitmap(width, height);
        for(int i = 0; i < uiMap.pixels.length; i++)
            uiMap.pixels[i] = random.nextInt();
    }

    public void render() {
        for(int i = 0; i < pixels.length; i++)
            pixels[i] = 0x0;

        drawMap(uiMap, 0, 0);
    }

}
