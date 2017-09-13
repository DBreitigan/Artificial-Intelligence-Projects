import java.util.Scanner;

public class Runner 
{
	
	
	public static void main(String[] args)
	{
		Scanner reader = new Scanner(System.in);
		System.out.println("Please enter the size of the board");
		int boardSize = reader.nextInt();
		GameBoard CurrentBoard = new GameBoard(boardSize);
		
		System.out.println("Where would you like to place your piece?");
		int move = reader.nextInt();
		CurrentBoard.addPieceToBoard(move, "RED");
		CurrentBoard.addPieceToBoard(move, "RED");
		CurrentBoard.printBoard();
	}
	

}
