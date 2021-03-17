/**
 * @author Kim Buckner
 * Date: Jan 14, 2021
 *
 *
 * A starting point for the COSC 3011 programming assignment
 * Probably need to fix a bunch of stuff, but this compiles and runs.
 *
 */
//package game;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main 
{
    static float[][][] pieceData;
    public static JOptionPane errorPane;
  
  public static void main(String[] args)
  {

    File file;
    MazeReader maze;
    file = new File("input/default.mze");
    try
    {
        maze = new MazeReader(file);
        maze.readData();
        pieceData = maze.getAllPieceLines();
        maze.close();
    }
    catch (FileNotFoundException e) 
    {
        JOptionPane.showMessageDialog(errorPane, "File Not Found Error","Error", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }
    catch (IOException e) 
    {
        JOptionPane.showMessageDialog(errorPane, "I/O Exception Error","Error", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    // This is the play area
    // HEY FIX THE NAME, WHAT IS ****YOUR**** GROUP
    GameWindow game = new GameWindow("Group B aMaze");
    
    // have to override the default layout to reposition things!!!!!!!
    
    
    game.setSize(new Dimension(800, 800));
    
    
    //layeredPane.add(game.getContentPane());
    // So the debate here was, do I make the GameWindow object the game
    // or do I make main() the game, manipulating a window?
    // Should GameWindow methods know what they store?
    // Answer is, have the "game" do it.
    
    game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    game.getContentPane().setBackground(new Color(127, 131, 135));  
    game.setUp(pieceData);
    game.setVisible(true); 

    try {
      // The 4 that are installed on Linux here
      // May have to test on Windows boxes to see what is there.
      //UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
      // This is the "Java" or CrossPlatform version and the default
      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
      // Linux only
      //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
      // really old style Motif 
      //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
    } 
    catch (UnsupportedLookAndFeelException e) {
     // handle possible exception
    }
    catch (ClassNotFoundException e) {
     // handle possible exception
    }
    catch (InstantiationException e) {
     // handle possible exception
    }
    catch (IllegalAccessException e) {
     // handle possible exception
    }
  
  }
  
};