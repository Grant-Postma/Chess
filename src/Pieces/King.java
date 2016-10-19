package Pieces;

import Chess.Move;
import Chess.Player;

public class King extends ChessPiece {

	/** this boolean lets you know if the piece can castle/has moved**/
	private boolean hasMoved;

	/*******************************************************************
	 * This is the Constructor for the King, also sets the hasMoved
	 * to false
	 * 
	 * @param player the player this piece belongs too
	 ******************************************************************/
	public King(Player player) {
		super(player);
		hasMoved = false;
	}

	/*******************************************************************
	 * returns the type of this piece
	 * 
	 * @return whatever the name of the piece is
	 ******************************************************************/
	@Override
	public String type() {
		return "King";
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
		if((Math.abs(move.fromRow-move.toRow) <= 1) &&
				(Math.abs(move.fromColumn-move.toColumn) <= 1)){
			hasMoved = true;
			return true;
		}
		//checks for castling
		if((!hasMoved) &&(Math.abs(move.fromColumn - move.toColumn)==2)
				&& (move.fromRow == move.toRow)){

			if((move.fromColumn > move.toColumn) && 
					(board[move.toRow][0] instanceof Rook) &&
					(!((Rook)board[move.toRow][0]).hasMoved()) &&
					(board[move.toRow][1] == null) &&
					(board[move.toRow][2] == null) &&
					(board[move.toRow][3] == null)) {
				board[move.toRow][0] = null;
				board[move.toRow][3] = new Rook(owner);
				return true;
			}

			else if	((board[move.toRow][7] instanceof Rook) &&
					(!((Rook)board[move.toRow][7]).hasMoved()) &&
					(board[move.toRow][5] == null) &&
					(board[move.toRow][6] == null)) {
				board[move.toRow][7] = null;
				board[move.toRow][5] = new Rook(owner);
				return true;
			}
		}
		return false;
	}
}
