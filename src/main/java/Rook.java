import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;

public class Rook extends Piece{
	private final static String name = "Rook";
	private static boolean isMoved_w1 = false;
	private static boolean isMoved_w2 = false;
	private static boolean isMoved_b1 = false;
	private static boolean isMoved_b2 = false;
	
	//Super Constructor with color, id
	public Rook(int color, int id) {
		super(color, id);	
		
		if(color == Chess.WHITE)
			{
			super.setImage(new ImagePattern(new Image(getClass().getResourceAsStream("wr.png"))));
			super.setImageGray(new ImagePattern(new Image(getClass().getResourceAsStream("wrg.png"))));
			}
		else
			{
			super.setImage(new ImagePattern(new Image(getClass().getResourceAsStream("br.png"))));
			super.setImageGray(new ImagePattern(new Image(getClass().getResourceAsStream("brg.png"))));
			}
	}

	//get all cells where this Rook can go to
	@Override
	public boolean[][] getAllowedCells() {

		//create a Board object to see if the cell is occupied
		Main board = new Main();	
		
		//set all fileds equal to false
		for(int i=0; i<8; i++)
		for(int j=0; j<8; j++) {
			allowedCells[i][j] = false;			
		}
		
		//from Rook current cell to the Left
		for(int i=getX()-1; i>=0; i--) {
			if(board.getPiece(i, getY())!=null && board.getPiece(i, getY()).getColor()==getColor())
			{
				break;
			}
				
			if(board.getPiece(i, getY())!=null && board.getPiece(i, getY()).getColor()!=getColor()) {
				allowedCells[i][getY()] = true;
				break;
			}
			allowedCells[i][getY()] = true;	
		}
		
		//from Rook current cell to the Right
		for(int i=getX()+1; i<8; i++) {
			if(board.getPiece(i, getY())!=null && board.getPiece(i, getY()).getColor()==getColor())
			{
				break;
			}
			if(board.getPiece(i, getY())!=null && board.getPiece(i, getY()).getColor()!=getColor()) {
				allowedCells[i][getY()] = true;
				break;
			}
			allowedCells[i][getY()] = true;		
		}
		
		//from Rook current cell to the Down
		for(int i=getY()-1; i>=0; i--) {
			if(board.getPiece(getX(), i)!=null && board.getPiece(getX(), i).getColor()==getColor())
			{
				break;
			}
			if(board.getPiece(getX(), i)!=null && board.getPiece(getX(), i).getColor()!=getColor()) {
				allowedCells[getX()][i] = true;
				break;
			}
			allowedCells[getX()][i] = true;		
		}
		
		//from Rook current cell to the Up
		for(int i=getY()+1; i<8; i++) {
			if(board.getPiece(getX(), i)!=null && board.getPiece(getX(), i).getColor()==getColor())
			{				
				break;
			}
			if(board.getPiece(getX(), i)!=null && board.getPiece(getX(), i).getColor()!=getColor()) {
				allowedCells[getX()][i] = true;
				break;
			}
			allowedCells[getX()][i] = true;		
		}
		
	return allowedCells;		
	}
	
	public void move(int x, int y) {			
		int defaultX = super.getX();
		int defaultY = super.getY();	
		start.setReservePiece(null);		
	
		if(checkTheMove(x, y) == false)					
			System.out.println("Move not allowed!");
				
		else
			{
			if(super.getColor()==Chess.WHITE && defaultX==0)
				isMoved_w1 = true;
			if(super.getColor()==Chess.WHITE && defaultX==7)
				isMoved_w2 = true;
			if(super.getColor() == Chess.BLACK && defaultX==0)
				isMoved_b1 = true;
			if(super.getColor()==Chess.BLACK && defaultX==7)
				isMoved_b2 = true;
			
			super.setX(x);
			super.setY(y);
			Main.setPieceInCell(start.getPiece(defaultX, defaultY), x, y);			
			Main.pieceField[defaultX][defaultY] = null;

			if((x+7-y)%2==0)
				Board.getRectangle(x, 7-y).setFill(super.getImage());
			else
				Board.getRectangle(x, 7-y).setFill(super.getImageGray());
			
			if((defaultX+7-defaultY)%2==0)
				Board.getRectangle(defaultX, 7-defaultY).setFill(Color.TRANSPARENT);
			else
				Board.getRectangle(defaultX, 7-defaultY).setFill(Color.LIGHTGRAY);
			
			}	
	}
	
