import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Scene;

public class Board {
	private boolean isCheckMate = false;
	Main start = new Main();	
	private int level = 0;
	private int moveCounter = 0;
	private static Rectangle[][] rectangle = new Rectangle[8][8];
	private Pane root0 = new Pane();
	private Pane root = new Pane();	
	private static GridPane gridRoot = new GridPane();
	private static Rectangle borderRectangle = new Rectangle(0, 0, 98, 98);
	private static Rectangle blackBorderRectangle1 = new Rectangle(0, 0, 98, 98);
	private static Rectangle blackBorderRectangle2 = new Rectangle(0, 0, 98, 98);
	private Scene scene = new Scene(root0, 350, 350);
	private Stage stage = new Stage();
	private Text textWhite = new Text();
	private Text textBlack = new Text();
	
	ArrayList<PieceMove> allPieceMovesB = new ArrayList<PieceMove>();
	ArrayList<PieceMove> allPieceMovesW = new ArrayList<PieceMove>();
	
	public Board() {
		//create 64 Rectangles and put them in the root
		for(int i=0; i<8; i++)
		for(int j=0; j<8; j++) {
			rectangle[i][j] = new Rectangle(Chess.WIDTH/8, Chess.LENGHT/8);
			gridRoot.add(rectangle[i][j], i, j);
			
			if((i+j)%2==0)
				rectangle[i][j].setFill(Color.TRANSPARENT);
			else
				rectangle[i][j].setFill(Color.LIGHTGREY);				
		}	
		
		borderRectangle.setFill(Color.TRANSPARENT);
		borderRectangle.setStroke(Color.RED);
		borderRectangle.setStrokeWidth(3);
		blackBorderRectangle1.setFill(Color.TRANSPARENT);
		blackBorderRectangle1.setStroke(Color.RED);
		blackBorderRectangle1.setStrokeWidth(3);
		blackBorderRectangle2.setFill(Color.TRANSPARENT);
		blackBorderRectangle2.setStroke(Color.RED);
		blackBorderRectangle2.setStrokeWidth(3);
		
		Rectangle infoRectangle = new Rectangle(0, 800, 840, 200);		
		root.getChildren().add(infoRectangle);
		
		root.getChildren().add(gridRoot);
		root.getChildren().add(textWhite);
		textWhite.setLayoutX(10);
		textWhite.setLayoutY(840);
		textWhite.setFill(Color.WHITE);
		textWhite.setText("White to move!");
		textWhite.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		root.getChildren().add(textBlack);
		textBlack.setTextAlignment(TextAlignment.RIGHT);
		textBlack.setLayoutX(550);
		textBlack.setLayoutY(840);
		textBlack.setText("waiting for White to move!");
		textBlack.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		textBlack.setFill(Color.WHITE);		
		
		Text infoText1 = new Text(" This program stimulates a Chess game; has two difficulty levels");
		Text infoText2 = new Text(" First Level \"thinks\"; foresees two moves");
		Text infoText3 = new Text(" Second level does random moves");
		Text infoText4 = new Text(" the Player plays white, the program plays black!");
		Text infoText5 = new Text(" coded with JAVAFX");
		Text infoText6 = new Text(" written by Behrouz Taghizadeh, April 2018");
		
		root0.getChildren().add(infoText1);
		root0.getChildren().add(infoText2);
		root0.getChildren().add(infoText3);
		root0.getChildren().add(infoText4);
		root0.getChildren().add(infoText5);
		root0.getChildren().add(infoText6);
		infoText1.setLayoutY(270);
		infoText2.setLayoutY(285);
		infoText3.setLayoutY(300);
		infoText4.setLayoutY(315);
		infoText5.setLayoutY(330);
		infoText6.setLayoutY(345);
		
		
		ClickHandler clickHandler = new ClickHandler();
		scene.setOnMouseClicked(clickHandler);
		
		startBoard();
		
		Text text0 = new Text("Choose Difficulty: ");
		text0.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		text0.setLayoutX(10);
		text0.setLayoutY(50);
		root0.getChildren().add(text0);
		
		Button btEasy = new Button("Easy");
		btEasy.setPrefWidth(100);
		btEasy.setLayoutX(160);
		btEasy.setLayoutY(30);
		root0.getChildren().add(btEasy);		
		EasyHandler easyHandler = new EasyHandler();
		btEasy.setOnAction(easyHandler);
		
		Button btRandom = new Button("Even More Easy");
		btRandom.setLayoutX(160);
		btRandom.setLayoutY(60);
		root0.getChildren().add(btRandom);		
		RandomHandler randomHandler = new RandomHandler();
		btRandom.setOnAction(randomHandler);	
	}
	
