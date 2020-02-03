package Main;

import javax.swing.*;

public class Game {
    public static void main(String[] args){

        //Notre fenetre graphique
        JFrame window = new JFrame("GAME BETA - PYRDA");

        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
}
