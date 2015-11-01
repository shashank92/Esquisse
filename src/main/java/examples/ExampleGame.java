package examples;

import esquisse.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ExampleGame implements GameLoop {
    Game game = new Game().setTitle("Example Game")
            .setDimensions(600, 600)
            .setFPS(60)
            .makeFrameInvisible()
            .setGameLoop(this)
            .start();
    // These instances are final, so you only need to get them *once*.
    KeyState keyState = game.getKeyState();
    MouseState mouseState = game.getMouseState();
    // You're probably going to want this.
    boolean exitGameFlag = false;
    
    // Internal Game State Variables
    int circleX;
    int circleY;
    int circleRadius = 20;
    boolean circleFilled = false;


    public static void main(String[] args) {
        // Instantiating the class runs all that constructor code above.
        new ExampleGame();
        // Now we just need to implement our three callbacks to satisfy the 
        // requirements of the interface.
        // (It's basically two since one is so easy.) 
    }
    
    @Override
    public boolean exitGame() {
        // This one is easy!
        return exitGameFlag;
    }

    @Override
    public void updateState() {
        
        // Exit game if Escape key is pressed.
        String key;
        while (!keyState.keyDownEvents.isEmpty()) {
            key = keyState.keyDownEvents.poll();
            if (key == "Escape") {
                // Causes the callback to return true as well, 
                // so Game can respond to it.
                exitGameFlag = true;
            }
        }
        // Capture mouse position and use it to change game state.
        int r = circleRadius;
        MouseEvent e;
        while (!mouseState.mouseMotions.isEmpty()) {
            e = mouseState.mouseMotions.poll();
            circleX = e.getX() - r;
            circleY = e.getY() - r;
        }
        circleFilled = mouseState.buttonsHeldDown.contains(1);
    }

    @Override
    public void paintFrame(Graphics2D g) {
        // Fill background
        g.setColor(new Color(0x0B486B));
        g.fillRect(0, 0, 600, 600);
        // Circle examples
        int r = circleRadius;
        // Fill circle around mouse
        g.setColor(new Color(0xCFF09E));
        g.fillOval(circleX, circleY, r * 2, r * 2);
        // Effect if mouse button is pressed
        if (circleFilled) {
            g.setStroke(new BasicStroke(5));
            g.drawOval(circleX, circleY, r * 2, r * 2);
            g.setColor(new Color(0x0B486B));
            g.fillOval(circleX, circleY, r * 2, r * 2);
        }
    }

}
