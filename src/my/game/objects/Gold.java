package my.game.objects;

import my.game.abstracts.AbstractGameObject;
import my.game.enums.GameObjectType;

public class Gold extends AbstractGameObject{

	public Gold() {
		super.setCoordinate(new Coordinate(0,0));
		super.setType(GameObjectType.GOLD);
		super.setIcon(super.getImageIcon("/my/game/texture/gold.png"));
	}
	
	public Gold(Coordinate coordinate) {
		super.setCoordinate(coordinate);
		super.setType(GameObjectType.GOLD);
		super.setIcon(super.getImageIcon("/my/game/texture/gold.png"));
	}
	
}
