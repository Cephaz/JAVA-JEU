package Entity;

import java.awt.*;

public class ThreadPlayers extends Thread{

    Players newEnnemi = new Players();
    private int x = 10;
    private int y = 10;
    private Graphics2D g;

    public void ThreadPlayers(){
    }
    public void run(){
        try {
            while(true) {
                if (newEnnemi != null){
                    if (g != null){
                        newEnnemi.setPersoX(x);
                        newEnnemi.setPersoY(y);
                        newEnnemi.draw(g);
                    } else {
                        sleep(300);
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setG(Graphics2D g){ this.g = g;}
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y =y;}
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
