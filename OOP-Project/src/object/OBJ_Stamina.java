package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Stamina extends SuperObject {
    
    public OBJ_Stamina() {
        
    name = "Stamina"; //name of variable
    try { //this is for loading image
        image = ImageIO.read(getClass().getResourceAsStream("/objects/stamina.png"));
        
    }catch(IOException e) {
        e.printStackTrace();
        }
    }
    
}
