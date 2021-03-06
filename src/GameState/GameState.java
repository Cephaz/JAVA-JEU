package GameState;

import java.io.IOException;

public abstract class GameState {
    protected GameStateManager gsm;

    public abstract void init() throws IOException;
    public abstract void update();
    public abstract void draw(java.awt.Graphics2D g);
    public abstract void keyPressed(int k) throws IOException;
    public abstract void keyReleased(int k);
}
