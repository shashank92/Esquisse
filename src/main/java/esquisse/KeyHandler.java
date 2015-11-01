package esquisse;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

class KeyHandler implements KeyListener {
    
    KeyState keyState = new KeyState();

    @Override
    public void keyPressed(KeyEvent e) {
        String key = KeyEvent.getKeyText(e.getKeyCode());
        if (!keyState.pressedKeys.contains(key)) {
            keyState.pressedKeys.add(key);
            keyState.keyDownEvents.add(key);
            // System.out.println("Key Pressed Event: " + key);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String key = KeyEvent.getKeyText(e.getKeyCode());
        keyState.pressedKeys.remove(key);
        keyState.keyUpEvents.add(key);
        // System.out.println("Key Released Event: " + key);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}