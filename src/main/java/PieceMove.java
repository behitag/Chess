/**
 * simulates a move in chess. a Piecemove is a move with a piece
 * the way how gatherAllMoves-method gathers all possible moves
 * @author Behrouz
 *
 */
public class PieceMove {
	private Piece piece;
	private int x;
	private int y;
	private int fromX;
	private int fromY;
	private int worstDiff;
	
	/**
	 * 1st constructor with piece and the target cell
	 * @param piece the Piece to move
	 * @param x x-coordinate of the target-cell
	 * @param y y-coordinate of the target-cell
	 */
	public PieceMove(Piece piece, int x, int y) {
		this.piece = piece;
		this.x = x;
		this.y = y;		
	}
	
	/**
	 * 2nd constructor with piece, target cell and inital cell
	 * @param piece the piece to move
	 * @param x x-coordinate of the target cell
	 * @param y y-coordinate of the target cell
	 * @param fromX x-coordinate of the initial cell
	 * @param fromY y-coordinate of the inital cell
	 */
	public PieceMove(Piece piece, int x, int y, int fromX, int fromY) {
		this.piece = piece;
		this.x = x;
		this.y = y;
		this.fromX = fromX;
		this.fromY = fromY;		
	}
	
	/**
	 * returns the piece which moves
	 * @return Piece
	 */
	public Piece getPiece() {
		return piece;
	}
	
	/**
	 * return x-coordinate of the target cell of the move
	 * @return int
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * return y-coordinate of the target cell of the move
	 * @return int
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * return x-coordinate of the initial cell of the move
	 * @return int
	 */
	public int getFromX() {
		return fromX;
	}
	
	/**
	 * sets the x-coordinate of the initial cell of the move
	 * @param fromX
	 */
	public void setFromX(int fromX) {
		this.fromX = fromX;
	}
	
	/**
	 * return y-coordinate of the initial cell of the move
	 * @return int
	 */
	public int getFromY() {
		return fromY;
	}
	
	/**
	 * sets the initial y-coordinate of the move
	 * @param fromY
	 */
	public void setFromY(int fromY) {
		this.fromY = fromY;
	}
	
	/**
	 * sets the worse difference between values of white and black pieces
	 * is used for finding the best move
	 * @param worstDiff the worst difference
	 */
	public void setWorstDiff(int worstDiff) {
		this.worstDiff = worstDiff;
	}
	
	/**
	 * returns the worse difference between values of white and black pieces
	 * is used for finding the best move
	 * @return int
	 */
	public int getWorstDiff() {
		return worstDiff;
	}
}
