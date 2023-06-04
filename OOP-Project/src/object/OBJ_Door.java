package object; //this is class for door

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject {
        public OBJ_Door() {
        
        name = "Door"; //name of variable
        try { //this is for loading image
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
            
        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
    
}
