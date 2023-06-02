//for displaying on window
package oop.project;

import Entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import tile.TileManager;

public class gamepanel extends JPanel implements Runnable { //this class enherites JPanel class
    
    //Screen Settings
    final int originalTileSize = 16; //16x16 tile //16 by 16 is default size of player's character, NPCs and map tiles
    final int scale = 3; //scaling to make it fit on screen
    
    public final int tileSize = originalTileSize * scale; //48x48 tile (actual size)
    public final int maxScreenCol = 18; //(length)
    public final int maxScreenRow = 14; //(height)
    public final int screenWidth = tileSize * maxScreenCol; //864 pixels
    public final int screenHeight = tileSize * maxScreenRow; //672 pixels
    
    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
        
    
    //FPS
    int FPS = 60;
    
    TileManager tileM = new TileManager(this);
    keyhandler keyH = new keyhandler();
    Thread gamethread; //to start and stop once thread starts
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);
    
    
    public gamepanel() {
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //set the size of the class
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //set true so all the drawing from this component will be done in an offscreen painting buffer //also enabling it improve game rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true); //use for focusing GamePanel to recieve key input
    }

    public void startgamethread(){
        
        gamethread = new Thread(this); //pass "this" to thread constructor (passing gamepanel)
        gamethread.start(); //automatically call run method vvv
    }
    
    @Override
    public void run() { //object implementing interface Runnable used to create a thread
        
        double drawInterval = 1000000000/FPS; // 0.0166* seconds //this is made for adjusting the fps with time ingame
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        
        while (gamethread != null) { //meant for repeating process that happens in here
            
            //how it works is that every loop player's add the past time divided by drawInterval to reach current drawInterval
            currentTime = System.nanoTime(); //to check current time
            
            delta += (currentTime - lastTime) / drawInterval; //to tell how much time has passed
            
            lastTime = currentTime;
            
            if(delta >= 1) {
                //1 Update: update information such as character positions
                update();
            
                //2 Draw: draw the screen with the updated information
                repaint();
                delta--; //reset delta
            }            
        }
    }
    public void update(){
        
        player.update();
        
    }
    public void paintComponent(Graphics g){ //Graphics: class functions to draw objects on the screen
        
        super.paintComponent(g); //super meant for parent class of Jpanel
        
        Graphics2D g2 = (Graphics2D)g; //convert Graphics into Graphics2D with more functions
        
        tileM.draw(g2); //call draw inside TileManager //put tiles first so it works as a background 
        
        player.draw(g2); //call draw inside Player
        
        g2.dispose(); //Dispose of this graphics context and release any system resources that it is using
    }
}
