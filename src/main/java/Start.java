import javafx.stage.Stage;
import javafx.application.Application;

/**
 * sole reason for this class is making the main class separate (not extending) from Application
 * to be able to run the runnable jar file
 * otherwise hard to get a runnable jar file with javafx
 * @author Behrouz
 *
 */
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
