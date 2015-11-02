# Esquisse
Easy-to-use Java 2d game library made using AWT+Swing and java.util.concurrent

Making 2d games should be as simple as writing a few callback functions to implement a GameLoop interface. This library is designed to help people do just that.

Check out ExampleGamLoop.java in examples to see a barebones program that handles mouse and key input, and has the updater thread communicate with the rendering thread through internal state variables.

updateState is invoked directly by the gameLoopInvoker Runnable. paintFrame is called by the gameCanvas, an internal object that has its own callback that is invoked by AWT's GUI rendering thread. The point of all this is that you get passed a Graphics2D instance which you can easily draw to the game window with.

The example doesn't use Images but Graphics2D definitely has great support for rendering them. You may want to consider implementing or adding Sprite and SpriteSheet classes to handle animation for your games. I will consider writing these classes myself in the future and pushing the updates to Esquisse.

I hope you enjoy programming 2d games with this library.
