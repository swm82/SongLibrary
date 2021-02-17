package songLib.app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

public class Library {
	private ObservableList<Song> obsSongList;
	private SortedList<Song> sortedSongList;

	
	public Library() {
		obsSongList = FXCollections.observableArrayList();
		sortedSongList = new SortedList<Song>(obsSongList, new Comparator<Song>() {
			@Override
			public int compare(Song a, Song b) {
				int nameCompVal = a.getName().compareToIgnoreCase(b.getName());
				if (nameCompVal != 0) return nameCompVal;
				return a.getArtist().compareToIgnoreCase(b.getArtist());
			}
		});
	}
	
	public ObservableList<Song> getSongs() {
		return sortedSongList;
	}
	
	public Song addSong(String[] songInfo) {
		if (songInfo.length != 4) return null;
		String name = songInfo[0];
		String artist = songInfo[1];
		String album = songInfo[2];
		String year = songInfo[3];
		
		Song song = new Song(name, artist, album, year);
		if (!obsSongList.contains(song)) obsSongList.add(new Song(name, artist, album, year));
		return song;
	}
		
	// I think this (slow) method for removal would work for observable lists
	public void deleteSong(Song songToDelete) {
		obsSongList.remove(songToDelete);
	}
	
	public int size() {
		return obsSongList.size();
	}
}
