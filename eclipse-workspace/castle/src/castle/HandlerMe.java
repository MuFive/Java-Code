package castle;

public class HandlerMe extends Handler{

	public HandlerMe(Game game) {
		super(game);
		
	}

	@Override
	public void doCmd(String word) {
		System.out.println("等级："+game.getMe("grade"));
		System.out.println("经验："+game.getMe("exp"));
		System.out.println("血量："+game.getMe("blood"));
		System.out.println("技能："+game.getMe("skill"));
	    System.out.println("金币："+game.getMe("gold"));
	}
}