	public static void addBlackBorderRectangles(int x, int y, int defaultX, int defaultY)
	{
		if(gridRoot.getChildren().contains(blackBorderRectangle1))
			gridRoot.getChildren().remove(blackBorderRectangle1);
		gridRoot.add(blackBorderRectangle1, x, 7-y);		
		
		if(gridRoot.getChildren().contains(blackBorderRectangle2))
			gridRoot.getChildren().remove(blackBorderRectangle2);
		gridRoot.add(blackBorderRectangle2, defaultX, 7-defaultY);
	}
	
	public Scene getScene() {
		return scene;
	}	
	
	public Stage getStage() {
		return stage;
	}

	public class EasyHandler implements EventHandler<ActionEvent>{

		//@Override
		public void handle(ActionEvent args) {
			stage.setWidth(Chess.WIDTH+20);
			stage.setHeight(Chess.LENGHT + 100);
			scene.setRoot(root);
			stage.centerOnScreen();
			level = 2;
		}	
	}
	
	public class RandomHandler implements EventHandler<ActionEvent>{

		//@Override
		public void handle(ActionEvent args) {
			stage.setWidth(Chess.WIDTH+20);
			stage.setHeight(Chess.LENGHT + 100);
			scene.setRoot(root);
			stage.centerOnScreen();		
			level = 1;
		}
		
	}
	
	public GridPane getGridRoot() {
		return gridRoot;
	}
	
	public static Rectangle getRectangle(int x, int y) {
		return rectangle[x][y];
	}

	public class ClickHandler implements EventHandler<MouseEvent>{
		boolean isClicked = false;
		int clickedPieceX, clickedPieceY;
		
