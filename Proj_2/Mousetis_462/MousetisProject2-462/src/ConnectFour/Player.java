package ConnectFour;
import java.util.ArrayList;

public class Player 
{
	boolean max = true;

	public ArrayList<GameBoard> States = new ArrayList<>();
	
	public Player()
	{
	}
	
	public int MakeSelection(GameBoard b)
	{
		int maxScore = 0;
		int minScore = 0;
		int selectedCol = 0;
		int temp;
		
		for(int i = 0; i < b.rowHeight; i++)
		{
			GameBoard board = new GameBoard(b.connectN, b);
			board.addPieceToBoard(i, 'B');
			temp = board.utility();
			if(max && temp > maxScore)
			{
				maxScore = temp;
				selectedCol = i;
			}

			if(max == false && temp < minScore)
			{
				minScore = temp;
				selectedCol = i;
			}
		}
		
		return selectedCol;
	}
	
}
