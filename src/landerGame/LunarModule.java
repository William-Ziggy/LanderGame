package landerGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class LunarModule {

	private int x;
	private int y;
	private int width;
	private int height;
	private double rotation;

	public static BufferedImage lem;
	
	public LunarModule(int x, int y, int width, int height, double rotation, GamePlay game) {
		
		this.width = width;
		this.height = height;
		this.x = x-width/2;
		this.y = y-height/2;
		this.rotation = rotation;
		
		lem = game.getLemImage();
		lem = BufferedImageLoader.resize(lem, width, height);
		
	}
	
	public void render(Graphics g){
        g.drawImage(lem, x, y, null);
    }
	
	public void tick() {
		
	}
	
	
	
}
