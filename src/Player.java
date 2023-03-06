import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;

public class Player extends JPanel {

    private final int playerSize = 25;
    private final Color playerColor;
    private final Color tileColor;
    private int x;
    private int y;
    private int xDirection = 5;
    private int yDirection = 5;
    private Rectangle bounds;
    private int mode;

    public Player(int x, int y, Color playerColor, Color tileColor, int mode) {

        this.tileColor = tileColor;
        this.playerColor = playerColor;
        this.x = x;
        this.y = y;
        this.mode = mode;

        setLayout(null);
        setPreferredSize(new Dimension(playerSize, playerSize));
        setLocation(x, y);
        setOpaque(true);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(playerColor);
        g.fillOval(x, y, playerSize, playerSize);

        if (mode == AreaDefenderGame.DEBUG_MODE){
            g.drawRect(bounds.x, bounds.y, (int) bounds.getWidth(), (int) bounds.getHeight());
            g.drawLine(x, y, x + xDirection * 25, y + yDirection * 25);
            g.drawString("X POSITION : " + x + " Y POSITION: " + y, x, y + 50);
        }
    }

    public void move() {
        if (x <= 0 || x >= AreaDefenderGame.BOARDSIZE_PIXEL - playerSize) {
            xDirection *= -1;
        }
        if (y <= 0 || y >= AreaDefenderGame.BOARDSIZE_PIXEL - playerSize) {
            yDirection *= -1;
        }
        x = x + xDirection;
        y = y + yDirection;

        bounds = new Rectangle(x, y, playerSize, playerSize);
    }

    public void reverse(Rectangle rectangle) {
        if (xDirection > 0 && yDirection > 0) {
            if (y + playerSize / 2 < rectangle.getY()) {
                reverseY();
            } else {
                reverseX();
            }
        } else if (xDirection < 0 && yDirection > 0) {
            if (y + playerSize / 2 < rectangle.getY()) {
                reverseY();
            } else {
                reverseX();
            }
        } else if (xDirection < 0 && yDirection < 0) {
            if (x + playerSize / 2 > rectangle.getX() + rectangle.getWidth()) {
                reverseX();
            } else {
                reverseY();
            }
        } else if (xDirection > 0 && yDirection < 0) {
            if (x + playerSize / 2 < rectangle.getX()) {
                reverseX();
            } else {
                reverseY();
            }
        }
    }

    private void reverseX() {
        x -= xDirection;
        xDirection *= -1;
    }

    private void reverseY() {
        y -= yDirection;
        yDirection *= -1;
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
        return tileColor;
    }
}