	public boolean checkTheMove(int x, int y) {	
		Main start = new Main();
		boolean[][] thisArray = getAllowedCells();
		start.setReservePiece(null);		
		int defaultX = super.getX();
		int defaultY = super.getY();
		
		//Check if this move is within the allowedCells Array
		for(int i=0; i<8; i++)
		for(int j=0; j<8; j++)
		{		
			
		if(thisArray[i][j]==true && i==x && j==y)
		{
			if(start.getPiece(x, y)!=null)
				start.setReservePiece(start.getPiece(x, y));
			
			Main.setPieceInCell(new Rook(super.getColor(), super.getId()), x, y);
			Main.pieceField[defaultX][defaultY] = null;			

			if(super.getColor() == Chess.WHITE)
			{
				for(int v=0; v<8; v++)
				for(int w=0; w<8; w++)
				{
					if(start.getPiece(v, w)!=null && start.getPiece(v, w).getColor()==Chess.BLACK)
					{
						boolean[][] thisArray2 = start.getPiece(v, w).getAllowedCells();
						for(int m=0; m<8; m++)
						for(int n=0; n<8; n++)
						{
						//System.out.println(m + ", " + n + ", " + thisArray2[3][4]);
						//if the move causes a check, then return the move and give an error
						if(thisArray2[m][n]==true && start.getWhiteKingX()==m && start.getWhiteKingY()==n)
						{	
							Main.setPieceInCell(new Rook(super.getColor(), super.getId()), defaultX, defaultY);
							if(start.getReservePiece()!=null)
								Main.pieceField[x][y] = start.getReservePiece();
							else
								Main.pieceField[x][y] = null;							
							
							return false;
						}
						}
					}				
				}
				
				//System.out.println("CHECKMOVE: Allowed");
				Main.setPieceInCell(new Rook(super.getColor(), super.getId()), defaultX, defaultY);				
				
				if(start.getReservePiece()!=null)
					Main.pieceField[x][y] = start.getReservePiece();
				else
					Main.pieceField[x][y] = null;
				
				return true;
			}
			
			if(super.getColor() == Chess.BLACK)
			{
				for(int v=0; v<8; v++)
				for(int w=0; w<8; w++)
				{
					if(start.getPiece(v, w)!=null && start.getPiece(v, w).getColor()==Chess.WHITE)
					{
						boolean[][] thisArray2 = start.getPiece(v, w).getAllowedCells();
						for(int m=0; m<8; m++)
						for(int n=0; n<8; n++)
						{
						if(thisArray2[m][n]==true && start.getBlackKingX()==m && start.getBlackKingY()==n)
						{
							//System.out.println("CHECKMOVE: NOT ALLOWED");
							Main.setPieceInCell(new Rook(super.getColor(), super.getId()), defaultX, defaultY);
							if(start.getReservePiece()!=null)
								Main.pieceField[x][y] = start.getReservePiece();
							else
								Main.pieceField[x][y] = null;
							
							return false;
						}
						}
					}				
				}
				//System.out.println("CHECKMOVE: ALLOWED");
				Main.setPieceInCell(new Rook(super.getColor(), super.getId()), defaultX, defaultY);				
				
				if(start.getReservePiece()!=null)
					Main.pieceField[x][y] = start.getReservePiece();
				else
					Main.pieceField[x][y] = null;
				
				return true;		
			}			
		}	
		
		//System.out.printf("%s, %s, %b\n", i, j, thisArray[i][j]);
		}		
		//System.out.println("CHECKMOVE: NOT ALLOWED");
		
		Main.setPieceInCell(new Rook(super.getColor(), super.getId()), defaultX, defaultY);		
		if(start.getReservePiece()!=null)
			Main.pieceField[x][y] = start.getReservePiece();
		
		return false;
	}
	
	public int getValue() {
		return Chess.ROOK_VALUE;
	}

	public boolean getIsMoved_w1() {
		return isMoved_w1;
	}
	
	public void setIsMoved_w1(boolean isMoved_w1) {
		Rook.isMoved_w1 = isMoved_w1;
	}
	
	public boolean getIsMoved_w2() {
		return isMoved_w2;
	}
	
	public void setIsMoved_w2(boolean isMoved_w2) {
		Rook.isMoved_w2 = isMoved_w2;
	}
	
	public boolean getIsMoved_b1() {
		return isMoved_b1;
	}
	
	public void setIsMoved_b1(boolean isMoved_b1) {
		Rook.isMoved_b1 = isMoved_b1;
	}
	
	public boolean getIsMoved_b2() {
		return isMoved_b2;
	}
	
	public void setIsMoved_b2(boolean isMoved_b2) {
		Rook.isMoved_b2 = isMoved_b2;
	}
	
	@Override
	public String getName() {
		return name;
	}

}