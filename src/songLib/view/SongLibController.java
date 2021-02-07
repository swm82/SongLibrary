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
	@FXML ListView<Song> list;
	
	
	private Library library;
	
	public void initData(Library library) {
		this.library = library;
		list.setItems(this.library.getSongs());
	}
	
	
	// Event handlers
	public void addSong(ActionEvent e) {
		//library.addSong();
	}
	
	public void deleteSong(ActionEvent e) {
		
	}
	
	public void editSong(ActionEvent e) {
		
	}
	

}
