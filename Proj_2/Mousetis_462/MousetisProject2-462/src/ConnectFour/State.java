package ConnectFour;

import java.util.LinkedList;

//AUTHOR: DR.BRIGGS
public interface State {
	public boolean isTerminal( );
	LinkedList<State> next( );
	public int utility( );
	int getDepth( ); 
	char getPlayer( );
}
