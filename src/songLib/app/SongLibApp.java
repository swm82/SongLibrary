package songLib.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import songLib.view.*;

public class SongLibApp extends Application {
	
	private static Library library;

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/SongLib/view/SongLib.fxml"));
		Parent root = loader.load();
		SongLibController controller = loader.getController();
		controller.start(primaryStage);
		controller.initData(library);
		primaryStage.setTitle("Song Library");
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	
	public static void main(String[] args) throws IOException {
		String filename = "test.csv";
		BufferedReader reader = getFile(filename);
		library = new Library();
		
		
		if (reader !=  null) {
			readFile(reader, library);
			reader.close();
		}
		
		launch(args);
		
	}
	
	public static BufferedReader getFile(String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			return reader;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void readFile(BufferedReader reader, Library library) throws IOException {
		String line = "";
		String delim = ";";
		while ((line = reader.readLine()) != null) {
			String[] songInfo = line.split(delim);
			library.addSong(songInfo);
		}
	}

}
