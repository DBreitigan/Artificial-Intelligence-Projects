package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ConnectFour.GameBoard;
import ConnectFour.Player;

public class Tests {

	@Test
	public void testIsHorizAWin() 
	{
		GameBoard g = new GameBoard(4);
		g.board[0][0] = 'B';
		g.board[1][0] = 'B';
		g.board[2][0] = 'B';
		g.board[3][0] = 'B';
		assertTrue(g.isWin('B'));
	}

	@Test
	public void testIsHorizAFarSideWin() 
	{
		GameBoard g = new GameBoard(4);
		g.board[3][0] = 'B';
		g.board[4][0] = 'B';
		g.board[5][0] = 'B';
		g.board[6][0] = 'B';
		assertTrue(g.isWin('B'));
	}

	@Test
	public void testIsVertAWin() 
	{
		GameBoard g = new GameBoard(4);
		g.board[4][0] = 'B';
		g.board[4][1] = 'B';
		g.board[4][2] = 'B';
		g.board[4][3] = 'B';
		assertTrue(g.isWin('B'));
	}
	
	@Test
	public void testIsVertFAWin() 
	{
		GameBoard g = new GameBoard(4);
		g.board[0][3] = 'B';
		g.board[0][4] = 'B';
		g.board[0][5] = 'B';
		g.board[0][6] = 'B';
		assertTrue(g.isWin('B'));
	}
	
	@Test
	public void testIsDiagDownLeftAWin() 
	{
		GameBoard g = new GameBoard(4);
		g.board[3][3] = 'B';
		g.board[4][2] = 'B';
		g.board[5][1] = 'B';
		g.board[6][0] = 'B';
		assertTrue(g.isWin('B'));
	}
	
	@Test
	public void testIsDiagUpLeftAWin() 
	{
		GameBoard g = new GameBoard(4);
		g.board[3][3] = 'B';
		g.board[4][4] = 'B';
		g.board[5][5] = 'B';
		g.board[6][6] = 'B';
		assertTrue(g.isWin('B'));
	}

	@Test
	public void testIsDiagDownRightAWin() 
	{
		GameBoard g = new GameBoard(4);
		g.board[3][3] = 'B';
		g.board[2][2] = 'B';
		g.board[1][1] = 'B';
		g.board[0][0] = 'B';
		assertTrue(g.isWin('B'));
	}
	
	@Test
	public void testIsDiagUpRightAWin() 
	{
		GameBoard g = new GameBoard(4);
		g.board[3][3] = 'B';
		g.board[2][4] = 'B';
		g.board[1][5] = 'B';
		g.board[0][6] = 'B';
		assertTrue(g.isWin('B'));
	}

	@Test
	public void testIsDiagUpAWinMidBoard() 
	{
		GameBoard g = new GameBoard(4);
		g.board[2][0] = 'B';
		g.board[3][1] = 'B';
		g.board[4][2] = 'B';
		g.board[5][3] = 'B';
		assertTrue(g.isWin('B'));
	}

	@Test
	public void testIsDiagDownAWinMidBoard() 
	{
		GameBoard g = new GameBoard(4);
		g.board[5][0] = 'B';
		g.board[4][1] = 'B';
		g.board[3][2] = 'B';
		g.board[2][3] = 'B';
		assertTrue(g.isWin('B'));
	}


	@Test
	public void Test2ndGameBoardConstructor()
	{
		GameBoard g = new GameBoard(4);
		g.board[4][2] = 'B';
		g.board[5][2] = 'R';
		g.board[6][2] = 'B';
		g.board[1][3] = 'R';
		g.board[2][5] = 'B';
		g.board[3][4] = 'R';
		GameBoard b = new GameBoard(4, g);
		for (int i = 0; i < g.rowHeight; i++)
		{
			for (int j = 0; j < g.colWidth; j++)
			{
				assertEquals(b.board[i][j], g.board[i][j]);
			}
		}
	}
/**	
	@Test
	public void TestAddPieceToBoard()
	{
		GameBoard g = new GameBoard(4);
		g.addPieceToBoard(0, 'B');
		assertEquals(g.board[6][0], 'B');
	}

	@Test
	public void TestAddPieceToBoardPieceinRow()
	{
		GameBoard g = new GameBoard(4);
		g.board[6][2] = 'B';
		g.addPieceToBoard(2, 'B');
		assertEquals(g.board[1][2], 'B');
	}
*/
	@Test
	public void TestAddPieceToBoardPiecePrint()
	{
		GameBoard g = new GameBoard(4);
		g.board[6][3] = 'B';
		g.board[6][2] = 'B';
		g.board[6][1] = 'B';
		g.addPieceToBoard(0, 'B');
		assertEquals(g.board[6][0], 'B');
	}
	
	@Test
	public void TestStateSelectionColumn()
	{
		GameBoard g = new GameBoard(4);
		Player p = new Player();
		g.board[6][3] = 'B';
		g.board[6][4] = 'B';
		g.board[6][5] = 'B';
		assertEquals(2, p.MakeSelection(g));
	}

	@Test
	public void TestStateSelection2Column()
	{
		GameBoard g = new GameBoard(4);
		Player p = new Player();
		g.board[6][3] = 'B';
		g.board[6][2] = 'R';
		assertEquals(1, p.MakeSelection(g));
	}
	
	@Test
	public void TestAB()
	{
		GameBoard g = new GameBoard(4);
		Player p = new Player();
		g.board[6][3] = 'B';
		g.board[6][2] = 'R';
		assertEquals(4, p.MiniMax(g , 6, -99999, 99999));
	}
	

}
