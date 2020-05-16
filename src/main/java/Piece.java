import javafx.scene.paint.ImagePattern;

public abstract class Piece {
	private int x;
	private int y;
	private int color;
	private int id;	
	private ImagePattern image;
	private ImagePattern imageGray;
	private String name;
	protected boolean[][] allowedCells = new boolean[8][8];
	
	Main start = new Main();
	
	/**
	 * constructor: creates a piece with color - id not used
	 * @param color color
	 * @param id color again - not used!
	 */
	public Piece(int color, int id) 
	{
		this.color = color;
		this.id = id;
		
		//set allowedCells to false: Piece can initially go no where
		for(int i=0; i<allowedCells.length; i++)
		for(int j=0; j<allowedCells.length; j++)
			allowedCells[i][j] = false;
	}
		
	/**
	 * returns the color of the piece
	 * @return int
	 */
	public int getColor() 
	{
		return color;
	}	
	
	/**
	 * returns x-coordinate of the piece in the piecefield[8][8]
	 * @return int
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * returns the y-coordinate of the piece in the piecefield[8][8]
	 * @return int
	 */
	public int getY() 
	{
		return y;
	}
	
	/**
	 * sets the x-coordinate of the piece in the piecefield[8][8]
	 * @param x x-coordinate
	 */
	public void setX(int x) 
	{
		this.x = x;
	}
	
	/**
	 * sets the y-coordinate of the piece in the piecefield[8][8]
	 * @param y y-coordinate
	 */
	public void setY(int y) 
	{
		this.y = y;
	}
		
	/**
	 * returns color of the piece
	 * @return int
	 */
	public int getId() 
	{
		return id;
	}	
	
	/**
	 * return the image file of the piece, to show on the board
	 * @return ImagePattern
	 */
	public ImagePattern getImage() 
	{
		return image;
	}
	
	/**
	 * sets the image file of the piece, to show on the board
	 * @param image the image of the piece
	 */
	public void setImage(ImagePattern image) 
	{
		this.image = image;
	}
	
	/**
	 * gives the image of the piece with gray back-ground. for showing the piece on the black cells
	 * @return ImagePattern
	 */
	public ImagePattern getImageGray() 
	{
		return imageGray;
	}
	
	/**
	 * sets the image of the piece with gray back-ground. for showing the piece on the black cells
	 * @param imageGray the image to be set
	 */
	public void setImageGray(ImagePattern imageGray)
	{
		this.imageGray = imageGray;
	}
	
	/**
	 * gets the name of the piece, to show the user in the infobox
	 * @return String
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * gives all possible cells where the piece can go in the current position in the board
	 * [3][4] = true means the piece can go to the cell [3, 4]
	 * @return boolean[8][8]
	 */
	public abstract boolean[][] getAllowedCells(); 
	
	/**
	 * this method is the brain of the program; has the most CPU-usage
	 * checks if the requested move is allowed, that means
	 * if the piece can go there
	 * if the cell is free
	 * if the move makes any check
	 * @param x x-coordinate of the target cell
	 * @param y y-coordinte of the target cell
	 * @return boolean
	 */
	public abstract boolean checkTheMove(int x, int y);
	
	/**
	 * does move the cell into the target cell, if checkTheMove allows it (see above)
	 * if not, does not change anything, only shows a message for the user in the infobox
	 * changes the images on the board
	 * changes the values in the piecefield
	 * @param x x-coordibate of the target cell
	 * @param y-coordinate of the target cell
	 */
	public abstract void move(int x, int y);
	
	/**
	 * returns the relative value of the piece in chess
	 * is used for comparing the current position of parties and finding the best move
	 * @return int
	 */
	public abstract int getValue();	
}