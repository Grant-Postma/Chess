package Pieces;

import Chess.Move;
import Chess.Player;

/***********************************************************************
 * This Class handles all operations for the queen chess piece
 * @author Troy Madsen and Grant Postma
 * @version 1.0
 **********************************************************************/

public class Queen extends ChessPiece {


	/*******************************************************************
	 * This is the Constructor for the Queen
	 * 
	 * @param player the player this piece belongs too
	 ******************************************************************/
	public Queen(Player player) {
		super(player);
	}

	/*******************************************************************
	 * returns the type of this piece
	 * 
	 * @return whatever the name of the piece is
	 ******************************************************************/
	@Override
	public String type() {
		return "Queen";
	}

	/*******************************************************************
	 * Checks if attempted move is valid for this piece
	 * 
	 * @param move the attempted move
	 * @param board the board the piece exists on 
	 * 
	 * @return whether or not the piece can perform this move
	 ******************************************************************/
	@Override
	public boolean isValidMove(Move move, IChessPiece[][] board) {

		if ((new Rook(owner)).isValidMove(move, board) ||
				(new Bishop(owner)).isValidMove(move, board))
			return true;

		return false;

	}

}
