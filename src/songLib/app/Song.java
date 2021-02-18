package songLib.app;

public class Song implements Comparable<Song> {
	
	private String artist;
	private String name;
	private String album;
	private String year;
	
	public Song(String name, String artist, String album, String year) {
		this.artist = artist;
		this.name = name;
		this.album = album;
		this.year = year;
	}
	
	
	// Getters & Setters
	public String getArtist() {
		return artist;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	
	//Overrides
	@Override
	public String toString() {
		// Display list of tracks as a single sting "Song Name - Artist Name" ??
		return name + " - " + artist;
	}
	
	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + artist.hashCode();
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Song)) return false;
		Song other = (Song) o;
		return other.name.equalsIgnoreCase(name) && other.artist.equalsIgnoreCase(artist);
	}

	
	@Override
	public int compareTo(Song other) {
		// TODO Auto-generated method stub
		int nameCompVal = name.compareToIgnoreCase(other.name);
		if (nameCompVal != 0) return nameCompVal;
		return artist.compareToIgnoreCase(other.artist);
	}

}
