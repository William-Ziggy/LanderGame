package landerGame;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {
    
    public static void main(String[] args) {
        GamePlay game = new GamePlay();
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(800, 800));
        frame.setMinimumSize(new Dimension(800, 800));
        frame.setMaximumSize(new Dimension(800, 800));
        frame.setTitle("LanderGame");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(game);
        frame.pack();
        game.start();
    }
    
}
