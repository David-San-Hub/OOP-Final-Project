package oop.project; //this is class for all UI on screen

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import object.OBJ_Key;


public class UI {
    
    gamepanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    
    double playTime; //set timer
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    
    public UI(gamepanel gp) {
        this.gp = gp;
        
        arial_40 = new Font("Arial", Font.PLAIN, 40);//this is font for the words/sentences
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }
    
    public void showMessage(String text){
        
        message = text;
        messageOn = true;
    }
    
    public void draw(Graphics2D g2) {
        
        if(gameFinished == true) { //this is for game finish sentence pops up
            
            g2.setFont(arial_40); 
            g2.setColor(Color.white); //this is color for the sentence
            
            String text;
            int textLength;
            int x;
            int y;
            
            text = "YOU FOUND THE TREASURE!";
            textLength = (int)g2.getFontMetrics().getStringBounds (text, g2).getWidth(); //make sentence pops up in middle screen
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize * 3);
            g2.drawString(text, x, y); //to draw the sentence
            
            text = "Your Time is:" + dFormat.format(playTime) + "!";
            textLength = (int)g2.getFontMetrics().getStringBounds (text, g2).getWidth(); //make sentence pops up in middle screen
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize * 4);
            g2.drawString(text, x, y); //to draw the sentence
            
            g2.setFont(arial_80B); 
            g2.setColor(Color.YELLOW); //this is color for the sentence
            text = "CONGRATULATIONS!";
            textLength = (int)g2.getFontMetrics().getStringBounds (text, g2).getWidth(); //make sentence pops up in middle screen
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize * 2);
            g2.drawString(text, x, y); //to draw the sentence
            
            gp.gameThread = null; //this is for stopping game
        }
        else {
            //Display the number of keys we have
            g2.setFont(arial_40); 
            g2.setColor(Color.white); //this is color for the sentence
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x "+ gp.player.hasKey, 74, 65);
            
            //TIME
            playTime +=(double)1/60;
            g2.drawString("Time:"+ dFormat.format(playTime), gp.tileSize*13, 65);
        
            //MESSAGE
            if(messageOn == true) { //where to put the sentence
            
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5); 
            
                messageCounter++;
            
                if(messageCounter > 180) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }            
        }
    }
}
