package my.game.interfaces.gameobjects;

import javax.swing.ImageIcon;

import my.game.enums.GameObjectType;
import my.game.objects.Coordinate;

 //for object who don't move
public interface StaticObject {

	ImageIcon getIcon();
	
	Coordinate getCoordinate();
	
	GameObjectType getType();
	
}
