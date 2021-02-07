package songLib.app;

import java.util.ArrayList;
import java.util.List;

public class Library {
	private List<Song> songs;
	
	public Library() {
		songs = new ArrayList<Song>();
	}
	
	public List<Song> getSongs() {
		return songs;
	}
	
	public void addSong(String[] songInfo, List<Song> songs) {
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
		if (!songs.contains(song)) songs.add(new Song(name, artist, album, year));
	}
		
	// I think this (slow) method for removal would work for observable lists
	public void deleteSong(String name, String artist) {
		songs.removeIf(song -> song.getArtist().toLowerCase().equals(name.toLowerCase()) 
				&& song.getName().toLowerCase().equals(artist.toLowerCase()));
	}
	
	public void sortSongs() {
		songs.sort(null);
	}
}
