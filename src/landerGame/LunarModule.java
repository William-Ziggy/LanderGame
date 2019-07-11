package landerGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class LunarModule {

	private int x;
	private int y;
	private int width;
	private int height;
	private int dx;
	private int dy;
	private double rotSpeed;
	private AffineTransform at;

	public static BufferedImage lem;
	
	public LunarModule(int x, int y, int width, int height, GamePlay game) {
		
		this.width = width;
		this.height = height;
		this.x = x-width/2;
		this.y = y-height/2;
		this.dx = 0;
		this.dy = 0;
		this.rotSpeed = 0;
		this.at = new AffineTransform();
		
		lem = game.getLemImage();
		//lem = BufferedImageLoader.resize(lem, width, height);
		
		//Sätter bilen till mitten av skärmen
		this.at.translate(400-lem.getWidth()/2, 400-lem.getHeight()/2); 
		
		this.at.scale(1, 1); //Sätter skalan till 1:1
		
	}
	
	public void render(Graphics g){
        // draw the image
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(lem, this.at, null);
    }
	
	
	public void tick() {
		this.rotate();
	}
	
	public void rotate() {
		this.at.rotate(rotSpeed, lem.getWidth()/2, lem.getHeight()/2);
		//this.at.translate(-lem.getWidth()/2, -lem.getHeight()/2);
	}
	
	public double getRotSpeed() {
		return this.rotSpeed;
	}
	
	public void setRotSpeed(double rotSpeed) {
		this.rotSpeed = rotSpeed;
	}
	
	public void changeRotSpeed(double change) {
		this.rotSpeed+=change;
	}
	
	
}
