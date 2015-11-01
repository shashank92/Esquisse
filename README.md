# Esquisse
Easy-to-use Java 2D game library made using AWT+Swing and java.util.concurrent

Making games should be as simple as designing a structure to hold game state and writing a few callback functions. This library is designed to help people do just that.

Check out ExampleGame.java in examples to see a barebones program that handles mouse and key input, and transfers it to a custom state class.

That custom state class is then accessed by the Event Dispatch thread from AWT which will indirectly invoke the paintFrame method. paintFrame is passed a Graphics2D instance which you can freely draw to the game window with.
