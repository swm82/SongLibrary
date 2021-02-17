package songLib.view;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import songLib.app.*;

public class SongLibController implements Initializable {
	
	@FXML TextField songName, songArtist, songAlbum, songYear;
	@FXML Button addButton, deleteButton, editButton;
	@FXML ListView<Song> songList;
	
	
	private Library library;
	private Stage primaryStage;
	
	public void initData(Library lib) {
		library = lib;
		songList.setItems(library.getSongs());
		songName.setEditable(false);
		songArtist.setEditable(false);
		songAlbum.setEditable(false);
		songYear.setEditable(false);
		
		 if (library.size() != 0) {
			songList.getSelectionModel().select(0);
			displayInfo(songList.getSelectionModel().getSelectedItem());
		 }
		
		songList.getSelectionModel().selectedIndexProperty().addListener(
				(obs, oldVal, newVal) -> 
				displayInfo(songList.getSelectionModel().getSelectedItem()));
	}
	
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
	}
	
	// Event handlers
	public void displayInfo(Song song) {
		
		if (song != null) {
		songArtist.setText(song.getArtist());
		songName.setText(song.getName());
		String album = song.getAlbum();
		String year = song.getYear();
		if (album != null) songAlbum.setText(song.getAlbum());
		else songAlbum.setText("");
		if (year != null) songYear.setText(song.getYear());
		else songYear.setText("");
		}
		
		else {
		songArtist.setText("");
		songName.setText("");
		songAlbum.setText("");
		songYear.setText("");
		}

	}

	public void addCommand(ActionEvent e) {
		System.out.println("Add");
	}
	
	public void deleteCommand(ActionEvent e) {
		int currIndex = songList.getSelectionModel().getSelectedIndex();
		library.deleteSong(songList.getSelectionModel().getSelectedItem());
		songList.getSelectionModel().select(currIndex);
		displayInfo(songList.getSelectionModel().getSelectedItem());
		System.out.println("Delete");
	}
	
	public void editCommand(ActionEvent e) {
		System.out.println("Edit");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// we can probably remove the intializable interface
		
	}
	

}
