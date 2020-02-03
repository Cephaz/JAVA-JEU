package GameState;

import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class MenuState extends GameState{

    private Background bg;
    private int currentChoice = 0;
    private String[] options = {
            "Solo",
            "Multiplayer",
            "Exit"
    };
    private Color titleColor;
    private Font titleFont;
    private Font font;

    public MenuState(GameStateManager gsm){
        this.gsm = gsm;
        try {
            bg = new Background("Menubg.gif", 1);
            bg.setVector(-0.1, 0);

            titleColor = new Color(171, 42, 144);
            titleFont = new Font("Century Gothic", Font.PLAIN, 28);
            font = new Font("Arial", Font.PLAIN, 16);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void init() {}
    public void update() {
        bg.update();
    }
    public void draw(Graphics2D g) {
        bg.draw(g);
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("PYRDA", 200, 80);
        g.setFont(font);
        for(int i = 0; i < options.length; i++){
            if(i == currentChoice) {
                g.setColor(new Color(137, 52, 163));
            } else {
                g.setColor(new Color(60, 6, 94));
            }
            g.drawString(options[i], 200,120 + i * 20);
        }
    }
    private void select() throws IOException {
        if(currentChoice == 0){
            gsm.setState(GameStateManager.SOLOMODE);
        }
        if(currentChoice == 1){
            gsm.setState(GameStateManager.MULTIMODE);
            //new ThreadMulti(gsm);
        }
        if(currentChoice == 2){
            System.exit(0);
        }
    }
    public void keyPressed(int k) throws IOException {
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
        if (k == KeyEvent.VK_Z){
            currentChoice--;
            if(currentChoice == -1){
                currentChoice = options.length - 1;
            }
        }
        if (k == KeyEvent.VK_S){
            currentChoice++;
            if (currentChoice == options.length){
                currentChoice = 0;
            }
        }
    }

    public void keyReleased(int k) {}
}
