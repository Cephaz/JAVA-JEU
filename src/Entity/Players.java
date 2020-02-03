package Entity;

import GameState.GameState;
import TileMap.ImageLoader;
import TileMap.SpriteSheet;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Players extends GameState implements Serializable{
    private transient BufferedImage perso;

    private boolean transformation = false;
    private int persoX = 32;
    private int persoY = 32;

    private transient SpriteSheet sheet;

    private int widthpersomin = 0;
    private int heightpersomin = 0;
    private int widthpersomax = 32;
    private int heightpersomax = 32;

    private Color hpcolor;
    private Font hpfont;
    private int hp = 10;
    private int damage = 2;

    private Graphics2D g;
    public Players(){
        hpcolor = new Color(0, 11, 128);
        hpfont = new Font("Century Gothic", Font.PLAIN, 10);
        perso = ImageLoader.loadImage("fat.png");
        sheet = new SpriteSheet(perso);

        init();
    }
    public void setG(Graphics2D g){
        this.g = g;
    }
    public void init() {

    }
    public void update() {
    }
    public void draw(java.awt.Graphics2D g) {
        g.setColor(hpcolor);
        g.setFont(hpfont);
        g.drawString("HP :"+hp, persoX, persoY);
        g.drawImage(sheet.crop(widthpersomin,heightpersomin,widthpersomax,heightpersomax), persoX, persoY, null);
    }
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_K){
            widthpersomin += 32;
            if (!transformation)
                if (160 < widthpersomin)
                    widthpersomin = 0;
            if(transformation){
                if(353 < widthpersomin) {
                    widthpersomin = 192;
                }
            }
        }
        if (k == KeyEvent.VK_O) {
            if (!transformation){
                transformation = true;
                hp--;
            }
            widthpersomin = 192;
        }
        if (k == KeyEvent.VK_L){
            transformation = false;
            widthpersomin = 0;
        }
        if (k == KeyEvent.VK_Z) {
            heightpersomin = 96;
            fantome("top");
        }
        if(k == KeyEvent.VK_S){
            heightpersomin = 0;
            fantome("bottom");
        }
        if(k == KeyEvent.VK_Q){
            heightpersomin = 32;
            fantome("left");
        }
        if(k == KeyEvent.VK_D){
            heightpersomin = 64;
            fantome("right");
        }
    }
    public void fantome(String move){
        if(transformation) {
            if (move.equals("left")){
                persoX -= 32;
                if (persoX < 0)
                    persoX = 0;
            }
            if (move.equals("right")){
                persoX+=32;
                if (persoX > 32 * 14)
                    persoX = 32 * 14;
            }
            if (move.equals("bottom")){
                persoY+=32;
                if (persoY > 288)
                    persoY = 288;
            }
            if (move.equals("top")){
                persoY-=32;
                if (persoY < 0)
                    persoY = 0;
            }
            if (!Blocks.getBlockss().get(persoY / 32).get((persoX / 32)).getMur()) {
                widthpersomin -= 192;
                transformation = false;
            }
        } else {
            if (move.equals("left"))
                if (Blocks.getBlockss().get(persoY/32).get(persoX/32) != null && persoX/32 - 1 >= 0)
                    if(!Blocks.getBlockss().get(persoY/32).get((persoX/32)-1).getMur())
                        persoX-=32;
            if (move.equals("right")){
                if (Blocks.getBlockss().get(persoY / 32).get(persoX / 32) != null && persoX / 32 + 1 <= 14)
                    if (!Blocks.getBlockss().get(persoY / 32).get((persoX / 32) + 1).getMur())
                        persoX += 32;}
            if (move.equals("bottom")){
                if (Blocks.getBlockss().get(persoY / 32).get(persoX / 32) != null && persoY / 32 + 1 <= 9)
                    if (!Blocks.getBlockss().get((persoY / 32) + 1).get(persoX / 32).getMur())
                        persoY += 32; }
            if (move.equals("top")){
                if (Blocks.getBlockss().get(persoY / 32).get(persoX / 32) != null && persoY / 32 - 1 >= 0)
                    if (!Blocks.getBlockss().get((persoY / 32) - 1).get(persoX / 32).getMur())
                        persoY -= 32; }
        }
        if (persoX < 0)
            persoX = 0;
    }
    public void keyReleased(int k) {}
    public int getHp(){
        return hp;
    }
    public void attack(Ennemi ennemi){

    }
    public void setHp(int hp){
        this.hp = hp;
    }
    public int getPersoX(){
        return persoX;
    }
    public int getPersoY(){
        return persoY;
    }
    public void setPersoX(int x){
        persoX = x;
    }
    public void setPersoY(int y){
        persoY = y;
    }
}
