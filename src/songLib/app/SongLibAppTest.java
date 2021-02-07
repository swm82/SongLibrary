package songLib.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SongLibAppTest {
	
	private static Library library;
	
	public static void main(String[] args) throws IOException {
		String filename = "test.csv";
		BufferedReader reader = getFile(filename);
		library = new Library();
		
		
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
	
	public static void readFile(BufferedReader reader, Library library) throws IOException {
		String line = "";
		String delim = ";";
		while ((line = reader.readLine()) != null) {
			String[] songInfo = line.split(delim);
			library.addSong(songInfo);
		}
	}

}
