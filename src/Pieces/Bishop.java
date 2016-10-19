package Pieces;

import Chess.Move;
import Chess.Player;

/***********************************************************************
 * This Class handles all operations for the bishop chess piece
 * @author Troy Madsen and Grant Postma
 * @version 1.0
 **********************************************************************/

public class Bishop extends ChessPiece  {

	/*******************************************************************
	 * This is the Constructor for the Bishop
	 * 
	 * @param player the player this piece belongs too
	 ******************************************************************/
	public Bishop(Player player) {
		super(player);
	}

	/*******************************************************************
	 * returns the type of this piece
	 * 
	 * @return whatever the name of the piece is
	 ******************************************************************/
	@Override
	public String type() {
		return "Bishop";
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
		if((Math.abs(move.fromRow-move.toRow)
				!= Math.abs(move.fromColumn-move.toColumn)))
			return false;


		if (board[move.toRow][move.toColumn] == null ||
				board[move.toRow][move.toColumn].player() != owner){

			int xInc, yInc; 
			int X = move.fromColumn;
			int Y = move.fromRow;

			//Checking the direction
			if(move.fromColumn > move.toColumn){
				xInc = -1;
			}
			else{
				xInc = 1;
			}

			if(move.fromRow > move.toRow){
				yInc = -1;
			}
			else{
				yInc = 1;
			}



			X+=xInc;
			Y+=yInc;
			while(X != move.toColumn || Y != move.toRow){

				if(board[Y][X] != null)
					return false;
				X+=xInc;
				Y+=yInc;
			}
			return true;
		}
		return false;		
	}


}
