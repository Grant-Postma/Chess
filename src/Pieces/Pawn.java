package Pieces;

import Chess.Move;
import Chess.Player;

/***********************************************************************
 * This Class handles all operations for the pawn chess piece
 * @author Troy Madsen and Grant Postma
 * @version 1.0
 **********************************************************************/

public class Pawn extends ChessPiece {

	/*******************************************************************
	 * This is the Constructor for the Pawn
	 * 
	 * @param player the player this piece belongs too
	 ******************************************************************/
	public Pawn(Player player) {
		super(player);
	}

	/*******************************************************************
	 * returns the type of this piece
	 * 
	 * @return whatever the name of the piece is
	 ******************************************************************/
	@Override
	public String type() {
		return "Pawn";
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



		//Movement
		if (player()== Player.WHITE){
			if((move.fromRow == 6) && (move.toRow == 4) &&
					(move.fromColumn == move.toColumn) &&
					board[5][move.fromColumn] == null &&
					board[4][move.fromColumn] == null)
				return true;

			else if((move.fromRow-1 == move.toRow) &&
					(move.fromColumn == move.toColumn) &&
					(board[move.toRow][move.toColumn] == null))
				return true;

			else if((move.fromRow-1 == move.toRow) &&
					(Math.abs(move.fromColumn-move.toColumn) == 1) &&
					(board[move.toRow][move.toColumn] != null) &&
					(board[move.toRow][move.toColumn].player()
							==Player.BLACK))
				return true;
		}

		else if(player()== Player.BLACK){

			if((move.fromRow == 1) && (move.toRow == 3) &&
					(move.fromColumn == move.toColumn) &&
					board[2][move.fromColumn] == null &&
					board[3][move.fromColumn] == null)
				return true;

			else if((move.fromRow+1 == move.toRow) &&
					(move.fromColumn == move.toColumn) &&
					(board[move.toRow][move.toColumn] == null))
				return true;

			else if((move.fromRow+1 == move.toRow) &&
					(Math.abs(move.fromColumn-move.toColumn) == 1) &&
					(board[move.toRow][move.toColumn] != null) &&
					(board[move.toRow][move.toColumn].player()
							==Player.WHITE))
				return true;
		}
		return false;		
	}

}
