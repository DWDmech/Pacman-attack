package my.game.enums;

import java.io.Serializable;

public enum GameObjectType implements Serializable{

	MONSTER(5),
	GOLD(4),
	EXIT(3),
	WALL(2),
	PLAYER(1),
	NOTHING(-1);

	private int state;
	
	private GameObjectType(int state){
		this.state = state;
	}
	
	public int getPriority(){
		return state;
	}
}
