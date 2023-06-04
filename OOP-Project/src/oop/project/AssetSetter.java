package oop.project; //this class is for placing objects on the map

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Stamina;


public class AssetSetter { 
    
    gamepanel gp;
    public AssetSetter(gamepanel gp) {
        this.gp = gp;
    }
    
    public void setobject(){
        
        //first key object
        gp.obj[0] = new OBJ_Key(); //subclass for SuperObject class that can be instantiate
        gp.obj[0].worldX = 50 * gp.tileSize; //placement on x axis
        gp.obj[0].worldY = 52 * gp.tileSize; //placement on Y axis
        
        //second key object
        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 7 * gp.tileSize; //placement on x axis
        gp.obj[1].worldY = 24 * gp.tileSize; //placement on Y axis
        
        //first door object
        gp.obj[2] = new OBJ_Door();
        gp.obj[2].worldX = 11 * gp.tileSize; //placement on x axis
        gp.obj[2].worldY = 9 * gp.tileSize; //placement on Y axis
        
        //second door object
        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 40 * gp.tileSize; //placement on x axis
        gp.obj[3].worldY = 7 * gp.tileSize; //placement on Y axis
        
        //first chest object
        gp.obj[4] = new OBJ_Chest();
        gp.obj[4].worldX = 8 * gp.tileSize; //placement on x axis
        gp.obj[4].worldY = 9 * gp.tileSize; //placement on Y axis
        
        //first stamina object
        gp.obj[5] = new OBJ_Stamina();
        gp.obj[5].worldX = 21 * gp.tileSize; //placement on x axis
        gp.obj[5].worldY = 50 * gp.tileSize; //placement on Y axis
    }
}