		//@Override
		public void handle(MouseEvent e) {	
			int x = (int)(e.getX()/100);
			int y = 7 - (int)(e.getY()/100);	
			
			if(isCheckMate==false)
			{
			//2nd Click
			if(isClicked == true)
			{
				//if 2nd Click on a Piece
				if(start.getPiece(x, y)!= null)
				{

					//if 2nd Click on a Piece same Color: want to move another Piece
					if(start.getPiece(x, y).getColor() == Chess.WHITE)
					{
						clickedPieceX = x;
						clickedPieceY = y;
						gridRoot.getChildren().remove(borderRectangle);
						gridRoot.add(borderRectangle, x, 7-y);					
					}

					//if 2nd Click on a Piece different Color: want to capture this
					else
					{
						isClicked = false;
						gridRoot.getChildren().remove(borderRectangle);
						if(start.getPiece(clickedPieceX, clickedPieceY).checkTheMove(x, y)) 
						{
							textWhite.setText(start.getPiece(clickedPieceX, clickedPieceY).getName() + " captured " + start.getPiece(x, y).getName());
							start.getPiece(clickedPieceX, clickedPieceY).move(x, y);
							
							if(((King)(start.getBlackKing())).isCheck())
							{
								textWhite.setText(textWhite.getText() + ": Check!");
							}
															
							if(level == 1)
								makeARandomMove();
							else if(level==2)
								think();
							
							if(((King)(start.getWhiteKing())).isCheck())
								{
								textBlack.setText(textBlack.getText() + ": Check!");							
								}
						}
						else
							textWhite.setText("Move not allowed!");
					}					
				}

				//if second click on blank: want to move here
				else
				{
					isClicked = false;
					gridRoot.getChildren().remove(borderRectangle);
					
					//if the move is allowed, do it and run the makeAMove method
					if(start.getPiece(clickedPieceX, clickedPieceY).checkTheMove(x, y)) 
					{
						textWhite.setText(start.getPiece(clickedPieceX, clickedPieceY).getName() + " moved");
						start.getPiece(clickedPieceX, clickedPieceY).move(x, y);						
						
						if(((King)(start.getBlackKing())).isCheck())
							{
							textWhite.setText(textWhite.getText() + ": Check!");			
							}
						
						if(level == 1)
							makeARandomMove();
						else if(level == 2)
							think();

						if(((King)(start.getWhiteKing())).isCheck())
							textBlack.setText(textBlack.getText() + ": Check!");
					}
					else
						textWhite.setText("Move not allowed!");
				}
			}
			
			//1st Click
			else 
			{
				if(start.getPiece(x, y)!=null && start.getPiece(x, y).getColor() == Chess.WHITE)
				{				
					isClicked = true;
					gridRoot.add(borderRectangle, x, 7-y);
					clickedPieceX = x;
					clickedPieceY = y;									
				}
			}
		}
		}		
	}
	
	
	//Set the board start position
	public void startBoard() {			
		
		start.setWhiteKingXY(4, 0);	
		setImageInCell(start.getWhiteKing(), 4, 0);		
		start.setBlackKingXY(4, 7);	
		setImageInCell(start.getBlackKing(), 4, 7);			
		
		Piece wr1 = new Rook(Chess.WHITE, 9);
		Piece wr2 = new Rook(Chess.WHITE, 9);
		Piece wn1 = new Knight(Chess.WHITE, 9);
		Piece wn2 = new Knight(Chess.WHITE, 9);
		Piece wb1 = new Bishop(Chess.WHITE, 9);		
		Piece wb2 = new Bishop(Chess.WHITE, 9);
		Piece wq = new Queen(Chess.WHITE, 9);	
		Piece wp1 = new Pawn(Chess.WHITE, 9);
		Piece wp2 = new Pawn(Chess.WHITE, 9);
		Piece wp3 = new Pawn(Chess.WHITE, 9);
		Piece wp4 = new Pawn(Chess.WHITE, 9);
		Piece wp5 = new Pawn(Chess.WHITE, 9);
		Piece wp6 = new Pawn(Chess.WHITE, 9);
		Piece wp7 = new Pawn(Chess.WHITE, 9);
		Piece wp8 = new Pawn(Chess.WHITE, 9);		
		Piece br1 = new Rook(Chess.BLACK, 9);
		Piece br2 = new Rook(Chess.BLACK, 9);
		Piece bn1 = new Knight(Chess.BLACK, 9);
		Piece bn2 = new Knight(Chess.BLACK, 9);
		Piece bb1 = new Bishop(Chess.BLACK, 9);
		Piece bb2 = new Bishop(Chess.BLACK, 9);
		Piece bq = new Queen(Chess.BLACK, 9);
		Piece bp1 = new Pawn(Chess.BLACK, 9);
		Piece bp2 = new Pawn(Chess.BLACK, 9);
		Piece bp3 = new Pawn(Chess.BLACK, 9);
		Piece bp4 = new Pawn(Chess.BLACK, 9);
		Piece bp5 = new Pawn(Chess.BLACK, 9);
		Piece bp6 = new Pawn(Chess.BLACK, 9);
		Piece bp7 = new Pawn(Chess.BLACK, 9);
		Piece bp8 = new Pawn(Chess.BLACK, 9);	
		
		Main.setPieceInCell(wp1, 0, 1);		
		Main.setPieceInCell(wp2, 1, 1);
		Main.setPieceInCell(wp3, 2, 1);
		Main.setPieceInCell(wp4, 3, 1);
		Main.setPieceInCell(wp5, 4, 1);
		Main.setPieceInCell(wp6, 5, 1);
		Main.setPieceInCell(wp7, 6, 1);
		Main.setPieceInCell(wp8, 7, 1);
		Main.setPieceInCell(wr1, 0, 0);
		Main.setPieceInCell(wr2, 7, 0);		
		Main.setPieceInCell(wn1, 1, 0);
		Main.setPieceInCell(wn2, 6, 0);		
		Main.setPieceInCell(wb1, 2, 0);		
		Main.setPieceInCell(wb2, 5, 0);
		Main.setPieceInCell(wq, 3, 0);		
				
		Main.setPieceInCell(bp1, 0, 6);
		Main.setPieceInCell(bp2, 1, 6);
		Main.setPieceInCell(bp3, 2, 6);
		Main.setPieceInCell(bp4, 3, 6);
		Main.setPieceInCell(bp5, 4, 6);
		Main.setPieceInCell(bp6, 5, 6);
		Main.setPieceInCell(bp7, 6, 6);
		Main.setPieceInCell(bp8, 7, 6);
		Main.setPieceInCell(br1, 0, 7);
		Main.setPieceInCell(br2, 7, 7);
		Main.setPieceInCell(bn1, 1, 7);
		Main.setPieceInCell(bn2, 6, 7);
		Main.setPieceInCell(bb1, 2, 7);		
		Main.setPieceInCell(bb2, 5, 7);
		Main.setPieceInCell(bq, 3, 7);					
				
		setImageInCell(wn1, 1, 0);
		setImageInCell(wb1, 2, 0);
		setImageInCell(wq, 3, 0);
		setImageInCell(wb2, 5, 0);
		setImageInCell(wn2, 6, 0);
		setImageInCell(wr2, 7, 0);		
		setImageInCell(wp1, 0, 1);
		setImageInCell(wp2, 1, 1);
		setImageInCell(wp3, 2, 1);
		setImageInCell(wp4, 3, 1);
		setImageInCell(wp5, 4, 1);
		setImageInCell(wp6, 5, 1);
		setImageInCell(wp7, 6, 1);
		setImageInCell(wp8, 7, 1);		
		setImageInCell(br1, 0, 7);		
		setImageInCell(bn1, 1, 7);
		setImageInCell(bb1, 2, 7);
		setImageInCell(bq, 3, 7);
		setImageInCell(bb2, 5, 7);
		setImageInCell(bn2, 6, 7);
		setImageInCell(br2, 7, 7);
		setImageInCell(bp1, 0, 6);
		setImageInCell(bp2, 1, 6);
		setImageInCell(bp3, 2, 6);
		setImageInCell(bp4, 3, 6);
		setImageInCell(bp5, 4, 6);
		setImageInCell(bp6, 5, 6);
		setImageInCell(bp7, 6, 6);
		setImageInCell(bp8, 7, 6);	
		setImageInCell(wr1, 0, 0);
		setImageInCell(wp1, 0, 1);
		setImageInCell(br1, 0, 7);
		setImageInCell(wp1, 0, 6);		
	}
	
