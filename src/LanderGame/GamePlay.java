package LanderGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GamePlay extends JPanel implements Runnable{
    
    private boolean running=false;
    private Thread thread;

    private BufferedImage spriteSheet = null;
    private BufferedImage space = null;
    private ArrayList<Background> b;
    
    
    private boolean keyLeft, keyRight, keyUp, keyDown;
    
    
    public void init(){
        addKeyListener(new KeyInput(this));
        setFocusable(true);
        
        BufferedImageLoader loader = new BufferedImageLoader();
        
        try{ //Alla bilder.
            spriteSheet = loader.loadImage("/sprite_sheet.png"); 
            space = loader.loadImage("/space.png");
        }catch(IOException e){
            e.printStackTrace();
        }
        
        b = new ArrayList<Background>();
        //Generera de fyra backgrundsbilderna
        
        
        for(int i=0; i<=1; i++){
            b.add(new Background(i*800, 0, 0, this));
            b.add(new Background(i*800, 800, 1, this));
        }
        
        
    }
    
    
    public synchronized void start(){
        if(running)
            return;
        
        running=true;
        thread = new Thread(this, "Game");
        thread.start();
    }
    
    private synchronized void stop(){
        if(!running)
            return;
        running=false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1); 
    }
    
    
    public void run(){ //Gameloop
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta>=1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("Ticks: "+updates+" FPS: "+frames);
                updates=0;
                frames=0;
            }
        }
        stop();
    }
    
    private void tick(){
        for(Background i : b){
            i.tick();
        }
        
        for(Background i : b){
            if(!i.getOccupiedX()){
                if(i.getX()>=0&&keyLeft){
                    i.setX(i.getX()-1600);
                }else if(i.getX()<0&&keyRight){
                    i.setX(i.getX()+1600);
                }
            }
            if(!i.getOccupiedY()&&i.getType()==1){
                if(i.getY()>=0&&keyDown){
                    i.setY(i.getY()-1600);
                }else if(i.getY()<0&&keyUp){
                    i.setY(i.getY()+1600);
                }
            }
        }
        
        
        
        int speed = 10;
        
        if(keyLeft){
            for(Background i : b){
                i.setX(i.getX()+speed);
            }
        }else if(keyRight){
            for(Background i : b){
                i.setX(i.getX()-speed);
            }
        }
        if(keyDown){
            for(Background i : b){
                i.setY(i.getY()+speed);
            }
        }else if(keyUp){
            for(Background i : b){
                i.setY(i.getY()-speed);
            }
        }
        
        //Testa om de är upptagna
        
        
    }
    
    private void render(){
        BufferStrategy bs = Main.frame.getBufferStrategy();
        
        if(bs == null){
            Main.frame.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        //////
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 800, 800);
        for(Background i : b){
            i.render(g);
        }
        //////
        g.dispose();
        bs.show();
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_RIGHT){
            keyRight = true;
        }else if(key == KeyEvent.VK_LEFT){
            keyLeft = true;
        }
        
        if(key == KeyEvent.VK_DOWN){
            keyUp = true;
        }else if(key == KeyEvent.VK_UP){
            keyDown = true;
        }
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_RIGHT){
            keyRight = false;
        }else if(key == KeyEvent.VK_LEFT){
            keyLeft = false;
        }
        
        if(key == KeyEvent.VK_DOWN){
            keyUp = false;
        }else if(key == KeyEvent.VK_UP){
            keyDown = false;
        }
        
    }
    
    public BufferedImage getSpriteSheet(){
        return spriteSheet;
    }
    
    public BufferedImage getSpaceImage(){
        return space;
    }
    
}
