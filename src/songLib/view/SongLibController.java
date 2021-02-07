package songLib.view;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import songLib.app.*;

public class SongLibController implements Initializable {
	
	@FXML TextArea songName, songArtist, songAlbum, songYear;
	@FXML Button add, deleteButton, edit, submitEdit;
	@FXML ListView<Song> songList;
	
	
	private Library library;
	private Stage primaryStage;
	
	public void initData(Library library) {
		this.library = library;
		songList.setItems(this.library.getSongs());
		songList.getSelectionModel().select(0);
		songList.getSelectionModel().selectedIndexProperty().addListener(
				(obs, oldVal, newVal) -> 
				displayInfo(songList.getSelectionModel().getSelectedItem()));
	}
	
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
	}
	
	// Event handlers
	public void displayInfo(Song song) {
		songArtist.setText(song.getArtist());
		songName.setText(song.getName());
		String album = song.getAlbum();
		String year = song.getYear();
		if (album != null) songAlbum.setText(song.getAlbum());
		else songAlbum.setText("");
		if (year != null) songYear.setText(song.getYear());
		else songYear.setText("");

	}

	public void addSong(ActionEvent e) {
		//library.addSong();
	}
	
	public void addButton(ActionEvent e) {
	
	}
	
	public void handleClick(ActionEvent e) {
		Button button = (Button)e.getSource();
		if (button == deleteButton) {
			library.deleteSong(songList.getSelectionModel().getSelectedItem());
		}
		
	}
	
	public void editSong(ActionEvent e) {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// we can probably remove the intializable interface
		
	}
	

}
