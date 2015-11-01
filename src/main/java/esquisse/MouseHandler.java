package esquisse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

class MouseHandler implements MouseListener, MouseMotionListener {

    MouseState mouseState = new MouseState();

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseState.mouseMotions.add(e);
        // System.out.println(e.getX()+","+e.getY());
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseState.mouseMotions.add(e);
        // System.out.println(e.getX()+","+e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseState.pressedButtons.add(e);
        mouseState.buttonsHeldDown.add(e.getButton());
        // System.out.println(e.getX()+","+e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseState.pressedButtons.remove(e);
        mouseState.buttonsHeldDown.remove(e.getButton());
        // System.out.println(e.getX()+","+e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}