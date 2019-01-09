package graphics;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainFrame extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new GameView().getScene());
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
}
