
public class GameBoard {
	char board[][];
	int rowHeight;
	int colWidth;
	int connectN = 0;
	int score;

	// Create the GameBoard for Connect-n
	public GameBoard(int size) {
		connectN = size;
		rowHeight = (2 * size) - 1;
		colWidth = (2 * size) - 1;
		board = new char[rowHeight][colWidth];
	}

	// Add a piece to the board
	public void addPieceToBoard(int col, char color) {
		int i = (rowHeight - 1);
		col = col - 1;

		for (i = (rowHeight - 1); i >= 0; i--) {
			if (board[i][col] == 'n') {
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
		
		int checkrightup = row +1;
		int winup = col + 1;
		while (checkrightup < rowHeight && winup < rowHeight && board[checkrightup + 1][winup + 1] == player) {
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
			
			if(checkHorizontal(i, midBoard, player) >= connectN)
				return true;
			
			if(checkVertical(midBoard, i, player) >= connectN)
				return true;
			
			if(CheckDiagonalUp(i, midBoard, player) >= connectN)
				return true;
			
			if(CheckDiagonalDown(i, midBoard, player) >= connectN)
				return true;
			
		}
		return false;
	}
	
	public boolean isDraw()
	{
		for(int i = 0; i < rowHeight; i++)
		{
			for(int j = 0; i < rowHeight; j++)
			{
				if(board[i][j] == 'n') return true;
			}
		}
		return false;
	}
	
	//Utility function to see how good board is for us to win.
	public int Utility()
	{
		if(isWin('B')) return 1000;
		if(isWin('R')) return -1000;
		if(isDraw()) return 0;
		
		//NEEDS CODE ADDED TO IT FOR THE ALPHA-BETA PRUNING
		//Will use evaluate function if it is at a terminal and a next() function
		
		return 0;
	}
	
	//Create a total for the board to give it a utility
	//Goes along the bottom of the board and checks if their is a piece. if their is, it goes up that column
	//until it finds a blank space.
	public int Evaluate()
	{
		int sum = 0;
		
		for(int i = 0; i < rowHeight; i++)
		{
			int columnPlace = 0;
			while(board[i][columnPlace] != 'n')
			{
				if(board[i][columnPlace] == 'r')
				{
					sum -= checkHorizontal(i, columnPlace, board[i][columnPlace]) ^ 2;
					sum -= checkVertical(i, columnPlace, board[i][columnPlace]) ^ 2;
					sum -= CheckDiagonalUp(i, columnPlace, board[i][columnPlace]) ^ 2;
					sum -= CheckDiagonalDown(i, columnPlace, board[i][columnPlace]) ^ 2;
				}
				else
				{
					sum += checkHorizontal(i, columnPlace, board[i][columnPlace]) ^ 2;
					sum += checkVertical(i, columnPlace, board[i][columnPlace]) ^ 2;
					sum += CheckDiagonalUp(i, columnPlace, board[i][columnPlace]) ^ 2;
					sum += CheckDiagonalDown(i, columnPlace, board[i][columnPlace]) ^ 2;
				}
				
				columnPlace++;
			}
		}
		
		return sum;
	}

	// Print the game Board
	public void printBoard() {
		for (int i = 0; i < rowHeight; i++) {
			for (int j = 0; j < colWidth; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
}
