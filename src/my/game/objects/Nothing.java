package my.game.objects;

import my.game.abstracts.AbstractGameObject;
import my.game.enums.GameObjectType;

public class Nothing extends AbstractGameObject{
	
	public Nothing() {
		super.setIcon(super.getImageIcon("/my/game/texture/nothing.png"));
		super.setCoordinate(new Coordinate(0,0));
		super.setType(GameObjectType.NOTHING);
	}
	
	public Nothing(Coordinate coordinate) {
		super.setIcon(super.getImageIcon("/my/game/texture/nothing.png"));
		super.setCoordinate(coordinate);
		super.setType(GameObjectType.NOTHING);
	}
	
}
