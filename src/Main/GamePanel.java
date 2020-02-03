package Main;

import GameState.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

//implementation des touches et du lancement de la fenetre en boucle
public class GamePanel extends JPanel implements Runnable, KeyListener {

    //Dimension de fenetre
    public static int WIDTH = 480;
    public static int HEIGHT = 320;
    public static int SCALE = 2;

    //Image
    private BufferedImage image;
    private Graphics2D g;

    //Game
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000/FPS;

    //game etat
    private GameStateManager gsm;

    public GamePanel(){
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify(){
        super.addNotify();

        if(thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    public void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        running = true;
        gsm = new GameStateManager();
    }

    public void run(){
        init();

        long start;
        long wait;
        long elapsed;

        while (running) {
            start = System.nanoTime();
            update();
            draw();
            drawToScreen();
            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed / 1000000;
            if (wait < 0)
                wait = 0;
            try {
                Thread.sleep(wait);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void update(){
        gsm.update();
    }
    private void draw(){
        gsm.draw(g);
    }
    private void drawToScreen(){
        Graphics2D g2 = (Graphics2D) getGraphics();
        g2.drawImage(image, 0,0, WIDTH * SCALE, HEIGHT * SCALE ,null);
        g2.dispose();
    }

    public void keyTyped(KeyEvent key) {}
    public void keyPressed(KeyEvent key) {
        try {
            gsm.keyPressed(key.getKeyCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void keyReleased(KeyEvent key) {
        gsm.keyReleased(key.getKeyCode());
    }
}
