package environment; //this is main class for managing lighting and dark effect

import java.awt.Graphics2D;
import oop.project.gamepanel;

public class EnvironmentManager {
    
    gamepanel gp;
    Lighting lighting;
    
    public EnvironmentManager(gamepanel gp){
        this.gp = gp;
    }
    
    public void setup() { //this is for instantiate lighting class
        
        lighting = new Lighting(gp, 250);
    }
    
    public void draw(Graphics2D g2) {
        
        lighting.draw(g2);
    }
}
