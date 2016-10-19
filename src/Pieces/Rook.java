package Pieces;

import Chess.Move;
import Chess.Player;

/***********************************************************************
 * This Class handles all operations for the Rook chess piece
 * @author Troy Madsen and Grant Postma
 * @version 1.0
 **********************************************************************/

public class Rook extends ChessPiece {

	/** this boolean lets you know if the piece can castle/has moved**/
	private boolean hasMoved;

	/*******************************************************************
	 * This is the Constructor for the Rook
	 * 
	 * @param player the player this piece belongs too
	 ******************************************************************/
	public Rook(Player player) {
		super(player);
		hasMoved = false;
	}
	
	/*******************************************************************
	 * returns true if the piece has moved
	 * 
	 * @return if this piece has moved
	 ******************************************************************/
	public boolean hasMoved() {
		return hasMoved;
	}

	/*******************************************************************
	 * returns the type of this piece
	 * 
	 * @return whatever the name of the piece is
	 ******************************************************************/
	@Override
	public String type() {
		return "Rook";
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
		if((move.fromRow != move.toRow) && (move.fromColumn != move.toColumn))
			return false;

		if (board[move.toRow][move.toColumn] == null ||
				board[move.toRow][move.toColumn].player() != owner){

			int xInc, yInc; 
			int X = move.fromColumn;
			int Y = move.fromRow;

			//Checking the direction
			if(move.fromColumn > move.toColumn){
				yInc = 0;
				xInc = -1;
			}

			else if(move.fromColumn < move.toColumn){
				yInc = 0;
				xInc = 1;
			}

			else if(move.fromRow > move.toRow){
				yInc = -1;
				xInc = 0;
			}

			else{
				yInc = 1;
				xInc = 0;
			}


			X+=xInc;
			Y+=yInc;
			while(X != move.toColumn || Y != move.toRow){
				if(board[Y][X] != null)
					return false;

				X+=xInc;
				Y+=yInc;
			}
			hasMoved = true;
			return true;
		}
		return false;		
	}



}
