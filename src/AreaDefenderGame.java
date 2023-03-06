import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AreaDefenderGame extends JPanel implements ActionListener {

    public static int NORMAL_MODE = 1;
    public static int DEBUG_MODE = 2;
    public static int BOARDSIZE_PIXEL = 1000;

    private final int cellSize;
    private final int boardSize;
    private final Timer timer;
    private final int mode;
    private Tile[][] grid;
    private ArrayList<Player> players;


    public AreaDefenderGame(int boardSize, int mode) {
        super();
        setPreferredSize(new Dimension(BOARDSIZE_PIXEL, BOARDSIZE_PIXEL));
        setBackground(Color.BLACK);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        this.cellSize = BOARDSIZE_PIXEL / boardSize;
        this.boardSize = boardSize;
        this.mode = mode;

        initGrid();
        initPlayers();

        timer = new Timer(1, this);
        timer.start();

    }

    private void initGrid() {
        grid = new Tile[boardSize][boardSize];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = new Tile(cellSize, row * cellSize, col * cellSize, Color.WHITE, mode);
                add(grid[row][col]);
            }
        }
    }

    private void initPlayers() {
        players = new ArrayList<Player>();

        players.add(new Player(12, 12, Color.decode("#FF6F91"), Color.decode("#FF9671"), mode));
        players.add(new Player(120, 120, Color.decode("#8E4865"), Color.decode("#7B3C3C"), mode));
        players.add(new Player(240, 240, Color.decode("#58E151"), Color.decode("#00C67C"), mode));
        players.add(new Player(360, 360, Color.decode("#45631B"), Color.decode("#216236"), mode));
        players.add(new Player(480, 480, Color.decode("#00A0A5"), Color.decode("#00ACD1"), mode));
        players.add(new Player(600, 600, Color.decode("#FF0000"), Color.decode("#EE005F"), mode));
        players.add(new Player(720, 720, Color.decode("#4A62FF"), Color.decode("#0082FF"), mode));
        players.add(new Player(720, 50, Color.decode("#E8A392"), Color.decode("#FFE6D9"), mode));

        for (Player player : players) {
            add(player);
        }

    }

    public void paint(Graphics g) {
        super.paint(g);
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col].paint(g);
            }
        }
        for (Player player : players) {
            player.paint(g);
        }
    }

    public void update() {
        movePlayers();
        checkCollision();
        repaint();
    }

    private void movePlayers() {
        for (Player player : players) {
            player.move();
        }
    }

    public void checkCollision() {
        for (Player player : players) {
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[row].length; col++) {
                    if (grid[row][col].getBounds().intersects(player.getBounds()) && !grid[row][col].getColor().equals(player.getColor())) {
                        grid[row][col].changeColor(player.getColor());
                        player.reverse(grid[row][col].getBounds());
                    }
                }
            }
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }
}
