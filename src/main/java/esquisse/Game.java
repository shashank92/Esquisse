package esquisse;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class Game {
    // Internal
    private JFrame frame = new JFrame();
    private GameCanvas gameCanvas = new GameCanvas();
    private KeyHandler keyHandler = new KeyHandler();
    private MouseHandler mouseHandler = new MouseHandler();

    // External (access through public methods).
    private int framesPerSecond = 60; // Client
    private GameLoop gameLoop;
    private boolean exitFlag = false;

    // Concurrency structures
    private ScheduledExecutorService looper = 
            Executors.newSingleThreadScheduledExecutor();
    private ExecutorService monitor =
            Executors.newSingleThreadExecutor();
    private ScheduledFuture<?> future;
    // Will be set later in startGameLoop()
    private Runnable gameLoopInvoker = () -> {
        if (exitFlag) {
            //System.out.println("exiting game...");
            frame.dispose();
            looper.shutdown();
            monitor.shutdown();
        }
        gameLoop.updateState();
        gameCanvas.repaint();
    };

    private void initWindow() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(gameCanvas);
        frame.pack();
        // Center JFrame to monitor resolution.
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((d.width - frame.getWidth()) / 2, (d.height - frame.getHeight()) / 2);
        frame.setVisible(true);
        /*
         * For testing purposes frame.addFocusListener(new FocusListener() {
         * 
         * @Override public void focusGained(FocusEvent e) { System.out.println(
         * "framerame gained focus"); }
         * 
         * @Override public void focusLost(FocusEvent e) { System.out.println(
         * "framerame lost focus"); } }); //
         */
    }

    private void startGameLoop() {
        // Final initialization before game loop
        if (gameLoop == null) {
            System.err.println(
                    "Warning: No instance of GameLoop has been set.");
            return;
        }

        // Calculate period (microseconds) between frames.
        // ((μs / sec) / (frames / sec)) -> (μs / frame)
        int period = 1000000 / framesPerSecond;

        // Register Focus Listener to Game Canvas
        gameCanvas.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                future = looper.scheduleAtFixedRate(
                        gameLoopInvoker, 
                        period, period, TimeUnit.MICROSECONDS);
                // Future monitor thread
                monitor.submit(() -> {
                    try {
                        future.get();
                    } catch (CancellationException ex) {
                        // This will happen when looper gets cancelled.
                        // looper will get cancelled when window loses focus.
                    } catch (ExecutionException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
                });
            }

            @Override
            public void focusLost(FocusEvent e) {
                future.cancel(false);
            }
        });

        // Give gameCanvas full focus
        // (Initializes the actual game loop)
        gameCanvas.requestFocusInWindow();
        // Register Input Listeners
        gameCanvas.addKeyListener(keyHandler);
        gameCanvas.addMouseListener(mouseHandler);
        gameCanvas.addMouseMotionListener(mouseHandler);
    }
    
    // Public getter API
    public KeyState getKeyState() {
        return keyHandler.keyState;
    }
    
    public MouseState getMouseState() {
        return mouseHandler.mouseState;
    }

    // Method cascading pattern for public setter API.
    public Game setTitle(String title) {
        frame.setTitle(title);
        return this;
    }

    public Game setDimensions(int width, int height) {
        gameCanvas.setDimensions(width, height);
        return this;
    }

    public Game setFPS(int fps) {
        framesPerSecond = fps;
        return this;
    }
    
    public Game makeFrameInvisible() {
        frame.setUndecorated(true);
        return this;
    }
    
    public Game setGameLoop(GameLoop gl) {
        gameLoop = gl;
        gameCanvas.setPainter(gl);
        return this;
    }

    public Game start() {
        initWindow();
        startGameLoop();
        return this;
    }
    
    public Game exit() {
        exitFlag = true;
        return this;
    }
}