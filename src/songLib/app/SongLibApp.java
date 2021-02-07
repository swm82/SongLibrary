package songLib.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import songLib.view.*;

public class SongLibApp extends Application {
	
	private Library library;

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/SongLib/view/SongLib.fxml"));
		Parent root = loader.load();
		SongLibController controller = loader.getController();
		// controller.initData(library.getSongs());
		primaryStage.setTitle("Sub order");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		// Read file
		// Build library
		launch(args);
	}

}
