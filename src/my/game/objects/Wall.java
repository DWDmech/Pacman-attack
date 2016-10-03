package my.game.objects;

import my.game.abstracts.AbstractGameObject;
import my.game.enums.GameObjectType;

public class Wall extends AbstractGameObject{
	
	public Wall() {
		super.setType(GameObjectType.WALL);
		super.setCoordinate(new Coordinate(0,0));
		super.setIcon(super.getImageIcon("/my/game/texture/wall.png"));
	}

	public Wall(Coordinate coordinate){
		super.setType(GameObjectType.WALL);
		super.setCoordinate(coordinate);
		super.setIcon(super.getImageIcon("/my/game/texture/wall.png"));
	}
}

