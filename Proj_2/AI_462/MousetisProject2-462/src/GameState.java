
public class GameState 
{
	GameBoard state;
	int score;
	
	public GameState(GameBoard g, int addedCol)
	{
		   state = new GameBoard(g.rowHeight);
		   for (int i = 0; i < state.rowHeight; i++)
		   {
			    for (int j = 0; j < state.colWidth; j++)
			    {
			    	state.board[i][j] = g.board[i][j];
			    }
		   }
		//state.addPieceToBoard(addedCol, "B");
	}
	
	public GameBoard getState()
	{
		return state;
	}


}
