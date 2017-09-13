import java.util.ArrayList;

public class Player 
{

	public ArrayList<GameState> States = new ArrayList<>();
	
	public Player()
	{
		
	}
	
	public void generateStates(GameBoard b)
	{
		for(int i = 1; i<= b.rowHeight; i++)
		{
			GameState GS = new GameState(b, i);
			System.out.println();
			GS.getState().printBoard();
			System.out.println();
		}
	}
	
}
