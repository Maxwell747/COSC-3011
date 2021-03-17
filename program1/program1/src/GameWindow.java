/**
 * @author Kim Buckner
 * Date: Jan 14, 2021
 *
 * This is the actual "game". May/will have to make some major changes.
 * This is just a "hollow" shell.
 *
 * When you get done, I should see the buttons at the top in the "play" area
 * (NOT a pull-down menu). The only one that should do anything is Quit.
 *
 * Should also see something that shows where the 4x4 board and the "spare"
 * tiles will be when we get them stuffed in.
 *
 * This version uses a GridBagLayout manager. I STRONGLY suggest that you use
 * SOME layout manager, and even though this one is not the easiest, it is VERY
 * flexible. Refer to
 *
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/layoutlist.html
 *
 * for an explaination of the various layout managers. And really read it and
 * try them out. Write some of you OWN code and play with them. It is the only
 * way to really learn programming. 
 */
//package game;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameWindow extends JFrame implements ActionListener
  {
    /**
     * because it is a serializable object, need this or javac
     * complains <b>a lot</b>
     */
    public static final long serialVersionUID=1;
    JButton lbutton = new JButton("New game");
    JButton mbutton = new JButton("Reset");
    JButton rbutton = new JButton("Quit");

    Panels gameBoard;
    Panels leftSide;
    Panels rightSide;
    

    /*
     * Here I declare some variables that used "throughout" the game 
     */
    private int startAt=1;
    
    
    /**
     * Constructor sets the window name using super(), changes the layout,
     * which you really need to read up on, and maybe you can see why I chose
     * this one.
     *
     * @param s The name of the window
     */

    public GameWindow(String s)
    {
      super(s);
      GridBagLayout gbl=new GridBagLayout();
      setLayout(gbl);
    }

    /**
     * For the buttons
     * @param e is the ActionEvent
     * 
     * BTW can ask the event for the name of the object generating event.
     * The odd syntax for non-java people is that "exit" for instance is
     * converted to a String object, then that object's equals() method is
     * called.
     *
     * Your code MUST not print out anything to console when you submit it.
     */

    public void actionPerformed(ActionEvent e) {
      if("quit".equals(e.getActionCommand()))
        System.exit(0);
      if("reset".equals(e.getActionCommand()))
        System.out.println("reset pressed\n");
      if("new".equals(e.getActionCommand()))
        System.out.println("new pressed\n");
      }

    /**
     *  Establishes the inital board
     */

    public void setUp(float[][][] pieceData)
    {
      //actually create the array for elements, make sure it is big enough
      
      // Need to play around with the dimensions and the gridx/y values
      // These constraints are going to be added to the pieces/parts I 
      // stuff into the "GridBag".
      // YOU CAN USE any type of constraints you like. Just make it work.
      /**
      GridBagConstraints basic = new GridBagConstraints();
      basic.gridx=startAt;
      basic.gridy=0;
      basic.gridwidth=1;
      basic.gridheight=1;
      */
      // This is really a constant in the GrdiBagConstraints. This way we 
      // don't need to know what type/value it is
      //basic.fill=GridBagConstraints.BOTH;

      //new constraints
      GridBagConstraints constraints = new GridBagConstraints();
      
      constraints.insets = new Insets(10, 10, 10, 30);
      constraints.weightx = 1;
      constraints.weighty = 1;

      //create 4x4 game board
      gameBoard = new Panels(4,4,0,16);
      constraints.gridx = 1;
      constraints.gridy = 2;
      add(gameBoard, constraints);  

      //create left pieces
      leftSide = new Panels(8,1,5,0,Arrays.copyOfRange(pieceData,0,8));
      leftSide.createPieces(0);
      constraints.gridx = 0;
      add(leftSide, constraints);  

      //create right pieces
      rightSide = new Panels(8,1,5,8,Arrays.copyOfRange(pieceData,8,16));
      rightSide.createPieces(8);
      constraints.gridx = 2;
      add(rightSide, constraints);
      
      //create a buttons panel and add buttons
      Panels topSide = new Panels(0,0,5,0);
      topSide.addButtons(lbutton, mbutton, rbutton);
      constraints.gridy = 0;
      constraints.gridx = 1;
      add(topSide, constraints);
      
      //set buttons to an action
      //	need to code actions as the code can't print to console, via instructions line 80
      // 	fix lines 86-89 before un-commenting 154-157
      rbutton.setActionCommand("quit");
      rbutton.addActionListener(this);
      //mbutton.setActionCommand("reset");
      //mbutton.addActionListener(this);
      //lbutton.setActionCommand("new");
      //lbutton.addActionListener(this);
          
      return;   
      
    }  
  };