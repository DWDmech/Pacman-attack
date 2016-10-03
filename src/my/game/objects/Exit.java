package my.game.objects;

import my.game.abstracts.AbstractGameObject;
import my.game.enums.GameObjectType;

public class Exit extends AbstractGameObject{

	public Exit() {
		super.setCoordinate(new Coordinate(0,0));
		super.setIcon(super.getImageIcon("/my/game/texture/exit.png"));
		super.setType(GameObjectType.EXIT);
	}
	
	public Exit(Coordinate coordinate) {
		super.setCoordinate(coordinate);
		super.setIcon(super.getImageIcon("/my/game/texture/door_exit.png"));
		super.setType(GameObjectType.EXIT);
	}
}
