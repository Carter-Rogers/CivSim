package main.gfx;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

public class Display extends Canvas {

    public static final long serialVersionUID = 1L;

    protected final int WIDTH, HEIGHT;

    private BufferedImage image;
    private int[] pixels;

    public Display(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

        Dimension screensize = new Dimension(width * 2, height * 2);
        this.setPreferredSize(screensize);

        JFrame window = new JFrame();
        try {
            window.setIconImage(ImageIO.read(getClass().getResource("/cs-icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        window.add(this);
        window.pack();
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(3);
        window.setVisible(true);
    }

    Bitmap2D screen = new Bitmap2D(640, 360);

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(2);
            return;
        }

        screen.render();
        screen.copyToTarget(pixels);

        Graphics g = bs.getDrawGraphics();

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        g.dispose();
        bs.show();
    }

}
