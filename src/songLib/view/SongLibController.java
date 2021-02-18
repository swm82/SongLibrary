//


package songLib.view;

import javafx.event.ActionEvent;
import java.util.List;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import songLib.app.*;

public class SongLibController {
	
	@FXML TextField songName, songArtist, songAlbum, songYear;
	@FXML TextField createName, createArtist, createAlbum, createYear;
	@FXML Button addButton, deleteButton, cancelSongCreate, activateEditor, submitEditor, cancelEditor;
	@FXML ListView<Song> songList;
	@FXML VBox detailPanel;
	
	private boolean editMode;
	
	
	private Library library;
	private Stage primaryStage;
	
	public void initData(Library lib) {
		editMode = false;
		library = lib;
		songList.setItems(library.getSongs());
		
		 if (library.size() != 0) {
			songList.getSelectionModel().select(0);
			displayInfo(songList.getSelectionModel().getSelectedItem());
		 }
		
		songList.getSelectionModel().selectedIndexProperty().addListener(
				(obs, oldVal, newVal) -> {
					displayInfo(songList.getSelectionModel().getSelectedItem());
					if (editMode) toggleEdit(null);
				});
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
		String[] details = {createName.getText().trim(), createArtist.getText().trim(), createAlbum.getText().trim(), createYear.getText().trim()};
		if (!confirm("Are you sure you want to add song: " + details[0] + " - " + details[1])) return;
		if (!verifyDetails(details))
			return;
		Song newAddition = library.addSong(details);
		displayInfo(newAddition);
		createName.setText("");
		createAlbum.setText("");
		createYear.setText("");
		createArtist.setText("");
		songList.getSelectionModel().select(library.getSongs().indexOf(newAddition));

	}
	
	public void deleteCommand(ActionEvent e) {
		if (!confirm("Are you sure you want to delete song: " + songList.getSelectionModel().getSelectedItem())) return;
		int currIndex = songList.getSelectionModel().getSelectedIndex();
		library.deleteSong(songList.getSelectionModel().getSelectedItem());
		songList.getSelectionModel().select(currIndex);
		displayInfo(songList.getSelectionModel().getSelectedItem());
		if (editMode) toggleEdit(null);
		songList.requestFocus();
	}
	
	public void eraseCreator(ActionEvent e) {
		createName.setText("");
		createAlbum.setText("");
		createYear.setText("");
		createArtist.setText("");
	}
	
	// Goes thru the detail pane to toggle controls
	public void toggleEdit(ActionEvent e) {
		detailPanel.getChildren().forEach(node -> {
			if (node instanceof GridPane) {
				GridPane gpNode = (GridPane) node;
				gpNode.getChildren().forEach(item -> {
					if (item instanceof TextField) {
						TextField textNode = (TextField) item;
						if (textNode.isEditable()) {
							textNode.setEditable(false);
							editMode = false;
							songList.requestFocus();
						}
						else {
							textNode.setEditable(true);
							editMode = true;
						}
					}
				});

			} else if (node instanceof HBox) {
				HBox hbNode = (HBox) node;
				hbNode.getChildren().forEach(item -> {
					if (item instanceof Button) {
						Button buttonNode = (Button) item;
						if (buttonNode.isDisabled()) buttonNode.setDisable(false);
						else buttonNode.setDisable(true);
					}
				});
			}
		});
		if (!editMode) displayInfo(songList.getSelectionModel().getSelectedItem());
		else {
			songArtist.requestFocus();
			songArtist.positionCaret(songArtist.getText().length());
		}
	}
	

	public boolean confirm(String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(primaryStage);
		alert.setTitle("Confirm");
		alert.setHeaderText(content);
		Optional<ButtonType> result = alert.showAndWait();
		if(!result.isPresent() || result.get() != ButtonType.OK) {
			return true;
		} 
		return false;
		
		
	}
	
	
	public void submitEdits(ActionEvent e) {
		String[] details = {songName.getText().trim(), songArtist.getText().trim(), songAlbum.getText().trim(), songYear.getText().trim()};
		if (!verifyDetails(details))
			return;
		Song edited = songList.getSelectionModel().getSelectedItem();
		if (!confirm("Change song details from: " + edited + " to " + details[0] + " - " + details[1])) return; 
		edited.setName(songName.getText());
		edited.setArtist(songArtist.getText());
		edited.setAlbum(songAlbum.getText());
		edited.setYear(songYear.getText());
		toggleEdit(null);
		editMode = false;
		songList.setItems(library.getSongs().sorted());
		songList.refresh();
	}

	
	public boolean verifyDetails(String[] details) {
		String content;
		boolean duplicate = false;
		List<Song> sortedSongList = library.getSongs();
		for (int i = 0; i < sortedSongList.size(); i++) {
			Song curr = sortedSongList.get(i); 
			if (editMode && curr != null && curr.getName().equalsIgnoreCase(details[0]) && curr.getArtist().equalsIgnoreCase(details[1])
					&& curr.getAlbum().equalsIgnoreCase(details[2]) && curr.getYear().equalsIgnoreCase(details[3])) {
				duplicate = true;
				break;
			} else if (!editMode && curr != null && curr.getName().equalsIgnoreCase(details[0]) && curr.getArtist().equalsIgnoreCase(details[1])) {
				duplicate = true;
				break;
			}
		}
		
		if (duplicate)
			content = "This song is already present";
		else if(details[0].equals("") || details[1].equals(""))
			content = "Empty Input for Artist/Song Name";
		else if(details[0].contains("|") || details[1].contains("|") || details[2].contains("|"))
			content = "Invalid Character '|' present";
		else if (!details[3].equals("") && !details[3].matches("^[0-9]{4}$"))
			content = "Invalid Year";
		else
			return true;
		
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(primaryStage);
		alert.setTitle("Error");
		alert.setHeaderText(content);
		alert.showAndWait();

		return false;
	}
	

}
