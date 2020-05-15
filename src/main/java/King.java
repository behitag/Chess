import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class King extends Piece{
	private final static String name = "King";
	private static boolean isMoved_w = false;
	private static boolean isMoved_b = false;
	Rook rook = new Rook(Chess.WHITE, 9);

	public King(int color, int id) {
		super(color, id);
		
		if(color == Chess.WHITE)
			{
			super.setImage(new ImagePattern(new Image(getClass().getResourceAsStream("wk.png"))));
			super.setImageGray(new ImagePattern(new Image(getClass().getResourceAsStream("wkg.png"))));
			}
		else
			{
			super.setImage(new ImagePattern(new Image(getClass().getResourceAsStream("bk.png"))));
			super.setImageGray(new ImagePattern(new Image(getClass().getResourceAsStream("bkg.png"))));
			}
	}

	//@Override
	public boolean[][] getAllowedCells() {
		Main board = new Main();
		
		//set all fileds equal to false
		for(int i=0; i<8; i++)
		for(int j=0; j<8; j++)
		allowedCells[i][j] = false;
		
		//for CastleKing
		if(super.getColor()==Chess.WHITE) {
			if(super.getX()==4 && super.getY()==0 && start.getPiece(5, 0)==null && start.getPiece(6, 0)==null)
				allowedCells[6][0] = true;
			if(super.getX()==4 && super.getY()==0 && start.getPiece(3, 0)==null && start.getPiece(2, 0)==null && start.getPiece(1, 0)==null)
				allowedCells[2][0] = true;
			}
		else {
			if(super.getX()==4 && super.getY()==7 && start.getPiece(5, 7)==null && start.getPiece(6, 7)==null)
				allowedCells[6][7] = true;
			if(super.getX()==4 && super.getY()==7 && start.getPiece(3, 7)==null && start.getPiece(2, 7)==null && start.getPiece(1, 7)==null)
				allowedCells[2][7] = true;			
		}				
		
		for(int i=getX()-1; i<=getX()+1; i++)
		for(int j=getY()-1; j<=getY()+1; j++) {
			if(i<0 || i>=8 || j<0 || j>=8)
				continue;
			if(i==getX() && j== getY())
				continue;			
			if(board.getPiece(i, j)!=null && board.getPiece(i, j).getColor()==getColor())
				continue;
			
			allowedCells[i][j] = true;
		}
		
		return allowedCells;
	}
	
	public void move(int x, int y) {
		int defaultX = super.getX();
		int defaultY = super.getY();	
		start.setReservePiece(null);
		
		if(checkTheMove(x, y) == false)
		{			
			System.out.println("...");
		}		
		else
		{		
			super.setX(x);
			super.setY(y);
			if(super.getColor() == Chess.WHITE)
				{
				start.setWhiteKingXY(x, y);
				isMoved_w = true;
				}
			else
				{
				start.setBlackKingXY(x, y);
				isMoved_b = true;
				}
			
			Main.setPieceInCell(new King(super.getColor(), super.getId()), x, y);			
			Main.pieceField[defaultX][defaultY] = null;			
			if((x+7-y)%2==0)
				Board.getRectangle(x, 7-y).setFill(super.getImage());
			else
				Board.getRectangle(x, 7-y).setFill(super.getImageGray());
			
			if((defaultX+7-defaultY)%2==0)
				Board.getRectangle(defaultX, 7-defaultY).setFill(Color.TRANSPARENT);
			else
				Board.getRectangle(defaultX, 7-defaultY).setFill(Color.LIGHTGRAY);
			
			//Castle-King
			if(super.getColor() == Chess.WHITE && defaultX==4 && x==6)
				{
				Main.setPieceInCell(new Rook(super.getColor(), super.getId()), x-1, y);			
				Main.pieceField[6][0] = null;
				
				if((x-1+7-y)%2==0)
					Board.getRectangle(x-1, 7-y).setFill(new Rook(Chess.WHITE, 9).getImage());
				else
					Board.getRectangle(x-1, 7-y).setFill(new Rook(Chess.WHITE, 9).getImageGray());
				
				Board.getRectangle(7, 7).setFill(Color.TRANSPARENT);				
				
				start.setWhiteKingXY(x, y);
				rook.setIsMoved_w2(true);
				}
			if(super.getColor() == Chess.WHITE && defaultX==4 && x==2)
				{
				Main.setPieceInCell(new Rook(super.getColor(), super.getId()), x+1, y);			
				Main.pieceField[0][0] = null;
				
				if((x+1+7-y)%2==0)
					Board.getRectangle(x+1, 7-y).setFill(new Rook(Chess.WHITE, 9).getImage());
				else
					Board.getRectangle(x+1, 7-y).setFill(new Rook(Chess.WHITE, 9).getImageGray());
				
				Board.getRectangle(0, 7).setFill(Color.LIGHTGRAY);				
				
				start.setWhiteKingXY(x, y);
				rook.setIsMoved_w1(true);
				}	
			if(super.getColor() == Chess.BLACK && defaultX==4 && x==6)
				{
				Main.setPieceInCell(new Rook(super.getColor(), super.getId()), x-1, y);			
				Main.pieceField[6][7] = null;
				
				if((x-1+7-y)%2==0)
					Board.getRectangle(x-1, 7-y).setFill(new Rook(Chess.BLACK, 9).getImage());
				else
					Board.getRectangle(x-1, 7-y).setFill(new Rook(Chess.BLACK, 9).getImageGray());
				
				Board.getRectangle(7, 0).setFill(Color.LIGHTGRAY);
				start.setBlackKingXY(x, y);
				rook.setIsMoved_b2(true);
				}
			if(super.getColor() == Chess.BLACK && defaultX==4 && x==2)
				{
				Main.setPieceInCell(new Rook(super.getColor(), super.getId()), x+1, y);			
				Main.pieceField[0][7] = null;
				
				if((x+1+7-y)%2==0)
					Board.getRectangle(x+1, 7-y).setFill(new Rook(Chess.BLACK, 9).getImage());
				else
					Board.getRectangle(x+1, 7-y).setFill(new Rook(Chess.BLACK, 9).getImageGray());
				
				Board.getRectangle(0, 0).setFill(Color.TRANSPARENT);
				
				start.setBlackKingXY(x, y);
				rook.setIsMoved_b1(true);
				}
			
			if(getColor() == Chess.BLACK)
				Board.addBlackBorderRectangles(x, y, defaultX, defaultY);
		}
	}
	
	public boolean checkTheMove(int x, int y) {
	
		Main start = new Main();
		boolean[][] thisArray = getAllowedCells();
		start.setReservePiece(null);		
		int defaultX = super.getX();
		int defaultY = super.getY();
		/*
		for(int i=0; i<8; i++)
		for(int j=0; j<8; j++)
		System.out.printf("%s, %s, %b\n", i, j, thisArray[i][j]);
		*/
		//Check if this move is within the allowedCells Array
		for(int i=0; i<8; i++)
		for(int j=0; j<8; j++)
		{		
	
		//System.out.printf("%s, %s, %b\n", i, j, thisArray[i][j]);
		if(thisArray[i][j]==true && i==x && j==y)
		{
			if(start.getPiece(x, y)!=null)
				start.setReservePiece(start.getPiece(x, y));
			
			Main.setPieceInCell(new King(super.getColor(), super.getId()), x, y);
			Main.pieceField[defaultX][defaultY] = null;			
			
			if(super.getColor() == Chess.WHITE)
			{
				for(int v=0; v<8; v++)
				for(int w=0; w<8; w++)
				{
					//go through all Cells and do this for every black Piece: 
					if(start.getPiece(v, w)!=null && start.getPiece(v, w).getColor()==Chess.BLACK)
					{
						boolean[][] thisArray2 = start.getPiece(v, w).getAllowedCells();
						for(int m=0; m<8; m++)
						for(int n=0; n<8; n++)
						{
						//System.out.println(m + ", " + n + ", " + thisArray2[3][4]);
						//if the move causes a check, then return the move and give an error
						if(thisArray2[m][n]==true && x==m && y==n)
						{	
							//System.out.println("CHECKMOVE: NOT ALLOWED");
							Main.setPieceInCell(new King(super.getColor(), super.getId()), defaultX, defaultY);
							if(start.getReservePiece()!=null)
								Main.pieceField[x][y] = start.getReservePiece();
							else
								Main.pieceField[x][y] = null;
							
							return false;
						}
						
						if(defaultX==4 && defaultY==0 && x==6 && y==0 && (isMoved_w == true || rook.getIsMoved_w2()==true))
							{
							Main.setPieceInCell(new King(super.getColor(), super.getId()), defaultX, defaultY);
							Main.pieceField[x][y] = null;
							return false;						
							}
						if(defaultX==4 && defaultY==0 && x==0 && y==0 && (isMoved_w == true || rook.getIsMoved_w1()==true))
							{
							Main.setPieceInCell(new King(super.getColor(), super.getId()), defaultX, defaultY);
							Main.pieceField[x][y] = null;
							return false;						
							}
						
						}
					}				
				}
				
				//System.out.println("CHECKMOVE: Allowed");
				Main.setPieceInCell(new King(super.getColor(), super.getId()), defaultX, defaultY);
				
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
						if(thisArray2[m][n]==true && x==m && y==n)
						{
							//System.out.println("CHECKMOVE: NOT ALLOWED");
							Main.setPieceInCell(new King(super.getColor(), super.getId()), defaultX, defaultY);
							if(start.getReservePiece()!=null)
								Main.pieceField[x][y] = start.getReservePiece();
							else
								Main.pieceField[x][y] = null;
							
							return false;
						}
						
						if(defaultX==4 && defaultY==7 && x==6 && y==7 && (isMoved_b == true || rook.getIsMoved_b2()==true))
							{
							Main.setPieceInCell(new King(super.getColor(), super.getId()), defaultX, defaultY);
							Main.pieceField[x][y] = null;
							return false;						
							}
						if(defaultX==4 && defaultY==7 && x==0 && y==7 && (isMoved_b == true || rook.getIsMoved_b1()==true))
							{
							Main.setPieceInCell(new King(super.getColor(), super.getId()), defaultX, defaultY);
							Main.pieceField[x][y] = null;
							return false;						
							}	
									
						}
					}				
				}
				//System.out.println("CHECKMOVE: ALLOWED");				
				Main.setPieceInCell(new King(super.getColor(), super.getId()), defaultX, defaultY);
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
		
		Main.setPieceInCell(new King(super.getColor(), super.getId()), defaultX, defaultY);		
		if(start.getReservePiece()!=null)
			Main.pieceField[x][y] = start.getReservePiece();
		
		return false;
	}

	public boolean isCheck() {
		for(int i=0; i<8; i++)
		for(int j=0; j<8; j++)
		if(start.getPiece(i, j)!=null && start.getPiece(i, j).getColor()!=super.getColor())
		if(start.getPiece(i, j).checkTheMove(super.getX(), super.getY()))
		{
			return true;
		}
		
		return false;		
	}
	
	public int getValue() {
		return 0;
	}

	//@Override
	public String getName() {
		return name;
	}

}