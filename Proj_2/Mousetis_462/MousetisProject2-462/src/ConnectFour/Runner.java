package ConnectFour;
import java.util.Scanner;

public class Runner 
{
	
	
	public static void main(String[] args)
	{
		Scanner reader = new Scanner(System.in);
		System.out.println("Please enter the size of N for Connect-N");
		int boardSize = reader.nextInt();
		GameBoard CurrentBoard = new GameBoard(boardSize);
		
		System.out.println("Does the AI Start? 1 for AI, 0 for Opponent");
		int WhoStarts = reader.nextInt();
		
		if(WhoStarts == 1)
		{
			CurrentBoard.addPieceToBoard(boardSize - 1, 'B');
			System.out.println("The AI drops a piece in Col " + (boardSize));
			CurrentBoard.printBoard();
			System.out.println();
			System.out.println("Where would you like to place your piece? (Column Pos 1 - " + (boardSize * 2 - 1) + ")");
			CurrentBoard.addPieceToBoard(reader.nextInt() - 1, 'R');
		}
		else
		{
			System.out.println("Where would you like to place your piece? (Column Pos 1 - " + (boardSize * 2 - 1) + ")");
			CurrentBoard.addPieceToBoard(reader.nextInt() - 1, 'R');
		}
		
		Player AIP = new Player();
		
		
		while(CurrentBoard.isWin('B') == false && CurrentBoard.isWin('R') == false)
		{
			CurrentBoard.printBoard();
			System.out.println();
			int AIMove = AIP.MakeSelection(CurrentBoard);
			System.out.println("The AI drops a piece in Col " + (AIMove + 1));
			CurrentBoard.addPieceToBoard(AIMove, 'B');
			CurrentBoard.printBoard();
			if(CurrentBoard.isWin('B'))
				break;
			System.out.println();
			System.out.println("Where would you like to place your piece? (Column Pos 1 - " + (boardSize * 2 - 1) + ")");
			CurrentBoard.addPieceToBoard(reader.nextInt() - 1, 'R');
		}
		
		System.out.println("Game Over!");
	}
	

}
