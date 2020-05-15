import javafx.stage.Stage;
import javafx.application.Application;

public class Start extends Application
{

	//@Override
	public void start(Stage primaryStage) throws Exception
	{
		Board board = new Board();
		board.getStage().setScene(board.getScene());
		//stage.setScene(board.scene);
		board.getStage().show();		
	}

}
