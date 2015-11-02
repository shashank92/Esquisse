package esquisse;

import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class MouseState {
    
    public final Queue<MouseEvent> mouseMotions = new LinkedList<>();
    
    public final Queue<MouseEvent> pressedButtons = new LinkedList<>();
    
    public final Queue<MouseEvent> releasedButtons = new LinkedList<>();
    
    public final Set<Integer> buttonsHeldDown = new TreeSet<>();
    
}
