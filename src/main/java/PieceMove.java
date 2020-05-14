public class PieceMove {
	private Piece piece;
	private int x;
	private int y;
	private int fromX;
	private int fromY;
	private int worstDiff;
	
	public PieceMove(Piece piece, int x, int y) {
		this.piece = piece;
		this.x = x;
		this.y = y;		
	}
	
	public PieceMove(Piece piece, int x, int y, int fromX, int fromY) {
		this.piece = piece;
		this.x = x;
		this.y = y;
		this.fromX = fromX;
		this.fromY = fromY;		
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getFromX() {
		return fromX;
	}
	
	public void setFromX(int fromX) {
		this.fromX = fromX;
	}
	
	public int getFromY() {
		return fromY;
	}
	
	public void setFromY(int fromY) {
		this.fromY = fromY;
	}
	
	public void setWorstDiff(int worstDiff) {
		this.worstDiff = worstDiff;
	}
	
	public int getWorstDiff() {
		return worstDiff;
	}
}
