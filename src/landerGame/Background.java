package landerGame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Background {
    private int x, y;

    private boolean occupiedX, occupiedY;
    private static int width;
    private static int height;
    public static BufferedImage space;
    
    public Background(int x, int y, GamePlay game) {
        this.x = x;
        this.y = y;
        space = game.getSpaceImage();
        width = space.getWidth();
        height = space.getHeight();
    }
    
    public void render(Graphics g){
        g.drawImage(space, x, y, null);
    }
    
    public void tick(){
        
        occupiedX = (x>-800&&x<800);
        
        occupiedY = (y>-800&&y<800);
        
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
    public boolean getOccupiedX(){
        return occupiedX;
    }
    public boolean getOccupiedY(){
        return occupiedY;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }   
    
}
