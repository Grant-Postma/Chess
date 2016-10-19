package Chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import Pieces.Bishop;
import Pieces.IChessPiece;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Queen;
import Pieces.Rook;

public class ChessPanel extends JPanel {

	/**
	 * Serial Version UID for serializing panels.
	 */
	private static final long serialVersionUID = 1L;

	private JButton[][] board;   
	private ChessModel model;

	private JPanel gameBoard;

	private JPanel logLayoutPanel;
	private JPanel logPanel;
	private JTextArea log;

	private Move move;

	private ImageIcon BlackQueenIcon;
	private ImageIcon BlackKingIcon;
	private ImageIcon BlackBishopIcon;
	private ImageIcon BlackKnightIcon;
	private ImageIcon BlackRookIcon;
	private ImageIcon BlackPawnIcon;

	private ImageIcon WhiteQueenIcon;
	private ImageIcon WhiteKingIcon;
	private ImageIcon WhiteBishopIcon;
	private ImageIcon WhiteKnightIcon;
	private ImageIcon WhiteRookIcon;
	private ImageIcon WhitePawnIcon;

	private JButton newGameButton;
	private JButton quitButton;

	private boolean isGameOver;



	// declare other instance variables as needed

	private ButtonListener buttonListener = new ButtonListener();

	public ChessPanel() {
		model = new ChessModel();
		board = new JButton[8][8];
		move = new Move(-1,-1,-1,-1);
		isGameOver = false;


		BlackQueenIcon=new ImageIcon(((new ImageIcon("Black Queen.png"))
				.getImage()).getScaledInstance
				(75, 75, java.awt.Image.SCALE_SMOOTH));

		BlackKingIcon=new ImageIcon(((new ImageIcon("Black King.png"))
				.getImage()).getScaledInstance
				(75, 75, java.awt.Image.SCALE_SMOOTH));

		BlackKnightIcon=new ImageIcon(((new
				ImageIcon("Black Knight.png"))
				.getImage()).getScaledInstance
				(75, 75, java.awt.Image.SCALE_SMOOTH));

		BlackBishopIcon=new ImageIcon(((new
				ImageIcon("Black Bishop.png"))
				.getImage()).getScaledInstance
				(75, 75, java.awt.Image.SCALE_SMOOTH));

		BlackRookIcon=new ImageIcon(((new ImageIcon("Black Rook.png"))
				.getImage()).getScaledInstance
				(75, 75, java.awt.Image.SCALE_SMOOTH));

		BlackPawnIcon=new ImageIcon(((new ImageIcon("Black Pawn.png"))
				.getImage()).getScaledInstance
				(75, 75, java.awt.Image.SCALE_SMOOTH));

		WhiteQueenIcon=new ImageIcon(((new ImageIcon("White Queen.png"))
				.getImage()).getScaledInstance
				(75, 75, java.awt.Image.SCALE_SMOOTH));

		WhiteKingIcon=new ImageIcon(((new ImageIcon("White King.png"))
				.getImage()).getScaledInstance
				(75, 75, java.awt.Image.SCALE_SMOOTH));

		WhiteKnightIcon=new ImageIcon(((new
				ImageIcon("White Knight.png"))
				.getImage()).getScaledInstance
				(75, 75, java.awt.Image.SCALE_SMOOTH));

		WhiteBishopIcon=new ImageIcon(((new
				ImageIcon("White Bishop.png"))
				.getImage()).getScaledInstance
				(75, 75, java.awt.Image.SCALE_SMOOTH));

		WhiteRookIcon=new ImageIcon(((new ImageIcon("White Rook.png"))
				.getImage()).getScaledInstance
				(75, 75, java.awt.Image.SCALE_SMOOTH));

		WhitePawnIcon=new ImageIcon(((new ImageIcon("White Pawn.png"))
				.getImage()).getScaledInstance
				(75, 75, java.awt.Image.SCALE_SMOOTH));



		//Constructing Game Board
		JPanel center;
		center = new JPanel();
		center.setLayout(new GridLayout(8, 8));
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				board[r][c] = new JButton();
				board[r][c].setPreferredSize(new Dimension(100, 100));
				if(c%2 == 0){
					if(r%2 == 0)
						board[r][c].setBackground(Color.WHITE);
					else
						board[r][c].setBackground(Color.LIGHT_GRAY);
				}
				else{
					if(r%2 == 0)
						board[r][c].setBackground(Color.LIGHT_GRAY);
					else
						board[r][c].setBackground(Color.WHITE);
				}

				board[r][c].addActionListener(buttonListener);
				center.add(board[r][c]);
			}
		}


		JPanel columnLabels;
		columnLabels = new JPanel();
		columnLabels.setLayout(new GridLayout(1, 8));
		for (int i = 1; i <= 8; i++) {
			columnLabels.add(new JLabel((new Integer(i)).toString(),
					JLabel.CENTER));
		}

		JPanel rowLabels;
		rowLabels = new JPanel();
		rowLabels.setLayout(new GridLayout(8, 1));
		for (char c = 65; c <= 72; c++) {
			rowLabels.add(new JLabel(" " + (char)c + " "));
		}

		gameBoard = new JPanel();
		gameBoard.setLayout(new BorderLayout());
		gameBoard.add(center, BorderLayout.CENTER);
		gameBoard.add(columnLabels, BorderLayout.NORTH);
		gameBoard.add(rowLabels, BorderLayout.WEST);
		gameBoard.add(new JLabel(" "), BorderLayout.SOUTH); //Spacing
		gameBoard.add(new JLabel("   "), BorderLayout.EAST); //Spacing

		//Constructing Move Log
		log = new JTextArea(49, 40);
		log.setEditable(false);
		DefaultCaret caret = (DefaultCaret)log.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		log.append("Welcome to Chess\n");
		log.append("\n" + model.currentPlayer() + " player's turn:\n");

		JScrollPane logScroll = new JScrollPane(log);
		logScroll.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		logPanel = new JPanel();
		logPanel.add(logScroll);

		JPanel buttonPanel = new JPanel();

		newGameButton = new JButton("Start New Game");
		newGameButton.addActionListener(buttonListener);
		buttonPanel.add(newGameButton);

		quitButton = new JButton("Quit");
		quitButton.addActionListener(buttonListener);
		buttonPanel.add(quitButton);

		logLayoutPanel = new JPanel();
		logLayoutPanel.setLayout(new BorderLayout());
		logLayoutPanel.add(new JLabel("  Action History"),
				BorderLayout.NORTH);
		logLayoutPanel.add(logPanel, BorderLayout.CENTER);
		logLayoutPanel.add(buttonPanel, BorderLayout.SOUTH);

		setLayout(new BorderLayout());
		add(gameBoard, BorderLayout.CENTER);
		add(logLayoutPanel, BorderLayout.EAST);




		//FIXME add another panel 'gameConrols' with two buttons
		//'Start New Game' on left and 'Quit' on right. Center both
		//below game board

		displayBoard();
	}

	private void displayBoard() {
		for (int c = 0; c < 8; c++)
			for (int r = 0; r < 8; r++) {
				if(model.pieceAt(r, c) == null) {
					board[r][c].setIcon(null);
					continue;
				}

				if (model.pieceAt(r, c).player() == Player.BLACK) {

					if (model.pieceAt(r, c) instanceof Queen)
						board[r][c].setIcon(BlackQueenIcon);

					else if (model.pieceAt(r, c) instanceof King)
						board[r][c].setIcon(BlackKingIcon);

					else if (model.pieceAt(r, c) instanceof Rook)
						board[r][c].setIcon(BlackRookIcon);

					else if (model.pieceAt(r, c) instanceof Knight)
						board[r][c].setIcon(BlackKnightIcon);

					else if (model.pieceAt(r, c) instanceof Bishop)
						board[r][c].setIcon(BlackBishopIcon);

					else if (model.pieceAt(r, c) instanceof Pawn)
						board[r][c].setIcon(BlackPawnIcon);
				}
				else {
					if (model.pieceAt(r, c) instanceof Queen)
						board[r][c].setIcon(WhiteQueenIcon);

					else if (model.pieceAt(r, c) instanceof King)
						board[r][c].setIcon(WhiteKingIcon);

					else if (model.pieceAt(r, c) instanceof Rook)
						board[r][c].setIcon(WhiteRookIcon);

					else if (model.pieceAt(r, c) instanceof Knight)
						board[r][c].setIcon(WhiteKnightIcon);

					else if (model.pieceAt(r, c) instanceof Bishop)
						board[r][c].setIcon(WhiteBishopIcon);

					else if (model.pieceAt(r, c) instanceof Pawn)
						board[r][c].setIcon(WhitePawnIcon);
				}
			}
	}

	private void printMove(Move move) {
		String startLoc = "" + (char)(move.fromRow+65) + "," +
				(move.fromColumn+1);
		String endLoc = "" + (char)(move.toRow+65) + "," +
				(move.toColumn+1);


		log.append("-" + model.pieceAt(
				move.fromRow, move.fromColumn).type() + " at "
				+ startLoc + " moved to " + endLoc + ".\n");

		if (model.pieceAt(move.toRow, move.toColumn) != null) {
			log.append("-" + model
					.pieceAt(move.toRow, move.toColumn).type() +
					" at " + (char)(move.toRow+65) + "," +
					(move.toColumn+1) + " captured.\n");
		}
	}

	private void promotePawn() {
		ArrayList<IChessPiece> picks =
				new ArrayList<IChessPiece>(model.getDeadPieces());


		for(int i = picks.size() - 1;
				i >= 0;i--) {
			if(picks.get(i).player() == model.currentPlayer()) {
				picks.remove(i);
			}
		}

		for (int i = 0; i < picks.size(); i++) {
			for (int j = i+1; j < picks.size(); j++) {
				if (picks.get(i).type().equals(picks.get(j).type())) {
					picks.remove(j);
					break;
				}
			}
		}

		String[] options = new String[picks.size()];
		for (int i = 0; i < picks.size(); i++) {
			options[i] = picks.get(i).type();
		}

		if (picks.size() != 0) {
			int p = JOptionPane.showOptionDialog(null,
					"Pick a piece" + " to promote to",
					"Piece Promotion",
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE,
					BlackPawnIcon, options,null);

			if (p == -1) {
				p = 0;
			}

			model.promotePawn(move.toRow, move.toColumn, options[p]);
		}
		else {
			model.promotePawn(move.toRow, move.toColumn, "Queen");
		}

		log.append("-Pawn at " + (char)(move.toRow+65) + "," +
				(move.toColumn+1) + " promoted to a " +
				model.pieceAt(move.toRow, move.toColumn).type() + "\n");
	}

	// Inner class that represents action listener for buttons
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {


			for (int r = 0; r < 8 ; r++){
				//Disables movement after Checkmate
				if(isGameOver) 
					break;

				for (int c = 0; c < 8; c++)
					if (board[r][c] == event.getSource()){
						if ( move.fromColumn == -1 && 
								model.pieceAt(r, c) != null &&
								model.pieceAt(r, c).player() == 
								model.currentPlayer()) {
							move.fromColumn = c;
							move.fromRow = r;
						}
						else if (move.fromColumn != -1) {
							move.toColumn = c;
							move.toRow = r;
							if (model.isValidMove(move)) {
								printMove(move);
								model.move(move);
								log.append("\n" + model.currentPlayer()
								+ " player's turn:\n");

								//Checking for promotion of pawn
								if (model.canPromotePiece()) {
									promotePawn();
								}

								displayBoard();

								if(model.inCheck(model.currentPlayer()))
								{
									log.append(model.currentPlayer() +
											" player is in check\n");

									if (model.isComplete()) {
										log.append("\n\nCheckmate!\n");
										log.append("" +
												model.currentPlayer()
										.next() + " wins!\n\n");
										log.append("Press Start New Game"
												+" Button to play again");
										isGameOver = true;
									}
								}
							}
							else if(model.inCheck(model.currentPlayer())
									&& model.rawValidMove(move)){
								log.append(model.currentPlayer() +
										" player is in check\n");
							}
							else {
								if(!(move.fromColumn == move.toColumn)&&
										!(move.fromRow == move.toRow)){
									log.append("-Invalid move\n");
								}

							}

							displayBoard();

							move = new Move(-1,-1,-1,-1);
						}
					}
			}
			if(event.getSource() == newGameButton){
				if(!isGameOver){
					int n = JOptionPane.showConfirmDialog(null,
							"Game not finished! \nAre you sure?");
					if((n == JOptionPane.CANCEL_OPTION) || 
							(n == JOptionPane.NO_OPTION) || (n == -1))
						return;
				}
				if(isGameOver)
					isGameOver = false;
				
				model = new ChessModel();
				displayBoard();
				log.setText("");
				log.append("Welcome to Chess\n");
				log.append("\n" + model.currentPlayer() +
						" player's turn:\n");
			}

			if(event.getSource() == quitButton){

				int n = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to quit?");
				if(n == JOptionPane.YES_OPTION)
					System.exit(0);			
			}

			
		}


	}

}


