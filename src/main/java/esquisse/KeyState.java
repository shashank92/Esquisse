package esquisse;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class KeyState {
    
    public final Queue<String> keyUpEvents = new LinkedList<>();
    
    public final Queue<String> keyDownEvents = new LinkedList<>();
    
    public final Set<String> pressedKeys = new TreeSet<>();
    
}
