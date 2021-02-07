package songLib.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SongLibAppTest {
	
	public static void main(String[] args) throws IOException {
		String filename = "test.csv";
		BufferedReader reader = getFile(filename);
		Library library = new Library();
		
		
		if (reader !=  null) {
			readFile(reader, library);
			reader.close();
		}
		
		List<Song> songs = library.getSongs();
		
		System.out.println(songs);
		library.sortSongs();
		System.out.println(songs);
		
		
	}
	
	public static BufferedReader getFile(String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			return reader;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static List<Song> readFile(BufferedReader reader, Library library) throws IOException {
		String line = "";
		String delim = ";";
		List<Song> songs = new ArrayList<Song>();
		while ((line = reader.readLine()) != null) {
			String[] songInfo = line.split(delim);
			library.addSong(songInfo, songs);
		}
		return songs;
	}
	
	/* Moved to library Class
	public static void addSong(String[] songInfo, List<Song> songs) {
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
	}*/

}
