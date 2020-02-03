package Entity;

import java.awt.*;

public class ThreadFire extends Thread {
    private Fireball fireball;

    private int pos;
    private int x;
    private int y;
    private Graphics2D g;
    private boolean feu = false;

    public ThreadFire (){
        fireball = new Fireball();
    }
    public void run(){
        try {
            while(true){
                if (feu){
                    for(int i = 0; i < 15000; i++){
                        fireball.draw(g,pos,x,y);
                    }
                    feu = false;
                }
                sleep(500);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setFeu(Boolean fe){this.feu = fe;}
    public void setG(Graphics2D g){ this.g = g;}
    public void setPos(int pos){this.pos = pos;}
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y =y;}
    public Fireball getFireball(){
        return fireball;
    }
}