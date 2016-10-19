package Chess;

import static org.junit.Assert.*;

import org.junit.Test;

public class chessJUnits {

	//tries moving 1 forward with a pawn
	@Test
	public void pawnMovement1() {
		ChessModel model = new ChessModel();
		
		assertTrue(model.isValidMove(new Move(6,0,5,0)));
	}
	
	//tries moving 2 forward with a pawn
	@Test
	public void pawnMovement2() {
		ChessModel model = new ChessModel();
		
		assertTrue(model.isValidMove(new Move(6,0,4,0)));
	}

	//tries capturing with a pawn
	@Test
	public void pawnMovement3() {
		ChessModel model = new ChessModel();
		
		model.move(new Move(6,0,4,0));
		model.move(new Move(1,1,3,1));
		
		assertTrue(model.isValidMove(new Move(4,0,3,1)));
	}
	
	//tries moving with a knight
	@Test
	public void knightMovement1() {
		ChessModel model = new ChessModel();
		
		assertTrue(model.isValidMove(new Move(7,1,5,0)));
	}
	
	//tries moving with a knight onto friendly
	@Test
	public void knightMovement2() {
		ChessModel model = new ChessModel();
		
		assertFalse(model.isValidMove(new Move(7,1,6,3)));
	}
	
	//tries moving a bishop
	@Test
	public void bishopMovement1() {
		ChessModel model = new ChessModel();
		model.move(new Move(6,1,5,1));
		model.move(new Move(1,1,2,1));
		assertTrue(model.isValidMove(new Move(7,2,5,0)));
	}
	
	//tries moving a rook forward
	@Test
	public void rookMovement1() {
		ChessModel model = new ChessModel();
		model.move(new Move(6,0,4,0));
		assertTrue(model.isValidMove(new Move(7,0,5,0)));
	}
	
	//tries moving a rook to the right
	@Test
	public void rookMovement2() {
		ChessModel model = new ChessModel();
		model.move(new Move(7,1,4,4));
		assertTrue(model.isValidMove(new Move(7,0,7,1)));
	}
	
	//tries to castle
	@Test
	public void Castle() {
		ChessModel model = new ChessModel();
		model.move(new Move(7,1,4,4));
		model.move(new Move(7,2,4,4));
		model.move(new Move(7,3,4,4));
		assertTrue(model.isValidMove(new Move(7,4,7,2)));
	}
	
	//tries move queen like rook
	@Test
	public void queenMoveRook() {
		ChessModel model = new ChessModel();
		model.move(new Move(6,3,0,0));
		assertTrue(model.isValidMove(new Move(7,3,4,3)));
	}
	
	//tries move queen like bishop
	@Test
	public void queenMoveBishop() {
		ChessModel model = new ChessModel();
		model.move(new Move(6,4,0,0));
		assertTrue(model.isValidMove(new Move(7,3,5,5)));
	}
	
	//tests check
	@Test
	public void check() {
		ChessModel model = new ChessModel();
		model.move(new Move(6,4,1,3));
		assertTrue(model.inCheck(Player.BLACK));
	}
	
	//tests checkmate
	@Test
	public void checkmate() {
		ChessModel model = new ChessModel();
		model.move(new Move(6,4,5,4));
		model.move(new Move(1,0,2,0));
		model.move(new Move(7,3,3,7));
		model.move(new Move(2,0,3,0));
		model.move(new Move(7,5,4,2));
		model.move(new Move(3,0,4,0));
		model.move(new Move(3,7,1,5));
		
		assertTrue(model.isComplete());
	}
	
	
	
	//tries to move onto itself
	@Test
	public void moveOntoSelf() {
		ChessModel model = new ChessModel();
		assertFalse(model.isValidMove(new Move(7,4,7,4)));
	}
}
