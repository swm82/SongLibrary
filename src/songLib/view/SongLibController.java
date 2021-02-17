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
	@FXML TextField createName, createArtist, createAlbum, createYear;
	@FXML Button addButton, deleteButton, cancelSongCreate, activateEditor, submitEditor, cancelEditor;
	@FXML ListView<Song> songList;
	
	private boolean editMode;
	
	
	private Library library;
	private Stage primaryStage;
	
	public void initData(Library lib) {
		editMode = false;
		library = lib;
		songList.setItems(library.getSongs());
		songName.setEditable(false);
		songArtist.setEditable(false);
		songAlbum.setEditable(false);
		songYear.setEditable(false);
		
		submitEditor.setDisable(true);
		cancelEditor.setDisable(true);
		
		 if (library.size() != 0) {
			songList.getSelectionModel().select(0);
			displayInfo(songList.getSelectionModel().getSelectedItem());
		 }
		
		songList.getSelectionModel().selectedIndexProperty().addListener(
				(obs, oldVal, newVal) -> 
				{
				displayInfo(songList.getSelectionModel().getSelectedItem());
				cancelEditing(null);
				}
				);
		
		
	}
	
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
	}
	
	// Event handlers
	public void displayInfo(Song song) {
		
		System.out.println("Displaying Info");
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
		String[] details = {createName.getText(), createArtist.getText(), createAlbum.getText(), createYear.getText()};
		Song newAddition = library.addSong(details);
		displayInfo(newAddition);
		createName.setText("");
		createAlbum.setText("");
		createYear.setText("");
		createArtist.setText("");
		songList.getSelectionModel().select(library.getSongs().indexOf(newAddition));

	}
	
	public void deleteCommand(ActionEvent e) {
		int currIndex = songList.getSelectionModel().getSelectedIndex();
		library.deleteSong(songList.getSelectionModel().getSelectedItem());
		songList.getSelectionModel().select(currIndex);
		displayInfo(songList.getSelectionModel().getSelectedItem());
		System.out.println("Delete");
	}
	
	public void eraseCreator(ActionEvent e) {
		createName.setText("");
		createAlbum.setText("");
		createYear.setText("");
		createArtist.setText("");
		
	}
	
	public void editCommand(ActionEvent e) {
		
		//songList.setMouseTransparent( true );
		//songList.setFocusTraversable( false );
		editMode = true;
		System.out.println("Edit");
		
		songName.setEditable(true);
		songArtist.setEditable(true);
		songAlbum.setEditable(true);
		songYear.setEditable(true);

		activateEditor.setDisable(true);
		
		songArtist.requestFocus();
		songArtist.positionCaret(songArtist.getText().length());
		submitEditor.setDisable(false);
		cancelEditor.setDisable(false);

	}
	
	public void cancelEditing(ActionEvent e) {
		
		System.out.println("Exiting Editor Mode");
		
		if (!editMode) {
			System.out.println(editMode);
			return;
		}
		
		
		submitEditor.setDisable(true);
		cancelEditor.setDisable(true);
		activateEditor.setDisable(false);

		displayInfo(songList.getSelectionModel().getSelectedItem());
		
		songName.setEditable(false);
		songArtist.setEditable(false);
		songAlbum.setEditable(false);
		songYear.setEditable(false);
		
		songList.requestFocus();
		editMode = false;
	}
	
	public void submitEdits(ActionEvent e) {
		
		System.out.println("Edits Submitted");
		String[] details = {songName.getText(), songArtist.getText(), songAlbum.getText(), songYear.getText()};
		Song edited = songList.getSelectionModel().getSelectedItem();
		edited.setName(songName.getText());
		edited.setArtist(songArtist.getText());
		edited.setAlbum(songAlbum.getText());
		edited.setYear(songYear.getText());
		editMode = false;
		songList.setItems(library.getSongs());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// we can probably remove the intializable interface
		
	}
	

}
