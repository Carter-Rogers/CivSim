package main.gfx;

import org.jetbrains.annotations.NotNull;

public class Bitmap {

    protected int width, height;
    protected int[] pixels;

    public Bitmap(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height * 4];
    }

    public void copyToTarget(int[] target) {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                if(x + y * width > target.length)
                    break;
                else
                    target[x + y * width] = pixels[x + y * width];
            }
        }
    }

    public void drawMap(@NotNull Bitmap map, int xOff, int yOff) {
        for(int y = 0; y < map.height; y++) {
            int yPix = y + yOff;

            if(yPix < 0 || yPix > height)
                break;
            for(int x = 0; x < map.width; x++) {
                int xPix = x + xOff;

                if(xPix < 0 || xPix > width)
                    break;

                int color = map.pixels[x + y * map.width];
                pixels[xPix + yPix * width] = color;
            }
        }
    }

}
