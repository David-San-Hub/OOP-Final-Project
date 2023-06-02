package Entity; //This package stores variables that will be used in player, enemy, and etc.

import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class entity {
    
    public int worldX, worldY; //x and y direction for the world map
    public int speed; //speed
    public BufferedImage upstand, up1, up2, downstand, down1, down2, leftstand, left1, left2, rightstand, right1, right2; //it describes an Image with an accessible buffer of image data
    public String direction;
    public int spriteCounter = 0; //how to make them move (uses more pictures)
    public int spriteNum = 1;
    public Rectangle solidArea;//this is for player's collision
    public boolean collisionOn = false;
    
    
}
