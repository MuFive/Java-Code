package castle;

import java.util.HashMap;

public class Me {
	
	
	private HashMap<String,Integer> me = new HashMap<String,Integer>();
	
	public Me(){
		me.put("grade", 1);
		me.put("exp", 0);
		me.put("blood", 100);
		me.put("skill", 100);
		me.put("gold", 0);
	}

	public int retMe(String data){
		return me.get(data);
	}
	
	public void setGrade(int grade) {
		me.put("grade", me.get("grade")+grade);
	}
	public void setExp(int exp) {
		me.put("exp", me.get("exp")+exp);
	}
	public void setBlood(int blood) {
		me.put("blood", me.get("blood")+blood);
	}
	public void setSkill(int skill) {
		me.put("skill", me.get("skill")+skill);
	}
	public void setGold(int gold) {
		me.put("gold", me.get("gold")+gold);
	}
}
