package Entity;

import GameState.GameState;
import TileMap.ImageLoader;
import TileMap.SpriteSheet;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Player extends GameState {
    private BufferedImage perso;

    private boolean transformation = false;
    private boolean play = false;
    private int persoX = 32;
    private int persoY = 32;

    private SpriteSheet sheet;

    private int widthpersomin = 0;
    private int heightpersomin = 0;
    private int widthpersomax = 32;
    private int heightpersomax = 32;

    private Color hpcolor;
    private Font hpfont;
    private int hp = 10;
    private int damage = 2;

    // fireball
    private int positionperso = 3; // 1gauche 2haut 3bas 4 droite

    private boolean test = true;
    private boolean feu = false;
    private ThreadFire firee;

    private Ennemi ennemi;
    public Player(){
        hpcolor = new Color(0, 11, 128);
        hpfont = new Font("Century Gothic", Font.PLAIN, 10);
        perso = ImageLoader.loadImage("fat.png");
        sheet = new SpriteSheet(perso);

        firee = new ThreadFire();
        firee.start();
        init();
    }
    public void init() {

    }
    public void update() {
        firee.setPos(positionperso);
        firee.setX(persoX);
        firee.setY(persoY);
    }
    public void setEnnemi(Ennemi ennemi) {
        this.ennemi = ennemi;
    }
    public void draw(java.awt.Graphics2D g) {
        g.setColor(hpcolor);
        g.setFont(hpfont);
        g.drawString("HP :"+hp, persoX, persoY);
        g.drawImage(sheet.crop(widthpersomin,heightpersomin,widthpersomax,heightpersomax), persoX, persoY, null);

        if (feu){
            firee.setG(g);
            firee.setFeu(feu);
            feu = false;
        }
    }
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_P){
            feu = true;
            attack(this.ennemi);
        }
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
            positionperso = 2;
            heightpersomin = 96;
            fantome("top");
        }
        if(k == KeyEvent.VK_S){
            positionperso = 3;
            heightpersomin = 0;
            fantome("bottom");
        }
        if(k == KeyEvent.VK_Q){
            positionperso = 1;
            heightpersomin = 32;
            fantome("left");
        }
        if(k == KeyEvent.VK_D){
            positionperso = 4;
            heightpersomin = 64;
            fantome("right");
        }
    }
    public void fantome(String move){
        play = true;

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

        System.out.println(ennemi.getEnnemiY());
        //1GAUCHE 2HAUT 3BAS 4DROITE
        if (positionperso == 1 && perso != null){
            if (persoY == ennemi.getEnnemiY()){
                if (persoX - 96 <= ennemi.getEnnemiX() && persoX >= ennemi.getEnnemiX()){
                    ennemi.setHp(ennemi.getHp()-this.damage);
                }
            }
        }
        if (positionperso == 2 && perso != null){
            if (persoX == ennemi.getEnnemiX()) {
                if (persoY - 96 <= ennemi.getEnnemiY() && persoY >= ennemi.getEnnemiY()) {
                    ennemi.setHp(ennemi.getHp() - this.damage);
                }
            }
        }
        if (positionperso == 3 && perso != null){
            if (persoX == ennemi.getEnnemiX()) {
                if (persoY + 96 >= ennemi.getEnnemiY() && persoY <= ennemi.getEnnemiY()) {
                    ennemi.setHp(ennemi.getHp() - this.damage);
                }
            }
        }
        if (positionperso == 4 && perso != null){
            if (persoY == ennemi.getEnnemiY()){
                if (persoX + 96 >= ennemi.getEnnemiX() && persoX <= ennemi.getEnnemiX()){
                    ennemi.setHp(ennemi.getHp()-this.damage);
                }
            }
        }
    }
    public void verifie(Ennemi ennemi){
        this.ennemi = ennemi;
        if (ennemi.getEnnemiX() == persoX && ennemi.getEnnemiY() == persoY && ennemi.getDegat()){
            this.hp -= ennemi.getDamage();
        }
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
}
