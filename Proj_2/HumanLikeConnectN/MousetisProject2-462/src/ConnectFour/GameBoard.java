package ConnectFour;
//Daniel Breitigan and Matt Mousetis
//GameBoard class which is the heart of our Connect-N
public class GameBoard 
{
	public char board[][];
	public int rowHeight;
	public int colWidth;
	int connectN = 0;
	int score;

	// Create the GameBoard for Connect-n
	public GameBoard(int size) 
	{
		connectN = size;
		rowHeight = (2 * size) - 1;
		colWidth = (2 * size) - 1;
		board = new char[rowHeight][colWidth];
	}

	//Create a gameboard based on size and an already created GameBoard
	public GameBoard(int size, GameBoard b) 
	{
		connectN = size;
		rowHeight = (2 * size) - 1;
		colWidth = (2 * size) - 1;
		board = new char[rowHeight][colWidth];
		
		for (int i = 0; i < b.rowHeight; i++)
		{
			for (int j = 0; j < b.colWidth; j++)
			{
			    	board[i][j] = b.board[i][j];
			}
		}
	}
	
	// Add a piece to the board
	public void addPieceToBoard(int col, char color) 
	{
		for (int i = (rowHeight - 1); i >= 0; i--) 
		{
			if (board[i][col] == '\u0000')
			{
				board[i][col] = color;
				break;
			}
		}
	}

	// Calculate how many pieces are in a row horizontally
	public int checkVertical(int row, int col, char player) {
		int counter = 0;
		if (board[row][col] == player)
			counter++;
		else
			return 0;

		int checkdown = col - 1;
		while (checkdown >= 0 && board[row][checkdown] == player) {
			checkdown--;
			counter++;
		}

		int checkup = col + 1;
		while (checkup < rowHeight && board[row][checkup] == player) {
			checkup++;
			counter++;
		}
		return counter;
	}

	//Calculate the number of pieces that are on the horizontal row
	public int checkHorizontal(int row, int col, char player) {
		int counter = 0;
		if (board[row][col] == player)
			counter++;
		else
			return 0;

		int checkleft = row - 1;
		while (checkleft >= 0 && board[checkleft][col] == player) {
			checkleft--;
			counter++;
		}

		int checkright = row + 1;
		while (checkright < rowHeight && board[checkright][col] == player) {
			checkright++;
			counter++;
		}
		return counter;
	}
	
	//Check the number of pieces going from bottom left to top right
	public int CheckDiagonalUp(int row, int col, char player){
		int counter = 0;
		if (board[row][col] == player)
			counter++;
		else
			return 0;
		
		int checkleftdown = row - 1;
		int windown = col - 1;
		while (checkleftdown >= 0 && windown >= 0 && board[checkleftdown][windown] == player) {
			counter++;
			checkleftdown--;
			windown--;
		}
		
		int checkrightup = row + 1;
		int winup = col + 1;
		while (checkrightup < rowHeight && winup < rowHeight && board[checkrightup][winup] == player) {
			counter++;
			checkrightup++;
			winup++;
		}
		
		return counter;
	}
	
	//Check the number of pieces in a row going from top right to bottom left
	public int CheckDiagonalDown(int row, int col, char player)
	{
		int counter = 0;
		if (board[row][col] == player)
			counter++;
		else
			return 0;
		
		int checkleftup = row + 1;
		int windown = col - 1;
		while (checkleftup < rowHeight && windown >= 0 && board[checkleftup][windown] == player) {
			counter++;
			checkleftup++;
			windown--;
		}
		
		int checkrightdown = row - 1;
		int winup = col + 1;
		while (checkrightdown >= 0 && winup < rowHeight && board[checkrightdown][winup] == player) {
			counter++;
			checkrightdown--;
			winup++;
		}
		
		return counter;
	}
	
	//Check if the board is a win horizontally, vertically, and diagonally
	public boolean isWin(char player){
		int midBoard = connectN - 1;
		for(int i = 0; i < rowHeight; i++)
		{
			if(board[i][midBoard] != player && board[midBoard][i] != player)
				continue;
			
			if(checkHorizontal(midBoard, i, player) >= connectN)
				return true;
			
			if(checkVertical(i, midBoard, player) >= connectN)
				return true;
			
			if(CheckDiagonalUp(i, midBoard, player) >= connectN)
				return true;
			
			if(CheckDiagonalDown(i, midBoard, player) >= connectN)
				return true;
			
		}
		return false;
	}
	
	//Check if it is a draw
	public boolean isDraw()
	{
		int counter = 0;
		for(int i = 0; i < rowHeight; i++)
		{
			if(board[i][rowHeight - 1] != 'R' || board[i][rowHeight -1] != 'B') counter++;
		}
		if(counter == rowHeight - 1)
			return true;
		else
			return false;
	}
	
	//Check if the code is at a terminal node
	public boolean isTerminal() {
		if(isWin('B')) return true;
		if(isWin('R')) return true;
		if(isDraw()) return true;
		return false; 
	}
	
	//Utility function to see how good board is for us to win.
	public int Utility()
	{
		if(isWin('B')) return 10000;
		if(isWin('R')) return -10000;
		if(isDraw()) return 0;
		
		return Evaluate();
	}
	
	//Create a total for the board to give it a utility
	//Goes along the bottom of the board and checks if their is a piece. if their is, it goes up that column
	//until it finds a blank space.
	public int Evaluate()
	{
		if(isWin('B')) return 10000;
		if(isWin('R')) return -10000;
		if(isDraw()) return 0;
		
		int sum = 0;
		
		for(int i = 0; i < rowHeight; i++)
		{
			int columnPlace = rowHeight - 1;
			while(columnPlace >= 0 && (board[columnPlace][i] == 'R' || board[columnPlace][i] == 'B'))
			{
				
				int horz = checkHorizontal(columnPlace, i, board[columnPlace][i]);
				int vert = checkVertical(columnPlace, i, board[columnPlace][i]);
				int diagup = CheckDiagonalUp(columnPlace, i, board[columnPlace][i]);
				int diagdown = CheckDiagonalDown(columnPlace, i, board[columnPlace][i]);
				
				if(board[columnPlace][i] == 'R')
				{
					sum -= (horz * horz) + (vert * vert) + (diagup + diagup) + (diagdown * diagdown);
				}
				else
				{
					sum += (horz * horz) + (vert * vert) + (diagup + diagup) + (diagdown * diagdown);
				}
				
				columnPlace--;
			}
		}
		
		return sum;
	}


	// Print the game Board
	public void printBoard() {
		for (int i = 0; i < rowHeight; i++) {
			for (int j = 0; j < colWidth; j++) {
				if(board[i][j] == '\u0000')
				{
					System.out.print('X' + " ");
				}
				else
				{
					System.out.print(board[i][j] + " ");
				}
			}
			System.out.println();
		}
	}


}

