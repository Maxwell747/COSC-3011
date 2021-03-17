//setup of maze pieces

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class GamePieces extends JPanel
{
    int pieceID;
    int pieceSize;
    float[][] lineData;
    float[][] scaledLineData;
    String stringID;

    public GamePieces(int ID, int size, float[][] pieceInputData) //setup for individual pieces
    {
        pieceSize = size;
        lineData = pieceInputData;
        pieceID = ID;

        scaleLines();
        setPreferredSize(new Dimension(size, size));
        setBackground(new Color(51, 120, 204)); 
        
        if(pieceID < 10)
        {
            stringID = "0" + pieceID;
        }
        else
        {
            stringID = Integer.toString(pieceID);
        }
        
        JLabel textDisplay = new JLabel(stringID);
        add(textDisplay);
    }

    public void scaleLines()
    {
        scaledLineData = new float[lineData.length][4];
        for(int i=0; i < lineData.length; i++)
        {
            for(int j=0; j < 4; j++)
            {
                scaledLineData[i][j] = lineData[i][j] * ((float)pieceSize/100);
            }
        }
    }

    private void drawLines(Graphics g) //draw scaled lines on piece
    {
        Graphics2D g2d = (Graphics2D) g;
        Line2D line;

        g2d.setColor(new Color(0,0,0));
        g2d.setStroke(new BasicStroke(3));

        for(int i=0; i < scaledLineData.length; i++)
        {
            line =  new Line2D.Float(scaledLineData[i][0], scaledLineData[i][1], scaledLineData[i][2], scaledLineData[i][3]);
            g2d.draw(line);
        }
    }

    public int getPieceID()
    {
        return pieceID;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawLines(g);
    }

    public void highlightPiece()
    {
        setBorder(BorderFactory.createLineBorder(new Color(0,100,0),3));
        repaint();
    }

    public void unhighlightPiece()
    {
        setBorder(BorderFactory.createEmptyBorder());
        repaint();
    }
}