package Entity;

import GameState.GameState;
import TileMap.ImageLoader;
import TileMap.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class Ennemi extends GameState {

    private BufferedImage ennemi;

    private int ennemiX = 128;
    private int ennemiY = 128;

    private int posMax = 128;
    private int posMin = 128;
    private SpriteSheet sheet;

    private BufferedImage[] posennemi = new BufferedImage [4];
    private int postexture = 0;

    private Color hpcolor;
    private Font hpfont;
    private int hp = 10;
    private int damage = 3;
    private boolean degat = true;

    public Ennemi(int x, int y){
        ennemiX = x;
        ennemiY = y;
        posMin = x;
        posMax = x + 64;
        hpcolor = new Color(0, 0,0);
        hpfont = new Font("Century Gothic", Font.PLAIN, 10);
        init();
    }

    public void init() {
        ennemi = ImageLoader.loadImage("ennemi.gif");
        sheet = new SpriteSheet(ennemi);
        posennemi[0] = sheet.crop(0,32,32,32);
        posennemi[1] = sheet.crop(64,32,32,32);
        posennemi[2] = sheet.crop(32,0,32,32);
        posennemi[3] = sheet.crop(32,64,32,32);
    }
    public void update() {
        int rnum = RandomNum.usingMathClass(2, 0);
        postexture = rnum;
        try {
            sleep(1000);
            if (rnum == 1){
                ennemiX += 32;
                if (ennemiX > posMax)
                    ennemiX = posMax;
            } else {
                ennemiX -=32;
                if (ennemiX < posMin)
                    ennemiX = posMin;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(java.awt.Graphics2D g) {
        g.setColor(hpcolor);
        g.setFont(hpfont);

        g.drawString("HP :"+hp, ennemiX, ennemiY);
        g.drawImage(posennemi[postexture], ennemiX, ennemiY, null);
    }

    public void keyPressed(int k) throws IOException {

    }
    public void keyReleased(int k) {

    }
    public int getHp(){
        return hp;
    }
    public int getEnnemiX(){
        return ennemiX;
    }
    public int getEnnemiY(){
        return ennemiY;
    }
    public int getDamage(){
        return damage;
    }
    public boolean getDegat(){
        return degat;
    }
    public void setDamage(int damage){ this.damage = damage;}
    public void setDegat(boolean tr){
        degat = tr;
    }
    public void setHp(int hp){
        this.hp = hp;
    }
}
