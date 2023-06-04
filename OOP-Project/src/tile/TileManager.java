package tile; //tile class that manages the tile placements

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import oop.project.gamepanel;

public class TileManager {
    
    gamepanel gp;
    public Tile[] tile;
    public int mapTileNum[][]; //maptiles
    
    public TileManager(gamepanel gp) {
        
        this.gp = gp;
        
        tile = new Tile[10]; //the amount of tiles to display
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; 
        
        
        getTileImage();
        loadMap("/maps/world01.txt"); //for the map file assets path
    }
    
    public void getTileImage() { //load tile images in this method
        
        try {
            
            tile[0] = new Tile(); //instantiate tile array
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png")); //call tiles
            
            tile[1] = new Tile(); //instantiate tile array
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png")); //call tiles
            tile[1].collision = true;
            
            tile[2] = new Tile(); //instantiate tile array
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png")); //call tiles
            tile[2].collision = true;
            
            tile[3] = new Tile(); //instantiate tile array
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png")); //call tiles
            
            tile[4] = new Tile(); //instantiate tile array
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png")); //call tiles
            tile[4].collision = true;
            
            tile[5] = new Tile(); //instantiate tile array
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png")); //call tiles
            
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadMap(String filePath){ //this loop is for generating/projects the map text file 
        
        try {
            InputStream is = getClass().getResourceAsStream(filePath); 
            BufferedReader br = new BufferedReader (new InputStreamReader(is)); //for reading the context file of map/format
            
            int col = 0;
            int row = 0;
            
            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
                
                String line = br.readLine(); //read a line of a text (can only read on string)
                
                while(col < gp.maxWorldCol) {
                    
                    String numbers[] = line.split(" "); //splits the string around matches of the given regular expression
                    
                    int num = Integer.parseInt(numbers[col]); //col use as an index for numbers[ array //also change string to integer for easy process
                    
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
            
        }catch(Exception e) {
            
        }
        
     
    }
    public void draw(Graphics2D g2) { //draw tiles
        
        int worldCol = 0; //coloumns
        int worldRow = 0; // rows
        
        while(worldCol <gp.maxWorldCol && worldRow < gp.maxWorldRow) { //how to generate tiles
            
            int tileNum = mapTileNum[worldCol][worldRow]; //extract a tile number which is stored in mapTileNum[0][0]
            
            //how to adjust/make camera follow player
            int worldX = worldCol * gp.tileSize; //this is world size
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX; //this is screen size
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null); //this is for improving rendering efficiency
            }
            
            worldCol++;
            
            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
                
            }
        }
    }
    
}
