package GameState;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public class GameStateManager {

    private ArrayList<GameState> gameStates;
    private int currentState;

    public static final int MENUSTATE = 0;
    public static final int SOLOMODE = 1;
    public static final int GAMEOVER = 2;
    public static final int GAMEWIN = 3;
    public static final int MULTIMODE = 4;

    public GameStateManager() {
        gameStates = new ArrayList<GameState>();

        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new SoloMode(this));
        gameStates.add(new GameOver(this));
        gameStates.add(new GameWin(this));
        gameStates.add(new MultiMode(this));
    }

    public void setState(int state) throws IOException {
        currentState = state;
        gameStates.get(currentState).init();
    }

    public void update(){
        gameStates.get(currentState).update();
    }

    public void draw(java.awt.Graphics2D g){
        gameStates.get(currentState).draw(g);
    }

    public void keyPressed(int k) throws IOException {
        if (k == KeyEvent.VK_ESCAPE){
            if (currentState > 0) {
                currentState = 0;
            }
        }
        if (k == KeyEvent.VK_E){
            if (currentState > 0) {
                currentState = 0;
                gameStates.remove(1);
                gameStates.add(1,new SoloMode(this));
            }
        }
        gameStates.get(currentState).keyPressed(k);
    }

    public void keyReleased(int k){
        gameStates.get(currentState).keyReleased(k);
    }

}
