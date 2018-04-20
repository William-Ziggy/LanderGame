package LanderGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class KeyInput extends KeyAdapter{
    
    GamePlay game;
    
    public KeyInput(GamePlay game){
        this.game = game;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        game.keyPressed(e);
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        game.keyReleased(e);
    }
}
