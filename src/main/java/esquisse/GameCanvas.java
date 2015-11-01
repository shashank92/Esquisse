package esquisse;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

//Internal API
@SuppressWarnings("serial")
class GameCanvas extends JPanel {
    private GameLoop painter;

    public GameCanvas() {
        setPreferredSize(new Dimension(500, 500));
    }

    public void setDimensions(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

    public void setPainter(GameLoop p) {
        painter = p;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (painter != null)
            painter.paintFrame((Graphics2D) g);
    }
}
