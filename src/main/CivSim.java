package main;

import main.gfx.Display;

public class CivSim implements Runnable{

    private Thread thread;
    private boolean running;
    private Display display;

    public void init() {
        display = new Display(640, 360);
    }

    public synchronized void start() {
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tick() {

    }

    public void render() {
        display.render();
    }

    public void run() {
        init();

        long timer = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        double ns = 1000000000/Constants.UPDATE_CAP;
        double delta = 0;

        int fps = 0, ticks = 0;

        while(running) {
            long current = System.nanoTime();
            delta += (current - lastTime) / ns;
            lastTime = current;
            if(delta >= 1.0D) {
                tick();
                ticks++;
                delta--;
            }
            render();
            fps++;

            while(System.currentTimeMillis() - timer >= 1000) {
                System.out.println(fps + " fps | " + ticks + " ticks");
                fps = 0;
                ticks = 0;
                timer += 1000;
            }
        }
    }

}
