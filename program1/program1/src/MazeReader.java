//read from mze file into usable format

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MazeReader extends FileInputStream
{
    byte[] rawByteData; //hold current byte of read in data
    int numPieces;
    int[] pieceIDs;
    float[][][] allPieceLines; //all piece lines for each piece

    MazeReader(File file) throws FileNotFoundException
    {
        super(file);
        rawByteData = new byte[4];
    }

    public int readInt() throws IOException
    {
        int eof = read(rawByteData);
        if(eof == -1)
        {
            return -1;
        }
        return Convert.toInt(rawByteData);
    }

    public float readFloat() throws IOException
    {
        int eof = read(rawByteData);
        if(eof == -1)
        {
            return -1;
        }
        return Convert.toFloat(rawByteData);
    }

    public void readPieceData(int piece, int numPieceLines) throws IOException
    {
        allPieceLines[piece] = new float[numPieceLines][4];
        for(int i=0; i < numPieceLines; i++)
        {
            for(int j=0; j < 4; j++)
            {
                allPieceLines[piece][i][j] = readFloat();
            }
        }
    }

    public void readData() throws IOException
    {
        numPieces = readInt();
        if(numPieces == -1)
        {
            return;
        }

        pieceIDs = new int[numPieces];
        allPieceLines = new float[numPieces][][];
        int pieceID = 0;
        int numPieceLines = 0;

        for(int i=0; i < numPieces; i++)
        {
            pieceID = readInt();
            if(pieceID == -1)
            {
                return;
            }
            pieceIDs[i] = pieceID;

            numPieceLines = readInt();
            if(numPieceLines == -1)
            {
                return;
            }
            readPieceData(i, numPieceLines);
        }
    }

    public int[] getPieceIDs()
    {
        return pieceIDs;
    }

    public float[][][] getAllPieceLines()
    {
        return allPieceLines;
    }

}