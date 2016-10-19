package Pieces;

import Chess.Move;
import Chess.Player;

/***********************************************************************
 * This Class handles all operations for the Knight chess piece
 * @author Troy Madsen and Grant Postma
 * @version 1.0
 **********************************************************************/

public class Knight extends ChessPiece {

	/*******************************************************************
	 * This is the Constructor for the Knight
	 * 
	 * @param player the player this piece belongs too
	 ******************************************************************/
	public Knight(Player player) {
		super(player);
	}

	/*******************************************************************
	 * returns the type of this piece
	 * 
	 * @return whatever the name of the piece is
	 ******************************************************************/
	@Override
	public String type() {
		return "Knight";
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
		if (!super.isValidMove(move, board))
			return false;

		//movement
		if(((Math.abs(move.fromRow-move.toRow) == 2) &&
				(Math.abs(move.fromColumn-move.toColumn) == 1)) ||
				((Math.abs(move.fromRow-move.toRow) == 1) && 
						(Math.abs(move.fromColumn-move.toColumn) == 2))){
			return true;
		}
		return false;

	}
}
