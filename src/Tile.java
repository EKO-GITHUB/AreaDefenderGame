import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {

    private final int x;
    private final int y;
    private final int cellSize;
    private Color color;
    private final Rectangle bounds;
    private final int mode;

    public Tile(int cellSize, int x, int y, Color color, int mode) {

        this.color = color;
        this.cellSize = cellSize;
        this.x = x;
        this.y = y;
        this.mode = mode;

        bounds = new Rectangle(x, y, cellSize, cellSize);

        setLayout(null);
        setPreferredSize(new Dimension(cellSize, cellSize));
        setLocation(x, y);
        setOpaque(true);
        setVisible(true);

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, cellSize, cellSize);

        if (mode == AreaDefenderGame.DEBUG_MODE) {
            g.setColor(Color.BLACK);
            g.drawRect(bounds.x, bounds.y, (int) bounds.getWidth(), (int) bounds.getHeight());
        }
    }

    public void changeColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    public Color getColor() {
        return color;
    }
}
