package Entity;

import TileMap.ImageLoader;
import TileMap.SpriteSheet;

import java.awt.image.BufferedImage;

public class Fireball {
    private BufferedImage fire;
    private BufferedImage[] spritefires = new BufferedImage[4];
    private SpriteSheet sheet;
    public Fireball(){
        init();
    }
    public void init() {
        fire = ImageLoader.loadImage("fireball.gif");
        sheet = new SpriteSheet(fire);
        spritefires [0] = (sheet.crop(90,30, 30, 30));
        spritefires [1] = (sheet.crop(30,0, 30, 30));
        spritefires [2] = (sheet.crop(0,30, 30, 30));
        spritefires [3] = (sheet.crop(30,30, 30, 30));
    }
    public BufferedImage[] getSpritefires(){
        return spritefires;
    }
    public void draw(java.awt.Graphics2D g, int positionperso, int X, int Y) {

        if (positionperso == 3) {
            for (int i = 0; i < spritefires.length; i++){
                g.drawImage(spritefires[i], X, Y+i*32, null);
            }
        }
        if (positionperso == 4) {
            for (int i = 0; i < spritefires.length; i++){
                g.drawImage(spritefires[i], X+i*32, Y, null);
            }
        }
        if (positionperso == 2 ) {
            for (int i = 0; i < spritefires.length; i++){
                g.drawImage(spritefires[i], X, Y-i*32, null);
            }
        }
        if (positionperso == 1) {
            for (int i = 0; i < spritefires.length; i++){
                g.drawImage(spritefires[i], X - i * 32, Y, null);
            }
        }
    }
}