package songLib.app;

import java.util.ArrayList;
import java.util.List;

public class Library {
	private List<Song> songs;
	
	public Library() {
		songs = new ArrayList<Song>();
	}
	
	public Library(List<Song> songs) {
		this.songs = songs;
	}
	
	public List<Song> getSongs() {
		return songs;
	}
	
	// Cases of missing album or year should be handled when taking input, else we need to overload this method
	public void addSong(String name, String artist, String album, String year) {
		Song song = new Song(name, artist, album, year);
		songs.add(song);
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
