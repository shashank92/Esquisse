# Esquisse
Easy-to-use Java 2D game library made using AWT+Swing and java.util.concurrent

Making games should be as simple as writing a few callback functions. This library is designed to help people do just that.

Check out ExampleGame.java in examples to see a barebones program that handles mouse and key input, and has the update thread communicate with the draw thread through internal state variables.

updateState is invoked directly by the gameLoopInvoker Runnable. paintFrame is called by the GameCanvas, an internal class that is indirectly called by AWT's GUI rendering thread. The point is that you get passed a Graphics2D instance which you can freely draw to the game window with.
