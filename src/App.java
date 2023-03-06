import javax.swing.*;
import java.awt.*;

public class App {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        AreaDefenderGame game = new AreaDefenderGame(12, AreaDefenderGame.NORMAL_MODE);

        frame.setTitle("AreaDefender");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(game);
        frame.getContentPane().setPreferredSize((new Dimension(AreaDefenderGame.BOARDSIZE_PIXEL,AreaDefenderGame.BOARDSIZE_PIXEL)));
        frame.pack();
    }
}
