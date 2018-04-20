package LanderGame;

import javax.swing.JFrame;

public class Main {
    public static JFrame frame = new JFrame();
    
    public static void main(String[] args) {
        GamePlay game = new GamePlay();
        frame.setBounds(10, 10, 800, 800);
        frame.setTitle("LanderGame");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        game.start();
    }
    
}
