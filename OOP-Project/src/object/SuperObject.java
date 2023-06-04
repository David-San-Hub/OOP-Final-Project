package object; //this is parent class for all objects class

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import oop.project.gamepanel;


public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); //default area collision for objects
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    
    public void draw(Graphics2D g2, gamepanel gp){ //this is for implementing objects in game
        
        int screenX = worldX - gp.player.worldX + gp.player.screenX; //this is screen size
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null); //this is for improving rendering efficiency
        }        
        
    }
}
