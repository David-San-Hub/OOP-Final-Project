package oop.project;//class for checking collision

import Entity.entity;

public class CollisionChecker {
    
    gamepanel gp;
    
    public CollisionChecker(gamepanel gp) {
        this.gp = gp;
    }
    
    public void checkTile(entity entity) { //check any asset's collision
        
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
}
