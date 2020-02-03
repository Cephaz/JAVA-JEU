package GameState;

import Entity.Player;
import Entity.Players;
import Entity.ThreadPlayers;
import TileMap.Background;

import java.awt.*;
import java.io.*;

public class MultiMode extends GameState {

    private Background bg;
    private Players perso;
    private ThreadPlayers ennemiplayer;

    //serveur
    private int posen = 10;
    private int posX = 10;
    private int posY = 10;
    private boolean a = true;
    private String limiteenvoyer = "";

    private Client client = new Client("localhost", 1331);

    public MultiMode(GameStateManager gsm){
        this.gsm = gsm;
        perso = new Players();
        ennemiplayer = new ThreadPlayers();
        ennemiplayer.start();
        try {
            bg = new Background("nuage.jpg", 1);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            client.connect();
        } catch (IOException e) {
            a = false;
            e.printStackTrace();
        }
    }
    public void init() {
    }
    public void update() {
        if (a){
            try {
                if (perso != null) {
                    String pos = (String.valueOf(perso.getPersoX()) + String.valueOf(perso.getPersoY()));
                    client.sendMessage(pos);
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public void draw(Graphics2D g) {
        if (client != null){
            if (client.getEnplayer() != null){
                ennemiplayer.setG(g);
                posen = Integer.parseInt(client.getEnplayer());
                ennemiplayer.setX(posen / 100);
                ennemiplayer.setY(posen % 100);
            }
        }
        bg.draw(g);
        perso.draw(g);
    }
    public void keyPressed(int k) {
        perso.keyPressed(k);
    }
    public void keyReleased(int k) {
    }
}
