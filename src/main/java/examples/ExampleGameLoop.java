package examples;

import esquisse.Game;
import esquisse.GameLoop;
import esquisse.KeyState;
import esquisse.MouseState;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ExampleGameLoop implements GameLoop {
    
    // Internal Game State Variables
    int circleX = -600;
    int circleY = -600;
    int circleRadius = 20;
    boolean circleFilled = false;

    // Initializing the library.
    Game game = new Game()
            .setTitle("Example Game")
            .setDimensions(600, 600)
            .setFPS(60)
            .makeFrameInvisible()
            .setGameLoop(this)
            .start();
    // These instances are final, so you only need to get them *once*.
    KeyState keyState = game.getKeyState();
    MouseState mouseState = game.getMouseState();

    public static void main(String[] args) {
        // Instantiating the class runs all that constructor code above.
        new ExampleGameLoop();
        // Now we just need to implement our two callbacks to satisfy the
        // requirements of the interface.
    }

    @Override
    public void updateState() {

        // Exit game if Escape key is pressed.
        String key;
        while (!keyState.keyDownEvents.isEmpty()) {
            key = keyState.keyDownEvents.poll();
            if (key == "Escape") {
                game.exit();
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
