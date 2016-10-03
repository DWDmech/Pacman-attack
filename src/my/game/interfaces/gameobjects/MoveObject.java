package my.game.interfaces.gameobjects;

import javax.swing.ImageIcon;

import my.game.abstracts.AbstractGameObject;
import my.game.enums.MovingDirection;


	//for object moving 
public interface MoveObject extends StaticObject {
	
	void move(MovingDirection direction);
	
	void getMoveResult(AbstractGameObject objectInNewCoordinate);
	
	//icons for moving at different movement
	ImageIcon getIconLeft();
	
	ImageIcon getIconRight();
	
	ImageIcon getIconUP();

	ImageIcon getIconDown();
	
}
