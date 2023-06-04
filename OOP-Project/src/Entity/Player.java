package Entity; //this is player class

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import oop.project.gamepanel;
import oop.project.keyhandler;

public class Player extends entity { //this is for setting up the player 
    
    gamepanel gp;
    keyhandler keyH;
    
    public final int screenX;
    public final int screenY;
    
    public int hasKey = 0; //indicates how many keys player has
    
    public Player(gamepanel gp, keyhandler keyH) {
        
        this.gp = gp;
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        solidArea = new Rectangle(); //collision for player
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x; //this is default collision
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        
        setDefaultValues(); //for calling methods
        getPlayerImage();
        
    }
    public void setDefaultValues() {
        
        worldX = gp.tileSize * 31; //player position in world map X
        worldY = gp.tileSize * 31; //player position in world map Y
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage() {
        
        try { //trying to load imgaes in player source packages
            
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));
            
            
        }catch(IOException e) {
            e.printStackTrace ();
        }
    }
    
    public void update(){
        
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true ) {
            
            if(keyH.upPressed == true) { //for moving character and adjust speed
                direction = "up";
            }
            else if(keyH.downPressed == true) {
                direction = "down";
            }
            else if(keyH.leftPressed == true) {
                direction = "left";
            }
            else if(keyH.rightPressed == true) {
                direction = "right";
            }
            
            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this); //accepts player as entity and to check player collision
            
            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
        
            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false){
                
                switch(direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
           
            spriteCounter++; //this gets called 1 frame per second and then changes which creates an animation 
                if(spriteCounter > 14) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        
    }
    public void pickUpObject(int i) {
        
        if(i != 999) {
            
            String objectName = gp.obj[i].name;
            
            switch(objectName) {
            case "Key":
                gp.playSE(1); //play pickup key sound effect
                hasKey++;
                gp.obj[i] = null; //make the key dissapear and player pick it up
                gp.ui.showMessage("YOU GOT A KEY!"); //text come out when pickup key
                break;
            case "Door": //making door disappear when interact with key
                if(hasKey > 0) {
                    gp.playSE(3); //play door opening sound effect
                    gp.obj[i] = null; //if has key then door disappear
                    hasKey--;
                    gp.ui.showMessage("YOU OPEN THE DOOR!");
                }
                else {
                    gp.ui.showMessage("YOU NEED A KEY!");
                }
                System.out.println("Key:"+hasKey);
                break;
            case "Stamina": //increases movement speed
                gp.playSE(2); //play stamina power up sound effect
                speed += 1;
                gp.obj[i] = null;
                gp.ui.showMessage("YOU GET SPEED BOOST!");
                break;
            case "Chest": //when interact chest the game finish
                gp.ui.gameFinished = true;
                gp.stopMusic();
                gp.playSE(4);
                break;
            }
        }
    }
        
    public void draw(Graphics2D g2) { //to visualize/draw character movement
        
        BufferedImage image = null;
        
        switch(direction) {
        case "up":
            if (spriteNum == 1) {
                image = up1;
            }
            if (spriteNum == 2) {
                image = up2;
            }
            break;
        case "down":
            if (spriteNum == 1) {
                image = down1;
            }
            if (spriteNum == 2) {
                image = down2;
            }
            break;
        case "left":
            if (spriteNum == 1) {
                image = left1;
            }
            if (spriteNum == 2) {
                image = left2;
            }
            break;
        case "right":
            if (spriteNum == 1) {
                image = right1;
            }
            if (spriteNum == 2) {
                image = right2;
            }
            break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);  //draw an image on the screen
    }
}
