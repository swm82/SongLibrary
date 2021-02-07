package songLib.view;
import java.util.ArrayList;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import songLib.app.*;

public class SongLibController {
	
	// Library library;
	
	/* 
	 * I think changes to observable list will propagate back to the original song arraylist as well as to
	   the sorted list.
	 * So I think, after the controller is loaded with the initial library song list, all changes should be made
	   to the observable list.
	 * I don't think we need to maintain a reference to the library in the controller
	 * 
	 * 
	*/
	
	ObservableList<Song> songsObsList;
	SortedList<Song> sortedSongs;
	
	
	public void initData(ArrayList<Song> songs) {
		songsObsList = FXCollections.observableList(songs);
		sortedSongs = new SortedList<Song>(songsObsList);
		
		/* Comparator for SortedList - not sure if needed since Songs implements Comparable
		Comparator comp = new Comparator<Song>() {
			@Override
			public int compare(Song a, Song b) {
				int nameCompVal = a.getName().compareToIgnoreCase(b.getName());
				if (nameCompVal != 0) return nameCompVal;
				return a.getArtist().compareToIgnoreCase(b.getArtist());
			}
		};
		*/
	}
	

}
