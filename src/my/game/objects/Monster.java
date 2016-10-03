package my.game.objects;

import my.game.abstracts.AbstractMovingObject;
import my.game.enums.GameObjectType;

public class Monster extends AbstractMovingObject{

	public Monster() {
		super.setCoordinate(new Coordinate(0,0));
		super.setType(GameObjectType.MONSTER);
		super.setIcon(super.getImageIcon("/my/game/texture/enemy_up.png"));
		
		super.setIconDown(super.getImageIcon("/my/game/texture/enemy_down.png"));
		super.setIconUP(super.getImageIcon("/my/game/texture/enemy_up.png"));
		super.setIconRight(super.getImageIcon("/my/game/texture/enemy_right.png"));
		super.setIconLeft(super.getImageIcon("/my/game/texture/enemy_left.png"));
	}
	
	public Monster(Coordinate coordinate){
		super.setCoordinate(coordinate);
		super.setType(GameObjectType.MONSTER);
		super.setIcon(super.getImageIcon("/my/game/texture/enemy_up.png"));
		
		super.setIconDown(super.getImageIcon("/my/game/texture/enemy_down.png"));
		super.setIconUP(super.getImageIcon("/my/game/texture/enemy_up.png"));
		super.setIconRight(super.getImageIcon("/my/game/texture/enemy_right.png"));
		super.setIconLeft(super.getImageIcon("/my/game/texture/enemy_left.png"));
	}
}
