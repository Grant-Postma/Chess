package Pieces;

import Chess.Move;
import Chess.Player;

/***********************************************************************
 * This class holds the base operations for chess pieces. 
 * @author Troy Madsen and Grant Postma
 * @version 1.0
 **********************************************************************/

public abstract class ChessPiece implements IChessPiece {
    /**This is the player for the chess piece **/ 
	protected Player owner;

	/*******************************************************************
	 * Constructor that sets the player of the chesspiece
	 * 
	 * @param player that this piece belongs too
	 ******************************************************************/
	protected ChessPiece(Player player) {
		this.owner = player;
	}

	/*******************************************************************
	 * returns the type of the piece, overridden in subclass
	 * 
	 * @return type of piece for those that extend this
	 ******************************************************************/
	public abstract String type();

	
	/*******************************************************************
	 *returns the player of this piece
	 *
	 * @return the player of this piece
	 ******************************************************************/
	public Player player() {
		return owner;
	}
	
	
	/*******************************************************************
	 * checks to see if the move on the board passes the basic
	 * operation of a chess piece(moving onto itself or its teammate)
	 * 
	 * @param move this is the move being tested
	 * @param board this is the board the move is being tested on
	 * 
	 * @return if this move is valid on the board
	 ******************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if ((move.fromRow == move.toRow) && (move.fromColumn == move.toColumn))
			return false;

		if((board[move.toRow][move.toColumn] != null) &&
				(board[move.toRow][move.toColumn].player() == player())){
			return false;
		}
		return true;
	}


}


