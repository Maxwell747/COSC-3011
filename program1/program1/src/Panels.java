//game visuals setup 
// pieces + board

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Panels extends JPanel
{
    GameBoard[][] panel;
    int pieceSize = 100;
    float[][][] pieceData;

    public Panels(int rows, int columns, int space, int startID)
    {
        GridBagLayout gbl = new GridBagLayout();

        setLayout(gbl);
        setOpaque(false);
        setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(space, space, space, space);
        if (rows == 0 && columns == 0) {
        	constraints.gridx = 3;
        }

        panel = new GameBoard[columns][rows];
        for(int i=0; i<panel.length; i++)
        {
            for(int j=0; j<panel[i].length; j++)
            {
                constraints.gridx = i;
                constraints.gridy = j;
                panel[i][j] = new GameBoard(70, startID+i);
                add(panel[i][j], constraints);
            }
        }
    }

    //for right and left panels to draw in
    public Panels(int rows, int coloumns, int space, int startID, float[][][] pieceLines)
    {
        this(rows,coloumns,space,startID);
        pieceData = pieceLines;
    }

    public void createPieces(int index) //insert pieces on left and right sides
    {
        for(int i=0; i<panel.length; i++)
        {
            for(int j=7; j>=0; j--)
            {
                panel[i][j].buildPiece(new GamePieces(index,pieceSize,pieceData[index%8]));
                index++;
            }
        }
    }

    public void addButtons(JButton lbutton, JButton mbutton, JButton rbutton){
    	
      GridBagConstraints constraints = new GridBagConstraints();
      constraints.insets = new Insets(10, 10, 10, 10); 
      constraints.ipadx = 10;
      constraints.ipady = 10;
      
      constraints.gridwidth = 1;
      constraints.gridy = 0;
      
      constraints.gridx = 0;
      add(lbutton, constraints);
      
      constraints.gridx = 1;
      add(mbutton, constraints);
      
      constraints.gridx = 2;
      add(rbutton, constraints);  
      return;
    }

}