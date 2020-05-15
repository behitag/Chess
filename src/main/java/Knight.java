import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Knight extends Piece{
	private final static String name = "Knight";

	public Knight(int color, int id) {
		super(color, id);
		
		if(color == Chess.WHITE)
			{
			super.setImage(new ImagePattern(new Image("wn.png")));
			super.setImageGray(new ImagePattern(new Image("wng.png")));
			}
		else
			{
			super.setImage(new ImagePattern(new Image("bn.png")));
			super.setImageGray(new ImagePattern(new Image("bng.png")));
			}
	}

	//@Override
	public boolean[][] getAllowedCells() {
		Main board = new Main();
		//set all fileds equal to false
		for(int i=0; i<8; i++)
		for(int j=0; j<8; j++)
		allowedCells[i][j] = false;
		
		int i, j;
		
		i=getX()-1; j=getY()-2;
		if(i>=0 && i<8 && j>=0 && j<8)
		if(board.getPiece(i, j)==null || board.getPiece(i, j).getColor()!=getColor())
			allowedCells[i][j] = true;
		
		i=getX()-2; j=getY()-1;
		if(i>=0 && i<8 && j>=0 && j<8)
		if(board.getPiece(i, j)==null || board.getPiece(i, j).getColor()!=getColor())
			allowedCells[i][j] = true;
		
		i=getX()-2; j=getY()+1;
		if(i>=0 && i<8 && j>=0 && j<8)
		if(board.getPiece(i, j)==null || board.getPiece(i, j).getColor()!=getColor())
			allowedCells[i][j] = true;
		
		i=getX()-1; j=getY()+2;
		if(i>=0 && i<8 && j>=0 && j<8)
		if(board.getPiece(i, j)==null || board.getPiece(i, j).getColor()!=getColor())
			allowedCells[i][j] = true;
		
		i=getX()+1; j=getY()+2;
		if(i>=0 && i<8 && j>=0 && j<8)
		if(board.getPiece(i, j)==null || board.getPiece(i, j).getColor()!=getColor())
			allowedCells[i][j] = true;
		
		i=getX()+2; j=getY()+1;
		if(i>=0 && i<8 && j>=0 && j<8)
		if(board.getPiece(i, j)==null || board.getPiece(i, j).getColor()!=getColor())
			allowedCells[i][j] = true;
		
		i=getX()+2; j=getY()-1;
		if(i>=0 && i<8 && j>=0 && j<8)
		if(board.getPiece(i, j)==null || board.getPiece(i, j).getColor()!=getColor())
			allowedCells[i][j] = true;
		
		i=getX()+1; j=getY()-2;
		if(i>=0 && i<8 && j>=0 && j<8)
		if(board.getPiece(i, j)==null || board.getPiece(i, j).getColor()!=getColor())
			allowedCells[i][j] = true;		
		
		return allowedCells;
	}
	
	public void move(int x, int y) {			
		int defaultX = super.getX();
		int defaultY = super.getY();	
		start.setReservePiece(null);
		
		//do not call this function twice!
		if(checkTheMove(x, y) == false)
		{			
			//if the move is not allowed
		}		
		else
		{		
			super.setX(x);
			super.setY(y);
			Main.setPieceInCell(new Knight(super.getColor(), super.getId()), x, y);			
			Main.pieceField[defaultX][defaultY] = null;
			if((x+7-y)%2==0)
				Board.getRectangle(x, 7-y).setFill(super.getImage());
			else
				Board.getRectangle(x, 7-y).setFill(super.getImageGray());
			
			if((defaultX+7-defaultY)%2==0)
				Board.getRectangle(defaultX, 7-defaultY).setFill(Color.TRANSPARENT);
			else
				Board.getRectangle(defaultX, 7-defaultY).setFill(Color.LIGHTGRAY);
			
			if(getColor() == Chess.BLACK)
				Board.addBlackBorderRectangles(x, y, defaultX, defaultY);	
		}
	}
	
	//@Override
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
			
			Main.setPieceInCell(new Knight(super.getColor(), super.getId()), x, y);
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
							System.out.printf("%s, %s\n", start.getWhiteKingX(), start.getWhiteKingY());
							Main.setPieceInCell(new Knight(super.getColor(), super.getId()), defaultX, defaultY);
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
				Main.setPieceInCell(new Knight(super.getColor(), super.getId()), defaultX, defaultY);				
				
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
							Main.setPieceInCell(new Knight(super.getColor(), super.getId()), defaultX, defaultY);
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
				Main.setPieceInCell(new Knight(super.getColor(), super.getId()), defaultX, defaultY);				
				
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
		
		Main.setPieceInCell(new Knight(super.getColor(), super.getId()), defaultX, defaultY);		
		if(start.getReservePiece()!=null)
			Main.pieceField[x][y] = start.getReservePiece();
		
		return false;
	}
	
	public int getValue() {
		return Chess.KNIGHT_VAUE;
	}
	
	//@Override
	public String getName() {
		return name;
	}

}