	public void setImageInCell(Piece piece, int x, int y){
		if((x+7-y)%2==0)
			rectangle[x][7-y].setFill(start.getPiece(x, y).getImage());
		else
			rectangle[x][7-y].setFill(start.getPiece(x, y).getImageGray());
	}
		
	public void gatherAllMoves(int color) {
		
		if(color == Chess.BLACK)
			allPieceMovesB.clear();
		else if(color == Chess.WHITE)
			allPieceMovesW.clear();
		else
			System.out.println("GatherAllPieceMoves has an invalid Color input");
		
		for(int i=0; i<8; i++)
		for(int j=0; j<8; j++)
		if(start.getPiece(i, j)!=null && start.getPiece(i, j).getColor()==color)
		{
			boolean[][] thisArray = start.getPiece(i, j).getAllowedCells();
			for(int m=0; m<8; m++)
			for(int n=0; n<8; n++)
			if(thisArray[m][n] && start.getPiece(i, j).checkTheMove(m, n))
			{
				if(start.getPiece(i, j).getColor() == Chess.BLACK)
					allPieceMovesB.add(new PieceMove(start.getPiece(i, j), m, n, i, j));
				else
					allPieceMovesW.add(new PieceMove(start.getPiece(i, j), m, n, i, j));					
			}			
		}
	}
	
	public void makeARandomMove() {
		
		gatherAllMoves(Chess.BLACK);		
			
		if(allPieceMovesB.size() > 0)
			{
			int random = (int)(Math.random()*allPieceMovesB.size());
			PieceMove thisPM = allPieceMovesB.get(random);
			textBlack.setText(thisPM.getPiece().getName() + " moved");
			if((start.getPiece(thisPM.getX(), thisPM.getY())!=null && start.getPiece(thisPM.getX(), thisPM.getY()).getColor() == Chess.WHITE))
				textBlack.setText(thisPM.getPiece().getName() + " captured " + start.getPiece(thisPM.getX(), thisPM.getY()).getName());
			
			allPieceMovesB.get(random).getPiece().move(allPieceMovesB.get(random).getX(), allPieceMovesB.get(random).getY());
			

			//System.out.println(allPieceMoves.get(random).getX() + ", " + allPieceMoves.get(random).getY());			
			}
			
		else
			{
			isCheckMate = true;
			if(((King)(start.getBlackKing())).isCheck())
				textBlack.setText("*** CHECKMATE! ***");
			else
				textBlack.setText("*** STALEMATE! ***");
			}
	}
	
