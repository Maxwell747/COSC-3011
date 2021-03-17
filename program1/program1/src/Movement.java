//deal with mouse events and move piece 

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

public class Movement extends MouseInputAdapter
{
	static GameBoard previouslyClicked = null;
	
	public Movement()
	{}

	//when piece is clicked turn green
	//if new clicked position is valid change clicked position to current clicked piece
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource() instanceof GameBoard && SwingUtilities.isLeftMouseButton(e))
		{
			GameBoard currentlyClicked = (GameBoard)e.getSource();
			boolean hasPiece = currentlyClicked.getHasPiece();

			if(previouslyClicked == null && hasPiece)
			{
				previouslyClicked = currentlyClicked;
				previouslyClicked.highlight();
			}
			else if(previouslyClicked != null && !hasPiece)
			{
				currentlyClicked.buildPiece(previouslyClicked.getPiece());

				previouslyClicked.unhighlight();
				previouslyClicked.ridPiece();
				previouslyClicked = null;
			}
			else if(previouslyClicked == currentlyClicked)//deselect
			{
				previouslyClicked.unhighlight();
                previouslyClicked = null;
                currentlyClicked = null;
			}
		}
	}
}