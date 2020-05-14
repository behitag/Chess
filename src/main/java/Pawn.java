import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Pawn extends Piece{
	private final static String name = "Pawn";

	public Pawn(int color, int id) {
		super(color, id);
		
		if(color == Chess.WHITE)			
			{
			super.setImage(new ImagePattern(new Image(getClass().getResourceAsStream("wp.png"))));
			super.setImageGray(new ImagePattern(new Image(getClass().getResourceAsStream("wpg.png"))));
			}
		else
			{
			super.setImage(new ImagePattern(new Image(getClass().getResourceAsStream("bp.png"))));
			super.setImageGray(new ImagePattern(new Image(getClass().getResourceAsStream("bpg.png"))));
			}
		}

	@Override
	public boolean[][] getAllowedCells() {
		Main board = new Main();

		//set all fileds equal to false
		for(int i=0; i<8; i++)
		for(int j=0; j<8; j++)
		allowedCells[i][j] = false;
		
		int x = getX();
		int y = getY();
		
		if(getColor() == Chess.WHITE) {
			
			if(y == 1) {
				if(board.getPiece(x, 2) == null) 
				{
					allowedCells[x][2] = true;
					if(board.getPiece(x, 3) == null)
						allowedCells[x][3] = true;		
				}						
			}
		
			else if(y<=6 && board.getPiece(x, y+1)==null){
				allowedCells[x][y+1] = true;
			}
			
			if(x>=1 && y<=6)
			if(board.getPiece(x-1, y+1) != null && board.getPiece(x-1, y+1).getColor()==Chess.BLACK)
				allowedCells[x-1][y+1] = true;			
			
			if(x<=6 && y<=6)
			if(board.getPiece(x+1, y+1) != null && board.getPiece(x+1, y+1).getColor()==Chess.BLACK)
				allowedCells[x+1][y+1] = true;
		}
				
		if(getColor() == Chess.BLACK) {
			
			if(y == 6) {
				if(board.getPiece(x, 5) == null) 
					{
					allowedCells[x][5] = true;
					if(board.getPiece(x, 4) == null)
						allowedCells[x][4] = true;				
					}				
			}
		
			else if(y>=1 && board.getPiece(x, y-1)==null){
				allowedCells[x][y-1] = true;
			}
			
			if(x>=1 && y>=1)
			if(board.getPiece(x-1, y-1) != null && board.getPiece(x-1, y-1).getColor()==Chess.WHITE)
				allowedCells[x-1][y-1] = true;			
			
			if(x<=6 && y>=1)
			if(board.getPiece(x+1, y-1) != null && board.getPiece(x+1, y-1).getColor()==Chess.WHITE)
				allowedCells[x+1][y-1] = true;
		}

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
			Main.setPieceInCell(new Pawn(super.getColor(), super.getId()), x, y);
			if((x+7-y)%2==0)
				Board.getRectangle(x, 7-y).setFill(super.getImage());
			else
				Board.getRectangle(x, 7-y).setFill(super.getImageGray());
			
			if(super.getColor()==Chess.WHITE && y==7)
				{
				Main.setPieceInCell(new Queen(super.getColor(), super.getId()), x, y);
				
				if((x+7-y)%2==0)
					Board.getRectangle(x, 7-y).setFill(new Queen(Chess.WHITE, 15).getImage());
				else
					Board.getRectangle(x, 7-y).setFill(new Queen(Chess.WHITE, 15).getImageGray());
				}
			if(super.getColor()==Chess.BLACK && y==0)
				{
				Main.setPieceInCell(new Queen(super.getColor(), super.getId()), x, y);

				if((x+7-y)%2==0)
					Board.getRectangle(x, 7-y).setFill(new Queen(Chess.BLACK, 15).getImage());
				else
					Board.getRectangle(x, 7-y).setFill(new Queen(Chess.BLACK, 15).getImageGray());
				}
			
			Main.pieceField[defaultX][defaultY] = null;			
			
			if((defaultX+7-defaultY)%2==0)
				Board.getRectangle(defaultX, 7-defaultY).setFill(Color.TRANSPARENT);
			else
				Board.getRectangle(defaultX, 7-defaultY).setFill(Color.LIGHTGRAY);			
		}
	}
	
	@Override
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
			
			Main.setPieceInCell(new Pawn(super.getColor(), super.getId()), x, y);
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
						if(thisArray2[m][n]==true && start.getWhiteKingX()==m && start.getWhiteKingY()==n)
						{	
							//System.out.printf("%s, %s\n", start.getWhiteKingX(), start.getWhiteKingY());
							
							Main.setPieceInCell(new Pawn(super.getColor(), super.getId()), defaultX, defaultY);
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
				Main.setPieceInCell(new Pawn(super.getColor(), super.getId()), defaultX, defaultY);				
				
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
							Main.setPieceInCell(new Pawn(super.getColor(), super.getId()), defaultX, defaultY);
							if(start.getReservePiece()!=null)
								Main.pieceField[x][y] = start.getReservePiece();
							else
								Main.pieceField[x][y] = null;
							
							return false;
						}
						}
					}				
				}
				//System.out.println("CHECKMOVE: ALLOWED " + super.getX() + ", " + super.getY());
				Main.setPieceInCell(new Pawn(super.getColor(), super.getId()), defaultX, defaultY);				
				
				if(start.getReservePiece()!=null)
					{
					Main.pieceField[x][y] = start.getReservePiece();
					}
				else
					Main.pieceField[x][y] = null;
				
				return true;		
			}			
		}	
		
		//System.out.printf("%s, %s, %b\n", i, j, thisArray[i][j]);
		}		
		//System.out.println("CHECKMOVE: NOT ALLOWED");
		
		Main.setPieceInCell(new Pawn(super.getColor(), super.getId()), defaultX, defaultY);		
		if(start.getReservePiece()!=null)
			{
			System.out.println("KATASROPHE");
			Main.pieceField[x][y] = start.getReservePiece();
			}
		
		return false;
	}
	
	public int getValue() {
		return Chess.PAWN_VALUE;
	}
	
	@Override
	public String getName() {
		return name;
	}
}