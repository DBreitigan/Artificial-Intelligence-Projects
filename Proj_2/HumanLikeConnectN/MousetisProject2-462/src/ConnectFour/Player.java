package ConnectFour;
import java.util.ArrayList;
import java.util.LinkedList;

//Daniel Breitigan and Matt Mousetis
//Player class, AKA the AI
public class Player 
{
	boolean max = true;

	public ArrayList<GameBoard> States = new ArrayList<>();
	
	public Player()
	{
	}
	
	//Returns the integer of the position to put the piece into
	public int MakeSelection(GameBoard b)
	{
		int maxScore = -9999;
		int selectedCol = 0;
		int temp;
		
		for(int i = 0; i < b.rowHeight; i++)
		{
			GameBoard board = new GameBoard(b.connectN, b);
			board.addPieceToBoard(i, 'B');
			temp = MiniMax(board , 7, -999999, 999999);
			if(temp > maxScore)
			{
				maxScore = temp;
				selectedCol = i;
			}
		}
		return selectedCol;
	}
	
	//Returns the gameboard of the move we want to use
	//Based off of Dr.Briggs Code
	public GameBoard ChooseMove(GameBoard b)
	{
		int max = -999999;
		GameBoard best = null;
		for(GameBoard c : next(b, 'B'))
		{
			int value = MiniMax(c , 7, -999999, 999999);
			if(value > max)
			{
				max = value;
				best = c;
			}
		}
		return best;
	}
	
	//Returns the next positions in the board
	public LinkedList<GameBoard> next(GameBoard b, char col) 
	{
		LinkedList<GameBoard> GBoards = new LinkedList<GameBoard>();
		for(int i = 0; i < b.rowHeight; i++)
		{
			if(b.board[0][i] == 'B' || b.board[0][i] == 'R')
				continue;
			else{
				GameBoard board = new GameBoard(b.connectN, b);
				board.addPieceToBoard(i, col);
				GBoards.add(board);
			}
		}
		return GBoards;
	}
	
	//This is called MiniMax but it actually it's actually Alpha Beta Pruning, I originally
	//built Minimax and then expanded it to be Alpha Beta. Based on Pseudocode found on
	//Cornell University website.
	public int MiniMax(GameBoard b, int depth, int min, int max){
		if(depth == 0 || b.isTerminal())
		{
			return b.Evaluate();
		}
		if(depth % 2 == 1)
		{
			int v = min;
			LinkedList<GameBoard> nxt = next(b, 'R');
			for(GameBoard C : nxt)
			{
				int vhat = Math.max(v, MiniMax(C , depth - 1, v, max));
				if (vhat > v)
					v = vhat;
				if (v > max)
					return max;
			}
			return v;
		}
		else
		{
			int v = max;
			LinkedList<GameBoard> nxt = next(b, 'B');
			for(GameBoard C : nxt)
			{
				int vhat = Math.min(v, MiniMax(C , depth - 1, min, v));
				if(vhat < v)
					v = vhat;
				if(v < min)
					return min;
			}
			return v;
		}
	}
}
