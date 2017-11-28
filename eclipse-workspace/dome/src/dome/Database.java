package dome;

import java.util.ArrayList;

public class Database {

	private ArrayList<Item> listItem =new ArrayList<Item>();
	
	public void add(Item item) {
		listItem.add(item);
	}
	
	public void list() {
		for(Item item : listItem) {
			item.print();
			System.out.println();
		}
	}
	public static void main(String[] args) {
		Database db = new Database();
		db.add(new CD("abc","qwe",4,60,"..."));
		db.add(new CD("abc","poi",4,60,"..."));
		db.add(new DVD("xxx","aaa",60,"..."));
		db.add(new VideoGame("ddd",10,true,"...",4));
		db.add(new MP3("演员","薛之谦","薛之谦","薛之谦",5,"..."));
		db.list();
	}

}
