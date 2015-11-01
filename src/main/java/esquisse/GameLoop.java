package esquisse;

import java.awt.Graphics2D;

//Public API
public interface GameLoop {
    // External callbacks
    // What to do when the state needs updating?
    public void updateState();

    // What to do when a frame needs to be painted to the window?
    public void paintFrame(Graphics2D g);

    // Do you want the game to exit? Return true if so, false otherwise.
    public boolean exitGame();
}