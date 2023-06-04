package object; //this is class for key

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {
    
    public OBJ_Key() {
        
        name = "Key"; //name of variable
        try { //this is for loading image
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            
        }catch(IOException e) {
            e.printStackTrace();
        }
    }    
}
