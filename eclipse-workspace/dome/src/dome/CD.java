package dome;

public class CD extends Item {
	
	private String artist;
	private int numofTracks;
	
	public CD(String title, String artist, int numofTracks, int playingTime, String comment) {
		super(title,playingTime,false,comment);
		this.artist = artist;
		this.numofTracks = numofTracks;
	}

	public void print() {
		System.out.print("CD:");
		super.print();
		System.out.println(artist);
	}

}
