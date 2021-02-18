package songLib.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import songLib.view.*;

public class SongLibApp extends Application {
	
	private static Library library;
	private static final String filename = "songData.txt";

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
		
		//Creates a file if doesn't exist and doesn't if it exists
		try {
		      File myObj = new File(filename);
		      myObj.createNewFile();
		    } 
		catch (Exception e) {}
		
		
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
		System.out.println("reading");
		String line = "";
		String delim = "\\|";
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			String[] songInfo = line.split(delim,-1);
			System.out.println(songInfo.length);
			for (int i = 0; i < songInfo.length; i++) {
				System.out.println(songInfo[i]);
			}
			System.out.println("**************************************");
			library.addSong(songInfo);
		}
	}
	
	public void stop() {
		try {
			FileWriter myWriter = new FileWriter(filename);
			List<Song> songs = library.getSongs();
			for (int i = 0; i < songs.size(); i++) {
				Song currSong = songs.get(i);
				System.out.println(currSong);
				String[] details = {currSong.getName(), currSong.getArtist(), currSong.getAlbum(), currSong.getYear()};
				myWriter.append(String.join("|", details) + "\n");
			}
			
			myWriter.flush();
			myWriter.close();
					
		} catch (IOException e) {
		}
		System.out.println("DONE");
	}

}
