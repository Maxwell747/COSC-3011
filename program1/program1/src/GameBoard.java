//setup of grid

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class GameBoard extends JPanel
{
    GamePieces piece;
    boolean hasPiece;
    int panelID;
    String stringID;
    JLabel textDisplay;
    Movement panelListener;

    public GameBoard(int size, int ID) //setup grid squares
    {
        piece = null;
        hasPiece = false;
        panelID = ID;
        panelListener = new Movement();

        addMouseListener(panelListener);
        setLayout(new BorderLayout());
        setBorder();
        setBackground(new Color(255, 255, 255));
        setPreferredSize(new Dimension(size, size));
    }

    public void buildPiece(GamePieces newPiece) //creates new piece
    {
        if(hasPiece == true)
        {
            return;
        }
        piece = newPiece;
        hasPiece = true;

        eraseBorder();
        add(piece);
        repaint();
    }

    public void ridPiece() //gets rid of piece in current spot
    {
        if(hasPiece == false)
        {
            return;
        }
    	remove(piece);
        piece = null;
        hasPiece = false;
        setBorder();
        repaint();
    }

    public boolean getHasPiece()
    {
        return hasPiece;
    }

    public GamePieces getPiece()
    {
        return piece;
    }

    public int getPanelID()
    {
        return panelID;
    }

    public void setBorder()
    {
        setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
    }

    public void eraseBorder()
    {
        setBorder(BorderFactory.createEmptyBorder());
    }

    public void highlight()
    {
        piece.highlightPiece();
    }

    public void unhighlight()
    {
        piece.unhighlightPiece();
    }
}