	public void think() {
		
		gatherAllMoves(Chess.BLACK);
		
		//Copy the original PieceField to reservePieceField 
		Piece[][] reservePieceField = new Piece[8][8], reservePieceField2 = new Piece[8][8];
		for(int i=0; i<8; i++)
		for(int j=0; j<8; j++) {
			reservePieceField[i][j] = null;
			if(Main.pieceField[i][j] != null) 
				reservePieceField[i][j] = Main.pieceField[i][j];
		}

		//for all black Moves
		for(int x=0; x<allPieceMovesB.size(); x++) {
			PieceMove thisPMB = allPieceMovesB.get(x);	
			boolean napelon = false;
			
			//System.out.println("--------------PMB " +x + ", " + thisPMB.getPiece()+" to " + thisPMB.getX() + ", " + thisPMB.getY());
			
			//do this specific Move on the original PieceField
			Main.pieceField[thisPMB.getX()][thisPMB.getY()] = thisPMB.getPiece();
			Main.pieceField[thisPMB.getFromX()][thisPMB.getFromY()] = null;
			
			allPieceMovesB.get(x).setWorstDiff(getDiff());
			int diff = getDiff();
			int worstDiff = diff;
			
			gatherAllMoves(Chess.WHITE);
			
			//Copy the original PieceField to reservePieceField2
			for(int i=0; i<8; i++)
			for(int j=0; j<8; j++) {
			reservePieceField2[i][j] = null;
			if(Main.pieceField[i][j] != null) 
				reservePieceField2[i][j] = Main.pieceField[i][j];
			//System.out.println(i + ", " + j + ", " + reservePieceField2[i][j]);
			}
			
			for(int y=0; y<allPieceMovesW.size(); y++) {
				
				//do for the i-th PieceMove of White allPieceMoves
				PieceMove thisPMW = allPieceMovesW.get(y);	
				
				Main.pieceField[thisPMW.getX()][thisPMW.getY()] = thisPMW.getPiece();
				Main.pieceField[thisPMW.getFromX()][thisPMW.getFromY()] = null;
				
				//System.out.println("PMB " + x + ", PMW " + y + ": " + thisPMW.getPiece()+" to " + thisPMW.getX() + ", " + thisPMW.getY());
				
				if(start.getPiece(5, 6)!=null && start.getPiece(5, 6).getColor()==Chess.WHITE && start.getPiece(5, 6).getValue()==20)
					{
					napelon = true;
					}
				
				if(getDiff() < worstDiff)
				{
					worstDiff = getDiff();
					allPieceMovesB.get(x).setWorstDiff(worstDiff);					
					//System.out.println("INSIDE worstDiff = " + worstDiff);
				}				
				
				for(int i=0; i<8; i++)
				for(int j=0; j<8; j++) {
					Main.pieceField[i][j] = null;
					if(reservePieceField2[i][j] != null)
						Main.pieceField[i][j] = reservePieceField2[i][j];
				}							
			}
			
			for(int i=0; i<8; i++)
			for(int j=0; j<8; j++) {
				Main.pieceField[i][j] = null;
				if(reservePieceField[i][j] != null)
					Main.pieceField[i][j] = reservePieceField[i][j];
				}
			
			allPieceMovesB.get(x).setWorstDiff(worstDiff);
			//System.out.println("PMB WORSTDIFF " + x + " -> " + worstDiff);
			
			//System.out.println("MC: "+ moveCounter + ", napelon: " + napelon);
			if(moveCounter >= 2 && moveCounter<=5  && napelon==true)
			{
				napelon = false;
				thisPMB.setWorstDiff(thisPMB.getWorstDiff()-20);
			}
		}		
		
		if(allPieceMovesB.size()>0)
		{
		int bestDiff = allPieceMovesB.get(0).getWorstDiff();
		for(int i=0; i<allPieceMovesB.size(); i++)
		{
			if(allPieceMovesB.get(i).getWorstDiff() > bestDiff)
			bestDiff = allPieceMovesB.get(i).getWorstDiff();
		}
		
		for(int i=allPieceMovesB.size()-1; i>=0; i--)
		{
			if(allPieceMovesB.get(i).getWorstDiff() < bestDiff)
			allPieceMovesB.remove(i);
		}
		
		int random = (int)(Math.random() * allPieceMovesB.size());
		PieceMove thisPM = allPieceMovesB.get(random);
		textBlack.setText(thisPM.getPiece().getName() + " moved");
		if((start.getPiece(thisPM.getX(), thisPM.getY())!=null && start.getPiece(thisPM.getX(), thisPM.getY()).getColor() == Chess.WHITE))
			textBlack.setText(thisPM.getPiece().getName() + " captured " + start.getPiece(thisPM.getX(), thisPM.getY()).getName());
		
		//System.out.println("################# " + bestMove);			
		allPieceMovesB.get(random).getPiece().move(allPieceMovesB.get(random).getX(), allPieceMovesB.get(random).getY());
		moveCounter++;
		}
		else
			{
			isCheckMate = true;
			if(((King)(start.getBlackKing())).isCheck())
				textBlack.setText("*** CHECKMATE! ***");
			else
				textBlack.setText("*** STALEMATE! ***");		
			}
		
	}
	
	public int getDiff() {
		int bv = 0;
		int wv = 0;
		
		for(int i=0; i<8; i++)
		for(int j=0; j<8; j++)		
		if(start.getPiece(i, j)!=null)
		if(start.getPiece(i, j).getColor() == Chess.BLACK)			
			bv = bv + Main.pieceField[i][j].getValue();
		else
			wv = wv + Main.pieceField[i][j].getValue();
		
		return bv - wv;
	}
}