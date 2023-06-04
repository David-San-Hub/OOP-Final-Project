//main
package oop.project;

import javax.swing.JFrame;

public class OOPProject {
    
    public static void main(String[] args) { //This is how you setup a Project (Basically basics stuff)on window 
        
        JFrame window = new JFrame ();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //This lets window properly close when user press "X" button
        window.setResizable(false);
        window.setTitle("The Legend of Zelda:Treasure Hunting"); //sets title for the game
        
        gamepanel gamepanel = new gamepanel(); //gamepanel class
        window.add(gamepanel);
        
        window.pack(); //causes this window to be sized to fit the preffered size and layouts of its subcomponents 
        
        window.setLocationRelativeTo(null); //The window will be displayed at the center of the screen
        window.setVisible(true); //we can see the window
        
        gamepanel.setupGame(); //setup objects
        gamepanel.startgamethread();
      
    }
    
}
