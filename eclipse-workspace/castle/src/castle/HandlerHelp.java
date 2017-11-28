package castle;

public class HandlerHelp extends Handler{
	
	public HandlerHelp(Game game) {
		super(game);
	}
	
	@Override
	public void doCmd(String word) {
        System.out.println("迷路了吗？你可以做的命令有：help me go bye\n如：\tgo east");
	}
}
