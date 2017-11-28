package castle;

import java.util.HashMap;
import java.util.Scanner;

public class Game {
	
	private Room currentRoom;
	//制造房间 
    private Room outside = new Room("城堡外"),
		    		lobby = new Room("大堂"),
		    		pub = new Room("小酒吧"),
		    		study = new Room("书房"),
		    		bedroom = new Room("卧室"),
		    		gold = new Room("包工头"),
		    		skill = new Room("法师"),
		    		blood = new Room("精灵"),
		    		exp = new Room("大老师");
	private Me me = new Me();
	private HashMap<String, Handler> handlers = new HashMap<String,Handler>();
	
    public Game() {
    		handlers.put("bye", new HandlerBye(this));
    		handlers.put("help", new HandlerHelp(this));
    		handlers.put("go", new  HandlerGo(this));
    		handlers.put("me", new HandlerMe(this));
        createRooms();
    }

    private void createRooms(){ 
        //	初始化房间的出口
        	lobby.setExit("ease", outside);
        	outside.setExit("west", lobby);
        	lobby.setExit("west", pub);
        	pub.setExit("ease", lobby);
        	lobby.setExit("north", study);
        	study.setExit("south", lobby);
        	lobby.setExit("up", gold);
        	gold.setExit("down", lobby);
        	pub.setExit("south", skill);
        	skill.setExit("north", pub);
        	study.setExit("ease", exp);
        	exp.setExit("west", study);
        	study.setExit("west",bedroom);
        	bedroom.setExit("east", study);
        	bedroom.setExit("down", blood);
        	blood.setExit("up", bedroom);
        currentRoom = lobby;  //	从城堡门外开始
    }

    private void printWelcome() {
        System.out.println("欢迎来到城堡!");
        System.out.println("这是一个比翁凯老师做的还超级无聊的游戏.");
        System.out.println("如果需要帮助,请输入 'help'.");
        Handler h = handlers.get("me");
		h.doCmd("");
        showPrompt();
    }

    // 以下为用户命令
    public int getMe(String data) {
    		return me.retMe(data);
    }
    public void amendMe() {
    		if(currentRoom == lobby) {
    			System.out.println("你想赚钱吗?只要\tgo up");
    		}
    		if(currentRoom == gold) {
    			int gold = (int)(Math.random()*100);
    			me.setGold(gold);
    			System.out.println("恭喜你赚了："+gold+"金币");
    		}
    		if(currentRoom == outside) {
    			double outside = Math.random();
    			System.out.println(outside);
    			if(outside < 0.5) {
    				int blood = (int)(Math.random()*50);
    				int skill = (int)(Math.random()*50);
    				me.setExp(10);
    				me.setBlood(-blood);
    				me.setSkill(-skill);
    				System.out.println("你受到了攻击，经验："+10+" 血量："+(-blood)+" 技能:"+(-skill));
    			}
    		}
    		if(currentRoom == pub) {
    			System.out.println("有个人想请你喝酒！这边走\tgo south");
    		}
    		if(currentRoom == skill) {
    			System.out.println("来一杯吧？Yes or No");
    			Scanner inRet = new Scanner(System.in);
    			String ret = inRet.nextLine();
    			if(ret.equals("Yes")) {
    				System.out.println("你回复了50点技能，但被抢走了100金币。");
    				me.setSkill(50);
    				me.setGold(-100);
    			}else {
    				System.out.println("再会！");
    				currentRoom = pub;
    				showPrompt();
    			}
    		}
    		if(currentRoom == study) {
    			System.out.println("想变得更强吗？大老师在等你哦。\tgo ease");
    		}
    		if(currentRoom == exp) {
    			if(getMe("exp") > 100) {
    				System.out.println("好好学习，天天向上。恭喜你升级了！");
    				me.setGrade(1);
    				me.setExp(-100);
    			}else {
    				System.out.println("继续努力吧！！！");
    			}
    		}
    		if(currentRoom == bedroom) {
    			System.out.println("啊，你受伤了吗？小精灵在楼下等你呢。\tgo down");
    		}
    		if(currentRoom == blood) {
    			System.out.println("%¥¥#@¥¥#！！#¥¥……*！Yes or No");
    			Scanner inRet = new Scanner(System.in);
    			String ret = inRet.nextLine();
    			if(ret.equals("Yes")) {
    				System.out.println("你回复了50点血量，花费了100金币。");
    				me.setBlood(50);
    				me.setGold(-100);
    			}else {
    				System.out.println("小精灵不见了！");
    				currentRoom = bedroom;
    				showPrompt();
    			}
    		}
    }
    
    public void goRoom(String direction) 
    {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("那里没有门！");
        }
        else {
            currentRoom = nextRoom;
            showPrompt();
        }
    }
    
	public void showPrompt() {
        System.out.println("\n"+currentRoom);
        System.out.print("出口有: ");
        System.out.println(currentRoom.getExitDesc());
        amendMe();
	}
    
	public void play() {
		Scanner in = new Scanner(System.in);
		while ( true ) {
	    		String line = in.nextLine();
	    		String[] words = line.split(" ");
	    		Handler handler = handlers.get(words[0]);
	    		String value = "";
	    		if(words.length > 1)
	    			value = words[1]; 
	    		if(handler != null) {
	    			handler.doCmd(value);
	    			if(handler.isBye())
	    				break;
	    		}
		}
		in.close();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.printWelcome();
		game.play();
        System.out.println("感谢您的光临。再见！");
	}
}
