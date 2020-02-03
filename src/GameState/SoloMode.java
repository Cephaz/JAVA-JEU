package GameState;

import Entity.*;
import TileMap.Background;

import java.io.*;
import java.util.ArrayList;

public class SoloMode extends GameState{

    private Player perso;
    private Ennemi ennemi;

    private Attack attacks;
    //creation d'ennemi
    private boolean tenn = true;
    private ThreadMode tennemi;

    private Background bg;

    public SoloMode(GameStateManager gsm) {
        this.gsm = gsm;
        perso = new Player();

        File f = new File("./src/GameState/map1.tx" +
                "");
        try{Blocks.readLines(f);}catch (IOException e){e.printStackTrace();}
        try {
            bg = new Background("nuage.jpg", 1);
            bg.setVector(-0.1, 0);
        } catch (Exception e){
            e.printStackTrace();
        }
        init();
    }
    public void init() {
        if (tenn){
            spawn();
            tennemi.start();
            ennemi = tennemi.getEnnemi();
            tenn = false;
            attacks = new Attack();
            attacks.start();
        }
    }
    public void update() {
        perso.update();
        perso.setEnnemi(ennemi);
        if (ennemi != null && perso != null){
            attacks.verifie(ennemi,perso);
        }
    }
    private void select(int choice) throws IOException {
        if (choice == 0)
            this.gsm.setState(GameStateManager.GAMEOVER);
        if (choice == 1)
            this.gsm.setState(GameStateManager.GAMEWIN);
    }
    public void draw(java.awt.Graphics2D g) {
        bg.draw(g);
        //Chaque case de la map et leur effet.
        for(ArrayList<Blocks> blo: Blocks.getBlockss()){
            for (Blocks bl:blo){
                if (bl.getPortail()){
                    if (perso.getPersoX()/32 == bl.getPosX() && perso.getPersoY()/32 == bl.getPosY()){
                        try { select(1); } catch (Exception e){e.printStackTrace();}
                    }
                }
                if (bl.getPiege()){
                    if (perso.getPersoX()/32 == bl.getPosX() && perso.getPersoY()/32 == bl.getPosY()){
                        try { select(0); } catch (Exception e){e.printStackTrace();}
                    }
                }
                bl.draw(g);
            }
        }
        if (ennemi.getHp() > 0){
            ennemi.draw(g);
        } else  {
            ennemi.setDamage(0);
        }
        try {
            if (perso.getHp() <= 0)
                select(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        perso.draw(g);
    }
    public void keyPressed(int k) {
        perso.keyPressed(k);
    }
    public void keyReleased(int k) {}
    public void spawn(){
        int nbr = RandomNum.usingMathClass(3,0);
        int y = 0; int x = 0;
        if (nbr == 0) {
            x = 12*32;
            y = 8*32;
        }
        if (nbr == 1) {
            x = 8*32;
            y = 8*32;
        }
        if (nbr == 2) {
            x = 10*32;
            y = 1*32;
        }
        tennemi = new ThreadMode(x,y);
    }
}
