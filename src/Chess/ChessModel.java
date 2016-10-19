package Chess;

import java.util.ArrayList;
import Pieces.Bishop;
import Pieces.IChessPiece;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Queen;
import Pieces.Rook;

/***********************************************************************
 * This Class handles all the game opperations
 * @author Troy Madsen and Grant Postma
 * @version 1.0
 **********************************************************************/


public class ChessModel implements IChessModel {
	/**Creates a board of type ichessPiece that is two dimensional**/
	private IChessPiece[][] board;
	
	/**Creates a variable for the current player**/
	private Player player;
	
	/**Creates a Arraylist for the dead IChessPieces**/
	private ArrayList<IChessPiece> dead;
	
	/**Creates a boolean varibale for if a pawn is accross the board**/
	private boolean canPromotePiece;

	
	/*******************************************************************
	 * Constructor that creates the game board
	 ******************************************************************/
	public ChessModel() {
		player = Player.WHITE;
		dead = new ArrayList<IChessPiece>();
		canPromotePiece = false;

		board = new IChessPiece[8][8];

		//Construct white side
		board[7][0] = new Rook(Player.WHITE);
		board[7][1] = new Knight(Player.WHITE);
		board[7][2] = new Bishop(Player.WHITE);
		board[7][3] = new Queen(Player.WHITE);
		board[7][4] = new King(Player.WHITE);
		board[7][5] = new Bishop(Player.WHITE);
		board[7][6] = new Knight(Player.WHITE);
		board[7][7] = new Rook(Player.WHITE);

		for (int i = 0; i < 8; i++) {
			board[6][i] = new Pawn(Player.WHITE);
		}

		//Construct black side
		board[0][0] = new Rook(Player.BLACK);
		board[0][1] = new Knight(Player.BLACK);
		board[0][2] = new Bishop(Player.BLACK);
		board[0][3] = new Queen(Player.BLACK);
		board[0][4] = new King(Player.BLACK);
		board[0][5] = new Bishop(Player.BLACK);
		board[0][6] = new Knight(Player.BLACK);
		board[0][7] = new Rook(Player.BLACK);

		for (int i = 0; i < 8; i++) {
			board[1][i] = new Pawn(Player.BLACK);
		}
	}

	/*******************************************************************
	 * this game looks to see if the current player is in check mate
	 * by seeing if the current player has any valid moves
	 * 
	 * @return  if the game is in check
	 ******************************************************************/
	
	public boolean isComplete() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if ((board[row][col] != null) && (board[row][col].player() == player)) {
					for (int r = 0; r < 8; r++) {
						for (int c = 0; c < 8; c++) {
							if (isValidMove(new Move(row, col, r, c)) ){
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}

	
	/*******************************************************************
	 * This method checks if the move is valid to be made
	 * 
	 * @param move the move being attempted
	 * @return if the move is valid
	 ******************************************************************/
	public boolean isValidMove(Move move) {
		if(board[move.fromRow][move.fromColumn]
				.isValidMove(move, board)){
			IChessPiece[][] temp = new IChessPiece[8][8];

			for(int i = 0; i < 8; i++)
				temp[i] = board[i].clone();


			board[move.toRow][move.toColumn] =
					board[move.fromRow][move.fromColumn];


			board[move.fromRow][move.fromColumn] = null;


			if(inCheck(player)){
				for(int i = 0; i < 8; i++)
					board[i] = temp[i].clone();
				return false;
			}
			else{
				for(int i = 0; i < 8; i++)
					board[i] = temp[i].clone();
				return true;
			}
		}
		return false;
	}

	/*******************************************************************
	 * This method tells you fi there is a pawn that can be promoted
	 * 
	 * @return if a pawn can be promoted
	 ******************************************************************/
	public boolean canPromotePiece() {
		return canPromotePiece;
	}

	
	/*******************************************************************
	 * This method returns the ArrayList with the dead pieces
	 * 
	 * @return all the dead IChessPieces in an ArrayList
	 ******************************************************************/
	public ArrayList<IChessPiece> getDeadPieces(){
		return dead;
	}

	/*******************************************************************
	 * This class performes the move if possible, also puts dead
	 * non-pawns in the dead ArrayList. Also Switches the current player
	 * and lets you know if a pawn can be promoted
	 * 
	 * @param move this is the move that will be performed
	 ******************************************************************/
	public void move(Move move) {
		if ((board[move.toRow][move.toColumn] != null) &&
				(!board[move.toRow][move.toColumn].type().equals("Pawn"))) {
			dead.add(board[move.toRow][move.toColumn]);
		}
		board[move.toRow][move.toColumn] =
				board[move.fromRow][move.fromColumn];


		board[move.fromRow][move.fromColumn] = null;

		if ((move.toRow == 0 || move.toRow == 7) &&
				(board[move.toRow][move.toColumn]
						.type().equals("Pawn"))) {
			canPromotePiece = true;
		}

		player = player.next();
	}
	
	/*******************************************************************
	 * This method looks to see if the player is in check and then 
	 * returns if that player is in check
	 * 
	 * @param p this is the player you want to see if they are in check
	 *  
	 * @return if player p is in check
	 ******************************************************************/
	public boolean inCheck(Player p) {
		Player oppColor = p.next();
		int kingRow = -1;
		int kingColumn = -1;

		for(int row = 0;row < 8; row++)
			for(int col = 0; col < 8; col++)
				if((board[row][col] instanceof King) && 
						(board[row][col].player() == p)){
					kingRow = row;
					kingColumn = col;
					break;
				}
		for(int row = 0;row < 8; row++)
			for(int col = 0; col < 8; col++){
				if( (board[row][col] != null) &&
						(board[row][col].player() == oppColor) &&
						(board[row][col].isValidMove(new Move(row,col,
								kingRow,kingColumn), board))){

					return true;
				}
			}		
		return false;
	}

	
	/*******************************************************************
	 * Returns the current player
	 * 
	 * @return the current player
	 ******************************************************************/
	public Player currentPlayer() {
		return player;
	}

	/*******************************************************************
	 * Gets a piece at the row and column then returns it
	 * 
	 * @param row is the row for the selected piece
	 * @param column is the column for the selected piece
	 * 
	 * @return the IChessPiece at this location
	 ******************************************************************/
	public IChessPiece pieceAt(int row, int column) {
		return board[row][column];
	}

	/*******************************************************************
	 * lets you know if the move is valid without worrying about putting
	 * any player in check
	 * 
	 * @param move the move being tested
	 * 
	 * @return if that move is possible on the board
	 ******************************************************************/
	public boolean rawValidMove(Move move){
		if(board[move.fromRow][move.fromColumn].isValidMove(move, board)){
			return true;
		}
		return false;
	}

	/*******************************************************************
	 *  Removes the pawn at this row and col and returns it with 
	 *  whatever chesspiece has type type.
	 * 
	 * @param row that is being replaced
	 * @param col that is being replaced
	 * @param type that is replacing the pawn
	 ******************************************************************/
	public void promotePawn(int row, int col, String type) {
		IChessPiece p;
		
		if (type.equals("Knight")) {
			p = new Knight(player.next());
		}
		else if (type.equals("Rook")) {
			p = new Rook(player.next());
		}
		else if (type.equals("Bishop")) {
			p = new Bishop(player.next());
		}
		else {
			p = new Queen(player.next());
		}
		
		board[row][col] = p;
		
		for (int i = 0; i < dead.size(); i++) {
			if (dead.get(i).type().equals(type)) {
				dead.remove(i);
				break;
			}
		}

		canPromotePiece = false;
	}
}
