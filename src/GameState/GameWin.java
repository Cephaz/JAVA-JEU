package GameState;

import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GameWin extends GameState{
    private Background bg;
    private int currentChoice = 0;
    private String[] options = {
            "Restart",
            "Exit"
    };
    private Color titleColor;
    private Font titleFont;
    private Font font;

    public GameWin(GameStateManager gsm){
        this.gsm = gsm;
        try {
            bg = new Background("nuage.jpg", 1);
            bg.setVector(-0.1, 0);

            titleColor = new Color(0, 214, 9);
            titleFont = new Font("Century Gothic", Font.PLAIN, 24);
            font = new Font("Arial", Font.PLAIN, 12);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void init() throws IOException {

    }

    @Override
    public void update() {

    }
    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("VOUS AVEZ TERMINER LE NIVEAU", 30, 120);
        g.setFont(font);
        for(int i = 0; i < options.length; i++){
            if(i == currentChoice) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.darkGray);
            }
            g.drawString(options[i], 200 + i * 50 ,170);
        }
    }

    private void select() throws IOException {
        if(currentChoice == 0){
            this.gsm.keyPressed(69);
        }
        if(currentChoice == 1){
            System.exit(0);
        }
    }
    @Override
    public void keyPressed(int k) throws IOException {
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
        if (k == KeyEvent.VK_Q){
            currentChoice--;
            if(currentChoice == -1){
                currentChoice = options.length - 1;
            }
        }
        if (k == KeyEvent.VK_D){
            currentChoice++;
            if (currentChoice == options.length){
                currentChoice = 0;
            }
        }
    }

    @Override
    public void keyReleased(int k) {

    }
}
