package songLib.app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

public class Library {
	private List<Song> songs;
	private ObservableList<Song> obsSongList;
	private SortedList<Song> sortedSongList;

	
	public Library() {
		songs = new ArrayList<>();
		obsSongList = FXCollections.observableList(songs);
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
	
	public void addSong(String[] songInfo) {
		if (songInfo.length < 2 || songInfo.length > 4) return;
		String name = songInfo[0];
		String artist = songInfo[1];
		String album = null;
		String year = null;
		if (songInfo.length == 4) {
			album = songInfo[2]; year = songInfo[3];
		} else if (songInfo.length == 3) {
			album = songInfo[2];
		}
		Song song = new Song(name, artist, album, year);
		if (!obsSongList.contains(song)) obsSongList.add(new Song(name, artist, album, year));
	}
		
	// I think this (slow) method for removal would work for observable lists
	public void deleteSong(Song songToDelete) {
		obsSongList.removeIf(song -> song.equals(songToDelete));
		//obsSongList.remove(songToDelete);
	}
	
	public void sortSongs() {
		songs.sort(null);
	}
	
	public int size() {
		return obsSongList.size();
	}
}
