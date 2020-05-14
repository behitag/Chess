/*
***************************************************************************************************
* Written with JAVAFX
* It splits the whole project into  11 classes;
* 1 super class Piece with 6 subclasses, class Board, class Start, class PieceMove and an interface.
* superclass Piece stimulates a real piece on chess board and has logically these subclasses:
* King, Queen, Rook, Bishop, Knight and Pawn.
* Class Board contains everything related to the GUI; the Graphics, all pieces and the
* starting position of the game and the chess board itself and a small InfoBox for the user.
* the class Start contains the main class and is used as a reference. It contains some static 
* variables which are called regularly from other classes, including a 2D-array of class Piece,
* which stimulates the chess Board with each Piece on it.
* each concrete Piece inheritances abstract methods getAllowedCells, checkTheMove and Move from 
* the superclass Piece. getAllowedCells returns all squares where its Piece can go(dependent on how 
* the Piece moves, for example Rook goes straight). These values are then passed to the method 
* checkTheMove. It controls, if the move causes a check for itself! if yes, the move is of course
* not allowed! If the move is allowed, true is passed to the method move, which finally does 
* the move and changes the Pieces in the reference 2D-array. The function checkTheMove has 
* the most computation expense.
* There are 2 difficulty levels. the first one does just a random move. Second level "thinks"
* two moves. It calculates all possible positions after 2 moves and counts the Pieces on the board and 
* gives each position a point. It then takes the move with the most black points and least white points.
* If there are more moves of the same point, it chooses one of them randomly.
* The moves are stored as objects of the class PieceMove.
* The program understands Castle-King.
* designed and written by Behrouz Taghizadeh, April 2018.
*******************************************************************************************************
*/

import javafx.application.Application;
	
public class Main
{
	private static King whiteKing = new King(Chess.WHITE, 16);
	private static King blackKing = new King(Chess.BLACK, 16);
	private static Piece reservePiece;
	protected static Piece[][] pieceField = new Piece[8][8];
	
	public static void main(String[] args) throws Exception 
	{
		Application.launch(Start.class);			
		initializePieceField();	
		System.out.println("ENDE GELAENDE");		
	}
	
	public Piece getPiece(int x, int y) {
		return pieceField[x][y];
	}	
	
	//set All Piecefields equal to null
	public static void initializePieceField() {
		for(int i=0; i<8; i++)
		for(int j=0; j<8; j++)
			pieceField[i][j] = null;
					
	}
	
	public static void setPieceInCell(Piece piece, int x, int y) {
		piece.setX(x);
		piece.setY(y);
		pieceField[x][y] = piece;		
		//Board.getRectangle(x, 7-y).setFill(piece.getImage());		
	}
		
	public int getWhiteKingX() {
		return whiteKing.getX();
	}
	public int getWhiteKingY() {
		return whiteKing.getY();
	}
	public int getBlackKingX() {
		return blackKing.getX();
	}
	public int getBlackKingY() {
		return blackKing.getY();
	}
	public void setWhiteKingXY(int x, int y) {		
		whiteKing.setX(x);
		whiteKing.setY(y);
		setPieceInCell(whiteKing, x, y);
	}
	public void setBlackKingXY(int x, int y) {
		blackKing.setX(x);
		blackKing.setY(y);
		setPieceInCell(blackKing, x, y);
	}
	
	public Piece getWhiteKing() {
		return whiteKing;
	}
	
	public Piece getBlackKing() {
		return blackKing;
	}
	
	public void setReservePiece(Piece piece) {
		reservePiece = piece;
	}
	
	public Piece getReservePiece() {
		return reservePiece;
	}
}