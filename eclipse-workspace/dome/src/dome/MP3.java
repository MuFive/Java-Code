package dome;

public class MP3 extends Item{
	private String singer;
	private String songsmith;
	private String songwriter;
	
	
	public MP3(String title, String singer,String songsmith,String songwriter,int playingTime, String comment) {
		super(title, playingTime, false, comment);
		this.singer = singer;
		this.songsmith = songsmith;
		this.songwriter = songwriter;
	}
	
	public void print() {
		System.out.print("MP3：");
		super.print();
		System.out.print(singer+":");
		System.out.print(songsmith+":");
		System.out.println(songwriter);
	}
}
