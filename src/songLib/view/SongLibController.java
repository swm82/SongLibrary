package songLib.view;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import songLib.app.*;

public class SongLibController {
	
	@FXML TextArea songName, songArtist, songAlbum, songYear;
	@FXML Button add, delete, edit, submitEdit;
	@FXML ListView<Song> songList;
	
	
	private Library library;
	
	public void initData(Library library) {
		this.library = library;
		songList.setItems(this.library.getSongs());
		songList.getSelectionModel().select(0);
		songList.getSelectionModel().selectedIndexProperty().addListener(
				(obs, oldVal, newVal) -> 
				displayInfo(songList.getSelectionModel().getSelectedItem()));
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
	
	public void deleteSong(ActionEvent e) {
		library.deleteSong(songList.getSelectionModel().getSelectedItem());
		
	}
	
	public void editSong(ActionEvent e) {
		
	}
	

}
