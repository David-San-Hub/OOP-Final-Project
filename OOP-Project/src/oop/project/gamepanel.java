//for displaying on window
package oop.project;

import Entity.Player;
import environment.EnvironmentManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import object.SuperObject;
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
    public final int maxWorldCol = 62;
    public final int maxWorldRow = 62; //this is world size
        
    
    //FPS
    int FPS = 60;
    
    //SYSTEM
    TileManager tileM = new TileManager(this);
    keyhandler keyH = new keyhandler();
    Sound music = new Sound();
    Sound se = new Sound();
    Thread gamethread; //to start and stop once thread starts
    public CollisionChecker cChecker = new CollisionChecker(this); //for checking collision
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    EnvironmentManager eManager = new EnvironmentManager(this);
    Thread gameThread;
    
    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10]; //for preparing slots for objects, so whenever you pick up an object it dissapears and spawned another// this use so game won't lag
    
    
    
    public gamepanel() {
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //set the size of the class
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //set true so all the drawing from this component will be done in an offscreen painting buffer //also enabling it improve game rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true); //use for focusing GamePanel to recieve key input
    }
    
    public void setupGame(){ //for setting up method objects
        
        aSetter.setobject();
        eManager.setup();
        playMusic(0); //this is for playing music theme when setup game
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
        
        //TILE
        tileM.draw(g2); //call draw inside TileManager //put tiles first so it works as a background 
        
        //OBJECT
        for(int i = 0; i < obj.length; i++) { //scan object array //to check if the slot is not empty to avoid NullPointer error
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }
        
        //PLAYER
        player.draw(g2); //call draw inside Player
        
        //ENVIRONMENT
        eManager.draw(g2);
        
        //UI
        ui.draw(g2);
        
        g2.dispose(); //Dispose of this graphics context and release any system resources that it is using
    }
    
    public void playMusic(int i) { //this is for playing music theme that can loop in game 
        
        music.setFile(i); 
        music.play();
        music.loop();
    }
    
    public void stopMusic() { //this is for stopping music in game
        
        music.stop();
    }
    
    public void playSE(int i) { //this plays sound effect in game
        
        se.setFile(i);
        se.play();
    }
}
