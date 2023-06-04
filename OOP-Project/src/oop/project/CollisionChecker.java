package oop.project;//class for checking collision

import Entity.entity;

public class CollisionChecker {
    
    gamepanel gp;
    
    public CollisionChecker(gamepanel gp) {
        this.gp = gp;
    }
    
    public void checkTile(entity entity) { //check any tile collision
        
        int entityLeftWorldX = entity.worldX + entity.solidArea.x; //these all are for character's collision position /to know their coloumn and row number
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        
        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;
        
        int tileNum1, tileNum2;
        
        switch(entity.direction) {  //player hit collision it detects which one it hits
        case "up": //for top only
            entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize; //predict where player will be after hitting collision
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            }
            break;
        case "down": //for bottom only
            entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize; //predict where player will be after hitting collision
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            }    
            break;
        case "left": //for left only
            entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize; //predict where player will be after hitting collision
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            } 
            break;
        case "right": //for right only
            entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize; //predict where player will be after hitting collision
            tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            } 
            break;
        }
    }
    public int checkObject(entity entity, boolean player) { //check any object collision
        
        int index = 999; //checking if player hitting object //we use 999 so it won't be used by other object array index
        
        for(int i = 0; i <gp.obj.length; i++) {
            if(gp.obj[i] != null){
                
                //get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                
                //get the object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
                
                switch(entity.direction) { //simulating entity's movement and check where it will be after it moved
                    case "up":
                        entity.solidArea.y -= entity.speed; 
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) { //check if two rectangles are colliding
                            if(gp.obj[i].collision == true) { //check if object solid
                                entity.collisionOn = true;
                            }
                            if(player == true) { //if player collision solid with object
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) { //check if two rectangles are colliding
                            if(gp.obj[i].collision == true) { //check if object solid
                                entity.collisionOn = true;
                            }
                            if(player == true) { //if player collision solid with object
                                index = i;
                            }                            
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) { //check if two rectangles are colliding
                            if(gp.obj[i].collision == true) { //check if object solid
                                entity.collisionOn = true;
                            }
                            if(player == true) { //if player collision solid with object
                                index = i;
                            }                            
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) { //check if two rectangles are colliding
                            if(gp.obj[i].collision == true) { //check if object solid
                                entity.collisionOn = true;
                            }
                            if(player == true) { //if player collision solid with object
                                index = i;
                            }                            
                        }                        
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX; //this is for resetting entity and object's solidArea so x and y keep
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
                
            }
        }
        
        return index; //return object
    }
}
