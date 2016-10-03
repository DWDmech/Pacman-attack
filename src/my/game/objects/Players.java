package my.game.objects;

import my.game.abstracts.AbstractMovingObject;
import my.game.enums.GameObjectType;

public class Players extends AbstractMovingObject{

	private int score;
	private String nickName;
	private int lvls;
	
	public Players() {
		super.setCoordinate(new Coordinate(0,0));
		super.setType(GameObjectType.PLAYER);
		super.setIcon(super.getImageIcon("/my/game/texture/_player_up.png"));
		
		super.setIconDown(super.getImageIcon("/my/game/texture/_player_down.png"));
		super.setIconUP(super.getImageIcon("/my/game/texture/_player_up.png"));
		super.setIconRight(super.getImageIcon("/my/game/texture/_player_right.png"));
		super.setIconLeft(super.getImageIcon("/my/game/texture/_player_left.png"));
	}
	
	public Players(Coordinate coordinate) {
		super.setCoordinate(coordinate);
		super.setType(GameObjectType.PLAYER);
		super.setIcon(super.getImageIcon("/my/game/texture/_player_up.png"));
		
		super.setIconDown(super.getImageIcon("/my/game/texture/_player_down.png"));
		super.setIconUP(super.getImageIcon("/my/game/texture/_player_up.png"));
		super.setIconRight(super.getImageIcon("/my/game/texture/_player_right.png"));
		super.setIconLeft(super.getImageIcon("/my/game/texture/_player_left.png"));
	}
	
	public Players(String nickName, int lvls, int score){
		this.nickName = nickName;
		this.lvls = lvls;
		this.score = score;
	}
	
	public void incScore(int score){
		this.score += score;
	}
	public void incLvlsCount(){
		this.lvls++;
	}
	

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getLvls() {
		return lvls;
	}

	public int getScore() {
		return score;
	}
	
	public void setToZero(){
		this.score = 0;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public void setLvls(int lvls) {
		this.lvls = lvls;
	}

}
