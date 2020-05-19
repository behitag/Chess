# Chess
Interactive Chess game with 2 levels of Difficulty (Maven Project)
<br>Level 1 : random - does random (but allowed!) moves
<br>Level 2: thinks  - compares all scenarios after 2 moves depth and choose the best one
<br>coded with Javafx
<br>designed and written by Behrouz Taghizadeh, April 2018
<br>to run the executable jar, build the project mit maven and then run "java -jar target\Chess-0.0.1-SNAPSHOT-jar-with-dependencies.jar"
<br>-------------------------------------------
<br><b>How it works:</b>        	
	<br> -> It splits the whole project into  11 classes;
	<br> -> 1 super class Piece with 6 subclasses + class Board, class Start, class PieceMove and an interface.
	<br> -> superclass Piece stimulates a real piece on chess board and has logically these subclasses: King, Queen, Rook, Bishop, Knight and Pawn.
	<br> -> Class Board contains everything related to the GUI; the Graphics, all pieces and the star position of the game and the chess board itself and a small InfoBox for the user.
	<br> -> class Start contains the main class and is used as a reference. It contains some static variables which are called regularly from other classes, including a 2D-array of class Piece, which stimulates the chess Board with each Piece on it.
	<br> -> each concrete Piece inheritances abstract methods getAllowedCells(), checkTheMove() and Move() from the superclass Piece.        
        <br> -> getAllowedCells() returns all squares where its Piece can go(dependent on how the Piece moves, for example Rook goes straight, and the current position ob the board, for example if one another piece is on the way). These values are then passed to the method checkTheMove(). 
	<br> -> checkTheMove() then controls if the move causes a check for the own's king! if yes, the move is of course not allowed! 
	<br> -> If the move is allowed, true is passed to the method move, which finally does the move and changes the Pieces in the reference 2D-array and in the GUI.
        <br> -> The function checkTheMove() has the most computation expense.
	<br> -> There are 2 difficulty levels. the first one does just a random move. Second level "thinks" two moves. It calculates all possible positions after 2 moves and counts the Pieces on the board and gives each position a value. It then takes the move with the most black points and least white points.
	<br> ->  If there are more moves of the same point, it chooses one of them randomly.
	<br> -> The moves are stored as objects of the class PieceMove.
	<br> ->  The program understands Castle-King.
