import javafx.scene.paint.ImagePattern;

public abstract class Piece {
	private int x;
	private int y;
	private boolean captured = false;
	private int color;
	private int id;
	protected boolean[][] allowedCells = new boolean[8][8];	
	private ImagePattern image;
	private ImagePattern imageGray;
	private String name;
	
	Main start = new Main();
	
	public Piece(int color, int id) {
		this.color = color;
		this.id = id;
		
		//set allowedCells to false: Piece can initially go no where
		for(int i=0; i<allowedCells.length; i++)
		for(int j=0; j<allowedCells.length; j++)
			allowedCells[i][j] = false;
	}
			
	
	public boolean getCaptured() {
		return captured;
	}	
	public int getColor() {
		return color;
	}	
	public int getX() {
		return x;
	}	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getY() {

		return y;
	}	
	public int getId() {
		return id;
	}	
	
	public ImagePattern getImage() {
		return image;
	}
	
	public void setImage(ImagePattern image) {
		this.image = image;
	}
	
	public ImagePattern getImageGray() {
		return imageGray;
	}
	
	public void setImageGray(ImagePattern imageGray) {
		this.imageGray = imageGray;
	}
	
	public String getName() {
		return name;
	}
	
	
	public abstract boolean[][] getAllowedCells(); 
	public abstract boolean checkTheMove(int x, int y);
	public abstract void move(int x, int y);
	public abstract int getValue();	
}